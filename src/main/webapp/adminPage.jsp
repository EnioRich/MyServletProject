<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PlanE
  Date: 02.05.2019
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
</head>
<body>
<center>
    <div>
        <form action="adminPage" method="post">
            Change username
            <br><br>
            User id : <input type="text" name="id" required="required">
            New Username : <input type="text" name="newusername" required="required">
            <input type="submit" value="Change">
            <div>
                <br>
                <c:out value="${updates}"/>
                <br>
            </div>
        </form>
        <form action="adminPage" method="post">
            Change password
            <br><br>
            User id : <input type="text" name="id" required="required">
            New Password : <input type="text" name="newpassword" required="required">
            <input type="submit" value="Change">
        </form>
        <form action="home.jsp" method="post">
            <input type="submit" value="Home">
        </form>
        <form action="deleteUser.jsp" method="post">
            <input type="submit" value="Delete user">
        </form>
        <form action="deleteGood.jsp" method="post">
            <input type="submit" value="Delete good">
        </form>
    </div>
</center>
</body>
</html>
