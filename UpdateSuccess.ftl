<!DOCTYPE html>
<html lang="en">

<head>


    <title>Success</title>

<style>
table, th, td {
    border: 1px solid black;
}

table.worst tr:nth-child(odd) {
	background: #db281e;
	font-family: helvetica;
	color: white;
}

table.worst tr:nth-child(even) {
	background: #dd9e80;
	color: white;
	font-family: helvetica;
}

table.best tr:nth-child(odd) {
	background: white;
	font-family: helvetica;
	color: black;
}

table.best tr:nth-child(even) {
	background: #93d888;
	color: white;
	font-family: helvetica;
}

table.common tr:nth-child(odd) {
	background: #4d70e2;
	font-family: helvetica;
	color: white;
}

table.common tr:nth-child(even) {
	background: #70b4d3;
	color: white;
	font-family: helvetica;
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
body {
	background-color:#42c5f4;
}

h1 {
	color: white;
	font-family: impact;
}

</style>

</head>

<body>

<h1> ${reason}</h1>

<form method="GET" action="DataByCompany">

<input type="submit" value="See data by company">

</form>

<div>
<table class = "worst">
<th>10 Worst Rated Companies</th>
	<#list worst as bad>
		<tr>
			<td>${bad}</td>
		</tr>
	</#list>
</table>


<div class="right">
<table class = "best">
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
<table class = "common">
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