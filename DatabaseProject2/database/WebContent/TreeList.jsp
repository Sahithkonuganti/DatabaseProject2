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
            <!-- need to update / change -->
				<th>Tree Height</th>
                <th>Tree Size</th>
                <th>Distance To House</th>
                <th>Location</th>

            </tr>
            <c:forEach var="Tree" items="${listTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${Tree.Height}" /></td>
                    <td><c:out value="${Tree.Size}" /></td>
                    <td><c:out value="${Tree.DistanceToHouse}" /></td>
                    <td><c:out value="${Tree.Location}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>