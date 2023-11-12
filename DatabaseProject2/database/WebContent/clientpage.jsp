<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client page</title>
</head>
<!--  originally called activitypage.jsp -->
<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p>Here, you can check the information of quotes, orders, and bills</p>
		 </center>
		 
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Jobs</h2></caption>
            <tr>
                <th>Quote</th>
                <th>Orders</th>
                <th>Bills</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td>${users.id}</td>
                    <td>${users.firstName}</td>
                    <td>${users.lastName}</td>
                    <td>${users.creditCard}</td>
                    <td>${users.email}</td>
                    <td>${users.password}</td>
                    </tr>
            </c:forEach>
        </table>
       <!--  button links to InitialRequest.jsp file -->
       <a href= "InitialRequest.jsp" target="_blank">
       <button>New Request</button>
       </a>
       
       <!-- <a href= "InitialRequest.jsp" target="_blank">
       <button>Respond to initial request</button>
       </a> -->
	</div>
	</body>
</html>