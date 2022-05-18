# Vue - Study



## MVVM（Model  ViewModel View）模型



- 其实就是一个MVC模型， 前端视图与 视图模型双向绑定， 然后 后端通过传递模型给视图模型，改变视图模型里的数据，  从而渲染前端视图。



第一个vue程序：

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div id="app">
    {{message}}
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            message: "hello,vue!"
        }
    });
</script>
</body>
</html>
```



- 我们首先需要引入<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>；

- 然后我们创建一个vm对象，与上面的一个元素相关联， 并且注入data数据， 这样上面的元素就可以获得data里的数据。并且这个元素会随着data里的数据变化而变化， 实现动态变化。

  ![image-20220425214308679](img\image-20220425214308679.png)





## vue语法



- 使用v-bind绑定信息 可以简写为 ：

```html
<div id="app">
    <span v-bind:title="message">
        鼠标悬浮
    </span>
    {{message}}
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            message: "hello,vue!"
        }
    });
</script>
```



- if  else语句

```html
<div id="app">
    <h1 v-if="flag">yes</h1>
    <h1 v-else>no</h1>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            flag: true
        }
    });
</script>
```



- else if语句

```html
<div id="app">
    <h1 v-if="flag==='a'">a</h1>
    <h1 v-else-if="flag==='b'">b</h1>
    <h1 v-else-if="flag==='c'">c</h1>
    <h1 v-else>d</h1>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            flag: 'a'
        }
    });
</script>
```





- for语句

```html
<div id="app">
    <h1 v-for="(flag,index) in flags">
        {{flag.message}}-->{{index}}
    </h1>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            flags: [{message: "ghj"},{message: "lj"}]
        }
    });
</script>
```



- v-on事件语句 可以简写为 @

```html
<div id="app">
    <button v-on:click="sayHi">click</button>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            flags: "你在干什么"
        },
        methods: {
            sayHi : function (){
                alert(this.flags);
            }
        }
    });
</script>
```





## 双向绑定



**重点就是通过v-model来双向绑定**

- 使用v-model实现双向绑定 ， 输入框里的value就会和vm.message同步， 从而输出

```html
<div id="app">
    <input type="text" v-model:value="message">
    {{message}}
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            message: ""
        }
    });
</script>
```



- 通过v-model:value="message"来绑定单选框里被选定的value

```html
<div id="app">
    性别：
    <input type="radio" name="sex" value="男" v-model:value="message">男
    <input type="radio" name="sex" value="女" v-model:value="message">女
    {{message}}
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            message: ""
        }
    });
</script>
```



- 绑定下拉框v-model="message"换成v-model:onselected="message"也可以。

```html
<div id="app">
    下拉：
    <select name="se" v-model="message">
        <option value="" disabled>--请选择--</option>
        <option>a</option>
        <option>b</option>
        <option>c</option>
        <option>d</option>
    </select>
    {{message}}
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            message: ""
        }
    });
</script>
```



这里的“请选择”的选项value要为空，否则默认不会选择， 因为message的初始值为空，要对应。





## component（组件）



我们可以自己定义一个组件， 里面包含一些html标签， 然后名字任意， 这样可以减少代码的复用



```html
<div id="app">
    <ghj v-for="flag in flags" v-bind:ghj="flag"></ghj>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>

    Vue.component("ghj",{
        props:['ghj'],
        template:'<li>{{ghj}}</li>'
    });
    var vm = new Vue({
        el: "#app",
        data:{
            flags: ["ghj","lj"]
        }
    });
</script>
```



​		**这里由于flags是在外部的div里面，如果直接写<ghj v-for="flag in flags" v-bind:ghj="flag"></ghj>， 则外部的组件识别不了 “ghj” 这个元素， 因此需要在component里面加上 props:['ghj']，就代表 “ghj” 是component的一个元素 **



- 可以用Vue.component("组件名"，{

  props: ['元素'，...]，

  template: '模板'

  })；



- 说明： 通过v-for="flag in flags"来遍历外部的flags里的所有元素， 再通过v-bind:ghj="flag"把组件里的元素与外部的flag绑定，这样就可以把vm里的flags与组件里的元素联系起来。 



## Axios



- 前提 ：我们可以设置浏览器的网速， 这样就可以看出网页开始并不是就有数据的， 而是从一个模板，慢慢加载出来的， 所以这里的视图需要先经过初始化阶段， 然后才回去执行我们的Vue上的代码， 才会把数据渲染上去。





**就是通过请求的方式来把数据返回到前端的视图上**



```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        [v-cloak]{
            display: none;
        }
    </style>
