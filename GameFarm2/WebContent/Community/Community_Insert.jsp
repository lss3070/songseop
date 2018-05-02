<%@page import="communityDB.*"%>
<%@page import="memberDB.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8">
<meta name="viewport"	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>::: 게임팜 :::</title>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/modern-business.css" rel="stylesheet">
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
.card-header {
	font-family: 'Jeju Hallasan';		
}
.col-lg-6 {
	display: inline-block;
	width: 49%;
}
.mb-0 {
	margin-left: 2em;
}
#collapseTwo {
	margin-left: 2em;
}
#collapseFour {
	margin-left: 2em;
}
.input-group {
	position: relative;
	display: -webkit-box;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-wrap: wrap;
	flex-wrap: wrap;
	-webkit-box-align: stretch;
	-ms-flex-align: stretch;
	align-items: stretch;
	width: 40%;
	margin-top: 2em;
	margin-left: auto;
	margin-bottom: 3em;
	text-align: center;
}
/* 게시판 모양 */
label {
	text-align: left;	
	font-size: 1.2em;
}
.form-control {
	width: 120%;
}
/* 게시판 모양 */

/* 버튼 부분 */
#pList {
	text-align: right;
	margin-right: 15%;
}
#buttonfield {
	text-align: right;
	display: inline-block;
	margin-bottom: 15px;
}
#joinbutton {
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	color: #fff;
	height: 50px;
	width: 100px;	
	font-family: 'Jeju Hallasan';
	font-size: 24px;
	margin-top: 20px;
	margin-right: 20px;
}
#joinbutton:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	cursor: pointer;	
}
/* 버튼 부분 */
</style>
<script type="text/javascript">
function check() {
	if(document.fr.comm_subject.value.length==0) {
		alert("제목은 비울 수 없습니다!");
		document.fr.comm_subject.select();
		return;
	}		
	if(document.fr.comm_content.value.length==0) {
		alert("내용을 입력하세요!");
		document.fr.comm_content.select();
		return;
	}
	document.fr.submit();
}
</script>
</head>
<body>
<%
//한글처리
request.setCharacterEncoding("utf-8");
// 세션값 호출
String mem_id = (String)session.getAttribute("sMem_id");
%>
<!-- 헤더 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 헤더 -->

	<!-- 본문 -->
	<div class="container">
		<br>
		<!-- 페이지 위치 -->
		<h1 class="mt-4 mb-3">	게임 이야기</h1>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">Community</li>			
		</ol>
		<!-- 페이지 위치 -->	
			
		<!-- 이용 수칙 -->
			<div class="card advance">
				<h5 class="card-header">
				<a class="collapsed" data-toggle="collapse" data-parent="#accordion"
						href="#collapseTwo" aria-expanded="false"
						aria-controls="collapseTwo">
						커뮤니티 이용 수칙
				</a>
				</h5>
				<div id="collapseTwo" class="collapse" role="tabpanel"
				aria-labelledby="headingTwo">
				1. 욕설과 비방 금지!<br><br>
				2. 음란물 게재 금지! <br><br>
				3. 광고나 상업성 게시물 금지!<br><br>
				4. 위 사항을 하나라도 위반하면 해당 계정은 영구 정지합니다.
				</div>
			</div>
		<!-- 이용 수칙 -->	
			
		<!-- 게시판 -->		
		<h2>커뮤니티</h2>		
		<form action="./CommunityInsertAction.co" name="fr" method="post" enctype="multipart/form-data"><!-- 첨부 파일이 있을 때는 enctype을 꼭 넣어주고 없을 때는 꼭 빼야한다. -->
		<div class="col-lg-8 mb-4">
			<div class="control-group form-group">
				<div class="controls">
					<label>제목</label>
					<input type="text" name="comm_subject" class="form-control" id="insert_form" required data-validation-required-message="제목을 입력하세요." placeholder="제목을 입력하세요." required>
					<br>
					<label>작성자</label>
					<input type="text" name="mem_id" value="<%=mem_id%>"class="form-control" style="width:40%;" readonly>　	
					<br>			
					<label>내용</label>
					<textarea rows="10" cols="75" name="comm_content" class="form-control" placeholder="내용을 입력해주세요~" required></textarea>
					<br>	
					<label>사진 업로드</label>					
					<input type="file" name="comm_image" class="form-control">		
				</div>
			</div>			
			<p id="pList">
			<button type="button" onclick="check()" id="joinbutton">작성완료</button>
			<button type="reset" id="joinbutton">다시쓰기</button>
			<button type="button" onclick="location.href='CommunityList.co'" id="joinbutton">목록</button><br>
			</p>
		</div>								
		</form>
		<!-- 게시판 -->	
			
	</div>	
	<br>
	<!-- 본문 -->
	
<!-- 푸터 -->
<jsp:include page="../inc/footer.jsp"></jsp:include>
<!-- 푸터 -->
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
