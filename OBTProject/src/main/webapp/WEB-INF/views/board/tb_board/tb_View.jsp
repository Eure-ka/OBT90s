<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
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
     
   .addRepl {
	border-style: none;
	width: 50px;
	height: 97px;
	border-radius: 5px;
	border-collapse: collapse;
	margin-bottom: 5px;
}

#replyTable {
	margin-left: 2.5em;
	margin-right: 2em;
}

#replyArea {
	border-collapse: collapse;
	padding: -1px;
}

.delRepl {
	font-size: 15px;
}

#date {
	font-size: 15px;
	color: lightgrey;
}

#reListTable {
	font-size: 18px;
	color: darkslategrey;
	margin-left: 2em;
	margin-right: 2em;
}

#letter_reply {
	font-size: 20px;
}

textarea {
	width: 900px;
	padding: 10px;
	box-sizing: border-box;
	border: solid 2px #d2d2d2;
	border-radius: 5px;
	font-size: 16px;
	resize: both;;
	border-collapse: collapse;
}
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script type="text/javascript" >
     function backToList(obj){
	    obj.action="${contextPath}/board/tb_board/tb_listArticles.do";
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
	 
	 function fn_modify_article(url,tb_number){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","tb_number");
	     articleNOInput.setAttribute("value", tb_number);
		 
	     form.appendChild(articleNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 }
	 
	 function fn_remove_article(url,tb_number){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","tb_number");
	     articleNOInput.setAttribute("value", tb_number);
		 
	     form.appendChild(articleNOInput);
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
	<c:set var="article" value="${resultMap.transBoard }" />
	<c:set var="replList" value="${resultMap.replList }" />
	<c:set var="imageFileList" value="${resultMap.imageFileList }" />
   <form name="frmArticle" method="post" action="${contextPath}/board/tb_board/tb_View.do" >

      <div class="row">
         <div class="col-md-3">
            <div class="form-group">
               <label for="writer">작성자</label>
               <input type="text" style="margin-right: 70px" class="form-control" name="member_id" id="writer" value="${article.member_id}" readonly>
            </div>
         </div>
         <input type="hidden" id="id" name="member_id"
            value="${article.member_id}">
      </div>
      <div class="form-group" style="margin-right: 70px">
         <label for="title">글제목 <input type=text name="tb_title"
            value="${article.tb_title}" disabled />
      </div>

      <div class="form-group" style="margin-left: 70px">
         <label for="content">글내용</label>
         <input type=text class="form-control" style="width:950px; height:500px" value="${article.tb_content}" name="tb_content"
            style="resize: none;" id="content" required="required" readonly></textarea>
      </div>

      <table border=0 align="center">
         <c:if
            test="${not empty imageFileList}">
            <c:forEach var="item" items="${imageFileList}"
               varStatus="status">
               <tr>
                  <td width="100" align="center" bgcolor="#2dcb73" rowspan="2">
                     이미지${status.count }</td>
                  <td><input type="hidden" name="originalFileName"
                     value="${item.imageFileName }" /> <img
                     src="${contextPath}/download.do?tb_number=${article.tb_number}&imageFileName=${item.imageFileName}"
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
       <c:if test="${memberInfo.member_id == article.member_id}"> 
	      <input type=button value="수정하기" onClick="fn_modify_article('${contextPath}/board/tb_board/tb_articlemodForm.do',${article.tb_number})">
	      <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/tb_board/removetrans.do' , ${article.tb_number})">
	    </c:if>
	    <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
   </td>
  </tr>
 </table>
 </form>
 <br>
	<hr>

	<!-- 댓글 -->

	<c:set var="reply" value="${resultMap.reply}" />
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.0/css/solid.min.css"></script>
	<form id="replyTable" action="${contextPath}/reply/addNewReply.do"
		method="post" onsubmit="return loginCheck()">

		<h3 id="letter_reply" align="left">
			<i class="far fa-comment-dots"></i>&nbsp&nbsp댓글
		</h3>
		<table id="replyArea" align="center" border="0" width="950px">
			<tr>
				<td><input type="hidden" size="20" name="member_id"
					id="member_id" value="${memberInfo.member_id}" /> <input
					type="hidden" name="article_number" value="${article.tb_number}" />
					<input type="hidden" name="boardtype" value="trans_board" /></td>
				<td><textarea name="reply_content" rows="3" cols="100"
						maxlength="500" placeholder="댓글을 남겨보세요"></textarea></td>
				<td>
					<button class="addRepl" type="submit">댓글 달기</button>
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


<table align="center" border="0" width="950px" id="reListTable">
		<c:forEach var="repl" items="${replList}" varStatus="reply_no">

			<tr class="${repl.reply_no}">
				<td align="left" style="font-size:20px;"><i class="fas fa-user-circle"></i>&nbsp&nbsp${repl.member_id
					}</td>
			</tr>
			<tr class="${repl.reply_no}">

				<td colspan="2" align="left">${repl.reply_content }</td>
			</tr>

			<tr class="${repl.reply_no}" id="plRemove_btn">
				<td align="left" id="date"><fmt:formatDate
						value="${repl.reply_date }" pattern="yyyy.MM.dd HH:mm" /></td>
				<td align="right"><c:if
						test="${memberInfo.member_id == repl.member_id }">
						<a class="delRepl" href="#none" id="${repl.reply_no}">삭제</a>
					</c:if></td>
			</tr>

		</c:forEach>

	</table>
	<hr>
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
			  data: JSON.stringify({"boardKind": "tb", "reply_no": reply_no}),
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