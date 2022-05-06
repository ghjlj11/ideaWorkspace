<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表单提交</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/hh/kk" method="post">
    <input type="text" name="names" id="names">
    <input type="submit">
</form>

</body>
</html>
