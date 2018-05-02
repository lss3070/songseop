<%@page import="newsDB.NewsBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="goodsDB.GoodsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>::: 게임팜 :::</title>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/modern-business.css" rel="stylesheet">
<style type="text/css">
/* 반응형 웹 */
@media ( min-width : 840px) {
	.col-lg-4 {
		-webkit-box-flex: 0;
		-ms-flex: 0 0 33.333333%;
		flex: 0 0 33.333333%;
		max-width: 100%;
	}
}
@media ( min-width : 992px) {
	.col-lg-4 {
		-webkit-box-flex: 0;
		-ms-flex: 0 0 33.333333%;
		flex: 0 0 33.333333%;
		max-width: 33.333333%;
	}
}
@media ( min-width : 1200px) {
	.col-lg-4 {
		-webkit-box-flex: 0;
		-ms-flex: 0 0 33.333333%;
		flex: 0 0 33.333333%;
		max-width: 33.333333%;
	}
}
@media ( min-width : 1440px) {
	.col-lg-4 {
		-webkit-box-flex: 0;
		-ms-flex: 0 0 33.333333%;
		flex: 0 0 33.333333%;
		max-width: 33.333333%;
	}
}
@media ( min-width : 1720px) {
	.col-lg-4 {
		-webkit-box-flex: 0;
		-ms-flex: 0 0 33.333333%;
		flex: 0 0 33.333333%;
		max-width: 16.15%;
	}
}
/* 반응형 웹 */
h2 {
	text-align: center;
	padding: 0.7em 0;
	color: white;
	font-size: 2em;
}
/* 게시판 모양 */
.btn-block {
	font-weight: 444;
	font-size: 1.5em;
}
.my-4 {
	font-family: Jeju Gothic;
	font-stretch: wider;
	color: #666 !important;
	text-align: center;
	background-color: #ccc;	
	padding: 0.7em 0;
	margin-bottom: 0rem !important;
	margin-top: 0rem !important;
}
.subject {
	font-size: 1.2em;
	color: #777;	
}
.card-body {
	height: 240px;
	flex: 0 0 auto;
}
.card-footer {
	padding-bottom: 0px;
	margin-bottom: 0px;
}
.card-text {	
	font-family: 맑은 고딕;
	font-size: 1.1em;
	font-weight: 444;
	color: #666;
	line-height: 2.2;
	box-sizing: border-box !important;
}
.card-header {
	text-align: center;
}
/* 게시판 모양 */
</style>
</head>
<body>
<%
int newsCount = (Integer)request.getAttribute("newsCount");
List mainNewsList = (List)request.getAttribute("mainNewsList"); 
int goodsCount = (Integer)request.getAttribute("goodsCount");
List newPs4GoodsList = (List)request.getAttribute("newPs4GoodsList");
List newSwitchGoodsList = (List)request.getAttribute("newSwitchGoodsList");
List newXboxGoodsList = (List)request.getAttribute("newXboxGoodsList");
//날짜값 형태를 변환하는 객체를 생성한다.
SimpleDateFormat simpledate = new SimpleDateFormat("yyyy년 MM월 dd일");
//세션에 저장된 아이디 값을 가져온다. (현재 로그인한 아이디)
String mem_id = (String)session.getAttribute("sMem_id");
%>
<!-- 헤더 -->
<jsp:include page="/inc/header.jsp"></jsp:include><br>
<!-- 헤더 -->
	
<!-- 이벤트 & 공지사항 (이미지 크기는 1920*750) --> 
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
	<ol class="carousel-indicators">
		<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
		<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
		<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<%
      	if (newsCount>=3) {	// 공지사항이 3개 이상 존재하면 출력한다.
      		NewsBean nb0 = (NewsBean)mainNewsList.get(0);
      		NewsBean nb1 = (NewsBean)mainNewsList.get(1);
      		NewsBean nb2 = (NewsBean)mainNewsList.get(2);
      	%>
		<!-- Slide One - Set the background image for this slide in the line below -->
		<div class="carousel-item active">
			<a href="./NewsContent.nw?news_number=<%=nb0.getNews_number()%>" ><img src="./upload/<%=nb0.getNews_image()%>"></a>
		</div>	
		<!-- Slide Two - Set the background image for this slide in the line below -->
		<div class="carousel-item">
			<a href="./NewsContent.nw?news_number=<%=nb1.getNews_number()%>" ><img src="./upload/<%=nb1.getNews_image()%>"></a>
		</div>
		<!-- Slide Three - Set the background image for this slide in the line below -->
		<div class="carousel-item">
			<a href="./NewsContent.nw?news_number=<%=nb2.getNews_number()%>" ><img src="./upload/<%=nb2.getNews_image()%>"></a>
		</div>
		<%	      			
      	} else {
      	%>
      	<div class="carousel-item active">
      		공지 사항이 없습니다.
      	</div>		
      	<%
      	}
      	%> 
	</div>
	<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev"> 
	<span class="carousel-control-prev-icon" aria-hidden="true"></span> 
	<span class="sr-only">Previous</span>
	</a> 
	<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next"> 
	<span class="carousel-control-next-icon" aria-hidden="true"></span> 
	<span class="sr-only">Next</span>
	</a>
</div>
<!-- 이벤트 & 공지사항 -->			

