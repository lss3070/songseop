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
#password {
	font-family: 'Nanum Gothic';
	font-weight: bold;	
	width: 22%;
}
label {
	margin-left: 15px;
	margin-right: 10px;
	text-align: right;
	width: 100px;
}
.form-control {
	width: 50%;
	display: inline;
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
</style>
<script type="text/javascript">
	function check() {
		if (document.fr.mem_pass.value.length==0) {
			alert("비밀번호를 입력해주세요!!!");
			document.fr.mem_pass.select();
			return;		// 함수 처음으로 돌아감
		}		
		if (confirm("정말 삭제하시겠어요?") == true) {
			document.fr.submit();	
		} else {
			document.fr.reset();
			return;
		}
	}
</script>
</head>
<body>
<%
//한글처리
request.setCharacterEncoding("utf-8");
// 현재 글의 번호와 페이지 번호를 리퀘스트 값으로 가져온다.
String mem_id = (String)session.getAttribute("sMem_id");
%>
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 헤더파일들어가는 곳 -->
<br>
<!-- 회원 탈퇴 -->
<form action="./MemberDeleteAction.me" method="post" name="fr">
<div class="container">
	<h1 class="mt-4 mb-3"> 회원 탈퇴</h1>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">Withdrawal</li>
		</ol>
	<div class="control-group form-group">
		<div class="controls">
			<label>아이디</label>:　<%=mem_id%>
			<input type="hidden" name="mem_id" value="<%=mem_id%>">
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>비밀번호</label> 
			<input type="password" name="mem_pass" class="form-control" id="password" placeholder="탈퇴하시려면 비밀번호를 입력하셔야 합니다!" required>
			<p class="help-block"></p>
		</div>
	</div>
	<div id="success"></div>
	<div id="buttonfield">
		<button type="button" id="joinbutton" onclick="check()">회원탈퇴</button>
		<button type="button" id="joinbutton" onclick="history.back()">취소</button>
	</div>
</div>	
</form>
<!-- 회원 탈퇴 -->
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