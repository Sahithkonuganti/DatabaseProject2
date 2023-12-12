<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Respond to initial quote</title>
</head>
<a href="clientpage.jsp" target="_self">Back to Client View</a>
<body>

<div align="center">
<form action="quoteReSubmission" method="post">
	
	<label for="note"> Reason for ReSubmission of Quote: </label><br>
	<textarea id="note" name="note" rows="5" cols="30" onfocus="this.value=''"></textarea><br>
	
	

<input type="submit" value="Submit"><br><br><br>

</form>
</div>


</body>
</html>