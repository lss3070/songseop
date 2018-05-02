<%@page import="newsDB.NewsBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>::: 게임팜 :::</title>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/blog-post.css" rel="stylesheet">
<style type="text/css">
.boardButton {
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	color: #fff;
	height: 50px;
	width: 113.5px;
	font-family: 'Jeju Hallasan';
	font-size: 20px;
}
.boardButton:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	cursor: pointer;
}
.mem_search {
	color: blue;
}
.mem_search:HOVER {
	color: orange;
	text-decoration: none;
}
.col-lg-8 {
	width: auto;
}
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
/* 페이지 부분 */
#currentpage {
	font-weight: bold;
	color: red;	
}
/* 페이지 부분 */
/* 툴팁 표시 */
[data-tooltip-text]:hover {
	position: relative;
}

[data-tooltip-text]:hover:after {
	background-color: #000000;
	background-color: rgba(0, 0, 0, 0.5);
	-webkit-box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);
	-moz-box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);
	box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	color: #FFFFFF;
	font-size: 12px;
	content: attr(data-tooltip-text);
	margin-bottom: 10px;
	top: 30%;
	left: 110%;	
	padding: 7px 12px;
	position: absolute;
	width: auto;
	min-width: 220px;
	max-width: 900px;
	word-wrap: break-word;
	z-index: 9999;
}
/* 툴팁 표시 */
</style>
</head>
<body>
<%
//한글처리
/* 게시물 내용에 필요한 변수들 */
request.setCharacterEncoding("utf-8");		
// 로그인을 안한 상태라면 세션 초기값을 지정한다. 
String mem_id = (String)session.getAttribute("sMem_id");
int news_number = Integer.parseInt(request.getParameter("news_number"));
String pageNum = request.getParameter("pageNum");
NewsBean nb = (NewsBean)request.getAttribute("nb");
//날짜값 형태를 변환하는 객체를 생성한다.
SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 게시물 내용에 필요한 변수들 */
%>
<!-- 헤더 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 헤더 -->

	<!-- 본문 -->
	<div class="container">
		<br>
		<!-- 페이지 위치 -->		
		<h1 class="mt-4"> 공지사항</h1>		
		<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
		<li class="breadcrumb-item active">News</li>		
		</ol>
		<!-- 페이지 위치 -->
		
		<!-- 본문 -->
		<div class="row">
			<!-- 게시물 정보 -->
			<div class="col-lg-8">
				<p class="lead">
					<span style="color:green;">제목: </span><%=nb.getNews_subject() %>
				</p>
				<p class="lead">
					<span style="color:green;">작성자: </span>
					<a href="./NewsSearchList.nw?searchOption=3&search=<%=nb.getMem_id()%>" class="mem_search" data-tooltip-text="이 작성자가 작성한 글을 검색합니다."><%=nb.getMem_id()%></a>
				</p>
				<p class="lead">
					<span style="color:green;">작성일: </span><%=simpledate.format(nb.getNews_reg_date())%>
				</p>
				<p class="lead">
					<span style="color:green;">조회수: </span><%=nb.getNews_readcount()%>
				</p>
				<hr>
				<p class="lead">
				<%
					if (nb.getNews_image()!=null) { // 첨부파일이 있으면? %>
					<img src="./upload/<%=nb.getNews_image()%>" class=img-fluid rounded">
				<%				
					} 
				%> 				
				</p>				
				<p class="lead"><%=nb.getNews_content()%></p>				
				<hr>					
				<%-- <p class="lead">
				<%
					if(nb.getNews_image()!=null) { // 첨부사진이 있으면? %>
					<hr>첨부 사진: <a href="./upload/<%=nb.getNews_image()%>"><%=nb.getNews_image()%></a><hr>
				<%
					}
				%>
				</p> --%>
			<!-- 게시물 정보 -->
			</div>						
			<!-- 사이드 메뉴 -->
			<div class="col-md-4">				
				<!-- 이용 수칙 -->         		
				<!-- <div class="card my-4">
					<h5 class="card-header">게임 이야기 이용 수칙</h5>
					<div class="card-body">
					1. 욕설과 비방 금지!<br><br>
					2. 음란물 게재 금지! <br><br>
					3. 광고나 상업성 게시물 금지!<br><br>
					4. 위 사항을 하나라도 위반하면 해당 계정은 영구 정지합니다.
					</div>
				</div>	 -->		
				<!-- 이용 수칙 -->
				<!-- 사이드 버튼 -->
				<div class="card my-4" style="display: inline; background-color: rgba(0,0,0,0);">
					<button type="submit" class="boardButton" onclick="location.href='./NewsList.nw'">목록</button>
					<%
					if (mem_id!=null) {	 // 현재 로그인한 상태일 때
						if (mem_id.equals(nb.getMem_id())) {	// 현재 로그인한 회원의 id와 작성자 id가 일치할 때
					%>
					<!-- 작성자만 수정과 삭제가 가능하도록 제어 -->
					<script type="text/javascript">
					function newsDeleteAction(){
						if (confirm("정말 삭제하시겠습니까??") == true){    //확인
							location.href='./NewsDeleteAction.nw?news_number=<%=news_number%>&pageNum=<%=pageNum%>';
						} else { //취소
							return;
						}
					}
					</script>
					<button type="submit" class="boardButton" onclick="location.href='./NewsUpdate.nw?news_number=<%=nb.getNews_number()%>&pageNum=<%=pageNum%>'">수정하기</button>
					<button type="submit" class="boardButton" onclick="newsDeleteAction()">삭제하기</button>
					<%			
						} 
					} else {	// 로그인하지 않았을 때						
					}
					%>									
				</div>
				<!-- 사이드 버튼 -->
			</div>
			<!-- 사이드 메뉴 -->
		</div>
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
