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
            <caption><h2>Order Information</h2></caption>
            <tr>
                <th>Quote</th>
                <th>Orders</th>
                <th>Bills</th>
            </tr>
        </table>
        
         <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Orders</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Distance From House</th>
                <th>Location</th>
            </tr>
             <c:forEach var="trees" items="${listClientTrees}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.getId()}"/></td>
                    <td><c:out value="${trees.getQuoteId()}"/></td>
                    <td><c:out value="${trees.getSize()}"/></td>
                    <td><c:out value="${trees.getHeight()}"/></td>
                    <td><c:out value="${trees.getDistanceFromHouse()}"/></td>
                    <td><c:out value="${trees.getLocation()}"/></td>
                    </tr>
            </c:forEach> 
        </table>
        <!-- request for new tree order -->
        <a href= "InitialRequest.jsp" target="_self">
       				<button>New Request</button>
       				</a>
        
        <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Quote Responses</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Quote Price</th>
                <th>Note</th>
                <th>Accept</th>
               
            </tr>
             <c:forEach var="quoteResponses" items="${listClientQuoteResponse}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteResponses.getId()}"/></td>
                    <td><c:out value="${quoteResponses.getQuoteid()}"/></td>
                    <td><c:out value="${quoteResponses.getPrice()}"/></td>
                    <td><c:out value="${quoteResponses.getNote()}"/></td>
                    <td><a href="ClientQuoteResponse.jsp">Respond</a></td>
                    
                    </tr>
            </c:forEach> 
        </table>
		
		<div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Quote Resubmission's</h2></caption>
            <tr>
                <th>ID</th>
                <th>Quote ID</th>
                <th>Note</th>
            </tr>
             <c:forEach var="quoteResubmission" items="${listClientQuoteResubmissions}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteResubmission.getId()}"/></td>
                    <td><c:out value="${quoteResubmission.getQuoteid()}"/></td>
                    <td><c:out value="${quoteResubmission.getNote()}"/></td>
                    
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
            </tr>
             <c:forEach var="quoteRejections" items="${listClientQuoteRejections}">
                <tr style="text-align:center">
                    <td><c:out value="${quoteRejections.getId()}"/></td>
                    <td><c:out value="${quoteRejections.getQuoteid()}"/></td>
                    <td><c:out value="${quoteRejections.getNote()}"/></td>
                    
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
            </tr>
             <c:forEach var="billInformation" items="${listClientBillInformation}">
                <tr style="text-align:center">
                    <td><c:out value="${billInformation.getId()}"/></td>
                    <td><c:out value="${billInformation.getBillid()}"/></td>
                    <td><c:out value="${billInformation.getBill()}"/></td>
                    <td><c:out value="${billInformation.getStatus()}"/></td>
                    <td><a href="paymentSubmission.jsp?bill=${billInformation.getBill()}">Pay</a></td>
                    </tr>
            </c:forEach> 
        </table>
        
        <%--  <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Payments</h2></caption>
            <tr>
                <th>ID</th>
                <th>Bill ID</th>
                <th>Bill</th>
                <th>Status</th>
            </tr>
             <c:forEach var="billInformation" items="${listClientBillInformation}">
                <tr style="text-align:center">
                    <td><c:out value="${billInformation.getId()}"/></td>
                    <td><c:out value="${billInformation.getBillid()}"/></td>
                    <td><c:out value="${billInformation.getBill()}"/></td>
                    <td><c:out value="${billInformation.getStatus()}"/></td>
                    </tr>
            </c:forEach> 
        </table> 
         --%>
	</div>
	</body>
</html>