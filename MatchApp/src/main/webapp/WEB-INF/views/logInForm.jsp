<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/formStyle.css"
	type="text/css">
<title>Log in</title>
</head>
<body>
	<h2>Log in</h2>
	<div>
		<form:form action="/MatchApp/user/logIn" method="post"
			modelAttribute="userDto">
			<label>Nickname:</label>
			<form:input path="nickname" type="text" placeholder="Enter nickname" />

			<label>Password:</label>

			<form:input path="password" type="password"
				placeholder="Enter password" />

			<button type="submit">Submit</button>

		</form:form>
	</div>
</body>
</html>