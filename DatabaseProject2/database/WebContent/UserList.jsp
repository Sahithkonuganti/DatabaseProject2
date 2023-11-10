<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All User list</title>
</head>
<body>
   <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of People</h2></caption>
            <tr>
				<th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Credit Card</th>
                <th>Email</th>
                <th>Password</th>
            </tr>
            <c:forEach var="users" items="${listUsers}">
                <tr style="text-align:center">
                    <td><c:out value="${users.id}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.creditCard}" /></td>
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.password}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>