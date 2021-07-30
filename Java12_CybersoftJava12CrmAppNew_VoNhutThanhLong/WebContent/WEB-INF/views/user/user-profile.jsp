<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.crmapp.util.UrlConst" %>
<head>
<meta charset="UTF-8">
<title>Profile User</title>
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
	                       
	                        <li class="breadcrumb-item active" aria-current="page">
	                            User profile
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">User Profile</h1>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
            <div class="row no-gutters">
                <div class="col-lg-4 card-body">
                    <p><strong class="headings-color">Welcome</strong></p>
                    <p class="text-muted">${sessionScope.UserInfo.name }</p>
                </div>
                <div class="col-lg-8 card-form__body card-body">
                    <form action="<c:url value="<%=UrlConst.USER_ADD %>" />" method="post">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" name="email" id="email" disabled="disabled" value="${sessionScope.UserInfo.email }">
                        </div>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" name="name" id="name" disabled="disabled" value="${sessionScope.UserInfo.name }">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone:</label>
                            <input type="text" class="form-control" name="phone" id="phone" disabled="disabled" value="${sessionScope.UserInfo.phone }">
                        </div>
                         <div class="form-group">
                            <label for="address">Address:</label>
                           <textarea rows="4" cols="70" disabled="disabled"
								class="form-control" name="address" id="address" 
								aria-label="With textarea">${sessionScope.UserInfo.address }
							</textarea>
                        </div>
                        <div class="form-group">
                                <label for="role">Role</label>
                                <select id="role" name="role" disabled="disabled"
								data-toggle="select" class="form-control">
								<c:if test="${sessionScope.UserInfo.roleId eq 1}">
									<option selected="selected" value="1">ADMIN</option>
									<option value="2">LEADER</option>
									<option value="3">STAFF</option>
								</c:if>
								<c:if test="${sessionScope.UserInfo.roleId eq 2 }">
									<option value="1">ADMIN</option>
									<option selected="selected" value="2">LEADER</option>
									<option value="3">STAFF</option>
								</c:if>
								<c:if test="${sessionScope.UserInfo.roleId eq 3 }">
									<option value="1">ADMIN</option>
									<option value="2">LEADER</option>
									<option selected="selected" value="3">STAFF</option>
								</c:if>

							</select>
                            </div>
                       
                    </form>
                </div>
            </div>
        </div>
     </div>
</body>