</head>
<body>
<div id="vue" v-cloak>
    <div>{{kk.name}}</div>
    <div>{{kk.address.city}}</div>
    <div>{{kk.address.country}}</div>
    <div>{{kk.address.stress}}</div>
    <a v-bind:href="kk.links[0].url">bilibili</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    var vm = new Vue({
        el: "#vue",
        data(){
          return{
              kk:{
                  name: null,
                  address: {
                      city: null,
                      stress: null,
                      country: null
                  },
                  links: [{name: null, url: null},
                      {name: null, url: null},
                      {name: null, url: null}]
              }
          }
        },
        mounted(){
            axios.get('../data.json').then(response=>(this.kk=response.data));
        }
    });
</script>
</body>
</html>
```



**这里定义的函数data会返回一个数据， 并且函数名只可以是data， 然后通过钩子函数mounted的链式语法，我们可以读取data.json里的数据， 并且让这里的data函数返回值设置成这个response.data， 就可以再视图上获取，我们还可以设置闪烁的时候不显示模型，只是空白页面 <style>[v-cloak]{display: none;}</style>。**



## computed（计算属性）



```html
<div id="app">
    <p>curTime: {{curTime()}}</p>
    <p>curTime: {{curTime2}}</p>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>
    var vm = new Vue({
        el: "#app",
        data:{
            message: "hello,vue!"
        },
        methods: {
            curTime: function (){
                return Date.now();
            }
        },
        computed: {
            curTime2 : function (){
                return Date.now();
            }
        }
    });
</script>
```



- 这里的computed属性很像methods， 但是这里computed的是表示一个属性， 就是方法名就是代表一个属性， 可以直接通过名字获取， 而methods里的方法只可以通过方法名加上（）去执行方法，再获得返回值，并且这里不要用相同的名字在 methods与computed里面， 以为method的优先级高，会认为是个方法。 



![image-20220426194330139](img\image-20220426194330139.png)



​		**这里的计算属性是在内存里面操作， 在控制台里一直输出这个属性也是没有变化， 而执行方法就会变化， 因此，我们就不需要一直去调用函数来获得数据， 对于一些不需要频繁刷新的数据来说， 可以大大的减少浏览器的压力。**



## slot（插槽）



- 之前我们定义了组件， 并且我们可以在组件里面定义组件，如果有带有列表的代码， <li>可以只需要定义一个， 剩下的通过for循环出来， 而<ul>就不好弄出来， 也不好为了一个<ul>定义一个组件，没意义， 这时候就可以用插槽， 就是只定义一个<ul>，然后其他的地方可以用插槽。

```html
<div id="app">
    <todo>
        <todo-title slot="todo-title" v-bind:title="title"></todo-title>
        <todo-items slot="todo-items" v-for="item in items" v-bind:item="item"></todo-items>
    </todo>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>

    Vue.component("todo",{
        template: '<div>\
                    <slot name="todo-title"></slot>\
                    <ul>\
                    <slot name="todo-items"></slot>\
                    </ul>\
                    </div>'
    });

    Vue.component("todo-title",{
        props: ['title'],
        template: '<p>{{title}}</p>'
    });

    Vue.component("todo-items",{
        props: ['item'],
        template: '<li>{{item}}</li>'
    })
    var vm = new Vue({
        el: "#app",
        data:{
            items: ["java","Linux","c++"],
            title: "你在搞什么"
        }
    });
