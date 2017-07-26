<!DOCTYPE html>
<html lang="en">

<head>


    <title>Success</title>

<style>
table, th, td {
    border: 1px solid black;
}

table {
	margin-left: auto;
	margin-right: auto;
	padding: 5px;
	background: #1854bc;
	color: white;
	font-family: helvetica;
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

</head>

<body>

<h2> ${reason}</h2>
<form action="DataByCompany" method="POST">
<table>
	<tr>
		<td><input type="radio" name="option" value ="a"> A </td>
		<td><input type="radio" name="option" value ="b"> B</td>
		<td><input type="radio" name="option" value ="c"> C </a></td>
		<td><input type="radio" name="option" value ="d"> D </a></td>
		<td><input type="radio" name="option" value ="e"> E </a></td>
		<td><input type="radio" name="option" value ="f"> F</a></td>
	</tr>
	<tr>
		<td><input type="radio" name="option" value ="g"> G </td>
		<td><input type="radio" name="option" value ="h"> H</td>
		<td><input type="radio" name="option" value ="i"> I </a></td>
		<td><input type="radio" name="option" value ="j"> J </a></td>
		<td><input type="radio" name="option" value ="k"> K </a></td>
		<td><input type="radio" name="option" value ="l"> L</a></td>
	</tr>
	<tr>
		<td><input type="radio" name="option" value ="m"> M </td>
		<td><input type="radio" name="option" value ="n"> N</td>
		<td><input type="radio" name="option" value ="o"> O </a></td>
		<td><input type="radio" name="option" value ="p"> P </a></td>
		<td><input type="radio" name="option" value ="q"> Q </a></td>
		<td><input type="radio" name="option" value ="r"> R</a></td>
	</tr>
	<tr>
		<td><input type="radio" name="option" value ="s"> S </td>
		<td><input type="radio" name="option" value ="t"> T</td>
		<td><input type="radio" name="option" value ="u"> U </a></td>
		<td><input type="radio" name="option" value ="v"> V </a></td>
		<td><input type="radio" name="option" value ="w"> W </a></td>
		<td><input type="radio" name="option" value ="x"> X</a></td>
	</tr>
	<tr>
		<td><input type="radio" name="option" value ="s"> Y </td>
		<td><input type="radio" name="option" value ="t"> T</td>
		<td><input type="radio" name="option" value ="u"> Z </a></td>
	</tr>
</table>


<input type="submit">
</form>



<form method="GET" action="Login">

<input type ="submit" value="Logout">

</form>


</body>
</html>