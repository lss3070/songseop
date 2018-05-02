<%@page import="java.text.SimpleDateFormat"%>
<%@page import="communityDB.*"%>
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
request.setCharacterEncoding("utf-8");
/* 게시물 내용에 필요한 변수들 */
String mem_id = (String)session.getAttribute("sMem_id");
int comm_number = Integer.parseInt(request.getParameter("comm_number"));
String pageNum = request.getParameter("pageNum");
CommunityBean cb = (CommunityBean)request.getAttribute("cb");
//날짜값 형태를 변환하는 객체를 생성한다.
SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 게시물 내용에 필요한 변수들 */
/* 댓글 목록 조회에 필요한 변수들 */
int commentCount = (Integer)request.getAttribute("commentCount");
String commentPageNum = (String)request.getAttribute("commentPageNum");
List getCommentList = (List)request.getAttribute("getCommentList");
int commentPageCount = (Integer)request.getAttribute("commentPageCount");
int commentPageBlock = (Integer)request.getAttribute("commentPageBlock");
int commentCurrentPage = (Integer)request.getAttribute("commentCurrentPage");
int commentStartPage = (Integer)request.getAttribute("commentStartPage");
int commentEndPage = (Integer)request.getAttribute("commentEndPage");
int commentStartRow = (Integer)request.getAttribute("commentStartRow");
int commentEndRow = (Integer)request.getAttribute("commentEndRow");
int commentMaxPage = (Integer)request.getAttribute("commentMaxPage");
/* 댓글 목록 조회에 필요한 변수들 */
%>
<script type="text/javascript">
function commentDeleteAction(comment_number, commentPageNum){
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인			
		location.href='./CommentDeleteAction.co?comment_number='+comment_number+'&commentPageNum='+commentPageNum+'&comm_number=<%=comm_number%>&pageNum=<%=pageNum%>';
	} else {   //취소
	    return;
	}
}	
function communityDeleteAction(comment_number, commentPageNum){
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		location.href='./CommunityDeleteAction.co?comment_number='+comment_number+'&commentPageNum='+commentPageNum+'&comm_number=<%=comm_number%>&pageNum=<%=pageNum%>';
	} else { //취소
		return;
	}
}	
function commentUpdate(comment_number) {
	// 댓글 수정을 위한 새 윈도우창 열기		
	window.open("./CommentUpdate.co?comment_number="+comment_number, "", "width=640, height=480, left=600, top=200, menubar=no, status=no, toolbar=no");
}
</script>

