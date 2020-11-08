<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고게시판</title>
</head>
<script>
	function fn_articleForm(isLogin){
	  if(isLogin== true){
	    location.href='${contextPath}/board/rb_board/rb_articleForm.do';
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href='${contextPath}/member/loginForm.do';
	  }
	}
</script> 
<body>
	<h2
		class="page-section-heading text-center text-uppercase text-secondary mb-0">신고게시판</h2>
	<!-- Icon Divider-->
	<div class="divider-custom">
		<div class="divider-custom-line"></div>
		<div class="divider-custom-icon">
			<i class="fas fa-star"></i>
		</div>
		<div class="divider-custom-line"></div>
	</div>
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
							<th>추천수</th>
						</tr>
					</thead>

					<c:forEach items="${articlesList}" var="article"
						varStatus="articleNum">
						<tr>
							<td>${articleNum.count}<br></td>
							<td><c:out value="${article.member_id }" /></td>
							<td align="left"><c:choose>
									<c:when test='${article.rb_level > 1 }'>
										<c:forEach begin="1" end="5" step="1">
											<span style="padding-left: 8px"></span>
										</c:forEach>
										<span style="font-size: 12px;">Re:</span>
										<a
											href="${contextPath}/board/rb_board/rb_View.do?rb_number=${article.rb_number}"><c:out
												value="${article.rb_title}" /></a>
									</c:when>
									<c:otherwise>
										<a
											href="${contextPath}/board/rb_board/rb_View.do?rb_number=${article.rb_number}"><c:out
												value="${article.rb_title}" /></a>
									</c:otherwise>
								</c:choose></td>
							<td><fmt:formatDate value="${article.write_date}"
									pattern="yyyy-MM-dd" /></td>
							<td><c:out value="${article.rb_countnum}" /></td>
							<td><c:out value="${article.rb_likenum}" /></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
		<button type="button" class="btn btn-info btn-xs" onclick="fn_articleForm(${isLogin})">글쓰기</button>
		<%-- <a href="${contextPath}/board/rb_board/rb_View.do?rb_number=${articlesList.rb_number}"><img  src="${contextPath}/download.do?rb_number=${articleMap.article.rb_number}&imageFileName=${item.imageFileName}"
                     id="preview" style="width: 200px;height:200px" alt="게시판이미지" /></a> --%
		<%-- <div class="search row">
			<div class="col-xs-2 col-sm-2">
				<select name="searchType" class="form-control">
					<option value="n"
						<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
					<option value="t"
						<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
					<option value="c"
						<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
					<option value="w"
						<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
					<option value="tc"
						<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
				</select>
			</div>

			<div class="col-xs-10 col-sm-10">
				<div class="input-group">
					<input type="text" name="keyword" id="keywordInput"
						value="${scri.keyword}" class="form-control" /> <span
						class="input-group-btn">
						<button id="searchBtn" type="button" class="btn btn-default">검색</button>
					</span>
				</div>
			</div>

			<script>
				$(function() {
					$('#searchBtn').click(
							function() {
								self.location = "list"
										+ '${pageMaker.makeQuery(1)}'
										+ "&searchType="
										+ $("select option:selected").val()
										+ "&keyword="
										+ encodeURIComponent($('#keywordInput')
												.val());
							});
				});
			</script>
		</div>
		<div class="col-md-offset-3">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li><a
						href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
				</c:if>

				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
					var="idx">
					<li
						<c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
						<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
					</li>
				</c:forEach>

				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li><a
						href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
				</c:if>
			</ul>
		</div> --%>
	</section>
</body>
</html>