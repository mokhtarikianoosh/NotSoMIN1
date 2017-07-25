<!DOCTYPE html>
<html lang="en">

<head>


    <title>Success</title>

<style>
table, th, td {
    border: 1px solid black;
}
</style>

</head>

<body>


<form method="POST" action="DataCompanyByState">
	<table>
		<th>View company complaints by state</th>
			<tr>
				<td>
					<select name ="company">
						<#list companies as company>
							<option value="${company}">${company}</option>
						</#list>
					</select>
		  		</td>
		 	</tr>
	</table>
	
<input type= "hidden" value="${state}" name ="state">
<input type ="submit" value="Get info by State">
</form>


<form method="GET" action="Login">

<input type ="submit" value="Logout">

</form>


</body>
</html>