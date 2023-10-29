<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David page</title>
</head>
<body>

<div align = "center">
	<!-- 
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form> -->
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all incoming requests of quote</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Jobs</h2></caption>
            <tr>
                <th>Quote</th>
                <th>Requests</th>
                <th>Orders of Work</th>
                <th>Bills</th>
                <!-- <th>PPS_bal</th> -->
            </tr><%-- 
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.birthday}" /></td>
                    <td><c:out value="${users.cash_bal}"/></td>
                    <td><c:out value="${users.PPS_bal}" /></td>
            </c:forEach> --%>
        </table>
	</div>
	 <form action = "initialize">
		<input type = "submit" value = "Generate Revenue"/>
	</form>
	</div>

</body>
</html>