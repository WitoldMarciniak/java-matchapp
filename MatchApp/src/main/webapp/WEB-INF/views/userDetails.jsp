<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/tableStyle.css"
	type="text/css">
<title>Player details</title>
</head>
<body>
	<h2>Details</h2>
	<table id="table">
		<thead>
			<tr>
				<th scope="col">Nickname</th>
				<th scope="col">Points</th>

			</tr>
		</thead>
		<tbody>

			<tr>
				<td>${userDto.nickname }</td>
				<td>${userDto.points }</td>
			</tr>

		</tbody>
	</table>

</body>
</html>