<!-- 신상품 소개란 -->
<h2 style="color:#666 !important; font-size: 3em;">- N E W　P R O D U C T -</h2>
<!-- 인기 상품 PS4 -->     
<h2 style="background-color:blue;">P L A Y S T A T I O N 4</h2>
	<div class="container">		
      	<div class="row" >
      	<%
      	if (goodsCount!=0) {	// 물건이 1개라도 존재하면 게시물을 출력한다.
      		for (int p=0; p<newPs4GoodsList.size(); p++) {      			
      			GoodsBean gb = (GoodsBean)newPs4GoodsList.get(p);
      			int goods_id = gb.getGoods_id();
      			int type = Integer.parseInt(gb.getGoods_type());      			
      	%>	 
      		<div class="col-lg-4 mb-4">      			     			
				<div class="card h-100">
					<a href="./Goods.go?goods_id=<%=gb.getGoods_id()%>&platform=1&type=<%=type%>"><img src="./upload/<%=gb.getGoods_image()%>" class=img-fluid rounded"></a>
					<div class="card-body" style="background-color: #EAFBFF; border-top :1px solid #00f; border-bottom :5px solid #00f;">						
						<table id="notice">
							<tr>
								<td class="subject"><%=gb.getGoods_name()%></td>
							</tr>
							<tr>
								<td><span style="color:#00d;" >가격: </span><%=gb.getGoods_price()%>원</td>
							</tr>
							<tr>
								<td><span style="color:#00d;" >입고일: </span><%=gb.getGoods_reg_date()%></td>
							</tr>
							<tr>
								<td><span style="color:#00d;" >제작사: </span><%=gb.getGoods_developer()%></td>
							</tr>
							<tr>
								<td><span style="color:#00d;" >유통사: </span><%=gb.getGoods_publisher()%></td>
							</tr>						
						</table>												
					</div>							
				</div>				
			</div>
		<%	      
			}
      	}
      	%> 	
		</div>
	</div>
	<!-- 인기 상품 PS4 -->
	 
	<!-- 인기 상품 Switch -->
<h2 style="background-color:red;">S W I T C H</h2>      
    <div class="container">
      	<div class="row">
      	<%
      	if (goodsCount!=0) {	// 물건이 1개라도 존재하면 게시물을 출력한다.
      		for (int s=0; s<newSwitchGoodsList.size(); s++) {      			
      			GoodsBean gb = (GoodsBean)newSwitchGoodsList.get(s);
      			int goods_id = gb.getGoods_id();
      			int type = Integer.parseInt(gb.getGoods_type());      			
      	%>	 
      		<div class="col-lg-4 mb-4">      			     			
				<div class="card h-100">
					<a href="./Goods.go?goods_id=<%=gb.getGoods_id()%>&platform=2&type=<%=type%>"><img src="./upload/<%=gb.getGoods_image()%>" class=img-fluid rounded"></a>
					<div class="card-body" style="background-color: #FFDFDC; border-top :1px solid #f00; border-bottom :5px solid #f00;">						
						<table id="notice">
							<tr>
								<td class="subject"><%=gb.getGoods_name()%></td>
							</tr>
							<tr>
								<td><span style="color:#d00;">가격: </span><%=gb.getGoods_price()%>원</td>
							</tr>
							<tr>
								<td><span style="color:#d00;">입고일: </span><%=gb.getGoods_reg_date()%></td>
							</tr>
							<tr>
								<td><span style="color:#d00;">제작사: </span><%=gb.getGoods_developer()%></td>
							</tr>
							<tr>
								<td><span style="color:#d00;">유통사: </span><%=gb.getGoods_publisher()%></td>
							</tr>
						</table>								
					</div>							
				</div>				
			</div>
		<%	      
			}
      	}
      	%> 	
		</div>
	</div>
	<!-- 인기 상품 Switch -->
	
	<!-- 인기 상품 XBOX -->
<h2 style="background-color:green;">X B O X - O N E</h2>      	
    <div class="container">
      	<div class="row">
      	<%
      	if (goodsCount!=0) {	// 물건이 1개라도 존재하면 게시물을 출력한다.
      		for (int x=0; x<newXboxGoodsList.size(); x++) {      			
      			GoodsBean gb = (GoodsBean)newXboxGoodsList.get(x);
      			int goods_id = gb.getGoods_id();
      			int type = Integer.parseInt(gb.getGoods_type());      			
      	%>	 
      		<div class="col-lg-4 mb-4">      			     			
				<div class="card h-100">			
					<a href="./Goods.go?goods_id=<%=gb.getGoods_id()%>&platform=3&type=<%=type%>"><img src="./upload/<%=gb.getGoods_image()%>" class=img-fluid rounded"></a>
					<div class="card-body" style="background-color: #DBFFDF; border-top :1px solid green; border-bottom :5px solid green;">
						<table id="notice">
							<tr>
								<td class="subject"><%=gb.getGoods_name()%></td>
							</tr>
							<tr>
								<td><span style="color:green;">가격: </span><%=gb.getGoods_price()%>원</td>
							</tr>
							<tr>
								<td><span style="color:green;">입고일: </span><%=gb.getGoods_reg_date()%></td>
							</tr>
							<tr>
								<td><span style="color:green;">제작사: </span><%=gb.getGoods_developer()%></td>
							</tr>
							<tr>
								<td><span style="color:green;">유통사: </span><%=gb.getGoods_publisher()%></td>
							</tr>
						</table>		
					</div>							
				</div>				
			</div>
		<%	      
			}
      	}
      	%> 	
		</div>
	</div>
	<!-- 인기 상품 XBOX -->
<!-- 신상품 소개란 -->	
<!-- 푸터 -->
<jsp:include page="/inc/footer.jsp"></jsp:include>
<!-- 푸터 -->	
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
