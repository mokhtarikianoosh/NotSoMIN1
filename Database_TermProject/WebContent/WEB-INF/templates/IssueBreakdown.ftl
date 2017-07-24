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
    padding: 10px;
}
</style>

</head>

<body>

<div>

<p>Below you will find a breakdown for the following issue: ${issue} <p>
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