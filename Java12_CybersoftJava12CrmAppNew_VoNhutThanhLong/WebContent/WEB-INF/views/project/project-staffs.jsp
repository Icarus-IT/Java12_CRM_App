<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Project Staff Dashboard</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">Project</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Project Staff Dashboard
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Project Staff Dashboard</h1>
	            </div>
	           
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	
	<!-- START BODY -->
	<div class="container">
		<div class="card card-form">
            <table class="table mb-0 thead-border-top-0">
                <thead>
                    <tr>
						<th>Name</th>
	                     <th>Email</th>
	                     <th>Role</th>
	                     <th>Phone</th>
	                     <th>#</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:when test="${users == null}">
                 			<tr class="row">
                 				<td class="col-12 text-center">There is no data.</td>
                 			</tr>
                 		</c:when>
                 		<c:otherwise>
                 			<c:forEach var="user" items="${users}" >
	                 			<tr>
		                           <td>
		                               ${user.name }
		                           </td>
		                           <td>${user.email }</td>
		                           <td><span class="badge badge-primary">STAFF</span></td>
		                           <td>${user.phone }</td>
		                           <td>
		                           <c:set  var="check" value="false" ></c:set>
		                           <c:forEach var="pudto" items="${PUList}" >
		                           	<c:if test="${pudto.userId eq user.id }">
		                           		 <c:set  var="check" value="true" ></c:set>
		                           	</c:if>
		                           </c:forEach>
		                           <c:if test="${check eq false }">
		                           		<a href="<c:url value="<%=UrlConst.PROJECT_STAFF_ADD%>" />?uid=${user.id}&pid=${pid}" class="text-muted"><i class="material-icons">add</i></a>
		                           </c:if>
		                           <c:if test="${check eq true }">
		                           		<a href="<c:url value="<%=UrlConst.PROJECT_STAFF_REMOVE%>" />?uid=${user.id}&pid=${pid}" class="text-muted"><i class="material-icons">remove</i></a>
		                           </c:if>
		                           </td>
	                    		</tr>
                 			</c:forEach>
                 		</c:otherwise>
                 	</c:choose>
               	</tbody>
            </table>
		</div>
	</div>
	<!-- END BODY -->
