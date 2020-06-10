<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Group ${gid} users</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>user name</td>
            <td>e -mail</td>
        </tr>
        <c:forEach items="${users}" var="u">
            <tr>
                <td>${u.userName}</td>
                <td>${u.email}</td>
            </tr>
        </c:forEach>
    </table>
<a href="/showallgroups">back</a>
</body>
</html>
