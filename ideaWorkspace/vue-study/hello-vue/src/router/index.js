import Vue from 'vue'
import Router from 'vue-router';

import TheMain from '../views/Main';
import TheLogin from '../views/Login';
import NotFound from "../views/NotFound";

import UserList from '../views/user/List';
import UserProfile from '../views/user/Profile';

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes:[
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/main/:name',
      props: true,
      component: TheMain,
      children: [
        {
          path: '/user/list',
          component: UserList
        },
        {
          path: '/user/profile/:id',
          name: 'UserProfile',
          component: UserProfile,
          props: true
        },
        {
          path: '/goHome',
          redirect: '/main'
        }
      ]
    },
    {
      path: '/login',
      component: TheLogin
    },
    {
      path: '*',
      component: NotFound
    }
  ]
});
