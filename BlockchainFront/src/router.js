import Vue from 'vue'
import Router from 'vue-router'
import Welcome from './views/Welcome.vue'
import transactions from './views/Blocks'
import BlockDetails from './views/BlockDetails'
import Transactions from './views/Transactions'
import TransactionDetails from './views/TransactionDetails'
import Clients from './views/Clients'
import ClientDetails from './views/ClientDetails'



Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
    routes: [
    {
      path: '/',
      name: 'Welcome',
      component: Welcome
    },
    {
      path: '/blocks',
      name: 'transactions',
      component: transactions
    },
    {
      path: '/blocks/:id',
      name: 'BlockDetails',
      component: BlockDetails
    },
    {
      path: '/transactions',
      name: 'Transactions',
      component: Transactions
    },
    {
      path: '/transactions/:id',
      name: 'TransactionDetails',
      component: TransactionDetails
    },
    {
      path: '/clients',
      name: 'Clients',
      component: Clients
    },
    {
      path: '/clients/:id',
      name: 'ClientDetails',
      component: ClientDetails
    },




      // otherwise redirect to home
    { path: '*', redirect: '/' }
    ]
})
