<!DOCTYPE html>
<html lang="en">

<head>
<style>
table, th, td {
    border: 1px solid black;
}
</style>

    <title>Company Report</title>



</head>

<body>

<p> Company Report for ${companyName}</p>

<table>
<th>Type Of Issue</th>
<th>Total Complaints of Issue</th>
	<#list report as rp>
		<tr>
			<td>${rp.issue}</td>
			<td>${rp.total}</td>
		</tr>
	</#list>
</table>



<form method="GET" action="Login">

<input type ="submit" value="Logout">

</form>
</body>
</html>