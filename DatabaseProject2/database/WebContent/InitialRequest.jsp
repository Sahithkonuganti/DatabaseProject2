<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request</title>
<center><h1>Here you can submit for an initial request</h1></center>
<center><h3>Quote: </h3></center>
<!-- request needs:  
1) tree info
2) submit multiple trees
	button to add more trees on side of page
3) in form: Size, height, distance to house, location
4) Note section for general information.
-->
</head>
<div align="center">
<form action="addTree">
	<label for="tSize">Tree size: </label><br>
	<input type="text" id="tSize" name="tSize"><br>
	
	<label for="tHeight">Tree height: </label><br>
	<input type="text" id="tHeight" name="tHeight"><br>
	
	<label for="tLocation">Tree location: </label><br>
	<input type="text" id="tLocation" name="tLocation"><br>
	
	<label for="tDistanceToHouse">What is the distance to the house? </label><br>
	<input type="text" id="tDistanceToHouse" name="tDistanceToHouse"><br><br>
	
	<input type="file" name="image1" accept="image/*"><br>
	<input type="file" name="image2" accept="image/*"><br>
	<input type="file" name="image3" accept="image/*"><br><br>
	
	<input type="submit" value="Add Tree"><br><br><br>
</form>

<form action="submitRequest">
	<label for="note">Note: </label><br>
	<textarea id="note" name="note" rows="5" cols="30"></textarea><br>
	
	<input type="submit" value="Submit">
</form>
<!--  <form action="upload.php" method="post" enctype="multipart/form-data"> -->


</div>
<body>

</body>
</html>