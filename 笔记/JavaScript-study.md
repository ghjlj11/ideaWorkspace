# JavaScript-study



js的代码是 逐行被浏览器的js引擎翻译给电脑执行的， 当某一行代码报错， 那么就不会继续执行下去。

js分为行内式、内嵌式以及外部式



## hello world

js的三种方式以及基础的 弹窗， 输入弹窗， 控制台打印

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <!-- 内嵌式 -->
        <script>
            // prompt()弹出输入框， 并且有返回值可以获取
            let info = prompt();
            // 控制台打印
            console.log(info);
            // alter() 弹出框
            alert(info);
            function test(){
                document.getElementById('qqq').value = Number.parseInt(document.getElementById('qqq').value)  + 1 ;
            }
        </script>
        <!-- 外部式 -->
        <script src='arguments.js'></script>
    </head>
    <body>
        <!-- 行内式 -->
        <button type='button' onclick='test()''>按钮</button>
        <input type='text' name='qwe' id='qqq'>
    </body>
</html>
```



## 变量



变量的声明与赋值： 不存在变量的类型

```html
        <script>
            // 普通声明变量
            var a = 9;
            // 多个声明
            var c = 2, b = 3;
            console.log(a, b, c);
            // 只声明不赋值 则是 undifine
            var dd;
            console.log(dd);
            // 不声明 直接赋值也可以使用
            qq = 22;
            console.log(qq);
            // 不声明 直接使用会报错
            consol.log(kk);
        </script>
```



**变量名称只能由数字、 _、$、字母组成， 并且不能由数字开头**



- `var`在方法内部声明则是局部变量， 在方法外部声明则是全局变量。

- 直接使用 `i= 12` 类似这种声明的变量也是全局变量，避免使用。

-  `let`声明的是局部变量，是属于块级变量， 即只在对应的代码块中生效， 在外部声明的同样可以在内部中生效， 例如：

  ```javascript
  let p = 0;
  function fun(){
      console.log(p);
  }
  ```

  尽量使用`let`声明块级变量， 以上定义的是全局变量。

-  `const`用来声明常量， 一旦声明就必须赋值， 否则会抛出异常， 声明的也是属于块级常量。



> 严格检查模式 strict

由于js的语法比较随意， 有时候虽然可以这么写， 但是很不符合规范， 就可以使用严格模式`'use strict';`， 这样下面的 `i = 9`这种写法就会报错， 不符合规范。

```javascript
        <script>
            // 放在代码第一行， 放下面的话只检测这行代码后面的代码
            'use strict';
            let p = 0;
            const a = 1;
            fun();
            console.log(i);
            function fun(){
                const a = 6;
                i = 9;
                console.log(p);
            }
        </script>
```





## 数据类型



**JS数据中一共有8种**
ES5中有6种：String、Number、Boolean、空（Null）、未定义（Undefined）、Object
ES6新增了Symbol：这种类型的对象永不相等，即始创建的时候传入相同的值，可以解决属性名冲突的问题，做为标记
es10新增了bigInt：是指安全存储、操作大整数



**按照类型来分有基本数据类型和引用数据类型：**

**基本数据类型：**`String`、`Number`、`Boolean`、`Null`、`Undefined`、`Symbol`

**引用数据类型：**`Object`【Object是个大类，function函数、array数组、date日期...等都归属于Object】



判断变量的类型

```javascript
// 返回对象的类型
typeof(o);

// 判断变量是否是这个类型， 与java一样， 返回值为bool
o instanceof String
```





> Number

Number类型表示数字类型， 不管是小数、整数、正数、 负数都属于Number类型，  `NaN`也是属于Number类型

可以通过`isNaN()`方法判断是否不是一个数字： 该方法首先会尝试将参数转化为Number类似， 如果不行那就`false`， 如果是`NaN`也是`false`， 否则就是`true`。

`Number.isNaN()`， 判断的是参数是否是 `NaN`。

`InFinite`表示无限的， 即js装不下这个数字了。



**浮点类型相关的计算会存在精度问题**



> 字符串

'abc'， "abc"

正常情况下只需要把内容写在单引号或者双引号内， 如果需要特殊符号就使用转译符

```javascript
\n    换行   
\"    双引号
\'	  单引号
\t	  空格
```



还可以使用`tab`键上面那个键的引号来定义字符串， 里面还可以取变量的值， 例如：```let b = `bbb ${a}`;```， b的值就是bbb 和a的值拼接而来。



字符串也会有类似java里的一些方法， 例如：

```java

            console.log(b.length);
            console.log(b.charAt(2));
            console.log(a.indexOf('a'));
            console.log(b.substring(1,4));
