<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/formStyle.css"
	type="text/css">
<title>Add match</title>
</head>
<body>
	<h2>Add match</h2>
	<div>
		<form:form action="/MatchApp/match/add" method="post"
			modelAttribute="matchDto">
			<label>Match name:</label>
			<form:input path="name" type="text" placeholder="Enter match name" />
			<form:errors cssStyle="color:red" path="name">
			</form:errors>
			<label>Discipline:</label>
			<form:select path="discipline" type="text">
				<form:option value="Football">Football</form:option>
				<form:option value="Basketball">Basketball</form:option>
				<form:option value="Volleyball">Volleyball</form:option>
			</form:select>
			<button type="submit">Submit</button>
		</form:form>
	</div>
	<div>
		<form:form action="/MatchApp/match/setDate" method="post">
			<label> Date and time</label>
			<input name="dateTime" type="text" placeholder="yyyy-mm-dd hh:mm" />
			<button type="submit">Submit</button>
		</form:form>
	</div>
</body>
</html>