</script>
```

  



- 需要注意的是<slot name="todo-title"></slot>需要写上 name属性， 并且在视图层 需要使用这个插槽也是要加上slot="todo-title"属性。



## 自定义事件内容分发



- 我们希望可以通过， 组件里的一些事件可以改变Vue这个对象里的数据， 然而component和Vue对象是同一级别的， 不能删除， 于是就可以通过视图层， 组件调用视图层的方法， 视图层调用Vue对象的方法， 从而实现删除。那组件如何调用视图的方法。
- **v-on:remove="removeItem(index)"视图上自己定义一个方法绑定Vue对象里的方法， 组件通过调用this.$emit('remove',index);这个方法既可以调用到视图的remove方法**

```html
<div id="app">
    <todo>
        <todo-title slot="todo-title" v-bind:title="title"></todo-title>
        <todo-items slot="todo-items" v-for="(item,index) in items" v-bind:item="item"
                    v-bind:index="index" v-on:remove="removeItem(index)" :key="index"></todo-items>
    </todo>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.min.js"></script>
<script>

    Vue.component("todo",{
        template: '<div>\
                    <slot name="todo-title"></slot>\
                    <ul>\
                    <slot name="todo-items"></slot>\
                    </ul>\
                    </div>'
    });

    Vue.component("todo-title",{
        props: ['title'],
        template: '<p>{{title}}</p>'
    });

    Vue.component("todo-items",{
        props: ['item','index'],
        template: '<li>{{index}}--->{{item}} &nbsp; <button v-on:click="toRemove()">删除</button></li>',
        methods: {
            "toRemove" : function (index){
                this.$emit('remove',index);
            }
        }
    });
    var vm = new Vue({
        el: "#app",
        data:{
            items: ["java","Linux","c++"],
            title: "你在搞什么"
        },
        methods: {
            "removeItem" : function (index){
                console.log("删除了"+this.items[index]+",ok");
                this.items.splice(index,1);
            }
        }
    });
