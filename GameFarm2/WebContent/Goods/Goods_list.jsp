<%@page import="java.text.NumberFormat"%>
<%@page import="goodsDB.GoodsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>::: 게임팜 :::</title>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/modern-business.css" rel="stylesheet">
<!-- 수정가능(안되는경우있음)/ -->
<style type="text/css">
li {
	float: left;
	list-style: none;
	margin: 1px;
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

#clear {
	clear: both;
}

<!--
본체, 주변, 기기타이틀 -->.list_item_category {
	width: 80%;
	margin-left: auto;
	margin-right: auto;
}

.list_item_category ul {
	display: inline-block;
	width: 100%;
	font-size: 0;
}

.list_item_category li {
	display: inline-block;
	width: 33%;
	font-size: 15px;
	text-align: center;
	vertical-align: middle;
}

.list_item_category li a {
	display: block;
	padding: 10px 10px 30px 10px;
	color: #0868AC;
	text-align: center;
	font-size: 20px;
}

.list_item_category li a:hover {
	text-decoration: none;
	font-weight: bold;
}

.list_item_category li em {
	color: #777;
}

.list_item_category li.on, .list_item_category li.on em {
	font-weight: bold;
}

#goods_pick_list {
	float: left;
}

#pick_list_box {
	float: right;
}

#pick_list_box li {
	padding: 0 15px 20px 15px;
}

#pick_list_box a, #goods_pick_list a {
	text-decoration: none;
	color: #777;
}

#pick_list_box a:hover {
	text-decoration: none;
	font-weight: bold;
}

.card-body {
	margin-left: auto;
	margin-right: auto;
}

.card-body a, .card-body p {
	text-align: center;

}

.card-body a:hover {
	text-decoration: none;
}

.input-group {
	width: 60%;
	margin-left: auto;
	margin-right: auto;
	padding: 0 0 30px 0;
}
</style>

