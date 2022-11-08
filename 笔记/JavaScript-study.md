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
