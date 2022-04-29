<%--
  Created by IntelliJ IDEA.
  User: 86187
  Date: 2022/4/19
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<h2>登录页面</h2>
<div>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        用户名：<input type="text" name="username">
        密码&nbsp;&nbsp;:<input type="text" name="password">
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
