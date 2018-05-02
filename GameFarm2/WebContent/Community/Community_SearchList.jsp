<%@page import="java.text.SimpleDateFormat"%>
<%@page import="communityDB.*"%>
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
#customerTable {		
	border: none;
	-webkit-box-flex: 1;
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	padding: 0.25rem;
	margin-left: 2em;
	margin-top: -1.25em;
	margin-bottom: 1.1em;		
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

/* 게시판 모양 */
article {
	text-align: center;
}
article table#notice {
	width: 100%;
	border-collapse: separate;
}
article table#notice th {
	text-align: center;
	height: 20px;
	font-size: 18px;
	font-weight: bold;
	padding-left: 5px;
	background-image: url("./img/center/t_back.jpg");
	background-repeat: repeat-x;
	background-position: center center;
	color: #FFF;	
}
article table#notice tr:HOVER {
	background-color: #bbb;	
}
article table#notice td {
	text-align: center;
	padding: 6px 0;	
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999;
	color: #449;
}
article table#notice tr#admin {
	text-align: center;
	padding: 6px 0;
	background-color: #ccc;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999;
	color: #449;
}
article table#notice tr#admin:HOVER {
	cursor: default;
	background-color: #bbb;
}
article table#notice td#adminsubject {	
	text-align: left;
	padding-left: 1.5em;
	text-decoration: none;
	color: #555;
}
article table#notice td.left {
	text-align: left;
	padding-left: 1.5em;
	text-decoration: none;
	color: #555;
}
article table#notice td.left:HOVER {
	cursor: default;
}
article th.tno {
	width: 70px;
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
}
article th.timage {
	width: 120px;
	letter-spacing: 0.7em;	
} 
article th.ttitle {
	width: 400px;
	letter-spacing: 0.7em;
}
article th.twrite {
	width: 200px;
}
article th.tdate {
	width: 200px;
}
article th.tread {
	width: 80px;
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
}
#subject {
	color: #449;
}
#subject:HOVER {
	color: red;
	text-decoration: none;
}
.mem_search {
	color: #blue;
}
.mem_search:HOVER {
	color: purple;
	text-decoration: none;
} 
/* 게시판 모양 */

/* 페이지 부분 */
#currentpage {
	font-weight: bold;
	color: red;	
}
/* 페이지 부분 */

/* 버튼 부분 */
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
}
#joinbutton:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	cursor: pointer;	
}
/* 버튼 부분 */

/* 검색 파트 */
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
.search_select {
	font-size: 1.2em;
	height: 48px;
	width: 110px;
	border-radius: 10px;
	background-color: #ffffff;
	border: 1px solid #fff;
	padding-left: 3px;
}
.form-control {
	display: inline-block;
	width: 150%;
	padding: .375rem .75rem;
	font-size: 18px;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}
.btn-secondary {
	height: 48px;	
	font-size: 22px;
}
.btn-secondary:HOVER {
	cursor: pointer;
	font-weight: bold;
}
/* 검색 파트 */

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
<script type="text/javascript">
	function searchCheck() {
		if (document.fr.search.value.length==0) {
			alert("검색어를 입력해주세요!");
			document.fr.search.select();
			return;
		}
		document.fr.submit();
	}	
