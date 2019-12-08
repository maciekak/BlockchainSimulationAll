
<template>
<!--    <v-card>-->
        <v-container
                fluid
                grid-list-lg
        >
            <v-layout row wrap>



    <v-flex xs3></v-flex>
    <v-flex xs12 sm12 md6>
        <v-card color="yellow darken-2" class="white--text">
            <v-layout>
                <v-flex xs2>
                    <v-img
                            :src="require('../assets/ethereum_blockchain-512.png')"
                            height="125px"
                            contain
                    ></v-img>
                    <v-chip
                            v-if="block.isNew"
                            :color="`green lighten-1`"
                            class="ml-0"
                            text-color="white"
                    >
                        <v-avatar>
                            <v-icon>mdi-new-box</v-icon>
                        </v-avatar>
                        New
                    </v-chip>
                    <v-chip
                            v-if="block.isUpdated"
                            :color="`yellow darken-3`"
                            class="ml-0"
                            text-color="white"
                    >
                        <v-avatar>
                            <v-icon>mdi-restore</v-icon>
                        </v-avatar>
                        Updated
                    </v-chip>
                </v-flex>
                <v-flex xs10>
                    <v-card-title primary-title>
                        <div>


                            <div> <v-icon>fingerprint</v-icon>  Hash: {{ block.hash }} </div>
                            <div> <v-icon>alarm</v-icon>        Mined:        {{ block.minedDate }} </div>
                            <div>
                                <v-icon>person</v-icon>
                                Miner:
                                <v-btn small flat dark transparent @click="goToClient(block.minerHash)">
                                    {{ block.minerHash }}
                                </v-btn>
                            </div>
                            <div> <v-icon>gavel</v-icon>        Transactions: {{ block.transactionCount }} </div>
                            <div> <v-icon>storage</v-icon>       Size: {{  block.size }} </div>

                            <div> <v-icon>local_gas_station</v-icon>       Gas used: {{  block.gasAmount }} </div>
                            <div> <v-icon>local_activity</v-icon>       Reward: {{  block.awardForMining }} </div>


                            <div>  <v-icon>remove_circle</v-icon>    Total sent amount: {{  block.totalSentAmount }} </div>
                            <div>  <v-icon>add_circle</v-icon>    Total received amount: {{  block.totalReceivedAmount }} </div>
                            <div> <v-icon>monetization_on</v-icon>     Total balance: {{  block.totalBalance }} </div>
                            <div> <v-icon>fingerprint</v-icon> Parent hash:
                                <v-btn small flat dark transparent @click="goToBlock(block.parentHash)">
                                    {{ block.parentHash }}
                                </v-btn>
                            </div>
                            <div> <v-icon>fingerprint</v-icon>     Child hash:
                                <v-btn small flat dark transparent @click="goToBlock(block.childHash)">
                                    {{ block.childHash }}
                                </v-btn>
                            </div>
                        </div>

                    </v-card-title>
                    <v-card-actions></v-card-actions>
                </v-flex>
            </v-layout>


            <v-card-actions></v-card-actions>

        </v-card>
        </v-flex>



        <v-flex xs3></v-flex>

            <v-flex xs12  v-for="transaction in block.transactions" :key="transaction.hash">
                <!--        sm12 md12 lg12 xl12-->



                <v-card color="blue darken-2" class="white--text">
                    <v-layout row wrap >

                        <v-flex xs9>
                            <v-card-title primary-title>
                                <div class="text-sm-left">
                                    <div class="headline"> <v-icon>fingerprint</v-icon>     {{ transaction.hash }}</div>
                                </div>
                            </v-card-title>
                            <v-chip
                                    v-if="transaction.isNew"
                                    :color="`green lighten-1`"
                                    class="ml-0"
                                    text-color="white"
                            >
                                <v-avatar>
                                    <v-icon>mdi-new-box</v-icon>
                                </v-avatar>
                                New
                            </v-chip>
                            <v-chip
                                    v-if="transaction.isUpdated"
                                    :color="`yellow darken-3`"
                                    class="ml-0"
                                    text-color="white"
                            >
                                <v-avatar>
                                    <v-icon>mdi-restore</v-icon>
                                </v-avatar>
                                Updated
                            </v-chip>
                        </v-flex>
                        <v-flex xs3>
                            <v-spacer></v-spacer>
                            <div> <v-icon>alarm</v-icon>            Time:   {{ transaction.transactionDate }} </div>
                            <div> <v-icon>monetization_on</v-icon>  Amount: {{  transaction.moneyAmount }} ETH </div>
                            <div> <v-icon>check</v-icon>  Status: <span v-if="transaction.blockHash === null">Unconfirmed</span> <span v-else>Confirmed</span>  </div>



                        </v-flex>
                    </v-layout>
                    <v-divider light></v-divider>
                    <v-card-actions class="pa-3">

                        <v-spacer></v-spacer>
                        <v-btn color="secondary" class="white--text" @click="goToTransaction(transaction.hash)">Explore</v-btn>  <!-- router :to="stats" -->

                    </v-card-actions>
                </v-card>


            </v-flex>
        </v-layout>



    </v-container>
</template>




<script>

import Transactions from './Transactions'
import {address} from '../main'

export default {
    methods: {
        goToTransaction: function (transactionNumber) {
            this.$router.push('/transactions/' + transactionNumber)
        },
        goToBlock: function (blockNumber) {
            this.$router.push('/blocks/' + blockNumber)
            this.reloadPage();
        },
        goToClient: function (clientNumber)
        {
            this.$router.push('/clients/' + clientNumber)
        },
        reloadPage: function () {
            window.location.reload()
        },
      myFetch: function(context)
      {
        context.$axios
          .get(address + 'blocks/' + this.$route.params.id)
          .then(response => (context.block = response.data))
          // eslint-disable-next-line no-console
          .catch(error => console.log(error));
      }
    },

    components: {
      Transactions
    },

    data()
    {
        return {
            block: {},
            transactions:[]
        }
    },
  mounted()
  {
    this.myFetch(this);
    this.$intervalId = setInterval(function(context)
    {
      // console.log("ReloadTransDet");
      context.myFetch(context);
    }, 1000, this);
  },
  destroyed()
  {
    clearInterval(this.$intervalId);
  }
}

</script>

