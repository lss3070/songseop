<%@page import="java.text.SimpleDateFormat"%>
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
	margin-left: 45px;
	margin-right: 10px;
	text-align: right;
	width: 120px;
	color: olive;
}
.controls {	
	font-size: 20px;
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
#deletebutton {
	background-color: #ff1500;
	border: none;
	border-radius: 5px;
	color: #fff;
	height: 50px;
	width: 200px;	
	font-family: 'Jeju Hallasan';
	font-size: 25px;
	margin-left: 1em;	
}
#deletebutton:HOVER {
	background-color: #ff3411;
	font-weight: bold;
	cursor: pointer;	
}
</style>
</head>
<body>
<%
//한글처리
request.setCharacterEncoding("utf-8");
// 세션에 저장된 아이디 값을 가져온다. (현재 로그인한 아이디)
String mem_id = (String)session.getAttribute("sMem_id");
// MemberBean mb 객체를 request로 불러온다.
MemberBean mb = (MemberBean)request.getAttribute("mb");
//날짜값 형태를 변환하는 객체를 생성한다.
SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
  <!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 헤더파일들어가는 곳 -->
<br>
<!-- 마이페이지 -->
<div class="container">
	<h1 class="mt-4 mb-3"> 마이페이지	</h1>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">My Page</li>
		</ol>
	<h2><%=mb.getMem_name()%>님의 정보</h2>
	<div class="control-group form-group">
		<div class="controls">
			<label>아이디</label>:　<%=mb.getMem_id()%>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>이름</label>:　<%=mb.getMem_name()%>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>성별</label>:　<%=mb.getMem_gender()%>
		</div>
	</div>
	<%
	if (!mb.getMem_age().equals("")) {	// 나이 값이 비어있지 않을 때만 출력한다.
	%>
	<div class="control-group form-group">
		<div class="controls">
			<label>나이</label>:　<%=mb.getMem_age()%>
		</div>
	</div>
	<%
	}
	%>
	<div class="control-group form-group">
		<div class="controls">
			<label>이메일</label>:　<%=mb.getMem_email1()%>@<%=mb.getMem_email2()%>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>보유 포인트</label>:　<%=mb.getMem_point()%> 포인트
		</div>
	</div>
	<%
	if (!mb.getMem_post().equals("")) {	// 주소 값이 비어있지 않을 때만 출력한다.
	%>
	<div class="control-group form-group">
		<div class="controls">
			<label>주소</label>:　(<%=mb.getMem_post()%>)　<%=mb.getMem_address1()%> <%=mb.getMem_address2()%>
		</div>
	</div>
	<%
	}
	%>
	<div class="control-group form-group">
		<div class="controls">
			<label>전화번호</label>:　<%=mb.getMem_phone1()%>-<%=mb.getMem_phone2()%>-<%=mb.getMem_phone3()%>
		</div>
	</div>
	<div class="control-group form-group">
		<div class="controls">
			<label>가입일자</label>:　<%=simpledate.format(mb.getMem_reg_date())%>
		</div>
	</div>						
	<div id="success"></div>			
	<div id="buttonfield">
		<button type="button" id="joinbutton" onclick="location.href='./MemberUpdate.me'">수정하기</button>　　
		<button type="button" id="joinbutton" onclick="location.href='index.jsp'">홈으로</button>
		<button type="button" id="deletebutton" onclick="location.href='./MemberDelete.me'">회원탈퇴</button>			
	</div>	
</div>		
<!-- 마이페이지 -->
<br><br>
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