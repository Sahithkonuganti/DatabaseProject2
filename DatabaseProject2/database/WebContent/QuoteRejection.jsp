<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quote Rejection</title>
</head>
<a href="davidView.jsp" target = "_self">Back to David's view</a>
<center><h1>Reject Quote</h1></center>
<body>

<div align="center">
<form action="quoteRejection" method="post">
    <input type="hidden" name="id" value="${quoteResponses.getId()}" >
    <input type="hidden" name="quoteid" value="${quoteResponses.getQuoteid()}" >
    

    <label for="note">Note for rejection: </label><br>
    <textarea id="note" name="note" rows="5" cols="30" onfocus="this.value=''"></textarea><br>
    
    <label for="email">Email of Client: </label><br>
    <input type="text" id="email" name="email" onfocus="this.value=''"><br>

    <input type="submit" value="Submit Rejection"><br><br><br>
</form>


</div>

</body>
</html>