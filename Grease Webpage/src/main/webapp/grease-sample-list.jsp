<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Sample Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Sample
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Samples</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Samples</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Sample</a>
				<a href="<%=request.getContextPath()%>/clear" class="btn btn-success"> 
					Clear Table</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Sample ID</th>
						<th>Barcode ID</th>
						<th>Mass</th>
						<th>PPM</th>
						<th>Color</th>
						<th>Date</th>
						<th>Time</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="sample" items="${listSample}">

						<tr>
							<td><c:out value="${sample.sampleId}" /></td>
							<td><c:out value="${sample.barcodeId}" /></td>
							<td><c:out value="${sample.mass}" /></td>
							<td><c:out value="${sample.PPM}" /></td>
							<td><c:out value="${sample.color}" /></td>
							<td><c:out value="${sample.date}" /></td>
							<td><c:out value="${sample.time}" /></td>
							<td><a href="edit?sampleId=<c:out value='${sample.sampleId}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?sampleId=<c:out value='${sample.sampleId}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>