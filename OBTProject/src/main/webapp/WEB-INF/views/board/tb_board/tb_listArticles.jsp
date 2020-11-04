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
<title>거래게시판</title>
</head>
<script>
	function fn_articleForm(isLogOn, articleForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = articleForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.")
			location.href = loginForm + '?action=/board/tb_board/tb_articleForm.do';
		}
	}
</script>
<body>
	<h1>거래게시판</h1>
	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>글번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
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
				<c:forEach var="article" items="${articlesList}"
					varStatus="articleNum">
					<tr align="center">
						<td width="5%">${articleNum.count}</td>
						<td width="10%">${article.member_id }</td>
						<td align='left' width="35%"><a class='cls1'
							href="${contextPath}/board/tb_board/tb_viewArticle.do?tb_number=${article.tb_number}">${article.tb_title}</a>

						</td>
						<td width="10%"><fmt:formatDate value="${article.write_date }"
									pattern="yyyy.MM.dd HH:mm" /></td>
						<td width="10%">${article.tb_countnum}</td>
						<td width="10%">${article.tb_likenum}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<!-- <a  class="cls1"  href="#"><p class="cls2">글쓰기</p></a> -->
	<%-- <a class="cls1"	href="javascript:fn_articleForm('${isLogOn}','${contextPath}/tb_board/tb_articleForm.do', '${contextPath}/member/loginForm.do')"> --%>
	<a class="cls1"	href="${contextPath}/board/tb_board/tb_articleForm.do">
	<p class="cls2">글쓰기</p></a>
</body>
</html>