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
	width: 100px;
}
.genderLabel {  
	width: 60px;
}
.form-control {
	width: 45.2%;
	display: inline;
}
.agreeContent {
	font-size: 0.8em;
	color: #222;
}
.selectBox {	
	height: 36px;
	width: 14.8% !important;
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
	width: 16%;
}
#emailText2 {
	width: 12%;
}
#phone {
	width: 13.7%;
}
#sample4_postcode {
	width: 33%;
}
#sample4_roadAddress {
	width: 80%;
}
#duplicate {
	margin-left: 12px;
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
#duplicate:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	color: #fff;
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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function check() {
	var pass = document.join.mem_pass.value;
	var pass2 = document.join.mem_pass2.value;
	var email1 = document.join.mem_email1.value;
	var email2 = document.join.mem_email2.value;
	if (document.join.mem_id.value.length==0) {
		alert("아이디를 입력해주세요!");
		document.join.mem_id.focus();
		return;		// 함수 처음으로 돌아감
	}	
	if(document.join.dup.value==0){
		alert("ID 중복체크를 하세요!");
		return;
	}
	if (document.join.mem_pass.value.length==0) {
		alert("비밀번호를 입력해주세요!");
		document.join.mem_pass.focus();
		return;		// 함수 처음으로 돌아감
	}
	if (document.join.mem_pass2.value.length==0) {
		alert("비밀번호 확인을 입력해주세요!");
		document.join.mem_pass2.focus();
		return;		// 함수 처음으로 돌아감
	}
	if (pass != pass2) {
		alert("비밀번호 확인을 다시 해주세요!");
		document.join.mem_pass2.select();
		return;		// 함수 처음으로 돌아감
	}
	if (document.join.mem_name.value.length==0) {
		alert("이름을 입력해주세요!!!");
		document.join.mem_name.focus();
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
	if (document.join.mem_phone2.value.length==0) {
		alert("휴대폰의 중간 번호를 입력해주세요!");
		document.join.mem_phone2.focus();
		return;		// 함수 처음으로 돌아감
	}
	if (document.join.mem_phone3.value.length==0) {
		alert("휴대폰의 마지막 번호를 입력해주세요!");
		document.join.mem_phone3.focus();
		return;		// 함수 처음으로 돌아감
	}	
	if (document.join.agreement.checked==false){
		alert("이용 약관에 동의해주세요!");
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
function checkId() {
	if (document.join.mem_id.value.length==0) {
	alert("아이디를 입력해주세요!");
	document.join.mem_id.select();
	return;		// 함수 처음으로 돌아감
	}
	var id = document.join.mem_id.value;
	// 아이디 중복 확인을 위한 윈도우 창 열기
	window.open("./MemberIdCheck.me?userId="+id, "", "width=420, height=160, left=600, top=200, menubar=no, status=no, toolbar=no");				
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
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 헤더파일들어가는 곳 -->
<br>
<!-- 회원가입 폼 -->
<div class="container">
<h1 class="mt-4 mb-3"> 회원 가입</h1>
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="./Main.me">Home</a></li>
		<li class="breadcrumb-item active">Sign Up</li>
	</ol>
	<div class="row">
		<!-- 필수 입력 사항 -->
		<div class="col-lg-8 mb-4">			
			<h2>필수 입력 사항</h2>
			<form action="./MemberJoinAction.me" name="join"  method="post">
				<div class="control-group form-group">
					<div class="controls">
						<label>아이디</label>
						<input type="text" name="mem_id" class="form-control" id="name" required data-validation-required-message="아이디를 입력해주세요!" placeholder="아이디를 입력해주세요!">
						<button type="button" name="idCheck" id="duplicate" onclick="checkId()">중복 확인</button>
						<p class="help-block"></p>
					</div>
					<input type="hidden" name="dup" value="0">	
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label>비밀번호</label>
						<input type="password" name="mem_pass" class="form-control" id="password" required data-validation-required-message="비밀번호를 입력해주세요!" placeholder="비밀번호를 입력해주세요!">　							
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label>비밀번호 확인</label>
						<input type="password" name="mem_pass2" class="form-control" id="password" required data-validation-required-message="비밀번호 확인을 해주세요!" placeholder="비밀번호 확인을 해주세요!">　							
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label>이름</label>
						<input type="text" name="mem_name" class="form-control" id="name" required data-validation-required-message="이름을 입력해주세요!" placeholder="이름을 입력해주세요!">　							
						<p class="help-block"></p>
					</div>
				</div>				
				<div class="control-group form-group">
					<div class="controls">
						<label>이메일</label>
						<input type="text" name="mem_email1" class="form-control" id="emailText" required data-validation-required-message="이메일을 입력해주세요!" placeholder="이메일을 입력해주세요!">
						@
						<input type="text" name="mem_email2" value="" class="form-control" id="emailText2" > 
						<select name="emailCheck" onchange="SetEmailTail(emailCheck.options[this.selectedIndex].value)" class="selectBox" style="width: 16.5%">
							<option value="etc">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="nate.com">nate.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="hotmail.com">hotmail.com</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="yahoo.com">yahoo.com</option>
							<option value="itwillbs.co.kr">itwillbs.co.kr</option>								
						</select>
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label>연락처</label>
					<select name="mem_phone1" class="selectBox" size="1" style="width:13.7% !important;">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="019">019</option>
					</select>
					&nbsp;-&nbsp;
					<input type="text" name="mem_phone2" size="7" class="form-control" id="phone" required>
					&nbsp;-&nbsp;
					<input type="text" name="mem_phone3" size="7" class="form-control" id="phone" required><br>
					</div>
				</div>
				<!-- 필수 입력 사항 -->
				
				<!-- 선택 입력 사항 -->	
				<h2>선택 입력 사항</h2>
				<div class="control-group form-group">
					<div class="controls">
						<label>성별</label>
						<input type="radio"  name="mem_gender" value="남성" class="genderradio" id="male" checked="checked"><label for="male" class="genderLabel"><span style="color: blue;">남성</span></label>
						<input type="radio"  name="mem_gender" value="여성" class="genderradio" id="female"> <label for="female" class="genderLabel"><span style="color: red;">여성</span></label>							
						<label style="margin-left: 45px;">|　　　　&nbsp;나이</label><input type="number" name="mem_age" class="form-control" id="ageText" required data-validation-required-message="나이를 입력해주세요!" placeholder="만 나이">
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label>우편번호</label>
						<input type="text" name="mem_post" class="form-control" id="sample6_postcode">								
						<input type="button" onclick="sample6_execDaumPostcode()" value="주소 검색" id="post"><br>
						<label>주소</label>														
						<input type="text" size="61" class="form-control" id="sample6_address" name="mem_address1" ><br>							
						<label>상세 주소</label>	
						<input type="text" size="61" class="form-control" id="sample6_address2" name="mem_address2">
					</div>
				</div>		
				<div id="success"><br><br><br><br></div>
				<!-- 선택 입력 사항 -->
				
				<!-- 버튼 처리부 -->
				<div id="buttonfield">
					<button type="button" id="joinbutton" onclick="check()">가입하기</button>　　
					<button type="reset" 	id="joinbutton">다시쓰기</button>
				</div>	
			</div>
				<!-- 버튼 처리부 -->
				
			<!-- 이용 약관 -->
			<div class="col-lg-4 mb-4">
				<h2>이용 약관</h2>
				<p>
				<textarea rows=34, cols=88 class="agreeContent">
				  <jsp:include page="../inc/agreepage.jsp"></jsp:include> 
				</textarea>
				</p>
				　<input type="checkbox" id="agree" name="agreement"><label for="agree" style="width:222px">　[이용 약관에 동의합니다.]</label>				
			</div>
			<!-- 이용 약관 -->
		</form>		
	</div>
</div>
<!-- 회원가입 폼 -->	

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