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
<title>Score</title>
</head>
<body>

	<table id="table">
		<thead>
			<tr>

				<th scope="col">Guest score</th>
				<th scope="col">Host score</th>

			</tr>
		</thead>
		<tbody>
			<c:if test="${score }!=null"></c:if>
			<tr>

				<td>${score.guestScore }</td>
				<td>${score.hostScore  }</td>

			</tr>

		</tbody>
	</table>


	<a href="/MatchApp/score/addScore/${id }">
		<button type="button" id="button">Add score</button>
	</a>
</body>
</html>