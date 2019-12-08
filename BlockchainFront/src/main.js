import Vue from 'vue'
import './plugins/vuetify'
import App from './App.vue'
import router from './router'
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import VueAxios from './plugins/axios'

Vue.use(VueAxios);

Vue.config.productionTip = false;

const ip = 'localhost' //'192.168.0.206'
const port = 51419
export const address = 'http://' + ip + ':' + port + '/api/blockchain/'

new Vue(
{
    router,
    render: h => h(App),
}).$mount('#app');
