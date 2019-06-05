<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/formStyle.css"
	type="text/css">
<link rel="stylesheet" href="/MatchApp/resources/style/tableStyle.css"
	type="text/css">
<title>Find player</title>
</head>
<body>
	<h2>Find player by nickname</h2>
	<div>
		<form:form action="/MatchApp/user/find" method="post">
			<label>Player name:</label>
			<input type="text" placeholder="Enter name" name="name">
			<button type="submit">Search</button>
		</form:form>
	</div>
	<h2>Results</h2>
	<table id="table">
		<thead>
			<tr>
				<th scope="col">Nickname</th>
				<th scope="col">Points</th>

			</tr>
		</thead>
		<tbody>

			<tr>
				<td>${foundUser.nickname }</td>
				<td>${foundUser.points }</td>

			</tr>
		</tbody>
	</table>
</body>
</html>