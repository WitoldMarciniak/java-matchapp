<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/formStyle.css"
	type="text/css">
<link rel="stylesheet" href="/MatchApp/resources/style/tableStyle.css"
	type="text/css">
<link rel="stylesheet" href="/MatchApp/resources/style/buttonStyle.css"
	type="text/css">
<title>Find match</title>
</head>
<body>
	<h2>Find match</h2>
	<div>
		<form:form action="/MatchApp/match/find" method="post">
			<label> Match name:</label>
			<input type="text" placeholder="Match name" name="name">


			<label> Discipline:</label>
			<select name="discipline">
				<option value="Football">Football</option>
				<option value="Basketball">Basketball</option>
				<option value="Volleyball">Volleyball</option>
			</select>

			<label>Match date:</label>
			<input type="text" placeholder="Match date" name="date">
			<button type="submit">Search</button>
		</form:form>
	</div>
	<h2>Results</h2>
	<table id="table">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Date</th>
				<th scope="col">Discipline</th>

				<th scope="col">Capitan</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${matches }" var="m">
				<tr>
					<td>${m.name }</td>
					<td>${m.dateTime }</td>
					<td>${m.discipline }</td>
					<td>${m.userDto.nickname }</td>
					<td><a href="/MatchApp/match/details/${m.name }">
							<button type="button" id="button">Details</button>
					</a></td>
					<td><a href="/MatchApp/score/seeScore/${m.id }">
							<button type="button" id="button">See score</button>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>