
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/jQuery.js"></script>
    <script>
        function del(i,s){
            $.get({
                url:"${pageContext.request.contextPath}/delete",
                data: {"id":i},
                success: function (data){
                    console.log(data);
                    let parse = JSON.parse(data);
                    s = parse;
                    console.log(parse);
                }
            });
        }
    </script>

</head>
<body>
<c:set var="request" value="${pageContext.request}"/>
<div style="text-align: center">
    <h1>hello</h1>
    <table id="table" style="margin-left: 30% ; border: solid 2px">
        <tr>
            <th>学生姓名</th>
            <th>学生学号</th>
            <th>学生邮箱</th>
            <th>学生电话</th>
            <th>学生生日</th>
            <th>学生班级名</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${students}" var="l">
            <tr>
                <td>${l.username}</td>
                <td>${l.no}</td>
                <td>${l.email}</td>
                <td>${l.phone}</td>
                <td>${l.birthdate}</td>
                <td>${clazz.get(l.clazz_id).name}</td>
                <td><button onclick="">修改</button>&nbsp; | &nbsp;<button onclick="del(${l.id},${students})">删除</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
