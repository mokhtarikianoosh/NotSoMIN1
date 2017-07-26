<!DOCTYPE html>
<html lang="en">
<style>
h2 {
	text-align: center;
	color: white;
	padding: 10px;
	font-family: helvetica;
}

form {
	text-align: center;
}

body {
	background-color:#42c5f4;
}
</style>
<head>


    <title>Success</title>



</head>

<body>

<h2> ${reason}</h2>
<form method = "POST" action="Reporting">
	<select name = "company">
		<#list list as names>
			<option value="${names}"> ${names}</option>
		</#list>
	</select>
	
	<input type="submit" value="Get Report!">
</form>



<form method="GET" action="Login">

<input type ="submit" value="Logout">

</form>
</body>
</html>