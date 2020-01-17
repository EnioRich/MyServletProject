<%--
  Created by IntelliJ IDEA.
  User: PlanE
  Date: 04.05.2019
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Goods Page</title>
</head>
<body>
<div class="w3-container w3-left-align">
    <table border='2' width='50%'>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Buy</th>
        </tr>
        <c:forEach items="${goods}" var="good">
            <tr>
                <td><c:out value="${good.getId()}"/></td>
                <td><c:out value="${good.getName()}"/></td>
                <td><c:out value="${good.getDescription()}"/></td>
                <td><c:out value="${good.getPrice()}"/></td>
                <td><a href='buy?id=${good.getId()}'>Buy</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
