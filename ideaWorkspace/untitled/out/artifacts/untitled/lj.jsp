<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page buffer="1kb" autoFlush="true"%>
<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>
<body class="container">
<h2>求职者信息表</h2>
<table class="table table-bordered table-hover text-center">
    <thead class="table-light">
    <tr>
        <th>姓名</th><th>性别</th><th>年龄</th><th>学历</th><th>专业</th><th>电话</th>
    </tr>
    </thead>
    <tbody>
    <tr class="table-light">
        <td colspan="2">刘语熙</td><td>22</td><td colspan="2" rowspan="2">本科</td><td>13112345678</td>
    </tr>
    <tr class="table-light">
        <td rowspan="2">周欣</td><td>女</td><td>21</td><td>13312345678</td>
    </tr>
    <tr class="table-light">
        <td>男</td><td>23</td><td>本科</td><td>土木工程</td><td>18912345678</td>
    </tr>
    <tr class="table-light">
        <td>林欢欢</td><td>女</td><td>22</td><td>研究生</td><td>国际经济贸易</td><td>15112345678</td>
    </tr>
    </tbody>
</table>
<script src="jquery.js"></script>
<script src="Popper.js"></script>
<script src="bootstrap-4.2.1-dist/js/bootstrap.js"></script>
</body>
</html>