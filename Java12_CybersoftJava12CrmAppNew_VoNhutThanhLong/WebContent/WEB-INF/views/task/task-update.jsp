<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>Add New Project</title>
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
								href="<c:url value="<%=UrlConst.TASK_DASHBOARD%>" />">Task</a></li>
							<li class="breadcrumb-item active" aria-current="page">Update
								Task</li>
						</ol>
					</nav>
					<h1 class="m-0">Update Task</h1>
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
					<form action="<c:url value="<%=UrlConst.TASK_UPDATE%>" />"
						method="post">
						<div class="form-group">
							<label for="name">Name:</label> <input type="text"
								class="form-control" name="name" value="${TaskToUpdate.name }"
								id="name" required> <input type="hidden" name="pid"
								value="${pId}">
								<input type="hidden" name="tid"
								value="${tid}">
						</div>
						<div class="form-group">
							<label for="name">Description:</label> <input type="text"
								value="${TaskToUpdate.description }" class="form-control"
								name="description" id="description" required>
						</div>
						<div class="form-group">
							<label for="name">Start date</label> <input type="date"
								value="${TaskToUpdate.start_date }" id="start" name="start_date"
								class="form-control mb-4" min="2000-01-01" max="2025-12-31">
							<script type="text/javascript">
								document.getElementById("start").valueAsDate = new Date();
							</script>
						</div>

						<div class="form-group">
							<label for="name">End date</label> <input type="date" id="end"
								value="${TaskToUpdate.end_date }" name="end_date"
								class="form-control mb-4" min="2000-01-01" max="2025-12-31">
							<script type="text/javascript">
								document.getElementById("end").valueAsDate = new Date();
							</script>
						</div>
						<div class="form-group">
							<select name="cboUserName">
								<c:forEach var="uDto" items="${USERLIST}">

									
									<c:if
										test="${uDto.id eq TaskToUpdate.userId}">
										<option selected="selected" value="${uDto.getId()}">${uDto.getName()}</option>
									</c:if>
									<c:if
											test="${uDto.id ne TaskToUpdate.userId}">
										<option value="${uDto.getId()}">${uDto.getName()}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<button class="btn btn-primary w-25 justify-content-center"
							type="submit" class="btn btn-primary">Update</button>
						<c:if test="${msg ne null}">
							<label style="color: red">${msg }</label>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>