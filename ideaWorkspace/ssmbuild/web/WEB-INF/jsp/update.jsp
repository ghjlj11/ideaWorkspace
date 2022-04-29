<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新书籍</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>更新书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <form class="form-horizontal" action="${pageContext.request.contextPath}/books/toUpdateBook" method="post">

        <div class="control-group">
            <div class="controls">
                <input type="text" name="bookID" value="${books.bookID}" hidden>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">bookName</label>
            <div class="controls">
                <input type="text" name="bookName" value="${books.bookName}" required>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">bookCounts</label>
            <div class="controls">
                <input type="text" name="bookCounts" value="${books.bookCounts}" required>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">detail</label>
            <div class="controls">
                <input type="text" name="detail" value="${books.detail}" required>
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <button type="submit" class="btn">提交</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
