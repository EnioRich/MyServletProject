<%--
  Created by IntelliJ IDEA.
  User: PlanE
  Date: 29.04.2019
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

Current Users:
<br><br>

<c:forEach var="user" items="${userList}">
    <tr>
        <td><c:out value="${user.getUsername()}" /></td>
        <br><br>
    </tr>
</c:forEach>
</body>
</html>