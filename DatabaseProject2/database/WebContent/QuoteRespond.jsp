<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quote Response</title>
</head>
<a href="davidView.jsp" target = "_self">Back to David's view</a>
<center><h1>Respond to quote</h1></center>
<body>

<div align="center">
<form action="quoteResponse" method="post">
    <input type="hidden" name="id" value="${quoteResponses.getId()}" >
    <input type="hidden" name="quoteid" value="${quoteResponses.getQuoteid()}" >
    
    <label for="price">Price for Quote: </label><br>
    <input type="text" id="price" name="price" placeholder="50.00" onfocus="this.value=''"><br>

    <label for="note">Note: </label><br>
    <textarea id="note" name="note" rows="5" cols="30" onfocus="this.value=''"></textarea><br>
    

    <input type="submit" value="Submit Quote"><br><br><br>
</form>


</div>

</body>
</html>