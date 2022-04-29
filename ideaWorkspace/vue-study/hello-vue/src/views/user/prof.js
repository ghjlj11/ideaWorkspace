import Vue from "_vue@2.6.14@vue";
import axios from "axios";

new Vue({
  el: '#profile',
  data(){
    return{
      kk: {
        name: null,
        url: null,
        links: [{name: null, url: null},
          {name: null, url: null},
          {name: null, url: null}]
      }
    }
  },
});
