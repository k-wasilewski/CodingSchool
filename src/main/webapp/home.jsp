<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<br>
    <table border="1">
        <tr>
            <td>id</td>
            <td>created</td>
            <td>updated</td>
            <td>description</td>
            <td>details</td>
        </tr>
        <c:forEach items="${solutions}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.created}</td>
                <td>${s.updated}</td>
                <td>${s.description}</td>
                <td><a href="/soldetails?i=${s.id}">show</a></td>
            </tr>
        </c:forEach>
    </table>
<a href="/showallgroups">show all groups</a>
<br>
<a href="paneladmin.jsp">panel admin</a>
<br>
<div style="position: absolute; bottom: 5px;">
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