</script>
</head>
<body>
<%
// 액션에서 리퀘스트 값을 받아온다.
String search = (String)request.getAttribute("search");
int searchOption = (Integer)request.getAttribute("searchOption");
int searchCount = (Integer)request.getAttribute("searchCount");
String pageNum = (String)request.getAttribute("pageNum");
List getSearchList = (List)request.getAttribute("getSearchList");
int pageCount = (Integer)request.getAttribute("pageCount");
int pageBlock = (Integer)request.getAttribute("pageBlock");
int currentPage = (Integer)request.getAttribute("currentPage");
int startPage = (Integer)request.getAttribute("startPage");
int endPage = (Integer)request.getAttribute("endPage");
int adminCount = (Integer)request.getAttribute("adminCount");
List getAdminList = (List)request.getAttribute("getAdminList");
int hotkeyCount = (Integer)request.getAttribute("hotkeyCount");
int minhotkey = (Integer)request.getAttribute("minhotkey");
List getHotkeyList = (List)request.getAttribute("getHotkeyList");
int startRow = (Integer)request.getAttribute("startRow");
int endRow = (Integer)request.getAttribute("endRow");
int maxPage = (Integer)request.getAttribute("maxPage");
//세션에 저장된 아이디 값을 가져온다. (현재 로그인한 아이디)
String mem_id = (String)session.getAttribute("sMem_id");
//날짜값 형태를 변환하는 객체를 생성한다.
SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
System.out.println("페이지수: "+pageCount);
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
		
		<!-- 자주찾는 검색어 + 이용 수칙 -->
				<div class="card advance">
				<h5 class="card-header">
					<a class="collapsed" data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" aria-expanded="false"
							aria-controls="collapseOne">
							자주 찾는 검색어
					</a>
					</h5>					
					<div id="collapseOne" class="collapse" role="tabpanel"
					aria-labelledby="headingOne">
						<div class="row">
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
								<%								
								if (hotkeyCount>=6) {	// 검색어 테이블에 값이 존재하면?
									for (int hot=0; hot<3; hot++) {	// 검색어 순위 1위부터 3위까지 반복한다.
										HotkeyBean hb = (HotkeyBean)getHotkeyList.get(hot);	// 리스트에 담긴 검색어의 값들을 가져온다.
								%>	
									<li><%=(hot+1)+"위: ("+hb.getSearchcount()+"회 검색) "%><a href="./CommunitySearchList.co?searchOption=0&search=<%=hb.getKeyword()%>"><%=hb.getKeyword()%></a></li>									
								<%
									}		
								} else if (hotkeyCount<6) { // 검색어 테이블이 비어있다면? %>
								<li>인기 검색어가 없습니다.</li>
								<%
								}
								%>								
								</ul>
							</div>
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
								<%								
								if (hotkeyCount>=12) {	// 검색어 테이블에 값이 존재하면?for (int hot=3; hot<6; hot++) {	// 검색어 순위 4위부터 6위까지 반복한다.
									for (int hot=3; hot<6; hot++) {	// 검색어 순위 4위부터 6위까지 반복한다.
										HotkeyBean hb = (HotkeyBean)getHotkeyList.get(hot);	// 리스트에 담긴 검색어의 값들을 가져온다.
								%>	
									<li><%=(hot+1)+"위: ("+hb.getSearchcount()+"회 검색) "%><a href="./CommunitySearchList.co?searchOption=0&search=<%=hb.getKeyword()%>"><%=hb.getKeyword()%></a></li>									
								<%
									}
								} else if (hotkeyCount<12) { // 검색어 테이블이 비어있다면? %>
								<li></li>
								<%
								}
								%>
								</ul>
							</div>
						</div>
					</div>
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
				<hr> 
		<!-- 자주찾는 검색어 + 이용 수칙 -->		
		
		<!-- 게시판 -->
			<article>
			<%-- <h2><marquee behavior="alternate" scrolldelay="200" scrollamount="25" direction="left">[<%=count %>개의 글이 있습니다! ]</marquee></h2> --%>
			<table id="notice">
				<tr>
					<th class="tno">번호</th>
					<th class="ttitle">제목</th>
					<th class="twrite">작성자</th>
					<th class="tdate">작성일</th>
					<th class="tread">조회수</th>
				</tr>
				<%				
				if (adminCount!=0) {	// 공지사항이 1개라도 존재하면?
					// 관리자 글들을 출력한다.					
					for (int a=0; a<getAdminList.size(); a++) {
						CommunityBean ab = (CommunityBean)getAdminList.get(a);	// 리스트에 담긴 회원 정보의 값들을 가져온다.
						%>
						<tr id="admin">
								<td><%=ab.getComm_number()%></td>
								<td id="adminsubject"><a href="./CommunityDetail.co?comm_number=<%=ab.getComm_number()%>&pageNum=<%=pageNum%>" id="subject"><%=ab.getComm_subject()%></a>
								<%	
									CommunityDAO cdao = new CommunityDAO();
									int commentCount = cdao.getCommentCount(ab.getComm_number());
									if(ab.getComm_image()!=null) {	// 첨부 이미지가 존재할 때
								%>
										<img src="./img/pictureExist.png">
								<%	
									}
									if (commentCount >= 1) { 	// 댓글이 하나 이상 존재하면 댓글의 갯수를 출력한다.
									%>
											<span style="color: green;">[<%=commentCount%>]</span>
									<%	
										}	
								%>
								</td>		
								<td><%=ab.getMem_id()%></td>
								<td><%=simpledate.format(ab.getComm_reg_date())%></td>
								<td><%=ab.getComm_readcount()%></td>
						</tr>
					<%			
					}
				} else { // 게시물이 하나도 없을 때					
				}				 
				%>
				<%
				// 배열 boardList 값을 초기화 해준다.
				// List getCoList = null; 
					if (searchCount!=0) { // 게시물이 하나 이상 존재할 때						
						for (int c=0; c<getSearchList.size(); c++) {	// 가져온 리스트의 갯수만큼 행을 반복하여 늘려준다.
							CommunityBean cb = (CommunityBean)getSearchList.get(c);	// 리스트에 담긴 회원 정보의 값들을 가져온다.
							%>
							<tr>
								<td><%=cb.getComm_number()%></td>
								<td class="left">																
								<a href="./CommunityDetail.co?comm_number=<%=cb.getComm_number()%>&pageNum=<%=pageNum%>" id="subject"><%=cb.getComm_subject()%></a>
									<%
										CommunityDAO cdao = new CommunityDAO();
										int commentCount = cdao.getCommentCount(cb.getComm_number());
										if(cb.getComm_image()!=null) {	// 첨부파일이 존재할 때 
									%>
											<img src="./img/pictureExist.png">
									<%	
										}	
										if (commentCount >= 1) { 	// 댓글이 하나 이상 존재하면 댓글의 갯수를 출력한다.
									%>		
											<span style="color: green;">[<%=commentCount%>]</span>
									<%
										}		
									%>
								</td>		
								<td><a href="./CommunitySearchList.co?searchOption=3&search=<%=cb.getMem_id()%>" class="mem_search" data-tooltip-text="이 작성자가 작성한 글을 검색합니다."><%=cb.getMem_id()%></a></td>
								<td><%=simpledate.format(cb.getComm_reg_date())%></td>
								<td><%=cb.getComm_readcount()%></td>
							</tr>
							<%
							}
					} else { // 게시물이 하나도 없을 때
						%>
						<tr>
						<td colspan="5">검색된 게시물이 없습니다.</td>
						</tr>
					<%	
					}
				%>
			</table>
			</article>		
			<br>
		<!-- 게시판 -->

		<!-- 페이지 넘버링 -->
		<article>
		<ul class="pagination justify-content-center">
		<%		
		if (pageCount==1) {
			// 최대 페이지가 1페이지 일때는 페이지 넘버링을 하지 않는다.
		} else {
			if (pageCount < endPage) {// 전체 페이지 번호가 끝 페이지 번호보다 작을 때
				//끝 페이지 번호의 값에 전체 페이지 번호를 대입한다.
				endPage = pageCount;
			}
			// [이전] 버튼을 표시하기	
			if (startPage > pageBlock) {	// (시작 페이지가 한 화면당 페이지 수보다 클 때)	
				// 현재 페이지 번호 = 첫 페이지 - 한 화면에 보여줄 페이지 수의 값을 가진 페이지로 이동
				%>
			<a href="./CommunitySearchList.co?pageNum=<%=startPage-pageBlock%>"></a>
			<li class="page-item"><a class="page-link" href="./CommunitySearchList.co?searchOption=<%=searchOption%>&search=<%=search%>&pageNum=<%=startPage-pageBlock%>"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span class="sr-only">
			이전</span>	</a></li>
			<%
			}
			// 페이지 버튼 출력하기
			for (int p = startPage; p<=endPage; p++) {	// 페이지의 최대 갯수를 계산해주고 그 값을 최대 페이지로 지정하여 for문을 생성	
				if (currentPage == p) { // 현재 페이지의 번호와 반복 중인 p의 값이 동일할 때 작동하지 않는 다른 버튼으로 대체한다. 
				%>
					<li class="page-item"><a class="page-link" id="currentpage"><%=p%></a></li>
				<%
				} else {	 // 현재 페이지의 번호와 반복 중인 p의 값이 다를 때는 일반적인 페이지 버튼으로 출력한다. 
				%>
					<li class="page-item"><a href="./CommunitySearchList.co?searchOption=<%=searchOption%>&search=<%=search%>&pageNum=<%=p%>" class="page-link"><%=p%></a></li>				
				<%
				}
			}
			// [다음] 버튼을 표시하기
			if (endPage < pageCount) {	// (끝 페이지가 전체 페이지 수보다 작을 때)	
			// 현재 페이지 번호 = 첫 페이지 - 한 화면에 보여줄 페이지 수의 값을 가진 페이지로 이동
			%>
			<li class="page-item"><a class="page-link" href="./CommunitySearchList.co?searchOption=<%=searchOption%>&search=<%=search%>&pageNum=<%=startPage+pageBlock%>"aria-label="Next"> 
			<span aria-hidden="true">&raquo;</span> <span class="sr-only">다음</span>
			</a></li>			
		</ul>
		<%
			}
		}
		%>
		<!-- 페이지 넘버링 -->
		</article>
		
		<!-- 목록, 글쓰기 등 버튼 처리부 -->
		<div id="buttonfield">
		<button type="button" id="joinbutton" onclick="location.href='CommunityList.co'">목록</button>		
		<%
		if (mem_id!=null) { // 로그인한 상태일 때 글쓰기 버튼을 보이게 한다.
		%>
		<button type="button" id="joinbutton" onclick="location.href='./CommunityInsertForm.co'">글쓰기</button>　
		<%
		} else {	// 로그인을 아직 안했을 때 글쓰기 버튼이 보이지 않는다.
		}%>			　
		</div>
		<!-- 목록, 글쓰기 등 버튼 처리부 -->		
		
		<!-- 검색 파트 -->
		<form action="./CommunitySearchList.co" name="fr">
		<div class="input-group">
			<select name="searchOption" class="search_select">
				<option value="0" <%-- <%=searchOption==0 ? "selected" : "" %> --%>>제목</option>
				<option value="1" >내용</option>
				<option value="2" >제목+내용</option>
				<option value="3" >작성자</option>
			</select>
		<input type="text" class="form-control" 
		  placeholder="검색어를 입력하세요." name="search"> <span class="input-group-btn">
		<button class="btn btn-secondary" type="button" onclick="searchCheck()">검색</button>
		</span>
		</div>
		</form>		
		<!-- 검색 파트 -->
	</div>	
<!-- 푸터 -->
<jsp:include page="../inc/footer.jsp"></jsp:include>
<!-- 푸터 -->
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
