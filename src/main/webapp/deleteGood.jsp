<%--
  Created by IntelliJ IDEA.
  User: PlanE
  Date: 07.05.2019
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>DeleteGood</title>
</head>
<body>
<center>
    <div>
        <form action="deleteGood" method="post">
            Good Id : <input type="text" name="id" required="required">
            <input type="submit" value="DeleteGood">
        </form>
        <form action="home.jsp" method="post">
            <input type="submit" value="Home">
        </form>
    </div>
    <div>
        <br>
        <c:out value= "${deleted}"/>
    </div>
</center>
</body>
</html>
