<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>

<style>
.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
}
</style>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<!-- <script>
	function fn_articleForm(isLogOn, articleForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = articleForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.")
			location.href = loginForm + '?action=/board/tb_board/tb_articleForm.do';
		}
	}
</script> -->
<body>
	<c:choose>
		<c:when test="${not empty memberInfo}">
			<button type="button"
				onclick="location.href='${contextPath}/board/tb_board/tb_articleForm.do'"
				style="position: absolute; top: 330px; right: 160px;">글쓰기</button>
		</c:when>
	</c:choose>
	
	<section id="container">
		<table class="table table-hover">
			<c:choose>
				<c:when test="${articlesList ==null }">
					<tr height="10">
						<td colspan="4">
							<p align="center">
								<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
							</p>
						</td>
					</tr>
				</c:when>
				<c:when test="${articlesList !=null }">
					<thead>
						<tr>
							<th>번호</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>좋아요</th>
						</tr>
					</thead>

					<c:forEach items="${articlesList}" var="article"
						varStatus="articleNum">
						<tr>
							<td>${articleNum.count}<br></td>
							<td><c:out value="${article.member_id }" /></td>
							<td align="left"><a
								href="${contextPath}/board/tb_board/tb_View.do?tb_number=${article.tb_number}"><c:out
										value="${article.tb_title}" /></a></td>
							<td><fmt:formatDate value="${article.write_date}"
									pattern="yyyy-MM-dd" /></td>
							<td><c:out value="${article.tb_countnum}" /></td>
							<td><c:out value="${article.tb_likenum}" /></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
</body>
</html>