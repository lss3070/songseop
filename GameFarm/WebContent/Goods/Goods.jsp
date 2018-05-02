<%@page import="java.text.NumberFormat"%>
<%@page import="goodsDB.ReviewBean"%>
<%@page import="goodsDB.GoodsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%
	GoodsBean gb = (GoodsBean) request.getAttribute("gb");
	List getReviewList = (List) request.getAttribute("getReviewList");
	String mem_id = (String) session.getAttribute("sMem_id");
	int platform = (Integer) request.getAttribute("platform");
	int type = (Integer) request.getAttribute("type");
	int count = (Integer)request.getAttribute("count");
	String pageNum = (String)request.getAttribute("pageNum");
	int pageCount = (Integer)request.getAttribute("pageCount");
	int pageBlock = (Integer)request.getAttribute("pageBlock");
	int currentPage = (Integer)request.getAttribute("currentPage");
	int startPage = (Integer)request.getAttribute("startPage");
	int endPage = (Integer)request.getAttribute("endPage");
	int startRow = (Integer)request.getAttribute("startRow");
	int endRow = (Integer)request.getAttribute("endRow");
	int maxPage = (Integer)request.getAttribute("maxPage");
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>::: 게임팜 :::</title>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/4-col-portfolio.css" rel="stylesheet">
<style type="text/css">
@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
.starname{
padding-top:1%;
float:left
}
.rating { 
  float: left;
  padding: 0.1% 0 0 2%;
}

.rating > input { display: none; } 
.rating > label:before { 
  margin: 5px;
  font-size: 1.25em;
  font-family: FontAwesome;
  display: inline-block;
  content: "\f005";
}

.rating > .half:before { 
  content: "\f089";
  position: absolute;
}

.rating > label { 
  color: black; 
 float: right; 
}

.rating2 { 
  float: left;
}

.rating2 > input { display: none; } 
.rating2 > label:before { 
  margin: 5px;
  font-size: 1.25em;
  font-family: FontAwesome;
  display: inline-block;
  content: "\f005";
}

/***** 별색 *****/
.rating2 > label {
  color: #FFD700; 
 float: right; 
}


.rating > input:checked ~ label, /* show gold star when clicked */
.rating:not(:checked) > label:hover, /* hover current star */
.rating:not(:checked) > label:hover ~ label { color: #FFD700;  } /* hover previous stars in list */

.rating > input:checked + label:hover, /* hover current star when changing rating */
.rating > input:checked ~ label:hover,
.rating > label:hover ~ input:checked ~ label, /* lighten current selection */
.rating > input:checked ~ label:hover ~ label { color: #FFED85;  } 
</style>
<style>

#img {
	height: 75%;
	width: 100%
}

/* 마스크 뛰우기 */
#mask {
	position: absolute;
	z-index: 9000;
	background-color: #000;
	display: none;
	left: 0;
	top: 0;
}
/* 팝업으로 뜨는 윈도우 css  */
.window1, .window2, .window3, .window4 {
	display: none;
	position: absolute;
	left: 40%;
	top: 20%;
	margin-left: -30%;
	width: 80%; /* 수정.  */
	height: 70%;
	z-index: 10000;
}

.window1 iframe, .window1 img {
	vertical-align: middle;
} /* 수정(추가).  */
#exit {
	border: 1px solid red;
	float: right;
}

.review-body {
	width: 80%
}

.review_score {
	float: left;
}

.review_date {
	float: right;
}

.review_content {
	float: none;
}

.mt-0 {
	width: 15%
}

.ReviewButton {
	float: right;
}

.starRating, .starRating span {
	display: inline-block;
	height: 14px;
	height: 14px;
	background: transparent url(icoFiveStar.gif) no-repeat;
	overflow: hidden;
}

.starRating {
	width: 79px;
	vertical-align: middle;
}

