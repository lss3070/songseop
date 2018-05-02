
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
#contactMessage {
	color: #333;
}
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
#aboutH3 {
	font-family: 'Nanum Pen Script';
	font-size: 4.2em;
}
.agreeContent {
	font-size: 0.8em;
	color: #222;
}
#password {
	font-family: 'Nanum Gothic';
	font-weight: bold;	
}
#ageText {
	width: 14.5%;
}
#emailText {
	width: 26%;
}
#emailText2 {
	width: 21%;
}
#phone {
	width: 10%;
}
#sample6_postcode {
	width: 27%;
}
#sample6_roadAddress {
	width: 80%;
}
#card {
	width: 9%;
}
.search_select {
	margin-left: 12px;
	height: 36px;
	width: 19.2%;
	border-radius: 5px;
	background-color: #efefef;
	border: 1px solid #333;
	padding-left: 5px;
}
.search_select:FOCUS {
	border: none;
	border-radius: 5px;
	background-color: #ffffff;
}
.phone_select {	
	height: 36px;
	width: 20%;
	border-radius: 5px;
	background-color: #efefef;
	border: 1px solid #333;
	padding-left: 5px;
}
.phone_select:FOCUS {
	border: none;
	border-radius: 5px;	
	background-color: #FFFFFF;
}
label {
	margin-left: 15px;
	margin-right: 10px;
	text-align: right;
	width: 120px;
}
.ver_center {
  vertical-align: middle;
}

.genderLabel {  
	width: 60px;
}
.form-control {
	width: 50%;
	display: inline;
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
	width: 150px;	
	font-family: 'Jeju Hallasan';
	font-size: 25px;
	position: relative;
	left: 150px;
	top: 3px;
	
}
#joinbutton:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	cursor: pointer;	
}
#phone {
	width: 20%;
}
#sample4_postcode {
	width: 27%;
}
#sample4_roadAddress {
	width: 80%;
}
#card {
	width: 9%;
}
</style>
</head>
<body>
<%
//한글처리
request.setCharacterEncoding("utf-8");
//사용자id,pageNum,qna_number,qna_re_ref,qna_re_lev,qna_re_seq값을 리퀘스트로 가져온다.
String mem_id = (String)session.getAttribute("sMem_id");
String pageNum = request.getParameter("pageNum");
int qna_number = Integer.parseInt(request.getParameter("qna_number"));
int qna_re_ref=Integer.parseInt(request.getParameter("qna_re_ref"));
int qna_re_lev=Integer.parseInt(request.getParameter("qna_re_lev"));
int qna_re_seq=Integer.parseInt(request.getParameter("qna_re_seq"));
%>
  <!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<br>
<!-- Page Content -->
<div class="container">
<!-- Page Heading -->
<h1 class="mt-4 mb-3">  </h1>
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
		<li class="breadcrumb-item active">QNA ReInsert</li>
	</ol>
	<!-- Content Row -->	
	<div class="row">
		<!-- Map Column -->
		<div class="col-lg-8 mb-4">
			<form action="./QnaReInsertAction.qn" name="join"  method="post" >				
				<div class="writeform" >					
					<input type="hidden" name =qna_re_ref value="<%=qna_re_ref%>">					
					<input type="hidden" name =qna_re_lev value="<%=qna_re_lev%>">
					<input type="hidden" name =qna_re_seq value="<%=qna_re_seq%>">
					<label>작성자</label><%=qna_re_ref %>
					<input type="text" name="mem_id" value="<%=mem_id%>"class="form-control" style="width:40%;" readonly>　	
					<br>
					<label>제목</label> <input type="text" class="form-control"name="qna_subject">
					<br>				
					<label>내용</label>
					<textarea rows="10" cols="75" name="qna_content" class="form-control ver_center" 
					placeholder="내용을 입력해주세요" required></textarea>
					<br>										
				</div>				
				<input type="submit" name="submit" id="joinbutton" value="작성완료" >
				<input type="reset" name="reset" id="joinbutton" value="초기화">
			</form>			
		</div>			
	</div>
		<!-- /.row -->
</div>
	<!-- /.container -->
	
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>	
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Contact form JavaScript -->
	<!-- Do not edit these files! In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>
</body>
</html>