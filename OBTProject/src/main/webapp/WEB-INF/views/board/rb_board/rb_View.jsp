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
   <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	<c:set var="replList" value="${resultMap.replList }" />
   <form name="frmArticle" method="post" action="${contextPath}/board/rb_board/rb_View.do" enctype="multipart/form-data">>

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
 
 
 <!-- 댓글 -->
	<c:set var="reply" value="${resultMap.reply}" />
	<form id="replyTable" action="${contextPath}/reply/addNewReply.do"
		method="post" onsubmit="return loginCheck()">
		<table align="center" border="0" width="45%">
			<tr>
				<th colspan="2">댓글</th>
			</tr>
			<tr>
				<td><input type="hidden" size="20" name="member_id"
					id="member_id" value="${memberInfo.member_id}"  /> <input
					type="hidden" name="article_number" value="${article.rb_number}"
					 /> <input type="hidden" name="boardtype" value="report_board"
					 /></td>
				<td colspan=2><textarea name="reply_content" rows="3" cols="65"
						maxlength="500"></textarea></td>
				<td>
					<button class="addRepl" type="submit">등록</button>
				</td>
			</tr>
		</table>
	</form>

	<script>
	/* 로그인 체크 */
	
		function loginCheck() {
			console.log("wow");
			console.log($('#member_id').val());
			if($('#member_id').val() == "" ){
				alert("로그인을 해주세요!");
				location.href = "${contextPath}/member/loginForm.do";
				return false;
			}else{
				console.log("wow else");
				return true;
			}
			 
		 }
	
		/* $(document).ready( function () {
		    $('#replyTable').DataTable();
		} );  */
	</script>


	<table align="center" border="0" width="45%">
		<c:forEach var="repl" items="${replList}" varStatus="reply_no">

			<tr class="${repl.reply_no}">
				<td>${repl.member_id }</td>
				<td align="right"><fmt:formatDate value="${repl.reply_date }"
						pattern="yyyy.MM.dd HH:mm" /></td>
			</tr>
			<tr class="${repl.reply_no}">

				<td colspan="2">${repl.reply_content }</td>
			</tr>

			<tr class="${repl.reply_no}" id="plRemove_btn">
				<td colspan="2" align="right"><c:if
						test="${memberInfo.member_id == repl.member_id }">
						<a class="delRepl" href="#none" id="${repl.reply_no}">삭제</a>
					</c:if></td>
			</tr>

		</c:forEach>
	</table>

	<script type="text/javascript">
	  
/* ---댓글 삭제 ajax */	  
	  
$(document).ready(function(){
	  $(".delRepl").click(function(e){
		  e.preventDefault();
		  console.log("wow");
		  console.log($(this).attr("id")); //32
		  var reply_no = $(this).attr("id");
	  	
		  $.ajax({
			  method: "POST",
			  url: "${contextPath}/reply/removeReply.do",
			  contentType: "application/json; charset=UTF-8",
			  data: JSON.stringify({"boardKind": "rb", "reply_no": reply_no}),
			  //dataType: "json",			  
			  success: function (res) {
				  console.log("done>>> ", res);
				  if(res=='success'){
					  $('.'+reply_no).remove();
				  }
			  },
			  error: function (request, status, error) {
				  console.log(status);
				  console.log(error);
			  }
	  		});
	});
});	

</script>
</body>
</html>