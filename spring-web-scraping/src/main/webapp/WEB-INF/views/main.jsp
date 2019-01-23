<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Web Scraping</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h3 id="form_header" class="text-warning" align="center">Web
			Scraping</h3>
		<div></div>
		<c:url var="getAuthors" value="/webscraping/getAuthors" />
		<form:form id="all_form" modelAttribute="attr1" method="GET"
			action="${getAuthors}">
			<table border='0' width='480px' cellpadding='0' cellspacing='0'
				align='center'>
				<tr>
					<td><label>Click to get all authors: </label></td>
					<td align='center'>
						<button id="fetchAuthorsButton" type="submit"
							class="btn btn-primary">Click</button>
					</td>
				</tr>
			</table>
		</form:form>
		<c:url var="searchAuthors" value="/webscraping/searchAuthors" />
		<form:form id="author_form" modelAttribute="attr2" method="POST"
			action="${searchAuthors}">
			<table border='0' width='480px' cellpadding='0' cellspacing='0'
				align='center'>
				<tr>
					<td><label>Search Based on Author's name: </label></td>
					<td><form:input id="author_name" path="author" /></td>
					<td align='center'>
						<button id="searchAuthorButton" type="submit"
							class="btn btn-primary">Search Author</button>
					</td>
				</tr>
			</table>
		</form:form>
		<c:url var="searchTitle" value="/webscraping/searchTitle" />
		<form:form id="title_form" modelAttribute="attr3" method="POST"
			action="${searchTitle}">
			<table border='0' width='480px' cellpadding='0' cellspacing='0'
				align='center'>
				<tr>
					<td><label>Search Based on Title: </label></td>
					<td><form:input id="title" path="title" /></td>
					<td align='center'>
						<button id="searchTitleButton" type="submit"
							class="btn btn-primary">Search Title</button>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