```





> 布尔值

true， false



> 比较运算符

```javascript
= 赋值
== 等于（类型不等， 值相等就是true）
=== 绝对等于（类型一样， 值一样， 结果才是true）
NaN === NaN 结果为false， NaN与任何数字比较都是false， 包括自己
```



> Undefined

不存在的变量



> 数组

与java不同的是， 因为js声明变量没有带类型， 所以一个数组里面可以存放多种类型的元素

```javascript
// 声明数组
var arr = [1, 2, 3, 4, 'aa', true, NaN, null];
// 也可以使用这种方式声明
var arr2 = new Array(1,2,3,4,'aa',NaN);

// 基本方法
// 箭头函数遍历， 也可以使用普通的for
arr.forEach(item => {
                if(item === 2){
                    b = item;
                }
            });
// 长度
arr.length;

// 可以直接修改数组长度， 后面加的位置为空， 不是null；如果长度变短， 那么会截去后面部分， 只保留前面部分
arr.length = 10;

// 判断数组里面是否含有此元素， 1和'1'是不一样的
arr.includes('2');

// 返回该元素在数组里的下标， 若不存在则为-1
arr.indexOf('2');

// 数组版的substring，返回一个新数组，  一个参数时就代表star为该参数， end为数组长度加一， 同substring。
arr.slice(0,2);

// push(T...t) 可以在数组尾部加入一个或多个元素， 返回值为数组长度；  pop() 删除尾部的元素，返回值为抛出的元素
arr.push(1,2,3);
arr.pop();

// unshift(T...t) 在数组头部增加一个或者多个元素， 返回值为数组的长度(多个元素插入时， 第一个在头部)；  shift() 抛出数组头部元素， 返回值为抛出的元素
arr.unshift(1,2,3);
arr.shift();

// sort()排序
arr.sort()

// reverse()反转
arr.reverse()

// concat([1,4,5])连接两个数组， 返回新的数组， 不会改变原来数组的值
arr.concat([1,4,5])

//join('-') 打印数组， 返回数组里的元素之间使用'-'拼接而成的字符串， 不包括undefined， null
arr.join('-')

// fill(v) 将数组填充， 全部填充为v ，与java一样；
arr.fill(2)

// 多维数组
arr[[1, 2], [3, 4], [5, 6]];
```



当访问数组下标越界， 会返回`Undefined`



> Symbol

```javascript
let a = Symbol('as');
let b = Symbol('as');
console.log(a);
// false
console.log(a == b);
// false
console.log(a === b);
```





> Object

对象类型赋值时， 使用大括号括起来， 每个属性之间使用逗号分隔， 属性的名字只能是`String`或者`Symbol`类型，属性的值可以任意

```javascript
// 测试数组
var arr = [1, 2, 3, 4, 'aa', true, NaN, null];
var arr2 = new Array(1,2,3,4,'aa',NaN);
console.log(arr);

// 测试Symbol
let b = Symbol('as');

// 测试对象
let obj = {
    name: 'lj',
    age: 12,
    sex: 'w',
    arr: arr,
    [b]: 'lll'
};
console.log(obj);
console.log(obj.arr[2]);
```



对象属性相关：

```java
let obj = {a: 12, b: 'ww', c: [1,2]};

// 获取对象不存在的属性值就是返回 undefined
obj.o;
    
// 动态增加对象的属性， 对象就会加一个属性， 返回值为加的属性值
obj.k = 'l';

// 动态删除对象的属性， 对象也会少了这个属性， 返回值为true, 即使不存在该属性删除失败也是true， 但是也会存在返回flase情况。
delete obj.a
    
// 判断属性是否在该对象中, 返回值为boolean值
'b' in obj
    
// obj.hasOwnProperty('toString')， 判断对象的自身属性是否含有该属性， 不包含继承父类的
obj.hasOwnProperty('b') // 为
obj.hasOwnProperty('toString') // 为flase
'toString' in obj // 为true
```



## 流程控制



if ，else， for， while， 和java基本一样。

```javascript
// 类似java的增强的for (可以用于迭代object的属性名称), key是arr的下标
            for (let key in arr) {
                console.log(key);
            }


            for (const key in obj) {
                console.log(key);
                // 通过object[key]方式获取对象的属性值， 这种方法用在获取变量的值 let obj = {[aa] : 'ss'}
                console.log(obj[key]);
            }

//只用于可以迭代的类型， object不行 ，map和set都可以  s是arr的值

            for (const s of arr) {
                console.log(s);
            }
// forEach 迭代  item是数组的值

            arr.forEach(item => {
                if(item === 2){
                    b = item;
                }
            });
```



## Map

类似java中的map， 也有些不一样



构造Map时参数可以为空， 也可以是一个二维或者多维数组。

```javascript
let map = new Map;
let map = new Map();
let map = new Map([['k1', 'v1'], ['k1', 'v1']]);
```



基本使用方法：

```javascript
// 插入键值对
map.set(k, v);

