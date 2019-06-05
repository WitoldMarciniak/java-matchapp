<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/buttonStyle.css"
	type="text/css">
<title>Hello!</title>
</head>
<body>

	<h2>Welcome in MATCH APP</h2>
	<a href="/MatchApp/user/details/${loggedUser.id }">
		<button type="button" id="button">My account</button>
	</a>
	<a href="/MatchApp/match/add">
		<button type="button" id="button">Add match</button>
	</a>
	<a href="/MatchApp/match/find">
		<button type="button" id="button">Find match</button>
	</a>
	<a href="/MatchApp/match/future">
		<button type="button" id="button">Future matches</button>
	</a>
	<a href="/MatchApp/match/past">
		<button type="button" id="button">Past matches</button>
	</a>
	<a href="/MatchApp/user/find">
		<button type="button" id="button">Find player</button>
	</a>
		<a href="/MatchApp/">
		<button type="button" id="button">Log out</button>
	</a>

</body>
</html>