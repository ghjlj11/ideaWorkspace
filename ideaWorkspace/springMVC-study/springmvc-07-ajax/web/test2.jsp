<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test2</title>
    <script src="${pageContext.request.contextPath}/statics/jsp/jQuery.js"></script>
    <script>
        function cli(){
            $.post("${pageContext.request.contextPath}/a2",function (data){
                var html="";
                for (let i = 0 ; i < data.length; i ++){
                    console.log(data[i]);
                    html += "<tr>" +
                        "<td>" + data[i].name + "</td>"+
                        "<td>" + data[i].age + "</td>" +
                        "<td>" + data[i].sex + "</td>" +
                        "</tr>"
                }
                $("#content").html(html);
            });
        }
    </script>
</head>
<body>
<div>
    <button type="button" style="width: 20px;height: 20px" value="加载数据" onclick="cli()"></button>
    <table>
        <thead>
        <tr>
            <td>名字</td>
            <td>年龄</td>
            <td>性别</td>
        </tr>
        </thead>
        <tbody id="content">

        </tbody>
    </table>
</div>
</body>
</html>
