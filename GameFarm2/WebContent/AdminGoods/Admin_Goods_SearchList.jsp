<%@page import="java.text.NumberFormat"%>
<%@page import="goodsDB.GoodsBean"%>
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
article table#notice td {
	text-align: center;
	padding: 6px 0;
	background-color: #eee;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999;
	color: #449;
}
article table#notice td#admin {
	text-align: center;
	padding: 6px 0;
	background-color: #ccc;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999;
	color: #449;
}
article table#notice td#admin:HOVER {
	cursor: default;
}
article table#notice td#adminsubject {
	background-color: #ccc;
	text-align: left;
	padding-left: 1.5em;
	text-decoration: none;
	color: #555;
}
article table#notice td.left {
	text-decoration: none;
	color: #555;
}
article table#notice td.left:HOVER {
	cursor: default;
}
article th.gId {
	width: 80px;
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
}
article th.gImg {
	width: 100px;
}

article th.gName {
	width: 300px;
	letter-spacing: 0.7em;	
} 
article th.gCost {
	width: 120px;
}
article th.gPrice {
	width: 120px;
}
article th.gStock {
	width: 80px;
}
article th.gPlat {
	width: 100px;
}
article th.gDate {
	width: 130px;
}
article th.gButton {
	width: 100px;
}
#subject {
	color: #449;
}

#subject:HOVER {
	color: red;
	text-decoration: none;
}

/* 게시판 모양 */

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
/* 페이지 부분 */
#currentpage {
	font-weight: bold;
	color: red;	
}
/* 페이지 부분 */
/* 버튼 부분 */

/* 검색 파트 */
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
//액션에서 리퀘스트 값을 받아온다.
String search = (String)request.getAttribute("search");
String pageNum = (String)request.getAttribute("pageNum");
int searchCount = (Integer)request.getAttribute("searchCount");
List getSearchList = (List)request.getAttribute("getSearchList");
int pageCount = (Integer)request.getAttribute("pageCount");
int pageBlock = (Integer)request.getAttribute("pageBlock");
int currentPage = (Integer)request.getAttribute("currentPage");
int startPage = (Integer)request.getAttribute("startPage");
int endPage = (Integer)request.getAttribute("endPage");
int startRow = (Integer)request.getAttribute("startRow");
int endRow = (Integer)request.getAttribute("endRow");
int maxPage = (Integer)request.getAttribute("maxPage");
//세션값 가져오기
String mem_id=(String)session.getAttribute("Mem_id");
//저장된 정보 가져오기
%>
<!-- 헤더 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 헤더 -->
	<!-- 본문 -->
	<div class="container">
		<br>		
		<!-- 페이지 위치 -->
		<h1 class="mt-4 mb-3">	상품리스트</h1>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">Goods_List</li>
		</ol>
		<!-- 페이지 위치 -->
				
		<!-- 전체 회원수 -->
		
		<!-- 회원정보 -->
			<article>
			<%-- <h2><marquee behavior="alternate" scrolldelay="200" scrollamount="25" direction="left">[<%=count %>개의 글이 있습니다! ]</marquee></h2> --%>
			<table id="notice">
				<tr>
					<th class="gId">ID</th>
					<th class="gImg">상품사진</th>
					<th class="gName">상품명</th>
					<th class="gCost">상품입고가</th>
					<th class="gPrice">상품출고가</th>
					<th class="gStock">재고량</th>
					<th class="gPlat">구동기기</th>
					<th class="gDate">등록일자</th>
					<th class="gButton">수정/삭제</th>
				</tr>
				<%
				if (searchCount!=0) { // 게시물이 하나 이상 존재할 때						
					for (int c=0; c<getSearchList.size(); c++) {	// 가져온 리스트의 갯수만큼 행을 반복하여 늘려준다.
						GoodsBean gb = (GoodsBean)getSearchList.get(c);	// 리스트에 담긴 회원 정보의 값들을 가져온다.
				%>
						<tr>
					<td><%=gb.getGoods_id()%></td>
					<td><a href="./Goods.go?goods_id=<%=gb.getGoods_id()%>&platform=<%=gb.getGoods_platform()%>&type=<%=gb.getGoods_type()%>">
						<img src="./upload/<%=gb.getGoods_image()%>"  width="80" height="80"></a></td>
					<td class="left">												
					<a href="./Goods.go?goods_id=<%=gb.getGoods_id()%>&platform=<%=gb.getGoods_platform()%>&type=<%=gb.getGoods_type()%>" id="subject"><%=gb.getGoods_name()%></a>
					</td>
					<td><%=NumberFormat.getInstance().format(gb.getGoods_cost())%>원</td>
					<td><%=NumberFormat.getInstance().format(gb.getGoods_price())%>원</td>
					<td><%=gb.getGoods_stock()%></td>
					<td><%if(gb.getGoods_platform().equals("01")){
						%> PS4 <%
					} else if (gb.getGoods_platform().equals("02")) {
						%> SWITCH <%
					} else{
						%> XBOX <%
					}
						%></td>
					<td><%=gb.getGoods_reg_date()%></td>
					<td><a href="./AdminGoodsModify.ag?goods_id=<%=gb.getGoods_id()%>">수정 </a>
   					   /<a href="./AdminGoodsDeleteAction.ag?goods_id=<%=gb.getGoods_id()%>"> 삭제</a></td>
				</tr>
				<%	 	
						}
						
				} else { // 상품정보가 하나도 없을 때
				%>
						<tr>
						<td colspan="5">검색된 상품정보가 없습니다.</td>
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
			<a href="./AdminGoodsList.ag?pageNum=<%=startPage-pageBlock%>"></a>
			<li class="page-item"><a class="page-link" href="./AdminGoodsList.ag?pageNum=<%=startPage-pageBlock%>"
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
					<li class="page-item"><a href="./AdminGoodsList.ag?pageNum=<%=p%>" class="page-link"><%=p%></a></li>				
				<%
				}
			}
			// [다음] 버튼을 표시하기
			if (endPage < pageCount) {	// (끝 페이지가 전체 페이지 수보다 작을 때)	
			// 현재 페이지 번호 = 첫 페이지 - 한 화면에 보여줄 페이지 수의 값을 가진 페이지로 이동
			%>
			<li class="page-item"><a class="page-link" href="./AdminGoodsList.ag?pageNum=<%=startPage+pageBlock%>"aria-label="Next"> 
			<span aria-hidden="true">&raquo;</span> <span class="sr-only">다음</span>
			</a></li>			
		</ul>
		<%
			}
		}
		%>
		</article>
		<!-- 페이지 넘버링 -->
		
		<!-- 목록 -->
		<div id="buttonfield">
		<button type="button" id="joinbutton" onclick="location.href='AdminGoodsList.ag'">목록</button>		
		</div>
		<!-- 목록 -->	
		
			<!-- 검색 파트 -->
		<form action="./AdminGoodsSearchList.ag" name="fr">
		<div class="input-group">
		
		<input type="text" class="form-control" 
		  placeholder="상품명을 입력하세요." name="search"> <span class="input-group-btn">
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
