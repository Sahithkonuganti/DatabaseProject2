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
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Credit card</th>
                <th>Email</th>
                <th>Password</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.id}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.creditCard}" /></td>
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.password}" /></td>
            </c:forEach>
        </table>
	</div>
	
	 <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Easy Clients</h2></caption>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Credit card</th>
                <th>Email</th>
               
            </tr>
             <c:forEach var="users" items="${listEasyClients}">
                <tr style="text-align:center">
                    <td><c:out value="${users.id}"/></td>
                    <td><c:out value="${users.firstName}"/></td>
                    <td><c:out value="${users.lastName}"/></td>
                    <td><c:out value="${users.creditCard}"/></td>
                    <td><c:out value="${users.email} "/></td>
                    
                    </tr>
            </c:forEach> 
        </table>
	</div>

	 <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>One Tree Quotes</h2></caption>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Credit card</th>
                <th>Email</th>
               
            </tr>
             <c:forEach var="users" items="${listOneTreeQuotes}">
                <tr style="text-align:center">
                    <td><c:out value="${users.id}"/></td>
                    <td><c:out value="${users.firstName}"/></td>
                    <td><c:out value="${users.lastName}"/></td>
                    <td><c:out value="${users.creditCard}"/></td>
                    <td><c:out value="${users.email} "/></td>
                    
                    </tr>
            </c:forEach> 
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Highest Tree</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Distance</th>
                <th>Location</th>
               	<th>Email</th>
            </tr>
             <c:forEach var="trees" items="${listHighestTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.getId()}"/></td>
                    <td><c:out value="${trees.getQuoteId()}"/></td>
                    <td><c:out value="${trees.getSize()}"/></td>
                    <td><c:out value="${trees.getHeight()}"/></td>
                    <td><c:out value="${trees.getDistanceFromHouse()}"/></td>
                    <td><c:out value="${trees.getLocation()}"/></td>
                    <td><c:out value="${trees.getEmail()}"/></td>
                    
                    </tr>
            </c:forEach> 
        </table>
	</div>

</body>
</html>