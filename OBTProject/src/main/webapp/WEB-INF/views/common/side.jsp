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
<title>사이드 메뉴</title>
</head>
<body>
	<h3>게시판 메뉴</h3>
	<a href="#" style="text-align: center">자유게시판</a><hr>
	<a href="#" style="text-align: center">거래게시판</a><hr>
	<a href="${contextPath}/board/report_boardlist.do" style="text-align: center">신고게시판</a><hr>
</body>
</html>