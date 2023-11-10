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
            </tr> 
        </table>
	</div>
	 <form action = "initialize">
		<input type = "submit" value = "Generate Revenue"/>
	</form>
	</div>
</body>
</html>