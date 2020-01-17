<%--
  Created by IntelliJ IDEA.
  User: PlanE
  Date: 01.05.2019
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<center>
    <div>
        <form action="registration" method="post">
            User name : <input type="text" name="username" required="required">
            Password : <input type="password" name="password" required="required">
            <input type="submit" value="Register">
        </form>
        <form action="home.jsp" method="post">
            <input type="submit" value="Home">
        </form>
    </div>
    <div>
        <br>
        <c:out value="${registered}"/>
    </div>
</center>
</body>
</html>
