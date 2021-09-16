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
				<a href="https://www.javaguides.net" class="navbar-brand"> Sample Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Samples</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${sample != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${sample == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${sample != null}">
            			Edit Sample
            		</c:if>
						<c:if test="${sample == null}">
            			Add New Sample
            		</c:if>
					</h2>
				</caption>

				<c:if test="${sample != null}">
					<input type="hidden" name="sampleId" value="<c:out value='${sample.sampleId}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Sample Barcode Number</label> <input type="text"
						value="<c:out value='${sample.barcodeId}' />" class="form-control"
						name="barcodeId" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Sample Mass</label> <input type="text"
						value="<c:out value='${sample.mass}' />" class="form-control"
						name="mass">
				</fieldset>

				<fieldset class="form-group">
					<label>Sample Ferrous PPM</label> <input type="text"
						value="<c:out value='${sample.PPM}' />" class="form-control"
						name="PPM">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Sample Color</label> <input type="text"
						value="<c:out value='${sample.color}' />" class="form-control"
						name="color">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Sample Date</label> <input type="text"
						value="<c:out value='${sample.date}' />" class="form-control"
						name="date">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Sample Time</label> <input type="text"
						value="<c:out value='${sample.time}' />" class="form-control"
						name="time">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>