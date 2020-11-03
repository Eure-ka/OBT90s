<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>
<head>
<meta charset="UTF-8">
<title>글보기</title>
<style>
#tr_file_upload {
	display: none;
}

#tr_btn_modify {
	display: none;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
     function backToList(obj){
    	 
	    obj.action="${contextPath}/board/fb_board/fb_listArticles.do";
	    obj.submit();
     }
 
	 function fn_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		/*  document.getElementById("tr_file_upload").style.display="block"; */
		 document.getElementById("tr_btn").style.display="none";
	 }
	 
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/fb_board/modArticle.do";
		 obj.submit();
	 }
	 
	 function fn_remove_article(url,fb_number){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","fb_number");
	     articleNOInput.setAttribute("value", fb_number);
		 
	     form.submit();
	 
	 }
	 
	/* 
	 function readURL(input) {
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

	<c:set var="article" value="${resultMap.freeBoard }" />
	<c:set var="replList" value="${resultMap.replList }" />
	<form name="frmArticle" method="post" action="${contextPath}"
		enctype="multipart/form-data">
		<table border=0 align="center">
			<tr>
				<td width=150 align="center" bgcolor=#FF9933>글번호</td>
				<td>
					<%--  <input type="text" name="a rticleNO" value="${article.articleNO }"  readonly />  --%>
					<!-- //보여주는데 못써 --> <input type="text"
					value="${article.fb_number }" disabled /> <!-- //보여주는데 못써 --> <input
					type="hidden" value="${article.fb_number}" /> <!-- // 안보여주고 넘길때 처리 -->


				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">작성자 아이디</td>
				<td><input type=text value="${article.member_id }"
					name="writer" disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">제목</td>
				<td><input type=text value="${article.fb_title }" name="title"
					id="i_title" disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">내용</td>
				<td><textarea rows="20" cols="60" name="content" id="i_content"
						disabled />${article.fb_content}</textarea></td>
			</tr>
			<%-- 
 <c:if test="${not empty imageFileList && imageFileList!='null' }">
	  <c:forEach var="item" items="${imageFileList}" varStatus="status" >
		    <tr>
			    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
			      이미지${status.count }
			   </td>
			   <td>
			     <input  type= "hidden"   name="originalFileName" value="${item.imageFileName }" />
			    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="preview"  /><br>
			   </td>   
			  </tr>  
			  <tr>
			    <td>
			       <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
			    </td>
			 </tr>
		</c:forEach>
 </c:if>
 	 --%>

			<%-- <c:choose> 
	  <c:when test="${not empty article.imageFileName && article.imageFileName!='null' }">
	   	<tr>
		    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
		      이미지
		   </td>
		   <td>
		     <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
		    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" id="preview"  /><br>
		    <img src="${contextPath}/upload/${article.articleNO}&imageFileName=${article.imageFileName}" id="preview"  /><br>
		   </td>   
		  </tr>  
		  <tr>
		    <td ></td>
		    <td>
		       <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
		    </td>
		  </tr> 
		 </c:when>
		 <c:otherwise>
		    <tr  id="tr_file_upload" >
				    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
				      이미지
				    </td>
				    <td>
				      <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
				    </td>
			    </tr>
			    <tr>
				    <td ></td>
				    <td>
				       <img id="preview"  /><br>
				       <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
				    </td>
			  </tr>
		 </c:otherwise>
	 </c:choose> --%>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">등록일자</td>
				<td><input type=text
					value="<fmt:formatDate value="${article.fb_write_date}" />"
					disabled /></td>
			</tr>
			<tr id="tr_btn_modify" align="center">
				<td colspan="2"><input type=button value="수정반영하기"
					onClick="fn_modify_article(frmArticle)"> <input type=button
					value="취소" onClick="backToList(frmArticle)"></td>
			</tr>

			<tr id="tr_btn">
				<td colspan="2" align="center"><c:if
						test="${memberInfo.id == article.member_id }">
						<input type=button value="수정하기" onClick="fn_enable(this.form)">
						<input type=button value="삭제하기"
							onClick="fn_remove_article('${contextPath}/fb_board/removeArticle.do', ${article.fb_number})">
					</c:if> <input type=button value="리스트로 돌아가기"
					onClick="backToList(this.form)"></td>
			</tr>

		</table>
	</form>


	<!-- 댓글 -->
	<table align="center" border="0" width="45%">
		<tr>
			<th colspan="2">댓글</th>
		</tr>
		<tr>
			<td><input type="text" size="20" name="member_id"
				value="${memberInfo.id}" hidden /></td>
			<td colspan=2><textarea name="reply_content" rows="3" cols="65"
					maxlength="500"></textarea></td>
			<td><input type="submit" value="등록"
				action="${contextPath}/reply/addNewArticle.do"></td>
		</tr>
	</table>

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
			<tr class="${repl.reply_no}">
				<td colspan="2" align="right">
					<a class="delRepl" href="${repl.reply_no}">삭제</a>
				</td>
			</tr>

		</c:forEach>
	</table>
<script type="text/javascript">
$(document).ready(function(){
	  $(".delRepl").click(function(event){
		  event.preventDefault();
		  console.log("wow");
		  var reply_no = $(this).attr("href");
		  console.log("wow >> " + reply_no));
		  
		  /* $.ajax({
			    url: "${contextPath}/reply/removeReply.do",
			    type:"post",
			    contentType:'application/json; charset=utf-8',
		        data: JSON.stringify({"boardKind": "fb" , "reply_no": reply_no}),
		        //dataType: 'json',
		        console.log("wow");
			    success: function(result){
			    	console.log(result);
			    	if (result=="OK") {
			            $("."+reply_no).remove();
			            alert("삭제되었습니다.");
			        } else{
			            alert("오류.");
			        }
			    }
			        
			}); */	
	  });
	});
	
</script>
</body>
</html>