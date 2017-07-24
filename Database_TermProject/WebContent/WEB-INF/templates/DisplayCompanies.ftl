<!DOCTYPE html>
<html lang="en">

<head>


    <title>Success</title>



</head>

<body>

<p> ${reason}</p>
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