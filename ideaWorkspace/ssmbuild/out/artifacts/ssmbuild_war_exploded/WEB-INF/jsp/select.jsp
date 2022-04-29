<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <style>
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表 ———————————— 显示所有书籍</small>
                </h1>
            </div>
        </div>
        <div class="col-md-4 column">
            <div style="float: left">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/books/addBook">新增书籍</a>
            </div>
        </div>
        <div class="col-md-8 column">
            <div style="float: right">
                <span style="color: red">${msg}</span>
                <form class="form-inline" action="${pageContext.request.contextPath}/books/search" method="post">
                    <input type="text" name="bookName" placeholder="请输入查询书籍的名称">
                    <button class="btn btn-primary">查询</button>
                </form>
            </div>
        </div>

    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>书籍编号</th>
                    <th>书籍名称</th>
                    <th>书籍数量</th>
                    <th>书籍描述</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${list}">
                    <tr>
                        <td>${book.bookID}</td>
                        <td>${book.bookName}</td>
                        <td>${book.bookCounts}</td>
                        <td>${book.detail}</td>
                        <td><a href="${pageContext.request.contextPath}/books/updateBook?bookID=${book.bookID}">修改</a>
                            | &nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/books/toDeleteBook/${book.bookID}">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
