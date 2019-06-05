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
<title>Details</title>
</head>
<body>
	<h2>Guest players:</h2>
	<table id="table">
		<thead>
			<tr>
				<th scope="col">Nickname</th>
				<th scope="col">Player details</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${guestPlayers }" var="p">
				<tr>
					<td>${p.userDto.nickname }</td>
					<td><a href="/MatchApp/user/details/${p.userDto.id }">
							<button type="button" id="button">Check</button>
					</a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/MatchApp/match/join/${matchName }/guest">
		<button type="button" id="button">Join</button>
	</a>
	<a href="/MatchApp/match/remove/${matchName }/${userId}">
		<button type="button" id="button">Remove</button>
	</a>
	<br>
	<h2>Host players:</h2>
	<table id="table">
		<thead>
			<tr>
				<th scope="col">Nickname</th>
				<th scope="col">Player details</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${hostPlayers }" var="p">
				<tr>
					<td>${p.userDto.nickname }</td>
					<td><a href="/MatchApp/user/details/${p.userDto.id }">
							<button type="button" id="button">Check</button>
					</a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/MatchApp/match/join/${matchName }/host">
		<button type="button" id="button">Join</button>
	</a>

	<a href="/MatchApp/match/remove/${matchName }/${userId}">
		<button type="button" id="button">Remove</button>
	</a>
</body>
</html>