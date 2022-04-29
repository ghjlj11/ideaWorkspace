<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增书籍</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>添加书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <form class="form-horizontal" action="${pageContext.request.contextPath}/books/toAddBook" method="post">

        <div class="control-group">
            <label class="control-label">bookName</label>
            <div class="controls">
                <input type="text" name="bookName" placeholder="bookName" required>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">bookCounts</label>
            <div class="controls">
                <input type="text" name="bookCounts" placeholder="bookCounts" required>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">detail</label>
            <div class="controls">
                <input type="text" name="detail" placeholder="detail" required>
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
