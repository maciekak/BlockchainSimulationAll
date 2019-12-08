<template>
  <v-container grid-list-md text-xs-center>


    <v-layout row wrap>
      <v-flex xs12 v-for="transaction in transactions" :key="transaction.hash">
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

import {address} from '../main'

export default {
    data()
    {
        return {

        transactions: [],
          intervalId: 0

        }
    },

    methods: {
        goToTransaction: function (transactionNumber)
        {
            this.$router.push('/transactions/' + transactionNumber)
        },
        myFetch: function(context)
        {
          context.$axios
            .get(address + 'transactions')
            .then(response => (context.transactions = response.data))
            // eslint-disable-next-line no-console
            .catch(error => console.log(error));
        }

    },

    mounted()
    {
      this.myFetch(this);
        this.$intervalId = setInterval(function(context)
        {
          // console.log("ReloadTransactions");
          context.myFetch(context);
        }, 1000, this);
    },
    destroyed()
    {
      clearInterval(this.$intervalId);
    }



}

</script>
