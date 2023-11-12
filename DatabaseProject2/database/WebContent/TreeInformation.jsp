<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Tree Information</h2></caption>
            <tr>
                <th>Id</th>
                <th>Quote Id</th>
                <th>Height</th>
                <th>Size</th>
                <th>Distance To House</th>
                <th>Location</th>
            </tr>
            <c:forEach var="trees" items="${listTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.ID}" /></td>
                    <td><c:out value="${trees.quoteID}" /></td>
                    <td><c:out value="${trees.Height}" /></td>
                    <td><c:out value="${trees.Size}" /></td>
                    <td><c:out value="${trees.DistanceToHouse}" /></td>
                    <td><c:out value="${trees.Location}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>