</script>
```





## 第一个Vue程序



- 下载好相应的文件。创建一个新的项目， 项目会很大， 然后在终端输入命令：**npm run dev**;就可以在指定端口访问了， 默认端口为8080，可以在config的index.js里面修改。

![image-20220427121939279](img\image-20220427121939279.png)





## 第一个webpack程序



- 我们写的Vue程序都是基于ES6下的才可以执行， 然而很多浏览器只支持ES5，所以我们需要把我们的Vue程序通过webpack打包， 打包成ES5支持的程序。



- 下载号对应的文件。



- 创建一个hello.js，这里的exports表示把这个方法扩散出去，就是别的文件可以访问。

  ```js
  exports.hello1 = function (){
      document.write("<h1>学个屁1</h1>");
  };
  exports.hello2 = function (){
      document.write("<h1>学个屁2</h1>");
  };
  exports.hello3 = function (){
      document.write("<h1>学个屁3</h1>");
  };
  ```



- 创建一个main.js文件 包含hello文件，这里的require就代表包含了hello文件。

  ```js
  var hello = require("./hello");
  hello.hello1();
  ```



- 再创建一个webpack.config.js文件， 这里的entry就是代表入口， 需要从哪里切入进去，然后在output这里指定的文件下输出对应的ES5支持的格式的代码。

  这个mode: 'development'是因为idea需要我们这样做的。

  ```js
  module.exports = {
      mode: 'development',
      entry: './modules/main.js',
      output: {
          filename: "./js/bundle.js"
      }
  };
  ```

  

- boundle.js， 把mode设置成development

  ```js
  /*
   * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
   * This devtool is neither made for production nor for readable output files.
   * It uses "eval()" calls to create a separate source file in the browser devtools.
   * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
   * or disable the default devtool with "devtool: false".
   * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
   */
  /******/ (() => { // webpackBootstrap
  /******/ 	var __webpack_modules__ = ({
  
  /***/ "./modules/hello.js":
  /*!**************************!*\
    !*** ./modules/hello.js ***!
    \**************************/
  /***/ ((__unused_webpack_module, exports) => {
  
  eval("exports.hello1 = function (){\r\n    document.write(\"<h1>学个屁1</h1>\");\r\n};\r\nexports.hello2 = function (){\r\n    document.write(\"<h1>学个屁2</h1>\");\r\n};\r\nexports.hello3 = function (){\r\n    document.write(\"<h1>学个屁3</h1>\");\r\n};\n\n//# sourceURL=webpack:///./modules/hello.js?");
  
  /***/ }),
  
  /***/ "./modules/main.js":
  /*!*************************!*\
    !*** ./modules/main.js ***!
    \*************************/
  /***/ ((__unused_webpack_module, __unused_webpack_exports, __webpack_require__) => {
  
  eval("var hello = __webpack_require__(/*! ./hello */ \"./modules/hello.js\");\r\nhello.hello1();\n\n//# sourceURL=webpack:///./modules/main.js?");
  
  /***/ })
  
  /******/ 	});
  /************************************************************************/
  /******/ 	// The module cache
  /******/ 	var __webpack_module_cache__ = {};
  /******/ 	
  /******/ 	// The require function
  /******/ 	function __webpack_require__(moduleId) {
  /******/ 		// Check if module is in cache
  /******/ 		var cachedModule = __webpack_module_cache__[moduleId];
  /******/ 		if (cachedModule !== undefined) {
  /******/ 			return cachedModule.exports;
  /******/ 		}
  /******/ 		// Create a new module (and put it into the cache)
  /******/ 		var module = __webpack_module_cache__[moduleId] = {
  /******/ 			// no module.id needed
  /******/ 			// no module.loaded needed
  /******/ 			exports: {}
  /******/ 		};
  /******/ 	
  /******/ 		// Execute the module function
  /******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
  /******/ 	
  /******/ 		// Return the exports of the module
  /******/ 		return module.exports;
  /******/ 	}
  /******/ 	
  /************************************************************************/
  /******/ 	
  /******/ 	// startup
  /******/ 	// Load entry module and return exports
  /******/ 	// This entry module can't be inlined because the eval devtool is used.
  /******/ 	var __webpack_exports__ = __webpack_require__("./modules/main.js");
  /******/ 	
  /******/ })()
  ;
  ```



- boundle.js， 把mode设置成production

  ```js
  (()=>{var e={645:(e,o)=>{o.hello1=function(){document.write("<h1>学个屁1</h1>")},o.hello2=function(){document.write("<h1>学个屁2</h1>")},o.hello3=function(){document.write("<h1>学个屁3</h1>")}}},o={};(function t(r){var n=o[r];if(void 0!==n)return n.exports;var h=o[r]={exports:{}};return e[r](h,h.exports,t),h.exports})(645).hello1()})();
  ```

  



- 再创建一个index.html文件。 引入这个bundle.js就可以访问了。



```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<script src="./dist/js/bundle.js"></script>
</body>
</html>
```



## vue-router路由



- 我们可以通过一个router路由，实现Controller类似的功能。通过Vue的组件， 点击链接，跳转到相应的组件， 不需要页面的跳转。 



- 通过router的routes属性， 把我们定义的组件与对应的访问路径绑定在一起，同时要注意要import引入对应的组件，也需要引入路由'vue-router'，并且需要开启路由**Vue.use(VueRouter);**

  ```js
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
  
  ```

  

**这里引入的组件import后面的名字就是组件名字，path就是绑定的路径**



- 其中的Content组件的内容

  ```vue
  <template>
    <h1>内容页面</h1>
  </template>
  
  <script>
  export default {
    name: "Content"
  }
  </script>
  
  <style scoped>
  
  </style>
  
  ```



- 通过 App.vue这个组件可以把他们都拼接起来，



 ```vu
 <template>
   <div id="app">
     <h1>你在搞什么</h1>
     <router-link to="/main">首页</router-link>
     <router-link to="/content">组件页</router-link>
     <router-link to="/lj">Lj</router-link>
     <router-view></router-view>
   </div>
 </template>
 
 <script>
 
 export default {
   name: 'App'
 }
 </script>
 
 <style>
 #app {
   font-family: 'Avenir', Helvetica, Arial, sans-serif;
   -webkit-font-smoothing: antialiased;
   -moz-osx-font-smoothing: grayscale;
   text-align: center;
   color: #2c3e50;
   margin-top: 60px;
 }
 </style>
 
 ```



**这里的App.vue其实也是一个组件， 只不过他把之前的组件都拼接了，就变成了一个总的组件， 也通过export的方式向外散播， <router-link to="/main">首页</router-link> 就是一个类似超链接的， 链接到别的组件，<router-view></router-view>这个就是组件的显示视图 **



- main.js需要导入router， 并且需要在Vue对象里使用它，**这里的Vue对象绑定了之前的app这个组件， 并且加上了路由，这样我们的App.vue就可以通过链接， 再通过路由来跳转到别的组件。**

```js
import Vue from 'vue'
import App from './App'

