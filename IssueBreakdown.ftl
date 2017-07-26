<!DOCTYPE html>
<html lang="en">

<head>


    <title>Success</title>

<style>
table, th, td {
    border: 1px solid black;
}

div{
	padding: 10px;
}

.right {
    position: absolute;
    right: 0px;
    width: 300px;
}

h3 {
	padding: 10px;
	text-align: center;
	font-family: helvetica;
	color: white;
}

table {
	margin-left: auto;
	margin-right: auto;
}

table tr:nth-child(odd) {
	background: #1854bc;
	color: white;
	font-family: helvetica;
}

table tr:nth-child(even) {
	background: white;
	color: black;
	font-family: helvetica;
}

form {
	text-align: center;
}

body {
	background-color:#42c5f4;
}
</style>

</head>

<body>

<div>

<h3>Below you will find a breakdown for the following issue: ${issue} <h3>
<table>
<th>Product</th>
<th>Total number of issues related to product</th>
	<#list breakdown as break>
		<tr>
			<td>${break.product}</td>
			<td>${break.totalIssues}</td>
		</tr>
	</#list>
</table>
</div>

<form method="GET" action="Login">

<input type ="submit" value="Logout">

</form>



</body>
</html>