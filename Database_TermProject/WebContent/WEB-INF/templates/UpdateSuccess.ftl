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

<p> ${reason}</p>

<form method="GET" action="DataByCompany">

<input type="submit" value="See data by company">

</form>

<div>
<table>
<th>10 Worst Rated Companies</th>
	<#list worst as bad>
		<tr>
			<td>${bad}</td>
		</tr>
	</#list>
</table>


<div class="right">
<table>
<th>10 Best Rated Companies</th>
	<#list best as good>
		<tr>
			<td>${good}</td>
		</tr>
	</#list>
</table>
</div>


</div>



<div>
<form method="GET" action="DisplayBreakdown">
<table>
<th>25 Most Common Issues</th>
	<#list issues as issue>
		<tr>
			<td>${issue}</td>
			<td><input type ="radio" value ="${issue}" name="getIssue"></td>
		</tr>
	</#list>
</table>
<input type="submit" value="Submit">
</form>
</div>

<form method="GET" action="Login">

<input type ="submit" value="Logout">

</form>



</body>
</html>