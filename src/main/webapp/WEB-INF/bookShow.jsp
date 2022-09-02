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
<title>${user.name}</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<a href="/books" class="nav-link mb-3">Back to the Bookshelf!</a>
			<main class="col-8 px-4 py-3 border border-1 border-pirmary rounded bg-light">
				<div class="text-end">
					<c:if test="${book.user == user}"><a href="/books/${book.id}/edit" class="nav-link">Edit</a></c:if>
				</div>
				<div>
					<h1 class="display-5 mb-4"><c:out value="${book.title}" /></h1>
					<h4 class="mb-3"><span class="text-danger"><c:out value="${book.user.getName()}" /></span> read <span class="text-primary"><c:out value="${book.title}" /></span> by <span class="text-success"><c:out value="${book.author}" /></span>.</h4>
					<h5 class="mb-2">Here are <c:out value="${book.user.getName()}" />'s thoughts:</h5>
					<hr />
					<p><c:out value="${book.myThoughts}" /></p>
					
				</div>
				<div class= "text-end">
					<c:if test="${book.user == user }"><a href="/books/${book.id}/delete" class="nav-link">Delete</a></c:if>
				</div>
			</main>
		</div>
	</div>
</body>
</html>