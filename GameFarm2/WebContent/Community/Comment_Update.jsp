<%@page import="java.text.SimpleDateFormat"%>
<%@page import="communityDB.*"%>
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
/* 댓글 영역 */
.commentButton {
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	color: #fff;
	height: 50px;
	width: 100px;
	font-family: 'Jeju Hallasan';
	font-size: 22px;
}
.commentButton:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	cursor: pointer;
}
.reg_date {
	text-align: right;
	font-size: 0.8em;
	color: #888;
}
.textButton:HOVER {
	text-decoration: none;
	color: red;
	cursor: pointer;
}
/* 댓글 영역 */
</style>
</head>
<body>
<%
// 한글처리
request.setCharacterEncoding("utf-8");
// 댓글의 정보가 담긴 값인 CommentBean cob 객체를 리퀘스트 값으로 가져온다.
CommentBean cob = (CommentBean)request.getAttribute("cob");
//날짜값 형태를 변환하는 객체를 생성한다.
SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<!-- 기존 댓글 조회 -->
<div class="media mb-4">
	<%
	if (cob.getMem_id().equals("admin") || cob.getMem_id().equals("adminJang") || cob.getMem_id().equals("adminLee") || cob.getMem_id().equals("adminShin") || cob.getMem_id().equals("adminSon") || cob.getMem_id().equals("adminPark") || cob.getMem_id().equals("adminGang")) {  
	%>				
	<img class="d-flex mr-3 rounded-circle" src="./img/admin.png" alt="">
	<%
	} else { // 일반 회원일 때
	%>
	<img class="d-flex mr-3 rounded-circle" src="./img/member.png" alt="">
	<%
	}
	%>	
	<div class="media-body">										
		<h5 class="mt-0"><%=cob.getMem_id()%></h5>
		<p class="reg_date"><%=simpledate.format(cob.getComment_reg_date())%></p>	
		<p><%=cob.getComment_content()%></p>
	</div>
</div>	
<!-- 기존 댓글 조회 -->						
<!-- 댓글 입력부 -->
<div class="card my-4" style="background-color: rgba(0,0,0,0);">
	<h5 class="card-header"  style="background-color: rgba(255,255,255,0.5);">수정할 댓글의 내용을 입력하세요.</h5>
	<div class="card-body">
		<form action="./CommentUpdateAction.co" name="fr" method="post">
			<div class="form-group">
				<textarea class="form-control" rows="3" name="comment_content" required></textarea>
				<input type="hidden" name="comment_number" value="<%=cob.getComment_number()%>">				
			</div>
			<button type="submit" class="commentButton">댓글 수정</button>
			<button type="button" class="commentButton" onclick="window.close()">취소</button>
		</form>
	</div>
</div>
<!-- 댓글 입력부 --> 
	
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Contact form JavaScript -->
	<!-- Do not edit these files! In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>
</body>
</html>