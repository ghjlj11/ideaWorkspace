<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <style>
      a{
        text-decoration: none;
        font-size: 30px;
        color: black;
      }
      h3{
        width: 200px;
        line-height: 38px;
        text-align: center;
        height: 38px;
        margin: 100px auto;
        background-color: aqua;
        border-radius: 5px;
      }
    </style>
  </head>
  <body>
  <h3>
    <a href="${pageContext.request.contextPath}/books/selectAll">点击查询</a>
  </h3>
  </body>
</html>
