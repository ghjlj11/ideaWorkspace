
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/modify" method="post">
    <input type="text" name="id" value="${student.id}" hidden>
    <input type="text" name="username" value="${student.username}">
    <input type="number" name="no" value="${student.no}">
    <input type="email" name="email" value="${student.email}">
    <input type="number" name="phone" value="${student.phone}">
    <select name="clazz_id">
        <c:forEach var="i" items="${clazz}">
            <option value="${i.id}">${i.name}</option>
        </c:forEach>
    </select>
    <input type="date" name="date" value="${student.birthdate}">
    <input type="submit" value="提交">
</form>
</body>
</html>
