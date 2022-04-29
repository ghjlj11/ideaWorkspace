<template>
  <div id="profile">
    <h1>用户信息</h1>
    {{id}}
    <h1>没事看看</h1>
    <a :href="kk.links[0].url">你在搞什么</a>
  </div>
</template>

<script>


export default{
  name: "Profile",
  props: ['id'],
  data() {
    return {
      kk: {
        name: '郭欢军',
        url: null,
        links: [{name: null, url: null},
          {name: null, url: null},
          {name: null, url: null}]
      }
    }
  },
  beforeRouteEnter:(to,from,next)=>{
    console.log("进入之前");
    next(vm => {
      vm.getData();
    });
  },
  beforeRouteLeave:(to,from,next)=>{
    console.log("离开之前");
    next();
  },
  methods: {
    getData : function (){
      var that = this;
      console.log(this.kk.name);
      this.axios({
        method: 'get',
        url: 'http://localhost:8080/static/mock/data.json',
      }).then(function (response){
        that.kk = response.data;
        console.log(this.kk.name);
      })
    }
  }
}
</script>

<style scoped>

</style>
