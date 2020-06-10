<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All exercises</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>id</td>
            <td>title</td>
            <td>description</td>
        </tr>
        <c:forEach items="${exs}" var="e">
            <tr>
                <td>${e.id}</td>
                <td>${e.title}</td>
                <td>${e.description}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/paneladmin">back</a>
</body>
</html>
