<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request</title>
<a href="clientpage.jsp"target ="_self" > Return to Client View</a><br><br> 
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
<form action="addTree" method="post">

	<label for="size">Tree size: </label><br>
	<input type="text" id="size" name="size" placeholder="5.0" onfocus="this.value=''"><br>
	
	<label for="height">Tree height: </label><br>
	<input type="text" id="height" name="height" placeholder="7.0" onfocus="this.value=''"><br>
	
	<label for="location">Tree location: </label><br>
	<input type="text" id="location" name="location" placeholder="Backyard" onfocus="this.value=''"><br>
	
	<label for="distanceFromHouse">What is the distance to the house? </label><br>
	<input type="text" id="distanceFromHouse" name="distanceFromHouse" placeholder="10.52" onfocus="this.value=''"><br><br>
	
	<input type="file" name="image1" accept="image/*" onfocus="this.value=''"><br>
	
	<input type="file" name="image2" accept="image/*" onfocus="this.value=''"><br>
	
	<input type="file" name="image3" accept="image/*" onfocus="this.value=''"><br><br>
	
	<input type="submit" value="Add Tree"><br><br><br>
</form>

<form action="submitRequest" method="post">
	<label for="note">Note: </label><br>
	<textarea id="note" name="note" rows="5" cols="30" onfocus="this.value=''"></textarea><br>
	
	<input type="submit" value="Submit">
</form>
<!--  <form action="upload.php" method="post" enctype="multipart/form-data"> -->


</div>
<body>

</body>
</html>