import Vue from 'vue'
import VueRouter from 'vue-router'

import Content from "../components/Content";
import Main from "../components/Main"
import Ghj from "../components/Lj"

Vue.use(VueRouter);

export default new VueRouter({
  routes: [
    {
      path: '/content',
      name: 'content',
      component: Content
    },
    {
      path: '/main',
      component: Main,
      name: 'main'
    },
    {
      path: '/lj',
      component: Ghj,
      name: 'lj'
    }
  ]
})
