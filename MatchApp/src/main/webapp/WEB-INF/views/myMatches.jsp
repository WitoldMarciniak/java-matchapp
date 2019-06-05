<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/tableStyle.css"
	type="text/css">
<link rel="stylesheet" href="/MatchApp/resources/style/buttonStyle.css"
	type="text/css">
<title>My matches</title>
</head>
<body>

	<h2>My matches:</h2>
	<table id="table">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Date</th>
				<th scope="col">Discipline</th>
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