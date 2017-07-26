<!DOCTYPE html>
<html lang="en">

<head>
<style>
table, th, td {
    border: 1px solid black;
}

table {
	font-family: helvetica;
	color: white;
	background: #1854bc;
	margin-left: auto;
	margin-right: auto;
}

form {
	text-align: center;
	padding: 5px;
}

h2 {
	text-align: center;
	padding: 20px;
	color: white;
	font-family: helvetica;
}

body {
	background-color:#42c5f4;
}
</style>

    <title>Company Report</title>



</head>

<body>

<h2> Company Report for ${companyName}</h2>

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