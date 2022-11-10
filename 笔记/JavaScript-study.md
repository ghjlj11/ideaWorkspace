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

