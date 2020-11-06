<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>


 function fn_overlapped(){
    var member_id=$("#member_id").val();
    if(member_id==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do",
       dataType:"text",
       data: {id:member_id},
       success:function (data,textStatus){
          if(data=='false'){
       	    alert("사용할 수 있는 ID입니다.");
       	    $('#btnOverlapped');
       	    $('#member_id');
       	    $('#_member_id').val(member_id);
          }else{
        	  alert("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }	 
</script>
</head>
<body>
	<h3>필수입력사항</h3>
	<form action="${contextPath}/member/addMember.do" method="post">	
	<div id="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td>
					  <input type="text" name="member_id"  id="member_id"  size="20" />
					  <input type="hidden" name="_member_id"  id="_member_id" />
					  
					  <input type="button"  id="btnOverlapped" value="중복체크" onClick="fn_overlapped()" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비밀번호</td>
					<td><input name="member_pw" type="password" size="20" /></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이름</td>
					<td><input name="name" type="text" size="20" /></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">성별</td>
					<td><input type="radio" name="gender" value="여" />
						여성<span style="padding-left:120px"></span>
						 <input type="radio" name="gender" value="남" checked />남성
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">법정생년월일</td>
					<td>
					<select name="birth_year">
					 
					     <c:forEach var="year" begin="1" end="100">
					       <c:choose>
					         <c:when test="${year==80}">
							   <option value="${ 1920+year}" selected>${ 1920+year} </option>
							</c:when>
							<c:otherwise>
							  <option value="${ 1920+year}" >${ 1920+year} </option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach> 
							
					</select>년 
					 <select name="birth_month" >
					   <c:forEach var="month" begin="1" end="12">
					       <c:choose>
					         <c:when test="${month==1 }">
							   <option value="${month }" selected>${month }</option>
							</c:when>
							<c:otherwise>
							  <option value="${month }">${month}</option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach>
					</select>월  
					<select name="birth_day">
							<c:forEach var="day" begin="1" end="31">
					       <c:choose>
					         <c:when test="${day==1 }">
							   <option value="${day}" selected>${day}</option>
							</c:when>
							<c:otherwise>
							  <option value="${day}">${day}</option>
							</c:otherwise>
							</c:choose>
					   	</c:forEach>
					</select>일 <span style="padding-left:50px"></span>
<!-- 					  <input type="radio" name="member_birth_gn" value="2" checked />양력
						 <span style="padding-left:50px"></span>
						<input type="radio"  name="member_birth_gn" value="1" />음력 -->
				  </td>
				</tr>
				
				<tr class="dot_line">
					<td class="fixed_join">이메일<br>(e-mail)</td>
					<td><input size="10px"   type="text" name="email_head" /> @ <input  size="10px"  type="text"name="email2"  /> 
						  <select name="email_tail" onChange=""	title="직접입력">
									<option value="non">직접입력</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="naver.com">naver.com</option>
									<option value="yahoo.co.kr">yahoo.co.kr</option>
									<option value="hotmail.com">hotmail.com</option>
									<option value="paran.com">paran.com</option>
									<option value="nate.com">nate.com</option>
									<option value="google.com">google.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="empal.com">empal.com</option>
									<option value="korea.com">korea.com</option>
									<option value="freechal.com">freechal.com</option>
							</select><br> <br> <!-- <input type="checkbox" name="emailsts_yn" value="Y" checked /> 쇼핑몰에서 발송하는 e-mail을 수신합니다.</td> -->
				</tr>
<!-- 				<tr class="dot_line">
					<td class="fixed_join">주소</td>
					<td>
					   <input type="text" id="zipcode" name="zipcode" size="10" > <a href="javascript:execDaumPostcode()">우편번호검색</a>
					  <br>
					  <p> 
					   지번 주소:<br><input type="text" id="roadAddress"  name="roadAddress" size="50"><br><br>
					  도로명 주소: <input type="text" id="jibunAddress" name="jibunAddress" size="50"><br><br>
					  나머지 주소: <input type="text"  name="namujiAddress" size="50" />
					   <span id="guide" style="color:#999"></span>
					   </p>
					</td>
				</tr> -->
			</tbody>
		</table>
		</div>
		<div class="clear">
		<br><br>
		<table align=center>
		<tr >
			<td >
				<input type="submit"  value="회원 가입">
				<input  type="reset"  value="다시입력">
			</td>
		</tr>
	</table>
	</div>
</form>	
</body>
</html>