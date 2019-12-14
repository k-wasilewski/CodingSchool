<%--
  Created by IntelliJ IDEA.
  User: marta
  Date: 14.12.2019
  Time: 08:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solution ${i}</title>
</head>
<body>
<table border="1">
    <tr>
        <td>id</td>
        <td>user</td>
        <td>exercise</td>
    </tr>
    <c:forEach items="${solutions}" var="s">
        <tr>
            <td>${i}</td>
            <td>${username}</td>
            <td>${exercisetitle}</td>
        </tr>
    </c:forEach>
</table>
<a href="/">back</a>
</body>
</html>
