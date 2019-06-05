<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MatchApp/resources/style/formStyle.css"
	type="text/css">
<title>Add score</title>
</head>
<body>
	<h2>Add score to match!</h2>
	<div>
		<form:form action="/MatchApp/score/addScore/${id }" method="post"
			modelAttribute="scoreDto">
			<label> Guests points:</label>
			<form:input path="guestScore" type="text"
				placeholder="Enter guests score" />
			<form:errors cssStyle="color:red" path="guestScore">
			</form:errors>
			<label>Hosts points:</label>
			<form:input path="hostScore" type="text"
				placeholder="Enter host score" />
			<form:errors cssStyle="color:red" path="hostScore">
			</form:errors>


			<button type="submit">Submit</button>
		</form:form>
	</div>
</body>
</html>