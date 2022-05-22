
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/add" method="post">
    <input type="text" name="username" placeholder="username">
    <input type="number" name="no" placeholder="no">
    <input type="email" name="email" placeholder="email">
    <input type="number" name="phone" placeholder="phone">
    <select name="clazz_id">
        <c:forEach var="i" items="${clazz}">
            <option value="${i.id}">${i.name}</option>
        </c:forEach>
    </select>
    <input type="date" name="date" placeholder="date">
    <input type="submit" value="提交">
</form>
</body>
</html>
