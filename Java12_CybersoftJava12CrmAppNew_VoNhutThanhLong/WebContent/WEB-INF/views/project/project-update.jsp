<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>Update Project</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD%>" />">Project</a></li>
							<li class="breadcrumb-item active" aria-current="page">Update Project</li>
						</ol>
					</nav>
					<h1 class="m-0">Update Project</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
			<div class="row no-gutters">
				<div class="col-lg-4 card-body">
					<p>
						<strong class="headings-color">Rules</strong>
					</p>
					<p class="text-muted">There is no rule!</p>
				</div>
				<div class="col-lg-8 card-form__body card-body">
					<form action="<c:url value="<%=UrlConst.PROJECT_UPDATE%>" />"
						method="post">
						<div class="form-group">
							<label for="name">Name:</label> 
							<input type="hidden" name="id" value="${ProjectToUpdate.id }">
							<input type="text" class="form-control" name="name" id="name" value="${ProjectToUpdate.name }" required>
						</div>
						<div class="form-group">
							<label for="name">Description:</label> 
							<input type="text" class="form-control" name="description" id="description" value="${ProjectToUpdate.description }" required>
							
						</div>
						<div class="form-group">
							<label for="name">Start date</label> 
							<input type="date" id="start" name="start_date" value="${ProjectToUpdate.start_date }"
								class="form-control mb-4" min="2000-01-01" max="2025-12-31">
							
						</div>

						<div class="form-group">
							<label for="name">End date</label> 
							<input type="date" id="end" name="end_date" value="${ProjectToUpdate.end_date }"
								class="form-control mb-4" min="2000-01-01" max="2025-12-31">
							
						</div>
						<div class="form-group">
							<label for="role">Owner</label> 
							<input type="number" class="form-control" name="owner" value="${ProjectToUpdate.owner }" id="owner" required>
						</div>
						<button class="btn btn-primary w-25 justify-content-center"
							type="submit" class="btn btn-primary">Update</button>
							<c:if test="${msg ne null}"><label style="color: red">${msg }</label></c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>