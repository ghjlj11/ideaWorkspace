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



> 字符串

'abc'， "abc"



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

