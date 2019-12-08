<template>
  <nav>

    <v-toolbar class="green" dense flat app>
      <v-toolbar-side-icon @click="drawer = !drawer" class="white--text"></v-toolbar-side-icon>

      <v-btn flat transparent @click="goHome()">
      <v-toolbar-title class="text-uppercase white--text" >
        <span class="font-weight-light">Blockchain  </span>
        <span>viewer</span>
      </v-toolbar-title>
      </v-btn>

      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>


    <v-btn flat transparent icon @click="goHome()">
      <v-img  :src="require('../assets/Ethereum_logo.svg')"
              class="text-xs-right"
              contain
              height="30"
      ></v-img>
    </v-btn>
    </v-toolbar>

    <v-navigation-drawer app v-model="drawer" class="secondary">
      <v-list>
        <v-list-tile v-for="link in links" :key="link.text" router :to="link.route" @click="drawer = !drawer">
          <v-list-tile-action>
            <v-icon class="white--text">{{ link.icon }} </v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title class="white--text">{{ link.text }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>

  </nav>
</template>

<script>

export default {

  data() {
    return {
      drawer: false,
      links: [
        { icon: 'view_module', text: 'Blocks', route: '/blocks' },
        { icon: 'swap_horiz', text: 'Transactions', route: '/transactions' },
        { icon: 'recent_actors', text: 'Clients', route: '/clients' }
      ],

    }
  },

  methods: {

    goHome: function ()
    {
      this.$router.push('/')
    }
  },

}
</script>

<style>

</style>
