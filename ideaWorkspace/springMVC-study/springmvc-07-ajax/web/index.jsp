<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="${pageContext.request.contextPath}/statics/jsp/jQuery.js"></script>
    <script>
      function c1(){
        $.post({
          url:"${pageContext.request.contextPath}/aa",
          data:{"name":$("#username").val()},
          success:function (data){
            console.log("data=" + data);
            console.log("status=" + this.state);
            alert(data);
          },
          error:function (){}
        })
      }
    </script>
  </head>
  <body>
  <input type="text" id="username" onblur="c1()">
  </body>
</html>
