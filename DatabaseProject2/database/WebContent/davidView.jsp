<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David's page</title>
</head>
<body>

<div align = "center">
	<a href="login.jsp"target ="_self" >logout</a><br><br> 

<h1>List all incoming requests of quote</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Jobs</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>distance From House</th>
                <th>Location</th>
                <th>User Email</th>
            </tr>
             <c:forEach var="trees" items="${listTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.getId()}"/></td>
                    <td><c:out value="${trees.getQuoteId()}"/></td>
                    <td><c:out value="${trees.getSize()}"/></td>
                    <td><c:out value="${trees.getHeight()}"/></td>
                    <td><c:out value="${trees.getDistanceFromHouse()}"/></td>
                    <td><c:out value="${trees.getLocation()}"/></td>
                    <td><c:out value="${trees.getEmail()}"/></td>
                    <td><a href="QuoteRespond.jsp" target="_self">Respond</a></td>
                    <td><a href="QuoteRejection.jsp" target = "_self">Reject</a></td>
                    </tr>
            </c:forEach> 
        </table>
        <table>
        <table border="1" cellpadding="6">
        <caption><h2>Quotes</h2></caption>
            <tr>
                <th>ID</th>
                <th>Contractor ID</th>
                <th>Price</th> 
                <th>Schedule Start</th>
                <th>Schedule End</th>
            </tr>
             <c:forEach var="quotes" items="${listQuotes}">
                <tr style="text-align:center">
                    <td><c:out value="${quotes.getId()}"/></td>
                    <td><c:out value="${quotes.getContractorID()}"/></td>
                    <td><c:out value="${quotes.getPrice()}"/></td>
                    <td><c:out value="${quotes.getScheduleStart()}"/></td>
                    <td><c:out value="${quotes.getScheduleEnd()}"/></td>
                    </tr>
            </c:forEach> 
        </table>
        
        
        <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Quote Responses</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Quote Price</th>
                <th>Note</th>
                <th>Email</th>
            </tr>
            
            <c:forEach var="quoteResponses" items="${listResponses}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteResponses.getId()}"/></td>
                    <td><c:out value="${quoteResponses.getQuoteid()}"/></td>
                    <td><c:out value="${quoteResponses.getPrice()}"/></td>
                    <td><c:out value="${quoteResponses.getNote()}"/></td>
                    <td><c:out value="${quoteResponses.getEmail()}"/></td>
                    <td><a href=cut?id="${trees.getId}">Cut Tree</a></td>
                    </tr>
            </c:forEach> 
            </table>
            
            
            <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Quote Re-Submission</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Note</th>
                <th>Email</th>
                <th>Respond</th>
            </tr>
            <c:forEach var="quoteResubmission" items="${listResubmission}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteResubmission.getId()}"/></td>
                    <td><c:out value="${quoteResubmission.getQuoteid()}"/></td>
                    <td><c:out value="${quoteResubmission.getNote()}"/></td>
                    <td><c:out value="${quoteResubmission.getEmail()}"/></td>
                    <td><a href="QuoteResubmissionResponse.jsp">Respond</a></td>
                    </tr>
            </c:forEach> 
            </table>
            
          <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Quote Rejections</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Note</th>
                <th>Email</th>
            </tr>
            <c:forEach var="quoteRejections" items="${listRejections}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteRejections.getId()}"/></td>
                    <td><c:out value="${quoteRejections.getQuoteid()}"/></td>
                    <td><c:out value="${quoteRejections.getNote()}"/></td>
                    <td><c:out value="${quoteRejections.getEmail()}"/></td>
                    </tr>
            </c:forEach> 
            </table> 
        
        <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Bill Information</h2></caption>
            <tr>
                <th>ID</th>
                <th>Bill ID</th>
                <th>Bill</th>
                <th>Status</th>
                <th>Email</th>
            </tr>
            <c:forEach var="billInformation" items="${listBill}">
                <tr style="text-align:center">
                    <td><c:out value="${billInformation.getId()}"/></td>
                    <td><c:out value="${billInformation.getBillid()}"/></td>
                    <td><c:out value="${billInformation.getBill()}"/></td>
                    <td><c:out value="${billInformation.getStatus()}"/></td>
                    <td><c:out value="${billInformation.getEmail()}"/></td>
                    </tr>
            </c:forEach> 
            </table>  
            
           <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Revenue Information</h2></caption>
            <tr>
                <th>ID</th>
                <th>Bill ID</th>
                <th>Payment</th>
                <th>Time Paid</th>
                <th>Email</th>
            </tr>
            <c:forEach var="revenueInformation" items="${listRevenue}">
                <tr style="text-align:center">
                    <td><c:out value="${revenueInformation.getId()}"/></td>
                    <td><c:out value="${revenueInformation.getBillid()}"/></td>
                    <td><c:out value="${revenueInformation.getPayment()}"/></td>
                    <td><c:out value="${revenueInformation.getTimepaid()}"/></td>
                    <td><c:out value="${revenueInformation.getEmail()}"/></td>
                    </tr>
            </c:forEach> 
            </table>   
        
        
	</div>
	 <form action = "initialize">
		<input type = "submit" value = "Generate Revenue"/>
	</form>
	
	</div>
</body>
</html>