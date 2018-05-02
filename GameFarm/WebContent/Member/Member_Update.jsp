<%@page import="memberDB.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>::: 게임팜 :::</title>
<!-- Bootstrap core CSS -->
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="./css/modern-business.css?ver=1" rel="stylesheet">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style type="text/css">
h2 {
	color: #0177A8;
	font-family: 'Jeju Gothic';
	font-size: 2em;
	font-weight: bold;
	border-bottom: 1px;
	border-bottom-style: dotted;
	margin-top: 1em;
	padding-bottom: 0.3em;
	margin-bottom: 1em;
}
label {
	margin-left: 15px;
	margin-right: 10px;
	text-align: right;
	width: 120px;
}
.genderLabel {  
	width: 60px;
}
.form-control {
	width: 35.5%;
	display: inline;
}
.agreeContent {
	font-size: 0.8em;
	color: #222;
}
.selectBox {	
	height: 36px;
	width: 10.1% !important;
	border-radius: 5px;
	background-color: #efefef;
	border: 1px solid #333;
	padding-left: 5px;
}
.selectBox:FOCUS {
	border: none;
	border-radius: 5px;	
	background-color: #FFFFFF;
}
#password {
	font-family: 'Nanum Gothic';
	font-weight: bold;	
}
#ageText {
	width: 16.5%;
}
#emailText {
	width: 12.8%;
}
#emailText2 {
	width: 11%;
}
#phone {
	width: 11.7%;
}
#post {
	border: none;
	background-color: #007bff;
	height: 37px;
	width: 100px;
	font-family: 'Jeju Gothic';
	font-size: 16px;
	vertical-align: center;
	color: #eee;
	border-radius: 5px;
	cursor: pointer;
}
#post:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	color: #fff;	
}
#sample6_postcode {
	width: 6em;
}
#gender {
	margin-left: 4em;
}
#buttonfield {
	text-align: center;
}
#joinbutton {
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	color: #fff;
	height: 50px;
	width: 200px;	
	font-family: 'Jeju Hallasan';
	font-size: 25px;
}
#joinbutton:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	cursor: pointer;	
}
/* 라디오 버튼을 이미지로 사용 */
input[type=radio] {
	display: none;	
}
input[type=radio]+label {
	display: inline-block;
	cursor: pointer;
	line-height: 22px;	
	background: url('./img/radio.png') left/22px no-repeat;
}
input[type=radio]:checked+label {
	background-image: url('./img/radiochecked.png');
} 
/* 라디오 버튼을 이미지로 사용 */
</style>
<script type="text/javascript">
function check() {
	var newpass = document.join.newpass.value;
	var newpass2 = document.join.newpass2.value;	
	if (document.join.mem_pass.value.length==0) {
		alert("기존 비밀번호를 입력해주세요!");
		document.join.mem_pass.select();
		return;		// 함수 처음으로 돌아감
	}
	if (document.join.newpass.value.length==0) {
		alert("새 비밀번호를 입력해주세요!");
		document.join.newpass.select();
		return;		// 함수 처음으로 돌아감
	}
	if (document.join.newpass2.value.length==0) {
		alert("새 비밀번호 확인을 입력해주세요!");
		document.join.newpass2.select();
		return;		// 함수 처음으로 돌아감
	}
	if (newpass != newpass2) {
		alert("새 비밀번호 확인을 다시 해주세요!");
		document.join.newpass2.select();
		return;		// 함수 처음으로 돌아감
	}
	if (document.join.mem_name.value.length==0) {
		alert("이름을 입력해주세요!!!");
		document.join.mem_name.select();
		return;		// 함수 처음으로 돌아감
	}	
	if (document.join.mem_email1.value.length==0) {
		alert("이메일을 입력해주세요!!!");
		document.join.mem_email1.focus();
		return;		// 함수 처음으로 돌아감
	}
	if (document.join.mem_email2.value.length==0) {
		alert("이메일 나머지 주소를 입력해주세요!!");
		document.join.mem_email2.focus();
		return;		// 함수 처음으로 돌아감
	}
	document.join.submit();
}

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('sample6_address').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('sample6_address2').focus();
        }
    }).open();
}
function SetEmailTail(emailValue) {
	  var email = document.all("mem_email1")    // 사용자 입력
	  var emailTail = document.all("mem_email2") // Select box	   
	  if ( emailValue == "etc" ) {
	   emailTail.readOnly = false;
	   emailTail.value = "";
	   emailTail.focus();
	  } else {
	   emailTail.readOnly = true;
	   emailTail.value = emailValue;
	  }
}
</script>
</head>
<body>
<%
//한글처리
request.setCharacterEncoding("utf-8");
//현재 글의 번호와 페이지 번호를 리퀘스트 값으로 가져온다.
String mem_id = (String)session.getAttribute("sMem_id");
// 기존에 리퀘스트 값으로 담겨있는 회원정보를 MemberBean mb 변수에 담는다.
MemberBean mb = (MemberBean)request.getAttribute("mb");
%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 헤더파일들어가는 곳 -->
<br>
<!-- 회원 정보 수정 폼 -->
<form action="./MemberUpdateAction.me" name="join"  method="post">
<div class="container">
<h1 class="mt-4 mb-3"> 회원 정보 수정 </h1>
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
		<li class="breadcrumb-item active">Modify Member</li>
	</ol>		
	<div class="control-group form-group">
		<div class="control-group form-group">
			<div class="controls">
				<label>아이디</label>:　<%=mb.getMem_id()%>
				<input type="hidden" name="mem_id" value="<%=mb.getMem_id()%>">
			</div>
		</div>
	<input type="hidden" name="dup" value="0">	
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>기존 비밀번호</label>
			<input type="password" name="mem_pass" class="form-control" id="password" required data-validation-required-message="비밀번호를 입력해주세요!" placeholder="기존 비밀번호를 입력해주세요!">　							
			<p class="help-block"></p>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>새 비밀번호</label>
			<input type="password" name="newpass" class="form-control" id="password" required data-validation-required-message="비밀번호를 입력해주세요!" placeholder="새 비밀번호를 입력해주세요!">　							
			<p class="help-block"></p>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>새 비밀번호 확인</label>
			<input type="password" name="newpass2" class="form-control" id="password" required data-validation-required-message="비밀번호 확인을 해주세요!" placeholder="새 비밀번호 확인을 해주세요!">　							
			<p class="help-block"></p>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>이름</label>
			<input type="text" name="mem_name" class="form-control" id="name" value="<%=mb.getMem_name()%>" required data-validation-required-message="이름을 입력해주세요!" placeholder="이름을 입력해주세요!">　							
			<p class="help-block"></p>
		</div>
	</div>								
	<div class="control-group form-group">
		<div class="controls">
			<label>이메일</label>
			<input type="text" name="mem_email1" value="<%=mb.getMem_email1()%>" class="form-control" id="emailText" required data-validation-required-message="이메일을 입력해주세요!" placeholder="이메일을 입력해주세요!">
			@
			<input type="text" name="mem_email2" value="<%=mb.getMem_email2()%>" class="form-control" id="emailText2"> 
			<select name="emailCheck" onchange="SetEmailTail(emailCheck.options[this.selectedIndex].value)" class="selectBox" style="width: 16.5%">
				<option value="etc">직접입력</option>
				<option value="naver.com"
				<%
				if ("naver.com".equals(mb.getMem_email2())) {
				%>
				selected 
				<%								
				}
				%>
				>naver.com</option>
				<option value="nate.com"
				<% 
				if ("nate.com".equals(mb.getMem_email2())) {
				%> 
				selected 
				<%								
				}
				%>
				>nate.com</option>
				<option value="gmail.com"
				<% 
				if ("gmail.com".equals(mb.getMem_email2())) {
				%> 
				selected 
				<%								
				}
				%>
				>gmail.com</option>
				<option value="hotmail.com"
				<% 
				if ("hotmail.com".equals(mb.getMem_email2())) {
				%> 
				selected 
				<%								
				}
				%>
				>hotmail.com</option>
				<option value="hanmail.net"
				<% 
				if ("hanmail.net".equals(mb.getMem_email2())) {
				%> 
				selected 
				<%								
				}
				%>
				>hanmail.net</option>
				<option value="yahoo.com"
				<% 
				if ("yahoo.com".equals(mb.getMem_email2())) {
				%> 
				selected 
				<%								
				}
				%>
				>yahoo.com</option>
				<option value="itwillbs.co.kr"
				<% 
				if ("itwillbs.co.kr".equals(mb.getMem_email2())) {
				%> 
				selected 
				<%								
				}
				%>
				>itwillbs.co.kr</option>								
			</select>
			<p class="help-block"></p>
		</div>
	</div>	
	<div class="control-group form-group">
		<div class="controls">
			<label>연락처</label>
			<select name="mem_phone1" class="selectBox" size="1">
				<option value="010" 
				<%
				if ("010".equals(mb.getMem_phone1())) {
				%>
				 selected 
				<%								
				}
				%>				
				>010</option>
				<option value="011"
				<% 
				if ("011".equals(mb.getMem_phone1())) {
				%> 
				selected 
				<%								
				}
				%>
				>011</option>
				<option value="016"
				<% 
				if ("016".equals(mb.getMem_phone1())) {
				%> 
				selected 
				<%								
				}
				%>
				>016</option>
				<option value="019"
				<% 
				if ("019".equals(mb.getMem_phone1())) {
				%>
				selected 
				<%								
				}
				%>
				>019</option>
			</select>&nbsp;-&nbsp;<input type="text" name="mem_phone2" size="1" class="form-control" id="phone" value="<%=mb.getMem_phone2()%>">
			&nbsp;-&nbsp;<input type="text" name="mem_phone3" size="1" class="form-control" id="phone" value="<%=mb.getMem_phone3()%>"><br>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>성별</label>
				<input type="radio"  name="mem_gender" value="남성" class="genderradio" id="male" 
				<%
				// gender 값이 null 일 때
				if(mb.getMem_gender().equals(null)) {
				%>
				checked="checked"
				<%
				}
				%>
				<%
				// gender 값이 "남성"일 때
				if(mb.getMem_gender().equals("남성")) {
				%>
				checked="checked"
				<%
				}
				%>>
				<label for="male" class="genderLabel"><span style="color: blue;">남성</span></label>
				<input type="radio"  name="mem_gender" value="여성" class="genderradio" id="female"
				<%
				// gender 값이 "여성"일 때
				if(mb.getMem_gender().equals("여성")) {
				%>
				checked="checked"
				<%
				}
				%>>
			<label for="female" class="genderLabel"><span style="color: red;">여성</span></label>
			<label style="margin-left: 24px;">|　　　　&nbsp;나이</label><input type="number" name="mem_age" value="<%=mb.getMem_age()%>" class="form-control" id="ageText" required data-validation-required-message="나이를 입력해주세요!" placeholder="만 나이">						
			<p class="help-block"></p>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>우편번호</label>
			<input type="text" name="mem_post" class="form-control" id="sample6_postcode" value="<%=mb.getMem_post()%>">								
			<input type="button" onclick="sample6_execDaumPostcode()" value="주소 검색" id="post"><br>
			<label>주소</label>														
			<input type="text" size="61" class="form-control" id="sample6_address" name="mem_address1" value="<%=mb.getMem_address1()%>"><br>							
			<label>상세 주소</label>	
			<input type="text" size="61" class="form-control" id="sample6_address2" name="mem_address2" value="<%=mb.getMem_address2()%>">
		</div>
	</div>			
	<div id="success"></div>
	<div id="buttonfield">
		<button type="button" id="joinbutton" onclick="check()">수정완료</button>　　
		<button type="reset" 	id="joinbutton">다시쓰기</button>
	</div>	
</div>
</form>	
<!-- 회원 정보 수정 폼 -->		
<br>
<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/footer.jsp"></jsp:include>
<!-- 푸터 들어가는 곳 -->	
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Contact form JavaScript -->
<!-- Do not edit these files! In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
<script src="js/jqBootstrapValidation.js"></script>
<script src="js/contact_me.js"></script>
</body>
</html>