import router from './router'
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
```



选择3.1.3版本安装，cnpm install vue-router@3.1.3 --save-dev



## 创建一个新的Vue项目





![image-20220427202610478](img\image-20220427202610478.png)



- 这里失败了就用cnpm去运行





## vue + ElementUI



**这部分主要就是要初始化的时候， 要注意， 就是很多版本问题， 以及npm与cnpm的问题，创建好项目后可能会出现一些问题， 版本的问题， 记得检查一下这里， vue-router的版本不能太高**



![image-20220428102533055](img\image-20220428102533055.png)



- 我们同之前一样， 创建好router， 以及创建组件在views文件夹下



- router

  ```js
  import Vue from 'vue'
  import Router from 'vue-router';
  
  import TheMain from '../views/Main';
  import TheLogin from '../views/Login'
  
  Vue.use(Router)
  
  export default new Router({
    routes:[
      {
        path: '/main',
        component: TheMain
      },
      {
        path: '/login',
        component: TheLogin
      }
    ]
  });
  
  ```

  



- main.vue

  ```vue
  <template>
    <h1>首页</h1>
  </template>
  
  <script>
  export default {
    name: "Main"
  }
  </script>
  
  <style scoped>
  
  </style>
  
  ```





- 这里的Login.vue我们使用的ElementUI里的模块， 跟我们正常的模块是一样的，只不过 他有自己的标签， 然后要注意export default里的name要定义：

  ```vue
  <template>
    <div>
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
        <h3 class="login-title">欢迎登录</h3>
        <el-form-item label="账号" prop="username">
          <el-input type="text" placeholder="请输入账号" v-model="form.username"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="onSubmit('loginForm')">登录</el-button>
        </el-form-item>
      </el-form>
  
      <el-dialog
        title="温馨提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <span>请输入账号和密码</span>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    name: "Login",
    data() {
      return {
        form: {
          username: '',
          password: ''
        },
  
        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          username: [
            {required: true, message: '账号不可为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不可为空', trigger: 'blur'}
          ]
        },
  
        // 对话框显示和隐藏
        dialogVisible: false
      }
    },
    methods: {
      onSubmit(formName) {
        // 为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            this.$router.push("/main");
          } else {
            this.dialogVisible = true;
            return false;
          }
        });
      }
    }
  }
  </script>
  
  <style lang="scss" scoped>
  .login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
  }
  
  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }
  </style>
  
  ```

   

- 然后是main.js， 这里需要导入ElementUI里的一些东西，并且下面的Vue对象里的东西也需要改变一点。

  ```js
  // The Vue build version to load with the `import` command
  // (runtime-only or standalone) has been set in webpack.base.conf with an alias.
  import Vue from 'vue'
  import App from './App'
  import router from "./router";
  
  import ElementUI from 'element-ui';
  import 'element-ui/lib/theme-chalk/index.css';
  
  Vue.config.productionTip = false
  Vue.use(router);
  Vue.use(ElementUI);
  
  /* eslint-disable no-new */
  new Vue({
    el: '#app',
    router,
    render: h=>h(App)
  })
  
  ```

  

- 最后就是App.vue

  ```vue
  <template>
    <div id="app">
      <h1>点我</h1>
      <router-link to="/login">登录</router-link>
      <router-view></router-view>
    </div>
  </template>
  
  <script>
  export default {
    name: 'App'
  }
  </script>
  
  <style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
    float: right;
    width: 60%;
  }
  </style>
  
  ```

  



## 路由嵌套





- 路由嵌套就是在路由里面再写一个路由， 就是链接里面再来一个链接， 但是前提是， 链接的这个组件里面会有<router-view>这个视图展示的地方， 并且需要配上<router-link>**需要注意的是这里可能会出现问题依旧是降版本就可以解决**

![image-20220428140016471](img\image-20220428140016471.png)



​	就类似这种，我们只需要在package.json把"sass-loader": "^4.0.0",就可以。

- 首先我们先抄一个Main.vue组件里的代码：

  ```vue
  <template>
    <div>
      <el-container>
        <el-aside width="200px">
          <el-menu :default-openeds="['1']">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-caret-right"></i>用户管理</template>
              <el-menu-item-group>
                <el-menu-item index="1-1">
                  <!--插入的地方-->
                  <router-link to="/user/profile">个人信息</router-link>
                </el-menu-item>
                <el-menu-item index="1-2">
                  <!--插入的地方-->
                  <router-link to="/user/list">用户列表</router-link>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-caret-right"></i>内容管理</template>
              <el-menu-item-group>
                <el-menu-item index="2-1">分类管理</el-menu-item>
                <el-menu-item index="2-2">内容列表</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
  
        <el-container>
          <el-header style="text-align: right; font-size: 12px">
            <el-dropdown>
              <i class="el-icon-setting" style="margin-right: 15px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-header>
          <el-main>
            <!--在这里展示视图-->
            <router-view></router-view>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </template>
  <script>
  export default {
    name: "Main"
  }
  </script>
  <style scoped lang="scss">
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  .el-aside {
    color: #333;
  }
  </style>
  
  ```



​	这里也是一样，只需要设置一下名字就好。



- 然后写两个组件，放在user目录下面：

  ```vue
  <template>
  <h1>用户列表</h1>
  </template>
  
  <script>
  export default {
    name: "List"
  }
  </script>
  
  <style scoped>
  
  </style>
  
  ```



- 再写router里的代码：

  ```js
  import Vue from 'vue'
  import Router from 'vue-router';
  
  import TheMain from '../views/Main';
  import TheLogin from '../views/Login';
  
  import UserList from '../views/user/List';
  import UserProfile from '../views/user/Profile';
  
  Vue.use(Router)
  
  export default new Router({
    routes:[
      {
        path: '/main',
        component: TheMain,
        children: [
          {
            path: '/user/list',
            component: UserList
          },
          {
            path: '/user/profile',
            component: UserProfile
          }
        ]
      },
      {
        path: '/login',
        component: TheLogin
      }
    ]
  });
  
  ```



​		**这里有了一个新的属性 ， 就是children， 定义它， 就相当于里面又有了一个路由， 语法格式跟外面的一样， 不过要记得要import外面新加的组件。**



## 参数传递



- 在组件之间传递参数， 通过params这个属性把参数传递到路由中， 再传递到相应的组件里面。

```vue
<el-menu-item index="1-1">
                <!--插入的地方-->
                <router-link :to="{name: 'UserProfile',params:{id: 1}}">个人信息</router-link>
              </el-menu-item>
