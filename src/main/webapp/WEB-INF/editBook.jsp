<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Edit ${book.title}</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<h1 class="display-4">Edit Your Book!</h1>
			<a href="/books" class="nav-link mb-3">Back to the Bookshelf!</a>
			<form:form action="/books/${book.id}/update" modelAttribute="book" method="POST" class="col-5 mt-4 p-3">
				<input type="hidden" name="_method" value="PUT" />
				<div>
					<form:errors path="*" class="text-danger"/>
				</div>
				<div class="mb-3">
					<form:label path="title" class="form-label">Title:</form:label>
					<form:input  path="title" class="form-control" />
				</div>
				<div class="mb-3">
					<form:label path="author" class="form-label">Author:</form:label>
					<form:input path="author" class="form-control" />
				</div>
				<div class="mb-3">
					<form:label path="myThoughts" class="form-label">My Thoughts:</form:label>
					<form:textarea path="myThoughts" class="form-control"></form:textarea>
				</div>
				<input type="submit" value="Update Book" class="btn btn-sm btn-primary" />
			</form:form>
		</div>
	</div>
</body>
</html>