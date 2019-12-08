<template>
  <v-container grid-list-md text-xs-center>



    <v-layout row wrap>
      <v-flex xs12 sm12 md12 lg12 xl12>


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



            </v-flex>
          </v-layout>

            <v-divider></v-divider>


          <v-layout row wrap>
            <v-flex xs5>
              <v-icon> account_balance_wallet </v-icon>
              <v-btn text flat dark transparent @click="goToClient(transaction.sourceClientHash)">
                <div>   {{ transaction.sourceClientHash }} </div>
              </v-btn>
            </v-flex>

            <v-flex xs2>
              <v-icon>arrow_forward</v-icon>
            </v-flex>


            <v-flex xs5>
               <v-icon> account_balance_wallet </v-icon>
              <v-btn  text flat dark transparent @click="goToClient(transaction.destinationClientHash)">
                <div>  {{ transaction.destinationClientHash }} </div>
              </v-btn>

            </v-flex>
          </v-layout>

          <v-divider></v-divider>

          <v-layout row wrap>
            <v-flex xs6>
<!--              <div> <v-icon>confirmation_number</v-icon> Status: {{ transaction.status }} </div>-->
<!--              <div> <v-icon>assignment_turned_in</v-icon> Confirmations: {{ transaction.confirmations }} </div>-->
              <div> <v-icon>alarm</v-icon>            Time:   {{ transaction.transactionDate }} </div>
              <div> <v-icon>monetization_on</v-icon>  Amount: {{  transaction.moneyAmount }} ETH </div>


            </v-flex>

            <v-flex xs6>
              <div> <v-icon>local_gas_station</v-icon> Gas price: {{ transaction.gasAmount }} </div>
              <div> <v-icon>check</v-icon>  Status: <span v-if="transaction.blockHash === null">Unconfirmed</span> <span v-else>Confirmed</span>  </div>
              <div v-if="transaction.blockHash != null"> <v-icon> border_all </v-icon>
                  <v-btn small flat dark transparent @click="goToBlock(transaction.blockHash)">
                      {{ transaction.blockHash }}
                  </v-btn>
              </div>


            </v-flex>
          </v-layout>




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
          .get(address + 'transactions/' + this.$route.params.id)
          .then(response => (context.transaction = response.data))
          // eslint-disable-next-line no-console
          .catch(error => console.log(error));
      },
        goToBlock: function (blockNumber)
        {
            this.$router.push('/blocks/' + blockNumber)
        },
    },

    data()
    {
      return {
          games: null,

        transaction: {}

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