<script type="text/javascript">
	function searchCheck() {
		if (document.fr.search.value.length == 0) {
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
		int count = (Integer) request.getAttribute("count");
		String pageNum = (String) request.getAttribute("pageNum");
		List getGoodsList = (List) request.getAttribute("getGoodsList");
		int pageCount = (Integer) request.getAttribute("pageCount");
		int pageBlock = (Integer) request.getAttribute("pageBlock");
		int currentPage = (Integer) request.getAttribute("currentPage");
		int startPage = (Integer) request.getAttribute("startPage");
		int endPage = (Integer) request.getAttribute("endPage");
		int startRow = (Integer) request.getAttribute("startRow");
		int endRow = (Integer) request.getAttribute("endRow");
		int maxPage = (Integer) request.getAttribute("maxPage");
		String mem_id = (String) session.getAttribute("sMem_id");
		int platform = (Integer) request.getAttribute("platform");
		int type = (Integer) request.getAttribute("type");
	%>

	<!-- 헤더 -->
	<jsp:include page="../inc/header.jsp"></jsp:include><br>

	<!-- 헤더 -->
	<!-- Page Content -->
	<div class="container">
		<!-- Page Heading -->
		<h1 class="my-4">
			<small> <%
 	if (platform == 1) {
 %> PS4 <%
 	} else if (platform == 2) {
 %> SWITCH <%
 	} else {
 %> XBOX <%
 	}
 %>
			</small>
		</h1>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">
				<!-- 상단의 home글자 다음에 나오는 상품의 플랫폼 --> <%
 	if (platform == 1) {
 %> PS4 <%
 	} else if (platform == 2) {
 %> SWITCH <%
 	} else {
 %> XBOX <%
 	}
 %>
			</li>

			<li class="breadcrumb-item active">
				<!-- 상단의 home글자 다음에 나오는 상품의 타입 --> <%
 	if (type == 1) {
 %> 본체 및 패키지 <%
 	} else if (type == 2) {
 %> 게임 소프트 <%
 	} else {
 %> 주변기기 <%
 	}
 %>
			</li>
		</ol>

		<!-- 상단의 상품분류 클릭시 이동하게끔 -->
		<div class="list_item_category">
			<ul>

				<li class=""><a
					href="./GoodsList.go?platform=<%=platform%>&type=1"><span>본체
							/ 패키지 </span></a></li>
				<li class=""><a
					href="./GoodsList.go?platform=<%=platform%>&type=2"><span>게임
							소프트 </span></a></li>
				<li class=""><a
					href="./GoodsList.go?platform=<%=platform%>&type=3"><span>주변기기
					</span></a></li>
			</ul>
		</div>
		
		<div id="goods_pick_list">
			<%
				if (count != 0) {// 상품이 하나 이상 있을 때
			%>
			<a><span class="pick_list_num">총 <strong><%=getGoodsList.size()%></strong>
					개
			</span></a>
			<%
				}
			%>
		</div>

		<!-- 상품의 정렬 -->
		<div id="pick_list_box">
			<ul class="pick_list">
				<li><a
					href="GoodsList.go?platform=<%=platform%>&type=<%=type%>&sort=goods_price">낮은가격순</a>
				</li>
				<li><a
					href="GoodsList.go?platform=<%=platform%>&type=<%=type%>&sort=goods_price desc">높은가격순</a>
				</li>
				<li><a
					href="GoodsList.go?platform=<%=platform%>&type=<%=type%>&sort=goods_reg_date desc">등록일순</a>
				</li>
			</ul>
		</div>
		<!-- 상품의 정렬끝 -->
		
		<!-- 상품의 리스트 -->
		<div id="clear"></div>
		<div class="row">
			<%
				int z = 1;
				if (count != 0) { // 상품이 하나 이상 존재할 때
					for (int i = 0; i < getGoodsList.size(); i++) {
						GoodsBean gb = (GoodsBean) getGoodsList.get(i);
			%>
			<%
				if (gb.getGoods_stock() >= 1) {//재고수가 하나 이상일때
			%>
			
			<div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">
				<div class="card h-100">
					<!-- 상품의 썸네일 이미지 -->
					<a href="./Goods.go?goods_id=<%=gb.getGoods_id()%>&platform=<%=platform%>&type=<%=type%>"">
						<img class="card-img-top" src="./upload/<%=gb.getGoods_image()%>"alt="" style="width: 100%">
					</a>
					<div class="card-body">
						<h4 class="card-title">
						<!-- 상품의 이름 -->
							<a href="./Goods.go?goods_id=<%=gb.getGoods_id()%>&pageNum=<%=pageNum%>&platform=<%=platform%>&type=<%=type%>"><%=gb.getGoods_name()%></a>
						</h4>
						<p class="card-text">
						<!-- 상품의 재고 -->
							<%=NumberFormat.getInstance().format(gb.getGoods_price()) + "원"%></p>
						<p class="card-text"><%="재고 있음"%></p>
					</div>
				</div>
			</div>
			<%
				}
					} // for문의 끝
				} else { // 상품이 존재하지 않을 때
			%>
			<div style="text-align: center; margin: auto;">상품이 준비되어 있지않습니다.</div>
			<%
				}//else문 끝
			%>
		</div>
	</div>
	<!-- 상품리스트의 끝 -->

	<!-- 페이징 -->
	<ul class="pagination justify-content-center">
		<%if(endPage>1){//페이지가 하나밖에 없을경우 나오지 않게끔
			if (startPage > pageBlock) {//이전페이지
		%>
		<li class="page-item"><a class="page-link"
			href="GoodsList.go?pageNum=<%=startPage - pageBlock%>&platform=<%=platform%>&type=<%=type%>"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
				class="sr-only">Previous</span>
		</a></li>
		<%
			}
			for (int i = startPage; i <= endPage; i++) {//페이지 숫자
		%>
		<li class="page-item"><a class="page-link"
			href="GoodsList.go?pageNum=<%=i%>&platform=<%=platform%>&type=<%=type%>"><%=i%></a></li>
		<%
			}
			if (endPage < pageCount) {//다음페이지
		%>
		<li class="page-item"><a class="page-link"
			href="GoodsList.go?pageNum=<%=startPage + pageBlock%>&platform=<%=platform%>&type=<%=type%>"
			aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
				class="sr-only">Next</span>
		</a></li>
		<%
			}
		}
		%>
	</ul>
	<!-- 페이징 끝 -->

	<!-- 검색 -->
	<form action="./GoodsList.go" name="fr">
		<div class="input-group">

			<select name="searchOption" class="search_select">
				<option value="0"<%-- <%=searchOption==0 ? "selected" : "" %> --%>>제목</option>
				<option value="1">개발사</option>
			</select> <input type="text" class="form-control" placeholder="검색어를 입력하세요."
				name="search" width="0"> <input type="hidden"
				name="platform" value="<%=platform%>"> <input type="hidden"
				name="type" value="<%=type%>"> <span class="input-group-btn">
				<button class="btn btn-secondary" type="button"
					onclick="searchCheck()">검색</button>
			</span>
		</div>
	</form>
	<!-- 검색 -->

	<!-- /.container -->

	<!-- 푸터 -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
	<!-- 푸터 -->

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
