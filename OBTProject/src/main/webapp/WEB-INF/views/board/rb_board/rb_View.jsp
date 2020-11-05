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
   <title>글보기</title>
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
	    obj.action="${contextPath}/board/rb_board/rb_listarticles.do";
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
	 
	 function fn_modify_article(url,rb_number){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","rb_number");
	     articleNOInput.setAttribute("value", rb_number);
		 
	     form.appendChild(articleNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 }
	 
	 function fn_remove_article(url,rb_number){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","rb_number");
	     articleNOInput.setAttribute("value", rb_number);
		 
	     form.appendChild(articleNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 
	 }
	 
	 function fn_reply_form(url, parent_no){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var parentNOInput = document.createElement("input");
	     parentNOInput.setAttribute("type","hidden");
	     parentNOInput.setAttribute("name","parent_no");
	     parentNOInput.setAttribute("value", parent_no); 
	     form.appendChild(parentNOInput);
	     document.body.appendChild(form);
		 form.submit();
	 }
	 
	/*  function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }   */
 </script>
</head>
<body>
   <form name="frmArticle" method="post" action="${contextPath}/board/rb_board/report_boardView.do">

      <div class="row">
         <div class="col-md-3">
            <div class="form-group">
               <label for="writer">작성자</label>
               <input type="text" style="margin-right: 70px" class="form-control" name="member_id" id="writer" value="${articleMap.article.member_id}" readonly>
            </div>
         </div>
         <input type="hidden" id="id" name="member_id"
            value="${articleMap.article.member_id}">
      </div>
      <div class="form-group" style="margin-right: 70px">
         <label for="title">글제목 <input type=text name="rb_title"
            value="${articleMap.article.rb_title}" disabled />
      </div>

      <div class="form-group" style="margin-left: 70px">
         <label for="content">글내용</label>
         <input type=text class="form-control" style="width:950px; height:500px" value="${articleMap.article.rb_content}" name="rb_content"
            style="resize: none;" id="content" required="required" readonly></textarea>
      </div>

      <table border=0 align="center">
         <c:if
            test="${not empty articleMap.imageFileList && articleMap.imageFileList !='null' }">
            <c:forEach var="item" items="${articleMap.imageFileList}"
               varStatus="status">
               <tr>
                  <td width="100" align="center" bgcolor="#2dcb73" rowspan="2">
                     이미지${status.count }</td>
                  <td><input type="hidden" name="originalFileName"
                     value="${item.imageFileName }" /> <img
                     src="${contextPath}/download.do?rb_number=${articleMap.article.rb_number}&imageFileName=${item.imageFileName}"
                     id="preview" style="width: 75%" /><br></td>
               </tr>
                <tr>
             <td>
                <!-- <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   /> -->
             </td>
          </tr>
            </c:forEach>
         </c:if>
 
  <tr   id="tr_btn_modify"  align="center"  >
	   <td colspan="2"   >
	       <input type=button value="수정반영하기"   onClick="fn_modify_article(frmArticle)"  >
           <input type=button value="취소"  onClick="backToList(frmArticle)">
	   </td>   
  </tr>
    
  <tr  id="tr_btn"    >
   <td colspan="2" align="center">
       <c:if test="${memberInfo.member_id == articleMap.article.member_id}"> 
	      <input type=button value="수정하기" onClick="fn_modify_article('${contextPath}/board/rb_board/rb_articlemodForm.do',${articleMap.article.rb_number})">
	      <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/rb_board/removereport_board.do' , ${articleMap.article.rb_number})">
	    </c:if>
	    <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
	    <input type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/board/rb_board/rb_rearticleForm.do', ${articleMap.article.rb_number})">
   </td>
  </tr>
 </table>
 </form>
</body>
</html>