```



修改上面的Main.vue的代码， 通过name属性传递到路由， 要把to改为:to，因为要把这个当成一个对象来绑定传到别的组件。



- 修改router里的代码， 给这个组件定义一个名字来传递，并在路径后面绑定id。

```js
{
          path: '/user/profile/:id',
          name: 'UserProfile',
          component: UserProfile
        }
```



- 接受id，用$route.params.id这个来取出id的值。

```vue
<template>
  <div>
    <h1>用户信息</h1>
    {{$route.params.id}}
  </div>
</template>
```



**需要注意的是<template>标签里面只能用一个子标签， 也就是说，我们在<template>里的所有内容都需要用一个<div>标签来包括。**



### 第二种方式传递

- 我们只需要在router里的UserProfile路由里面加上一个属性props: true，就可以支持。

```vue
{
          path: '/user/profile/:id',
          name: 'UserProfile',
          component: UserProfile,
          props: true
        }
```



- 然后在组件UserProfile加上一个props，里面加上一个id属性， 然后就可以直接取出id。

```vue
<template>
  <div>
    <h1>用户信息</h1>
    {{id}}
  </div>
</template>

<script>
export default {
  name: "Profile",
  props: ['id']
}
</script>

<style scoped>

</style>

```



### 重定向



- 我们定义一个路由路径， 然后加上重定向， 我们就可以到别的路径上的了。



- 首先在'/main'下加上一个子路径

```vue
{
          path: '/goHome',
          redirect: '/main'
        }
