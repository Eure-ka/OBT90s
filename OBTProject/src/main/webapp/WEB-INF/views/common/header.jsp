<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>

</head>
<style>
header .masthead {
	position: relative;
	overflow: hidden;
}

header .container {
	position: relative;
	z-index: 2;
}

header .overlay {
	position: absolute;
	top: 0;
	left: 0;
	weight: 5%;
	height: 5%;
	opacity: 0.5;
	z-index: 1;
}
</style>
<body>
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="${contextPath}">니즈마켓</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="${contextPath}">Home</a>
					</li>
					<li class="nav-item"><c:choose>
							<c:when test="${memberInfo != null }">
								<a class="nav-link" href="${contextPath}/member/logout.do">로그아웃</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" href="${contextPath}/member/loginForm.do">로그인</a>
							</c:otherwise>
						</c:choose></li>

					<li class="nav-item"><c:choose>
							<c:when test="${memberInfo != null }">
								<a class="nav-link" href="${contextPath}/member/memberInfo.do">회원정보</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" href="${contextPath}/member/memberForm.do">회원가입</a>
							</c:otherwise>
						</c:choose></li>
				</ul>
			</div>
		</div>
	</nav>
	<header class="masthead"
		style="background-image: url('https://cdn.pixabay.com/photo/2015/11/19/08/45/banner-1050602_1280.jpg'); height: 300px;">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto">
						<c:choose>
							<c:when test="${memberInfo != null }">
								<p style="position:absolute;top:150px; right:20px; color: white">${memberInfo.name}님반갑습니다</p>
							</c:when>
						</c:choose>
				</div>
			</div>
		</div>
	</header>
</body>
</html>