.starRating span {
	font-size: 0;
	line-height: 0;
	vertical-align: top;
	text-indent: -100px;
	*text-indent: 0;
	background-position: 0 -14px;
}
.reviewButton {
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	color: #fff;
	height: 50px;
	width: 100px;
	font-family: 'Jeju Hallasan';
	font-size: 22px;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	//큰화면으로 나오는 이미지
	for (i = 1; i < 5; i++) {
		function wrapWindowByMask(i) {
			//화면의 높이와 너비를 구한다.
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();
			//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
			$("#mask").css({
				"width" : maskWidth,
				"height" : maskHeight
			});
			//애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.
			$("#mask").fadeIn(0);
			$("#mask").fadeTo("slow", 0.6);
			//윈도우 같은 거 띄운다.
			$(".window" + i).show();
		}
	}
	$(document).ready(function() {
						//첫번째이미지클릭시나오게끔
						$(".openMask1").click(function(e) {
							e.preventDefault();
							wrapWindowByMask(1);
						});
						//닫기 버튼을 눌렀을 때
						$(".window1 .close").click(function(e) {
							//링크 기본동작은 작동하지 않도록 한다.
							e.preventDefault();
							$("#mask, .window1").hide();
						});
						//검은 막을 눌렀을 때
						$("#mask").click(function() {
							$(this).hide();
							$(".window1").hide();
						});
						//두번째 이미지 클릭시 두번째 이미지 나오게끔
						$(".openMask2").click(function(e) {
							e.preventDefault();
							wrapWindowByMask(2);
						});
						//닫기 버튼을 눌렀을 때
						$(".window2 .close").click(function(e) {
							//링크 기본동작은 작동하지 않도록 한다.
							e.preventDefault();
							$("#mask, .window2").hide();
						});
						//검은 막을 눌렀을 때
						$("#mask").click(function() {
							$(this).hide();
							$(".window2").hide();
						});

						//세번쨰 이미지 클릭시 세번째 이미지 나오게끔
						$(".openMask3").click(function(e) {
							e.preventDefault();
							wrapWindowByMask(3);
						});
						//닫기 버튼을 눌렀을 때
						$(".window3 .close").click(function(e) {
							//링크 기본동작은 작동하지 않도록 한다.
							e.preventDefault();
							$("#mask, .window3").hide();
						});
						//검은 막을 눌렀을 때
						$("#mask").click(function() {
							$(this).hide();
							$(".window3").hide();
						});
						//네번째 이미지 클릭시 네번째 이미지 나오게끔
						$(".openMask4").click(function(e) {
							e.preventDefault();
							wrapWindowByMask(4);
						});
						//닫기 버튼을 눌렀을 때
						$(".window4 .close").click(function(e) {
							//링크 기본동작은 작동하지 않도록 한다.
							e.preventDefault();
							$("#mask, .window4").hide();
						});
						//검은 막을 눌렀을 때
						$("#mask").click(function() {
							$(this).hide();
							$(".window4").hide();
						});
						//1번에서 뒤로        

						$(".back1").click(function(e) {
							$(this).closest("div").hide();
							$("#mask").hide();
							e.preventDefault();
							wrapWindowByMask(4);
						});
						//1번에서 앞으로
						$(".next1").click(function(e) {
							$(this).closest("div").hide();
							$("#mask").hide();
							e.preventDefault();
							wrapWindowByMask(2);
						});
						//2번에서 뒤로
						$(".back2").click(function(e) {
							$(this).closest("div").hide();
							$("#mask").hide();
							e.preventDefault();
							wrapWindowByMask(1);
						});
						//2번에서 앞으로
						$(".next2").click(function(e) {
							$(this).closest("div").hide();
							$("#mask").hide();
							e.preventDefault();
							wrapWindowByMask(3);
						});
						//3번에서 뒤로
						$(".back3").click(function(e) {
							$(this).closest("div").hide();
							$("#mask").hide();
							e.preventDefault();
							wrapWindowByMask(2);
						});
						//3번에서 앞으로
						$(".next3").click(function(e) {
							$(this).closest("div").hide();
							$("#mask").hide();
							e.preventDefault();
							wrapWindowByMask(4);
						});
						//4번에서 뒤로
						$(".back4").click(function(e) {
							$(this).closest("div").hide();
							$("#mask").hide();
							e.preventDefault();
							wrapWindowByMask(3);
						});
						//4번에서 앞으로
						$(".next4").click(function(e) {
							$(this).closest("div").hide();
							$("#mask").hide();
							e.preventDefault();
							wrapWindowByMask(1);
						});
						
						//리뷰버튼 클릭시 삭제
						$(".ReviewButton")
								.click(
										function(e) {
											if (confirm("정말 삭제하시겠습니까??") == true) {
												location.href = './ReviewDeleteAction.go?review_number='
														+ $(this).val()
														+ '&platform='
														+ document
																.getElementById("platform").value
														+ '&type='
														+ document
																.getElementById("type").value
														+ '&goods_id='
														+ document
																.getElementById("goods_id").value
											} else { //취소
												return;
											}
										});
									});
	//개수 및 가격증가///
 	function change(num){
 	var x  = document.form;
 	var y = 1;
 	//y는 상품개수
 	y = Number(x.cart_count.value) + num;
 	//개수의 최대값은 재고수로 지정
 	var max ='<%=gb.getGoods_stock()%>';
 	var min = 1;
 	document.getElementById("good_price_area").innerText =parseInt(document.getElementById("real_goods_price").value* y);//개수가 증가되면 총가격 변하는 것

		//콤마로 변환//
		var reg = /(^[+-]?\d+)(\d{3})/;
		if(y>max){ //재고수 증가 못하게 제어
	 		alert("수량 초과"); 		
	 		return document.getElementById("good_price_area").innerText;	
	 	} 	

		document.getElementById("good_price_area").innerText += '';

		while (reg.test(document.getElementById("good_price_area").innerText)) {
			// replace 정규표현식으로 3자리씩 콤마 처리
			document.getElementById("good_price_area").innerText = document
					.getElementById("good_price_area").innerText.replace(reg,
					'$1' + ',' + '$2');
		}
		//콤마변환 끝
		//상품개수가 최소값보다 작으면 가격을 1개값으로 지정한다
		if (y < min){
			y = 1
			document.getElementById("good_price_area").innerText =parseInt(document.getElementById("real_goods_price").value* y);
			document.getElementById("good_price_area").innerText = document
			.getElementById("good_price_area").innerText.replace(reg,
			'$1' + ',' + '$2');
		}
		x.cart_count.value = y;
		return document.getElementById("good_price_area").innerText;
	}//가격 및 개수 끝
	
	//결제버튼 클릭시 결제
 	function PaymentOrder() {
 		document.form.arr3.value = document.form.cart_count.value

 		document.form.action = './PaymentOrder.pa'
 		document.form.submit();
 	}
	//장바구니 클릭시 장바구니 이동
 	function BasketOrder() {
 		
 		document.form.action = './Basket.pa'
 		document.form.submit();
 	}
</script>
<script>
	function fnMove(seq) {
		var offset = $("#mask").offset();
		$('html, body').animate({
			scrollTop : offset.top
		}, 400);
	}
</script>

</head>

<body>

	<!-- 헤더 -->
	<jsp:include page="../inc/header.jsp"></jsp:include><br>
	<!-- 헤더 -->


	<!-- 본문 전체 -->
	<div class="container">
		<div id="mask"></div>
		<!-- 클릭시 나오는 이미지1(숨김으로 설정해 놓음) -->
		<div class="window1">
			<p style="width: 100%; height: 500px; text-align: center; vertical-align: middle;">
				<img src="img/Goods/exit.png" class="close" width="80px"><br>
				<br>
				<br>
				<br> <img src="img/Goods/back.png" width="10%" class="back1">
				<iframe width="75%" height="130%" src="https://www.youtube.com/embed/<%=gb.getGoods_video()%>"frameborder="0" allowfullscreen></iframe>
				<img src="img/Goods/next.png" width="10%" class="next1">
			</p>
		</div>
		<!-- 클릭시 나오는 이미지2(숨김으로 설정해 놓음) -->
		<div class="window2">

			<p
				style="width: 100%; height: 500px; text-align: center; vertical-align: middle;">
				<img src="img/Goods/exit.png" class="close" width="80px"><br>
				<br>
				<br>
				<br> <img src="img/Goods/back.png" width="10%" class="back2"><img
					src="./upload/<%=gb.getGoods_image2()%>" width="75%" height="130%"><img
					src="img/Goods/next.png" width="10%" class="next2">
			</p>

		</div>
		<!-- 클릭시 나오는 이미지3(숨김으로 설정해 놓음) -->
		<div class="window3">
			<img src="img/Goods/exit.png" class="close" width="80px"><br>
			<br>
			<br>
			<br>
			<p
				style="width: 100%; height: 500px; text-align: center; vertical-align: middle;">
				<img src="img/Goods/back.png" width="10%" class="back3"><img
					src="./upload/<%=gb.getGoods_image3()%>" width="75%"><img
					src="img/Goods/next.png" width="10%" class="next3">
			</p>
		</div>
		<!-- 클릭시 나오는 이미지4(숨김으로 설정해 놓음) -->
		<div class="window4">
			<img src="img/Goods/exit.png" class="close" width="80px"><br>
			<br>
			<br>
			<br>
			<p
				style="width: 100%; height: 500px; text-align: center; vertical-align: middle;">
				<img src="img/Goods/back.png" width="10%" class="back4"><img
					src="./upload/<%=gb.getGoods_image4()%>" width="75%"><img
					src="img/Goods/next.png" width="10%" class="next4">
			</p>
		</div>
		<!-- 클릭시 나오는 이미지 끝 -->

		<!-- Portfolio Item Heading -->
<h1 class="my-4">
			<!-- 상품이름 -->
			<small><%=gb.getGoods_name()%></small>
		</h1>
		<!-- 상단의 상품위치 -->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">
				<%
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
				<%
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
		<!-- 상단의 상품위치 끝-->
		
		<!-- 상품의 내용 -->
		<div class="row">

			<!-- 상품의 타이틀 이미지 --> 
			<div class="col-md-8">
				<center>
					<img class="img-fluid" src="./upload/<%=gb.getGoods_image()%>"alt="" width="400px" height="600px">
				</center>
			</div>
			<!-- 상품의 타이틀 이미지 --> 
			
			<!-- 상품의개수및 결제 등등 상품내의 텍스트박스 -->
			
			<div class="col-md-4">
			<!-- 변동되는 상품의 정보 -->
			<form name='form' method="post">		
				<!-- 변동되지않는 상품 정보 -->
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">플랫폼</td>
							<td align="left" style="padding-left: 30px;"><span id="good_point">
								<%if(platform==1){
							%>PS4<%}else if(platform==2){ %>
							SWITCH<%}else if(platform==3){ %>
							XBOX<%} %></span></td>
						</tr>
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">퍼블리셔</td>
							<td align="left" style="padding-left: 30px;"><%=gb.getGoods_publisher()%></td>
						</tr>
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">개발사</td>
							<td align="left" style="padding-left: 30px;"><%=gb.getGoods_developer()%></td>
						</tr>
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">음성지원</td>
							<td align="left" style="padding-left: 30px;">
							<%if(gb.getGoods_voice()==1){
							%>한국어<%}else if(gb.getGoods_voice()==2){ %>
							영어<%}else if(gb.getGoods_voice()==3){ %>
							일본어<%}else{ %>
							기타
							<%} %>
						</td>
						</tr>
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">자막</td>
							<td align="left" style="padding-left: 30px;">
							
							<%if(gb.getGoods_lang()==1){
							%>한국어<%}else if(gb.getGoods_lang()==2){ %>
							영어<%}else if(gb.getGoods_lang()==3){ %>
							일본어<%}else{ %>
							기타
							<%} %>
							</td>
						</tr>
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">가격</td>
							<td align="left" style="padding-left: 30px;"><%=NumberFormat.getInstance().format(gb.getGoods_price())%>원</td>
							<input type="hidden" id="real_goods_price" value="<%=gb.getGoods_price()%>">
						</tr>
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">배송</td>
							<td align="left" style="padding-left: 30px;">무료배송</td>
						</tr>
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">남은 수량</td>
							<td align="left" style="padding-left: 30px;"><%=gb.getGoods_stock() + "개"%></td>
						</tr>
					
						<tr height="40px">
							<td width="100px" align="left" style="padding-left: 30px;">수량</td>							
							<td width="30px">							
							<a id="btn_count_down" for="txt_cart_count">
							<!-- 마이너스 아이콘--> <img src="img/Goods/minus_btn.png"
							onclick='javascript_:change(-1);'>
							<!-- 마이너스 아이콘 끝 -->
							</a>
													
							<!-- 변경되는 상품의 개수 -->
							
							<input type="text" id="txt_cart_count" name="cart_count" value="1" size="3"
								style="height: 32px; width: 45px; border: solid 0px #b7b7b7; text-align: center; line-height: 28px;"
								readonly>
							<!-- 변경되는 상품의 개수 끝 -->
								
							
							<!-- 플러스 아이콘 -->
							<a id="btn_count_up" for="txt_cart_count"> 
							<img src="img/Goods/plus_btn.png" border="0" onclick='javascript_:change(1);'>
							</a>
							</td><!-- 플러스 아이콘 끝 -->
						</tr>
					</table>
					<!-- 변동되는 상품의 정보 끝-->
					<table>		
						<tr height="40px">
						<td width="120px" align="left" style="padding-left: 30px; ">총상품금액</td>
						
						<!-- 변동되는 상품의 금액 -->
						<td width="278px"align="right" style="padding-left: 30px;" name="good_price" colspan="2">\
							<span id="good_price_area" 	style="font-size: 16px; color: #f61515;">
							<%= NumberFormat.getInstance().format(gb.getGoods_price())%>
							</span>
						</td>
						<!-- 변동되는 상품의 금액 끝-->
						</tr>
					</table>
					<!-- 결제 페이지로 넘기기 위한 상품의 배열 -->
					<input type="hidden" name="arr" id="arr" value="1"><!-- 순번 -->
					<input type="hidden" name="arr1" id="arr1" value="<%=gb.getGoods_name()%>"><!-- 이름 -->
					<input type="hidden" name="arr2" id="arr2" value="<%=gb.getGoods_id()%>"><!-- 아이디 -->
					<input type="hidden" name="arr3" id="arr3" value=""><!-- 개수 -->
					<input type="hidden" name="arr4" id="arr4" value="<%=gb.getGoods_price()%>"><!-- 가격 -->
					<input type="hidden" name="arr5" id="arr5" value="<%=gb.getGoods_image()%>"><!-- 이미지 -->
					<!-- 장바구니로 넘기기 위한 상품의 정보 -->
					<input type="hidden" name="goods_id" value="<%=gb.getGoods_id()%>">
					<input type="hidden" name="goods_price" value="<%=gb.getGoods_price() %>">
					<input type="hidden" name="goods_name" value="<%=gb.getGoods_name()%>">
					<input type="hidden" name="goods_image" value=<%=gb.getGoods_image()%>>
					<input type="hidden" name="platform" value="<%=platform%>">
					<input type="hidden" name="type" value="<%=type%>">
				</form>
				<!-- 변동되지않는 상품 정보 끝 -->


				
					
					
				<%//비회원이면 상품의 구매하기 장바구니 버튼이 보이지 않게끔
				if(mem_id ==null){
				}else{
				%>
				<table border="0" cellspacing="0" cellpadding="0" width="352px">
					<tr height="40px">
						<td></td>
					</tr>
					<tr>
						<td width="145px"><a href="javascript:PaymentOrder()" id="btn_good_buy"><img
								src="img/Goods/buy_btn.jpg" border="0"></a></td><!--결제하기-->
						<td width="7px"></td>
						<td width="125px"><a href="javascript:BasketOrder()" id="btn_good_cart"><img
								src="img/Goods/cart_btn.jpg" border="0"></a></td><!--장바구니  -->
						<td width="5px"></td>

					</tr>
				</table>
				<%} %>
			</div>
			<!-- 상품의개수및 결제 등등 상품내의 텍스트박스 끝-->
		</div>
		<!-- 상품의 내용끝 -->

		
		<h3 class="my-4">제품 이미지</h3>
		<!-- 상품의 슬라이드 이미지 -->
		<div class="row">

			<div class="col-md-3 col-sm-6 mb-4">
				<a class="openMask1" onclick="fnMove()"> <img class="img-fluid"
					src="https://i.ytimg.com/vi/<%=gb.getGoods_video()%>/0.jpg" alt=""
					id="img"> <!--  https://i.ytimg.com/vi/영상번호-->
				</a>
			</div>
			<div class="col-md-3 col-sm-6 mb-4">
				<a class="openMask2" onclick="fnMove()"> <img class="img-fluid"
					src="./upload/<%=gb.getGoods_image2()%>" alt="">
				</a>
			</div>
			<div class="col-md-3 col-sm-6 mb-4">
				<a class="openMask3" onclick="fnMove()"> <img class="img-fluid"
					src="./upload/<%=gb.getGoods_image3()%>" alt="">
				</a>
			</div>
			<div class="col-md-3 col-sm-6 mb-4">
				<a class="openMask4" onclick="fnMove()"> <img class="img-fluid"
					src="./upload/<%=gb.getGoods_image4()%>" alt="">
				</a>
			</div>
		</div>
		<!-- 상품의 슬라이드 이미지 끝 -->
		
		<div class="">
			<h3 class="my-4">상세설명</h3>
			<center>
				<img src="./upload/<%=gb.getGoods_content()%>">
			</center>
		</div>
		<div class="">
			<h3 class="my-4">배송안내</h3>
			<center>
				<img src="img/Goods/ban.jpg">
			</center>
		</div>

		<!-- 리뷰  -->
		<div class="">
			<h3 class="my-4">리뷰</h3>
			
			<%
				if (getReviewList.size() != 0) {//리뷰가 하나도 없을시 나오지 않게끔
					for (int i = 0; i < getReviewList.size(); i++) {
						ReviewBean rb = (ReviewBean) getReviewList.get(i);
			%>
			<!-- 리뷰내용 -->
			<div class="media mb-4">
				<h5 class="mt-0"><%=rb.getMem_id()%></h5>
				<div class="review-body">
					<p class="review_score">
					
					<!-- 상품의 별 -->
					<div class="rating2">
					<%for(int s=1;s<=rb.getReview_score();s++){%>
					<label class = "full" for="sstar<%=s%>"></label>
					<% }%> 
					</div>
					<!-- 상품의 별 끝-->	
					
					<!-- 등록일 -->
					<p class="review_date"><%=rb.getReview_reg_date()%></p>
					<!-- 등록일 끝-->
					
					<br>
					<br>
					<!-- 리뷰내용 -->
					<p class="review_content"><%=rb.getReview_content()%></p>
					<!-- 리뷰내용 끝-->
					
					<%
					if(mem_id!=null){
						if (mem_id.equals(rb.getMem_id())) { // 댓글 작성자의 id와 현재 접속한 사용자의 id가 같을 때는 수정과 삭제 버튼이 나온다.
					%>
					<button type="button" class="ReviewButton"
						value="<%=rb.getReview_number()%>">삭제</button>
					<%
						} else {
					%>
					<button type="button" class="ReviewButton"
						onclick="ReviewReportAction()">신고</button>
					<%
						}//else끝
					}//if문 끝
					%>
				</div>
			</div>
			<!-- 리뷰내용 끝-->

			<%
				}//for문끝
				}//if문끝
			%>
	<!-- 리뷰목록-->
	<div>
	<!-- 페이징 -->
		<ul class="pagination justify-content-center">
	<% if(startPage>pageBlock){//이전페이지%>
		<li class="page-item">
		<a class="page-link" href="Goods.go?pageNum=<%=startPage-pageBlock%>&platform=<%=platform%>&type=<%=type%>&goods_id=<%=gb.getGoods_id()%>" aria-label="Previous"> 
		<span aria-hidden="true">&laquo;</span> 
			<span class="sr-only">Previous</span>
		</a></li>
		<%}
		for(int p=startPage; p<=endPage; p++){//페이지번호%>
		<li class="page-item"><a class="page-link" href="Goods.go?pageNum=<%=p%>&platform=<%=platform%>&type=<%=type%>&goods_id=<%=gb.getGoods_id()%>"><%=p%></a></li>
		<% }
		if(endPage<pageCount){//다음페이지%>
		<li class="page-item">
		<a class="page-link" href="Goods_list.go?pageNum=<%=startPage+pageBlock%>&platform=<%=platform%>&type=<%=type%>&goods_id=<%=gb.getGoods_id()%>"aria-label="Next"> 
		<span aria-hidden="true">&raquo;</span> 
		<span class="sr-only">Next</span>
		</a></li>
		<%} %>
	</ul>
	</div>
	<!-- 페이징 -->
	
			<% //회원아이디가 없을 경우 리뷰남기기가 보이지 않게끔
			if(mem_id!=null){
			%>
			<div class="card my-4" style="background-color: rgba(0, 0, 0, 0);">
				<h5 class="card-header"
					style="background-color: rgba(255, 255, 255, 0.5);">리뷰 남기기</h5>
				<div class="card-body">
				
					<form action="./ReviewInsertAction.go" name="fr" method="post">
			<p class="starname">평점</p>
<!-- 별점 -->
<div class="rating">
    <input type="radio" id="star5" name="review_score" value="5" /><label class = "full" for="star5"></label>   
    <input type="radio" id="star4" name="review_score" value="4" /><label class = "full" for="star4"></label>   
    <input type="radio" id="star3" name="review_score" value="3" /><label class = "full" for="star3"></label>   
    <input type="radio" id="star2" name="review_score" value="2" /><label class = "full" for="star2"></label>   
    <input type="radio" id="star1" name="review_score" value="1" /><label class = "full" for="star1"></label>
</div>
<!-- 별점 끝-->
						<div class="form-group">
							<textarea class="form-control" rows="3" name="review_content"
								required></textarea>
							<input type="hidden" name="goods_id" id="goods_id"
								value="<%=gb.getGoods_id()%>"> <input type="hidden"
								name="platform" id="platform"
								value="<%=gb.getGoods_platform()%>"> <input
								type="hidden" name="type" id="type"
								value="<%=gb.getGoods_type()%>">
						</div>
						<button type="submit" class="reviewButton">쓰기</button>
						<button type="reset" class="reviewButton">취소</button>

					</form>

				</div>
			</div>
<%}//if문 끝 %>
		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- 푸터 -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
	<!-- 푸터 -->

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
