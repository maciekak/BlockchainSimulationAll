package blockchain.ethereumj;

import blockchain.dto.BlockRequestDto;
import org.ethereum.core.Block;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.db.ByteArrayWrapper;
import org.ethereum.listener.EthereumListenerAdapter;
import org.ethereum.mine.EthashListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MinerNode extends BasicNode implements EthashListener {

    private final Queue<Block> minedBlocks;

    public MinerNode(String nodeName, int nodeIndex) {
        super(nodeName, nodeIndex);
        minedBlocks = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void run() {
        ethereum.addListener(new EthereumListenerAdapter() {
            @Override
            public void onPendingTransactionUpdate(TransactionReceipt txReceipt, PendingTransactionState state, Block block) {
                ByteArrayWrapper txHashW = new ByteArrayWrapper(txReceipt.getTransaction().getHash());
                // Catching transaction errors
                if (txWaiters.containsKey(txHashW) && !txReceipt.isSuccessful()) {
                    txWaiters.put(txHashW, txReceipt);
                }
            }
        });
        ethereum.getBlockMiner().addListener(this);
        ethereum.getBlockMiner().startMining();
    }

    @Override
    public void onDatasetUpdate(DatasetStatus minerStatus) {
        logger.info("Miner status updated: {}", minerStatus);
        if (minerStatus.equals(EthashListener.DatasetStatus.FULL_DATASET_GENERATE_START)) {
            logger.info("Generating Full Dataset (may take up to 10 min if not cached)...");
        }
        if (minerStatus.equals(DatasetStatus.FULL_DATASET_GENERATED)) {
            logger.info("Full dataset generated.");
        }
    }

    @Override
    public void miningStarted() {
        logger.info("Miner started");
    }

    @Override
    public void miningStopped() {
        logger.info("Miner stopped");
    }

    @Override
    public void blockMiningStarted(Block block) {
        logger.info("Start mining block: " + block.getShortDescr());
    }

    @Override
    public void blockMined(Block block) {
        logger.info("Block mined! : \n" + block);
        System.out.println("Balance: " + ethereum.getRepository().getBalance(getMyAddress()));
        minedBlocks.add(block);
    }

    @Override
    public void blockMiningCanceled(Block block) {
        logger.info("Cancel mining block: " + block.getShortDescr());
    }

    public List<BlockRequestDto> getMinedBlocks() {
        List<BlockRequestDto> blocks = new ArrayList<>();
        for (Block minedBlock : minedBlocks) {
            blocks.add(BlockRequestDto.of(minedBlock));
            minedBlocks.remove(minedBlock);
        }
        return blocks;
    }
}
