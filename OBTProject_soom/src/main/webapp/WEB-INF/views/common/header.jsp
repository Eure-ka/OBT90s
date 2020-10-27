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
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<style>
header {
	position: relative;
	background-color: green;
	height: 50px;
	min-height: 25rem;
	width: 100%;
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
	height: 100%;
	width: 100%;
	background-color: black;
	opacity: 0.5;
	z-index: 1;
}

@media ( pointer : coarse) and (hover: none) {
	header {
		background:
			url('http://sandbox.thewikies.com/vfe-generator/images/big-buck-bunny_poster.jpg')
			black no-repeat center center scroll;
	}
}
</style>
<body>


	<header>
		<div class="overlay"></div>
		<div class="container h-100">
			<div class="d-flex text-center h-100">
				<div class="my-auto w-100 text-white">
					<h1>헤더</h1>
					<div class="button" style="text-align: right">
					<button type="button" onclick="#">로그인</button>
					<button type="button" onclick="#">로그아웃</button>
					</div>
				</div>
			</div>
		</div>
		
		
		<%-- <table border=0 width="1
	00%">
			<tr>
				<td>
					<a href="#" style="color:white"><h3>로그인</h3></a>
					 <c:choose>
						<c:when test="${isLogOn == true  && member!= null}">
							<h3>환영합니다. ${member.name }님!</h3>
							<a href="${contextPath}/member/logout.do"><h3>로그아웃</h3></a>
						</c:when>
						<c:otherwise>
							<a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table> --%>
	</header>



</body>
</html>