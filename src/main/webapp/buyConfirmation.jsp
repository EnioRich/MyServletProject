<%--
  Created by IntelliJ IDEA.
  User: PlanE
  Date: 05.05.2019
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Buy Confirmation</title>
</head>
<body>
<center>
    <h3>Enter your code</h3>
    <form action="/buy" method="post">
        <input hidden type= "text" name ="goodId" value="<c:out value= "${goodId}"/>"/>
        <input type="password" title="confirmation code" name="code"/>
        <input type="submit" value="Enter"/>
    </form>
</center>
</body>
</html>
