<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
 
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
   <meta charset="UTF-8">
   <title>회원정보</title>
   <style>
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
   
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >
     function backToList(obj){
	    obj.action="${contextPath}";
	    obj.submit();
     }
 
	 function fn_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("i_imageFileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_file_upload").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }
	 
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/board/rb_board/modArticle.do";
		 obj.submit();
	 }
	 
	 function fn_remove_article(url){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
		 document.body.appendChild(form);
	     form.submit();
	 } 
	 
 </script>
</head>
<body>
  <form method="get"  >
  <table  border="1">
  <tr>
   <td width=150 align="center" bgcolor=#FF9933>
      아이디
   </td>
   <td >
    <input type="text"  value="${memberInfo.member_id}"  disabled />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      이름
   </td>
   <td >
    <input type=text value="${memberInfo.name}" name="name"  disabled />
   </td>
  </tr>   
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      가입일
   </td>
   <td>
    <input type="text" name="joinDate" value="${memberInfo.joinDate}" disabled />
   </td>  
  </tr>
 <tr>
    <td width="150" align="center" bgcolor="#FF9933">
     이메일
   </td>
   <td>
    <input type="text" name="email" value="${memberInfo.email_head}@${memberInfo.email_tail}" disabled />
   </td>  
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
     생년
   </td>
   <td>
    <input type="text" name="birth_year" value="${memberInfo.birth_year}" disabled />
   </td>  
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
     생월
   </td>
   <td>
    <input type="text" name="birth_month" value="${memberInfo.birth_month}" disabled />
   </td>  
  </tr>
   <tr>
    <td width="150" align="center" bgcolor="#FF9933">
     생일
   </td>
   <td>
    <input type="text" name="birth_day" value="${memberInfo.birth_day}" disabled />
   </td>  
  </tr>
    
  <tr  id="tr_btn"    >
   <td colspan="2" align="center">
	      <input type=button value="회원정보수정하기" onClick="fn_enable(this.form)">
	       <input type=button value="회원탈퇴하기" onClick="fn_remove_article('${contextPath}/member/memberDeleteView.do')">
	    <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
   </td>
  </tr>
 </table>
 </form>
</body>
</html>