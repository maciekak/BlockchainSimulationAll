
<template>
        <v-container
                fluid
                grid-list-lg
        >
            <v-layout row wrap>

    <v-flex xs12 sm12 md6 lg6 xl4 v-for="client in clients" :key="client.number">
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
                            <div> <v-icon>fingerprint</v-icon>  Hash:  {{ client.hash}} </div>
                            <div> <v-icon>assessment</v-icon>        Number of Transactions:        {{ client.transactionsCount }} </div>
                            <div> <v-icon>account_balance</v-icon>       Amount: {{ client.amount }} ETH </div>
                            <div>        </div>
                            <div> <v-icon>alarm</v-icon>      Created: {{  client.startDate }}  </div>
                        </div>
                    </v-card-title>
                </v-flex>
            </v-layout>

            <v-card-actions class="pa-3">
                <v-btn color="secondary" class="white--text" @click="goToClient(client.hash)">Explore</v-btn>  <!-- router :to="stats" -->
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

        goToClient: function (clientNumber)
        {
            this.$router.push('/clients/' + clientNumber)
        },
      myFetch: function(context)
      {
        context.$axios
          .get(address + 'clients')
          .then(response => (context.clients = response.data))
          // eslint-disable-next-line no-console
          .catch(error => console.log(error));
      }
    },
    data()
    {
        return {
            clients: [],
            intervalId: 0
        }
    },
  mounted()
  {
    this.myFetch(this);
    this.$intervalId = setInterval(function(context)
    {
      // console.log("ReloadClient");
      context.myFetch(context);
    }, 1000, this);
  },
  destroyed()
  {
    clearInterval(this.$intervalId);
  }

}

</script>
