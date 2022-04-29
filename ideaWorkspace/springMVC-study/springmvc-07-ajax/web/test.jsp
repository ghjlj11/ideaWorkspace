<%--
  Created by IntelliJ IDEA.
  User: 86187
  Date: 2022/4/18
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function go(){
            var url = document.getElementById("url").value;
            document.getElementById("iframe1").src = url;
        }
    </script>
</head>
<body>
<div>
    <p>请输入地址：</p>
    <p>
        <input type="text" id="url" value="https://www.bilibili.com/">
        <input type="button" value="提交" onclick="go()">
    </p>
</div>
<div>
    <iframe id="iframe1" style="width: 100%; height: 500px"></iframe>
</div>
</body>
</html>
