<template>
  <v-container fluid fill-height>

    <v-layout  justify-center>
      <v-flex xs12 sm8 md4>
        <v-card class="elevation-12">
          <v-toolbar dark color="myGrey">
            <v-toolbar-title>Welcome!</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            Welcome to our Blockchain viewer app! Here you can simulate various blockchain scenarios and learn how it works!
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn @click="goToBlocks()" dark color="myGrey">Let's go</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>

  </v-container>
</template>

<script>
export default {
  methods: {
    goToBlocks: function () {
      this.$router.push('/blocks')
    }
  },
  data: () => ( {
    message: {isNew: true, isUpdated: true}
  } )
}
</script>