// 根据键获取值
map.get(k);

// 根据键删除键值对
map.delete(k);

// 判断是否含有该键
map.has(k);

// 清空map
map.clear();

// 获取map的大小， size是属性， 不是方法
map.size;
```



遍历方法：

```javascript
// forEach 遍历map的值
map.forEach(item => {
    console.log('item:' + item);
})

// 获取map的key的遍历器
let ks = map.keys();
for (let key of ks) {
    console.log(key);
    console.log(map.get(key));
}

// 获取map的value的遍历器
let vs = map.values();
for(let v of vs){
    console.log(v);
}

// 获取 map的entry 的遍历器， entries里的每个元素就是一个二维数组
let entries = map.entries();
for (let entry of entries) {
    console.log(entry[0]);
    console.log(entry[1]);
    console.log(entry);
}

// 因为entries遍历的值其实是一个二维数组， 也可以
for (const [k, v] of entries) {
    console.log(k);
    console.log(v);
}

// 也可以直接遍历map， 和获取entries类似
for (const [k, v] of map) {
    console.log(k);
    console.log(v);
}
for(let ent of map){
    console.log(ent);
    console.log(ent[0]);
    console.log(ent[1]);
}
```



Map与Object类型互相转换

```javascript

let obj = {

};

//map转化为obj对应的键值对
for (let [k, v] of map) {
    obj[k] = v;
}

let map1 = new Map();

// Object的键值对转化为map的键值对
for (let a in obj) {
    console.log(a);
    console.log(obj[a]);
    map1.set(a, obj[a]);
}
```



## Set



set与map基本差不多，拥有去重效果，  其实Set里面的数据结构和Map基本一样， 里面也是有一个entries的数组， 只不过该数组的对应的元素的key和value是一样的。



新建Set：

```javascript
let set = new Set();
let set = new Set;
// 可以放入一个数组
let set = new Set(['2', 'a']);
```



基本方法：

```javascript
set.add(2);
// size是属性，不是方法
set.size;
set.delete(k);
set.has(k);
set.clear();
```



遍历set：

```javascript
// forEach遍历set的值
set.forEach(item => console.log(item));

// 通过keys遍历
let ks = set.keys();
for (let k of ks) {
    console.log(k);
}

// 通过values遍历
let vs = set.values();
for (let v of vs) {
    console.log(v);
}

// 通过entries遍历， 获取的键值对数组的key与value是一样的
let entries = set.entries();
for (let entry of entries) {
    console.log(entry);
}

for (const [k, v] of entries) {
    console.log(k);
    console.log(v);
}

// 直接通过set遍历
// 此时遍历的只是entry的值， 与遍历keys和values一样。
for (let item of set) {
    console.log(item);
}

```



## function

函数在对象里面就叫做方法， 在外部就是函数。



声明函数：

```javascript
// 方式一和二效果完全相等
// 方式一
let fun1 = function(){
    return '999';
}

// 方式二
function fun2(v){
    return v + 'wer';
}
```



函数的参数：

```javascript
// 控制台输出

// 少传参数也可以调用， 但是如果后面用到参数就会返回undefined
fun2();
'undefinedwer'

// 多传参数也可以， 也会接收， 后面用到哪个参数就是哪个， 参数列表按顺序对应着
fun2(2,3,4,45);
'2wer'


// 传进来的参数可以使用arguments 接收， 是一个数组， 即使参数参长度超过了显式声明的参数列表也可以在arguments里面获取到
//形参可以任意，为了后续方便使用.
function fun(a, b) {
    console.log(a);
    console.log(b);
    console.log(arguments.length);
    //当前函数
    console.log(arguments.callee);
    //第几个实参
    console.log(arguments[3]);
}
//可以传多个参数，都会接收
fun(1, 2, 3, 45, 5);


// rest方式  当参数列表后面加上 ...v(必须在最后面)， 多余的参数就会放在v里面， 如果入参比参数列表少或者一样， 那么v就是一个空的数组。

function fun3(a,b, ...kkk){
    console.log(kkk);
}
```



在方法内定义异常

```javascript
function(x){
    if(typeof(x) !== "number"){
        throw 'not a number!';
    }
    return x > 0 ? x : -x;
}
```





## JSON



>JSON分类

1.对象 {}
 2.数组 []



> JSON中允许的值

1.字符串
 2.数值
 3.布尔值
 4.null
 5.对象
 6.数组

如果对象中含有以外类型的属性， 需要先转化为可以接收的类型，例如： Map转化为Object类型， Set转化为数组类型



对象转化为JSON字符串

```javascript
let ss = JSON.stringify(obj);
```



JSON字符转化为对象

```javascript
let oo = JSON.parse(ss);
```

