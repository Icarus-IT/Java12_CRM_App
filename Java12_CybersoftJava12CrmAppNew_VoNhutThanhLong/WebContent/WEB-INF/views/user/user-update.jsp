<%@page import="cybersoft.java12.crmapp.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>Update User</title>
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
								href="<c:url value="<%=UrlConst.USER_DASHBOARD%>" />">User</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Update User</li>
						</ol>
					</nav>
					<h1 class="m-0">Update User</h1>
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
					<form action="<c:url value="<%=UrlConst.USER_UPDATE%>" />"
						method="post">
						<input type="hidden" name="id" value="${UserToUpdate.id}">
						<div class="form-group">
							<label for="email">Email:</label> <input disabled="email" required
								value="${UserToUpdate.email}" class="form-control" name="email"
								id="email">
						</div>
						
						<div class="form-group">
							<label for="name">Name:</label> <input type="text"
								value="${UserToUpdate.name}" class="form-control" name="name"
								id="name" required>
						</div>
						<div class="form-group">
							<label for="phone">Phone:</label> <input type="text"
								value="${UserToUpdate.phone}" class="form-control" name="phone"
								id="phone" required>
						</div>
						<div class="form-group">
							<label for="address">Address:</label>
							<textarea rows="4" cols="70"
								class="form-control" name="address" id="address"
								aria-label="With textarea">${UserToUpdate.address.trim()}
							</textarea>
						</div>
						<div class="form-group">
							<label for="role">Role</label> <select id="role" name="role"
								data-toggle="select" class="form-control">
								<c:if test="${UserToUpdate.roleId eq 1}">
									<option selected="selected" value="1">ADMIN</option>
									<option value="2">LEADER</option>
									<option value="3">STAFF</option>
								</c:if>
								<c:if test="${UserToUpdate.roleId eq 2 }">
									<option value="1">ADMIN</option>
									<option selected="selected" value="2">LEADER</option>
									<option value="3">STAFF</option>
								</c:if>
								<c:if test="${UserToUpdate.roleId eq 3 }">
									<option value="1">ADMIN</option>
									<option value="2">LEADER</option>
									<option selected="selected" value="3">STAFF</option>
								</c:if>

							</select>

						</div>
						<button class="btn btn-primary w-25 justify-content-center"
							type="submit" class="btn btn-primary">Update</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>