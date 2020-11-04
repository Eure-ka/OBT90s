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
<div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">게시판목록 </div>
      <div class="list-group list-group-flush">
        <a href="#" class="list-group-item list-group-item-action bg-light">자유게시판</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">거래게시판</a>
        <a href="${contextPath}/board/rb_board/report_boardlist.do" class="list-group-item list-group-item-action bg-light">신고게시판</a>
      </div>
    </div>
	<%-- <h3>게시판 메뉴</h3>
	<a href="#" style="text-align: center"></a><hr>
	<a href="#" style="text-align: center"></a><hr>
	<a href="" style="text-align: center">신고게시판</a><hr> --%>
</body>
</html>