<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
	<head>
		<title>회원탈퇴</title>
	</head>
	<!-- <script type="text/javascript">
		$(document).ready(function(){
			// 취소
			$(".cencle").on("click", function(){
				
				location.href = "${contextPath}/member/memberInfo.do";
						    
			});
	</script> -->
	<body>
		<section id="container">
			<form action="${contextPath}/member/removeMember.do" method="post">
				<h1>정말로 탈퇴하실건가요??? ㅠㅠㅠㅠㅠ</h1>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="submit" id="submit">회원탈퇴</button>
					<!-- <button class="cencle" type="button">취소</button> -->
				</div>
			</form>
			<div>
			</div>
		</section>
		
	</body>
	
</html>