<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/formStyle.css"
	type="text/css">
<title>Register</title>
</head>
<body>
	<h2>Register</h2>
	<div>
		<form:form action="/MatchApp/user/register" method="post"
			modelAttribute="userDto">
			<label>Nickname:</label>
			<form:input path="nickname" type="text" placeholder="Enter nickname" />
			<form:errors cssStyle="color:red" path="nickname">
			</form:errors>
			<label> Password:</label>
			<form:input path="password" type="password"
				placeholder="Enter password" />
			<form:errors cssStyle="color:red" path="password"></form:errors>
			<button type="submit">Submit</button>
		</form:form>
	</div>
</body>
</html>