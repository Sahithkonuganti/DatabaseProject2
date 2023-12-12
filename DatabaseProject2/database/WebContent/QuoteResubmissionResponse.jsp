<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Respond to Client's initial Response</title>
</head>
<a href="davidView.jsp" target="_self"></a>
<body>

<div align="center">
<form action="quoteResponse" method="post">
	
	<label for="note">Response: </label><br>
	<textarea id="note" name="note" rows="5" cols="30" onfocus="this.value=''"></textarea><br>
	
	<label for="price">Price for Quote: </label><br>
    <input type="text" id="price" name="price" placeholder="50.00" onfocus="this.value=''"><br>
	
	
	<label for="email">Email of Client: </label><br>
	<textarea id="email" name="email"></textarea>

<input type="submit" value="Submit"><br><br><br>

</form>
</div>
</body>
</html>