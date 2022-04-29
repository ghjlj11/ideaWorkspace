<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/statics/jsp/jQuery.js"></script>
    <script>
        function a1(){
            $.post({
                url:"${pageContext.request.contextPath}/a3",
                data:{"name":$("#name").val()},
                success:function (data){
                    console.log(data);
                    if(data === "ok"){
                        $("#nameS").css("color","green");
                    }
                    else {
                        $("#nameS").css("color","red");
                    }
                    $("#nameS").html(data);
                }
            });
        }
        function a2(){
            $.post({
                url:"${pageContext.request.contextPath}/a3",
                data:{"psw":$("#psw").val()},
                success:function (data){
                    console.log(data);
                    if(data === "ok"){
                        $("#pswS").css("color","green");
                    }
                    else {
                        $("#pswS").css("color","red");
                    }
                    $("#pswS").html(data);
                }
            });
        }
    </script>
</head>
<body>

<div>
    <p>用户名：<input type="text" id="name" onblur="a1()">
        <span id="nameS"></span>
    </p>
    <p>密&nbsp;&nbsp;码：<input type="text" id="psw" onblur="a2()">
        <span id="pswS"></span>
    </p>
</div>
</body>
</html>
