<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
  </style>
  <meta charset="UTF-8">
  <title>신고게시판</title>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<!-- <script>
	function fn_articleForm(isLogOn,articleForm,loginForm){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=articleForm;
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href=loginForm+'?action=/board/articleForm.do';
	  }
	}
</script> -->
<body>
<h1 style="text-align: center">신고게시판입니다</h1>

<table align="center" border="0"  width="80%"  >
  <tr height="10" align="center"  bgcolor="lightgreen">
     <td >글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
     <td>조회수</td>
     <td>좋아요</td>
  </tr>
<c:choose>
  <c:when test="${articlesList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesList !=null }" >
    <c:forEach  var="article" items="${articlesList}" >
     <tr align="center">
	<td >${article.rb_number}</td>
	<td >${article.member_id }</td>
	<td >${article.rb_title}</td>
	<!-- 답글다는 부분 나중에 다시 확인 -->
	<%-- <td align='left'  width="35%">
	  <span style="padding-right:30px"></span>
	   <c:choose>
	      <c:when test='${article.rb_number > 1 }'>  
	         <c:forEach begin="1" end="${article.level }" step="1">
	              <span style="padding-left:20px"></span>    
	         </c:forEach>
	         <span style="font-size:12px;">[답변]</span>
                   <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.rb_number}">${article.rb_title}</a>
	          </c:when>
	          <c:otherwise>
	            <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.rb_number}">${article.rb_title }</a>
	          </c:otherwise>
	        </c:choose>
	  </td> --%>
	  <td >${article.write_date}</td> 
	  <td>${article.rb_countnum}</td>
	  <td>${article.rb_likenum}</td>
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>
<a  href="${contextPath}/board/addNewreport_board.do">글쓰기</p></a>
</body>
</html>