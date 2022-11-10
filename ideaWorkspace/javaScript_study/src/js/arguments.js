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