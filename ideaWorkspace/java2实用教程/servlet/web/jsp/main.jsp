
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/jQuery.js"></script>
    <script type="text/javascript" src="/static/selectAll.js" charset="UTF-8"></script>
</head>
<body>
<c:set var="request" value="${pageContext.request}"/>
<div style="text-align: center">
    <h1>hello</h1>
    <button onclick="selectAll()">查询</button>
    <a href="${pageContext.request.contextPath}/toAdd"><button>添加</button></a>
    <table id="table" style="margin-left: 30% ; border: solid 2px">
        <thead>
        <tr>
            <th>学生姓名</th>
            <th>学生学号</th>
            <th>学生邮箱</th>
            <th>学生电话</th>
            <th>学生生日</th>
            <th>学生班级名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="body">

        </tbody>
    </table>
</div>
</body>
</html>
