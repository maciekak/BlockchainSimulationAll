
<template>
        <v-container
                fluid
                grid-list-lg
        >
            <v-layout >

    <v-flex xs3></v-flex>
    <v-flex xs12 sm12 md6>
        <v-card color="red darken-2" class="white--text">
            <v-layout>
                <v-flex xs2>
                    <v-img
                            v-if="(client.type === 0)"
                            :src="require('../assets/client.png')"
                            height="125px"
                            contain
                    ></v-img>
                    <v-img
                            v-if="(client.type === 1)"
                            :src="require('../assets/miner.png')"
                            height="125px"
                            contain
                    ></v-img>
                    <v-chip
                            v-if="client.isNew"
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
                            v-if="client.isUpdated"
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
                            <div> <v-icon>fingerprint</v-icon>  Hash: {{ client.hash}} </div>
                            <div> <v-icon>assessment</v-icon>        Number of Transactions:        {{ client.transactionsCount }} </div>
                            <div> <v-icon>account_balance</v-icon>       Amount: {{ client.amount }} ETH </div>
                            <div>        </div>
                            <div> <v-icon>alarm</v-icon>      Created: {{  client.startDate }}  </div>
                        </div>
                    </v-card-title>
                </v-flex>
            </v-layout>

            <v-card-actions class="pa-3">
            </v-card-actions>
        </v-card>
        </v-flex>

        <v-flex xs3></v-flex>
        </v-layout>




        <v-layout row wrap>
            <v-flex xs12  v-for="transaction in client.transactionsHashes" :key="transaction.hash">
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

        <v-layout row wrap>
            <v-flex xs12 sm12 md6 lg6 xl4 v-for="block in client.minedBlocksHashes" :key="block.hash">
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
                                <div> <v-icon>gavel</v-icon>        Transactions: {{ block.transactionCount }} </div>
                                <div> <v-icon>storage</v-icon>       Size: {{  block.size }} </div>


                            </div>
                        </v-card-title>
                    </v-flex>
                </v-layout>
                <v-divider light></v-divider>
                <v-card-actions class="pa-3">

                    <v-spacer></v-spacer>
                    <v-btn color="secondary" class="white--text" @click="goToBlock(block.hash)">Explore</v-btn>  <!-- router :to="stats" -->

                </v-card-actions>
            </v-card>
            </v-flex>
        </v-layout>


    </v-container>
</template>

<script>

import {address} from '../main'

export default {
    methods: {

        goToBlock: function (blockNumber)
        {
            this.$router.push('/blocks/' + blockNumber)
        },
        goToTransaction: function (transactionNumber)
        {
            this.$router.push('/transactions/' + transactionNumber)
        },

      myFetch: function(context)
      {
        context.$axios
          .get(address + 'clients/' + this.$route.params.id)
          .then(response => (context.client = response.data))
          // eslint-disable-next-line no-console
          .catch(error => console.log(error));
      }
    },
    data()
    {
        return {
            client: {}
        }
    },
  mounted()
  {
    this.myFetch(this);
    this.$intervalId = setInterval(function(context)
    {
      // console.log("ReloadClientDet");
      context.myFetch(context);
    }, 1000, this);
  },
  destroyed()
  {
    clearInterval(this.$intervalId);
  }

}

</script>
