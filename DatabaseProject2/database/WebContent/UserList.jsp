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
				<th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Adress</th>
                <th>Password</th>
                <th>Birthday</th>
                <th>cash_bal($)</th>
                <th>PPS_bal</th>

            </tr>
            <c:forEach var="trees" items="${listTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.Height}" /></td>
                    <td><c:out value="${trees.Size}" /></td>
                    <td><c:out value="${trees.DistanceToHouse}" /></td>
                    <td><c:out value= "${trees.Location}" /></td>
                    <%-- <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.birthday}" /></td>
                    <td><c:out value="${users.cash_bal}"/></td>
                    <td><c:out value="${users.PPS_bal}" /></td> --%>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>