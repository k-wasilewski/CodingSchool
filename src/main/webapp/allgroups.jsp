<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All groups</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>id</td>
            <td>name</td>
        </tr>
        <c:forEach items="${arrallgroups}" var="g">
            <tr>
                <td>${g.id}</td>
                <td>${g.name}</td>
                <td><a href="/groupusers?i=${g.id}">show all users</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">back</a>
</body>
</html>
