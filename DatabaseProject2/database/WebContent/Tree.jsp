<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Location</th>
                <th>Distance To House</th>
            </tr>
             <c:forEach var="Tree" items="${get_Tree}">
                <tr style="text-align:center">
                    <td><c:out value="${Tree.id}" /></td>
                    <td><c:out value="${Tree.quoteId}" /></td>
                    <td><c:out value="${Tree.size}" /></td>
                    <td><c:out value="${Tree.height}" /></td>
                    <td><c:out value="${Tree.location}" /></td>
                    <td><c:out value="${Tree.distance}" /></td>
                 </tr>
            </c:forEach>
          </table>
	</div>
<body>

</body>
</html>