<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>Project Dashboard</title>
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
							<li class="breadcrumb-item"><a href="#">Project</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Project Dashboard</li>
						</ol>
					</nav>
					<h1 class="m-0">Project Dashboard</h1>
				</div>
				<div class="ml-auto">
					<a href="<c:url value="<%=UrlConst.PROJECT_ADD%>" />"
						class="btn btn-light"><i
						class="material-icons icon-16pt text-muted mr-1">add</i> Add New
						Project </a>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->

	<!-- START BODY -->
	<div class="container">
		<c:if test="${msg ne null }">
			<script language="javascript">
				var t = alert("${msg}");
			</script>
		</c:if>
		<div class="card card-form">

			<table class="table mb-0 thead-border-top-0">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Start date</th>
						<th>End date</th>
						<th>Owner</th>
						<c:if test="${sessionScope.UserInfo.roleId ne 3 }"><th>#</th></c:if>
						
					</tr>
				</thead>
				<tbody class="list" id="staff02">
					<c:choose>
						<c:when test="${projects == null}">
							<tr class="row">
								<td class="col-12 text-center">There is no data.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="dto" items="${projects}">
								<tr>
									<td>${dto.getName()}</td>
									<td>${dto.getDescription()}</td>
									<td><span class="badge badge-primary">${dto.start_date}</span></td>
									<td><span class="badge badge-primary">${dto.end_date}</span></td>
									<td>${dto.getOwner().getName()}</td>
									<c:if test="${sessionScope.UserInfo.roleId ne 3 }">
									<td>
									
										<a
										href="<c:url value="<%=UrlConst.PROJECT_STAFF%>" />?id=${dto.id}"
										class="text-muted"><i class="material-icons">add</i></a> <a
										href="<c:url value="<%=UrlConst.PROJECT_UPDATE%>" />?id=${dto.id}"
										class="text-muted"><i class="material-icons">settings</i></a>
										<a
										href="<c:url value="<%=UrlConst.PROJECT_DELETE%>" />?id=${dto.id}"
										class="text-muted"><i class="material-icons">delete</i></a> <a
										href="<c:url value="<%=UrlConst.TASK_DASHBOARD%>" />?id=${dto.id}"
										class="text-muted"><i class="material-icons">
												create_new_folder</i></a>
									</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- END BODY -->
</body>