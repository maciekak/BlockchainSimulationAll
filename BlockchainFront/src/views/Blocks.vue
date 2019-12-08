
<template>
     <v-container
                fluid
                grid-list-lg
        >
            <v-layout row wrap>

    <v-flex xs12 md6 xl4 v-for="block in blocks" :key="block.hash">
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

        goToClient: function (clientNumber)
        {
            this.$router.push('/clients/' + clientNumber)
        },
      myFetch: function(context)
      {
        context.$axios
          .get(address + 'blocks')
          .then(response => (context.blocks = response.data))
          // eslint-disable-next-line no-console
          .catch(error => console.log(error));
      }
    },
    data()
    {
        return {
            blocks: [],
            intervalId: 0
        }
    },
    mounted()
    {
        this.myFetch(this);
        this.$intervalId = setInterval(function(context)
        {
            // console.log("ReloadBlocks");
            context. myFetch(context);
        }, 1000, this);
    },
    destroyed()
    {
        clearInterval(this.$intervalId);
    }


}

</script>