<!-- 헤더 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 헤더 -->

	<!-- 본문 -->
	<div class="container">
		<br>
		<!-- 페이지 위치 -->		
		<h1 class="mt-4">게임 이야기</h1>		
		<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
		<li class="breadcrumb-item active">Community</li>		
		</ol>
		<!-- 페이지 위치 -->
		
		<!-- 본문 -->
		<div class="row">
			<!-- 게시물 정보 -->
			<div class="col-lg-8">
				<p class="lead">
					<span style="color:green;">제목: </span><%=cb.getComm_subject() %>
				</p>
				<p class="lead">
					<span style="color:green;">작성자: </span>
					<a href="./CommunitySearchList.co?searchOption=3&search=<%=cb.getMem_id()%>" class="mem_search" data-tooltip-text="이 작성자가 작성한 글을 검색합니다."><%=cb.getMem_id()%></a>
				</p>
				<p class="lead">
					<span style="color:green;">작성일: </span><%=simpledate.format(cb.getComm_reg_date())%>
				</p>
				<p class="lead">
					<span style="color:green;">조회수: </span><%=cb.getComm_readcount()%>
				</p>
				<hr>
				<p class="lead">
				<%
					if (cb.getComm_image()!=null) { // 첨부파일이 있으면? %>
					<img src="./upload/<%=cb.getComm_image()%>" class=img-fluid rounded">
				<%				
					} 
				%> 				
				</p>				
				<p class="lead"><%=cb.getComm_content()%></p>			
				<hr>				
			<!-- 게시물 정보 -->
				
			<!-- 댓글 입력부 -->
			<%
			if(mem_id!=null) {	// 로그인한 회원만 댓글 작성이 가능함
			%>
			<div class="card my-4" style="background-color: rgba(0,0,0,0);">
				<h5 class="card-header"  style="background-color: rgba(255,255,255,0.5);">댓글 남기기</h5>
				<div class="card-body">
					<form action="./CommentInsertAction.co" name="fr" method="post">
						<div class="form-group">
							<textarea class="form-control" rows="3" name="comment_content" required></textarea>
							<input type="hidden" name="comm_number" value="<%=comm_number%>">
							<input type="hidden" name="pageNum" value="<%=pageNum%>">
						</div>
						<button type="submit" class="commentButton">댓글 쓰기</button>
						<button type="reset" class="commentButton">취소</button>
					</form>
				</div>
			</div>
			<%
			}
			%>
			<!-- 댓글 입력부 -->
			
			<!-- 댓글 출력부 -->
			<%
			if (commentCount!=0) { // 댓글이 하나 이상 존재할 때
				for (int c=0; c<getCommentList.size(); c++) { // 댓글 리스트 개수만큼 행을 반복한다.
					CommentBean cob = (CommentBean)getCommentList.get(c);	// 리스트에 담긴 댓글들을 가져온다.					
			%>
				<%
					if (mem_id!=null) {	 // 현재 로그인한 상태일 때	
				%>
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
					<p class="reg_date">	
				<!-- 댓글 작성자만 수정과 삭제가 가능하도록 제어 -->						
				<%					
						if (mem_id.equals(cob.getMem_id())) {	// 댓글 작성자의 id와 현재 접속한 사용자의 id가 같을 때는 수정과 삭제 버튼이 나온다.
				%>
					<a class=textButton onclick="commentDeleteAction(<%=cob.getComment_number()%>, <%=commentPageNum%>)">삭제</a> |					
					<a class=textButton onclick="commentUpdate(<%=cob.getComment_number()%>)">수정</a> |					
				<%	
						} else { // 댓글이 하나도 없을 때 출력하지 않는다.
						}
				%>
				<!-- 댓글 작성자만 수정과 삭제가 가능하도록 제어 -->	 
						<%=simpledate.format(cob.getComment_reg_date())%></p>	
						<p><%=cob.getComment_content()%></p>								
					</div>
				</div>							
			<%	
					} else {	// 현재 로그인한 상태가 아닐 때
			%>
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
			<%						
					}
				}
			}
			%>
			<!-- 댓글 출력부 -->		
				
			<!-- 페이지 넘버링 -->			
				<article>
					<ul class="pagination justify-content-center">
						<%							
						if (commentPageCount==1) { 
						// 최대 페이지가 1페이지 일때는 페이지 넘버링을 하지 않는다.							
						} else {	// 최대 페이지가 2페이지 이상일 때
							if (commentPageCount < commentEndPage) {// 전체 페이지 번호가 끝 페이지 번호보다 작을 때
								//끝 페이지 번호의 값에 전체 페이지 번호를 대입한다.
								commentEndPage = commentPageCount;
							}
							// [이전] 버튼을 표시하기	
							if (commentStartPage > commentPageBlock) { // (시작 페이지가 한 화면당 페이지 수보다 클 때)	
								// 현재 페이지 번호 = 첫 페이지 - 한 화면에 보여줄 페이지 수의 값을 가진 페이지로 이동
						%>
						<a href="./CommunityDetail.co?comm_number=<%=comm_number%>&pageNum=<%=pageNum%>&commentPageNum=<%=commentStartPage - commentPageBlock%>"></a>
						<li class="page-item"><a class="page-link"
							href="./CommunityDetail.co?comm_number=<%=comm_number%>&pageNum=<%=pageNum%>&commentPageNum=<%=commentStartPage - commentPageBlock%>"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								<span class="sr-only">이전</span>
						</a></li>
						<%
							}
							// 페이지 버튼 출력하기
							for (int p = commentStartPage; p <=commentEndPage; p++) { // 페이지의 최대 갯수를 계산해주고 그 값을 최대 페이지로 지정하여 for문을 생성	
								if (commentCurrentPage == p) { // 현재 페이지의 번호와 반복 중인 p의 값이 동일할 때 작동하지 않는 다른 버튼으로 대체한다.
						%>
						<li class="page-item"><a class="page-link" id="currentpage"><%=p%></a></li>
						<%
								} else { // 현재 페이지의 번호와 반복 중인 p의 값이 다를 때는 일반적인 페이지 버튼으로 출력한다.
						%>
						<li class="page-item"><a
							href="./CommunityDetail.co?comm_number=<%=comm_number%>&pageNum=<%=pageNum%>&commentPageNum=<%=p%>" class="page-link"><%=p%></a></li>
						<%
								}
							}
							// [다음] 버튼을 표시하기
							if (commentEndPage < commentPageCount) { // (끝 페이지가 전체 페이지 수보다 작을 때)	
								// 현재 페이지 번호 = 첫 페이지 - 한 화면에 보여줄 페이지 수의 값을 가진 페이지로 이동
						%>
						<li class="page-item"><a class="page-link"
							href="./CommunityDetail.co?comm_number=<%=comm_number%>&pageNum=<%=pageNum%>&commentPageNum=<%=commentStartPage + commentPageBlock%>"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
								class="sr-only">다음</span>
						</a></li>
					</ul>
						<%
							}
						}								
						%>
				</article>			
				<!-- 페이지 넘버링 -->
			</div>						
			<!-- 사이드 메뉴 -->
			<div class="col-md-4">				
				<!-- 이용 수칙 -->         		
				<div class="card my-4">
					<h5 class="card-header">게임 이야기 이용 수칙</h5>
					<div class="card-body">
					1. 욕설과 비방 금지!<br><br>
					2. 음란물 게재 금지! <br><br>
					3. 광고나 상업성 게시물 금지!<br><br>
					4. 위 사항을 하나라도 위반하면 해당 계정은 영구 정지합니다.
					</div>
				</div>			
				<!-- 이용 수칙 -->
				<!-- 사이드 버튼 -->
				<div class="card my-4" style="display: inline; background-color: rgba(0,0,0,0);">
					<button type="submit" class="boardButton" onclick="location.href='./CommunityList.co'">목록</button>
					<%
					if (mem_id!=null) {	 // 현재 로그인한 상태일 때
						if (mem_id.equals(cb.getMem_id())) {	// 현재 로그인한 회원의 id와 작성자 id가 일치할 때
					%>
					<!-- 작성자만 수정과 삭제가 가능하도록 제어 -->					
					<button type="submit" class="boardButton" onclick="location.href='./CommunityUpdate.co?comm_number=<%=cb.getComm_number()%>&pageNum=<%=pageNum%>'">수정하기</button>
					<button type="submit" class="boardButton" onclick="communityDeleteAction(<%=cb.getComm_number()%>, <%=pageNum%>)">삭제하기</button>
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