```



- 然后在Main.vue加上一个列表， 用于绑定这个路径：

```vue
<el-menu-item index="1-3">
                <!--插入的地方-->
                <router-link to="/goHome">回到首页</router-link>
              </el-menu-item>
```



这样就可以实现重定向到原来的'/main'的页面。





## 默认打开的页面



- 我们每次进去都会是白的页面， 如果加了下面的404， 那就是404页面， 所以我们可以加一个默认的页面，只需要在路由下面加一个路径'/'就可以，通过重定向到对应的路径就行。

  ```js
  {
        path: '/',
        redirect: '/login'
      },
  ```

  





## 404 和路由钩子



### 路由模式

- 我们之前写的代码在网站上的路径会多一个#，路由模式有两种， 这种就是hash模式（默认）， 还有就是history模式

- ```js
  export default new Router({
    mode: 'history',
    routes:[
        ]
  ```





### 给Main.vue页面加上用户名



- 我们是通过这个函数来提交的， 可以在this.$router.push后面加上表单的username。

```vue
onSubmit(formName) {
      // 为表单绑定验证功能
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
          this.$router.push("/main/" + this.form.username);
        } else {
          this.dialogVisible = true;
          return false;
        }
      });
    }
```



- 路由这边也要接收，就是用之前的方法，修改path: '/main/:name', 和props: true，这样这个参数就可以在路由里传递。

```vue
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
```



- 在组件里面接受， 也是和之前的第二种接收的方式一样加上一个props: ['name']的属性， 然后我们就可以直接取出。



### 404页面

- 我们在平时访问的时候很多网站都有自己的404页面， 我们也可以自己写， 只需要加上一个自己的组件，然后在路由里面配置这个组件的路径是'*'就可以，因为只要访问不到我们自己定义的路径， 他就会走这个路径。

- 组件内容：

  ```vue
  <template>
    <h1>404 没有找到页面</h1>
  </template>
  
  <script>
  export default {
    name: "NotFound"
  }
  </script>
  
  <style scoped>
  
  </style>
  
  ```

   



- 路由配置

  ```js
  {
        path: '*',
        component: NotFound
      }
  ```





### 钩子函数



- 这里我们有两个钩子函数beforeRouteEnter代表我们进入这个组件之前该做什么， beforeRouteLeave代表我们离开这个组件之前该做什么。



- 在profile.vue里面定义：

  ```vue
  <template>
    <div>
      <h1>用户信息</h1>
      {{id}}
    </div>
  </template>
  
  <script>
  export default {
    name: "Profile",
    props: ['id'],
    beforeRouteEnter:(to,from,next)=>{
      console.log("进入之前");
      next();
    },
    beforeRouteLeave:(to,from,next)=>{
      console.log("离开之前");
      next();
    }
  }
  </script>
  
  <style scoped>
  
  </style>
  
  ```



​	**这里在我们进入这个组件之前会在控制台打印， 在离开之前也会打印。next（），就是代表进入下一个路由。**



![image-20220428170426791](img\image-20220428170426791.png)





- 导入axios， 输入cnpm install --save axios vue-axios



- 把data.json放在static/mock下面。测试能否访问， 运行程序，直接 输入路径static/mock/data.json， 将profile定义成：

  ```vue
  export default {
    name: "Profile",
    props: ['id'],
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
        this.axios({
          method: 'get',
          url: 'http://localhost:8080/static/mock/data.json',
        }).then(function (response){
          console.log(response.data);
        })
      }
    }
  }
  ```

  



- 这样就可以或得到data.json里的内容了。







### 通过axios来获得数据， 并渲染到前端页面



- 我们稍微修改一下Profile.vue的代码：

  ```vue
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
  
  ```



​		**我们给加上一个data()来传递我们接收的数据，这里的this进入到axios里面是会变的，所以我们不能用this.kk来接收数据， 需要在外面定义一个that = this， 这样我们就可以通过这个 that.kk =  response.data， 来获得数据， 再来渲染到前端的页面。 **



- 我们可以看看这里的this是否一样， 这里提示进入到axios里的this.kk根本没有定义，所以这并不是同一个this  ：

![image-20220428225744396](img\image-20220428225744396.png)



这样我们终于可以实现数据交互了， 并且渲染到页面。 
