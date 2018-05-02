<%@page import="goodsDB.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>::: 게임팜 :::</title>
<!-- Bootstrap core CSS -->
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="./css/modern-business.css?ver=1" rel="stylesheet">

<style type="text/css">
label {
	margin-left: 15px;
	margin-right: 10px;
	text-align: right;
	width: 100px;
}
.form-control {
	width: 50%;
	display: inline;
}
.form-control2 {
	width: 11%;
	display: inline;
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
	width: 200px;	
	font-family: 'Jeju Hallasan';
	font-size: 25px;
}
#joinbutton:HOVER {
	background-color: #0069d9;
	font-weight: bold;
	cursor: pointer;	
}
</style>
<script type="text/javascript" src="js/jquery-3.3.1.js" charset="utf-8"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

$(function() {
    $("#goods_image").on('change', function(){
        readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#load').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
    }
}
</script>
			<script type="text/javascript">
					function check() {
					if (document.join.goods_name.value.length==0) {
						alert("상품명을 입력하세요");
						document.join.goods_name.focus();
						return;		// 함수 처음으로 돌아감
					}
					if (document.join.goods_developer.value.length==0) {
						alert("제작사를 입력하세요");
						document.join.goods_developer.focus();
						return;		// 함수 처음으로 돌아감
					}
					if (document.join.goods_price.value.length==0) {
						alert("상품 가격을 넣으세요");
						document.join.goods_price.focus();
						return;		// 함수 처음으로 돌아감
					}
					
					
					if (document.join.goods_stock.value.length == 0) {
					alert("재고수를 입력하세요");
					document.join.goods_stock.focus();
						return; // 함수 처음으로 돌아감
					}

					if (document.join.goods_cost.value.length == 0) {
						alert("입고가격을 입력하세요");
						document.join.goods_cost.focus();
						return; // 함수 처음으로 돌아감
					}
					if (document.join.goods_publisher.value.length == 0) {
						alert("개발사를 입력하세요");
						document.join.goods_publisher.focus();
						return; // 함수 처음으로 돌아감
					}
					document.join.submit();
				}
					</script>

</head>
<body>
<%
//한글처리
request.setCharacterEncoding("utf-8");
//request.setAttribute("gb", gb);
GoodsBean gb = (GoodsBean)request.getAttribute("gb");
%>
  <!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/header.jsp"></jsp:include>
<br>
<div class="container">
<h1 class="mt-4 mb-3">상품 정보 수정</h1>
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
		<li class="breadcrumb-item active">Goods Modify</li>
	</ol>
	<!-- Content Row -->
	<div class="row">
		<!-- Map Column -->
		<div class="col-lg-8 mb-4">
				<form action="./AdminGoodsModifyAction.ag" name="join" method="post" enctype="multipart/form-data">
					<div class="control-group form-group">
						<div class="control-group form-group">
							<div class="controls">
								<label>아이디</label>:　<%=gb.getGoods_id()%>
								<input type="hidden" name="goods_id" value="<%=gb.getGoods_id()%>">
							</div>
						</div>
					<input type="hidden" name="dup" value="0">	
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<label>상품명</label>
							<input type="text" name="goods_name" class="form-control" id="goods_name" value="<%=gb.getGoods_name() %>" required placeholder="상품명 확인해주세요!">　							
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<label>상품분류</label>
							<select name="goods_type" class="search_select">
								<option value="01" <%if ("01".equals(gb.getGoods_type())) {
								%> selected <%}%>
								>하드웨어</option>
								<option value="02" <%if ("02".equals(gb.getGoods_type())) {
								%> selected <%}%>
								>소프트웨어</option>
								<option value="03" <%if ("03".equals(gb.getGoods_type())) {
								%> selected <%}%>
								>주변기기</option>
							   </select>　							
							<p class="help-block"></p>
						</div>
					</div>					
					<div class="control-group form-group">
						<div class="controls">
							<label>대응기기</label>
							<select name="goods_platform" class="search_select">
								<option value="01" <%if ("01".equals(gb.getGoods_platform())) {
								%> selected <%}%>
								>Ps4</option>
								<option value="02" <%if ("02".equals(gb.getGoods_platform())) {
								%> selected <%}%>
								>스위치</option>
								<option value="03" <%if ("03".equals(gb.getGoods_platform())) {
								%> selected <%}%>
								>엑스박스</option>
							   </select>　	　							
							<p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<label>자막</label>
							<select name="goods_lang" class="search_select">
								<option value="01" <%if ("01".equals(gb.getGoods_lang())) {
								%> selected <%}%>
								>한국어</option>
								<option value="02" <%if ("02".equals(gb.getGoods_lang())) {
								%> selected <%}%>
								>영어</option>
								<option value="03" <%if ("03".equals(gb.getGoods_lang())) {
								%> selected <%}%>
								>일어</option>
								<option value="04" <%if ("04".equals(gb.getGoods_lang())) {
								%> selected <%}%>
								>기타</option>
							   </select>　	　							
							<p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<label>음성</label>
							<select name="goods_voice" class="search_select">
								<option value="01" <%if ("01".equals(gb.getGoods_voice())) {
								%> selected <%}%>>한국어</option>
								<option value="02" <%if ("02".equals(gb.getGoods_voice())) {
								%> selected <%}%>>영어</option>
								<option value="03" <%if ("03".equals(gb.getGoods_voice())) {
								%> selected <%}%>>일어</option>
								<option value="04" <%if ("04".equals(gb.getGoods_voice())) {
								%> selected <%}%>>기타</option>
							   </select>　	　							
							<p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<label>제작사</label>
							<input type="text" name="goods_developer" class="form-control" value="<%=gb.getGoods_developer() %>">　							
							<p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="name">
							<label>유통사</label>
							<input type="text" class="form-control" name="goods_publisher" value="<%=gb.getGoods_publisher() %>"><br>
						</div>
					</div>		
					<div class="control-group form-group">
						<div class="name">
							<label>판매가격</label>
							<input type="number" name="goods_price" class="form-control" id="goods_price" value="<%=gb.getGoods_price() %>">　							
							<p class="help-block"></p>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="name">
							<label>영상</label>
							<input type="text" class="form-control" name="goods_video" value="youtu.be/<%=gb.getGoods_video() %>"><br>
						</div>
					</div>
					<div class="control-group form-group">
						<div>
						 
							<label>본문이미지</label>
							<input type="file" name="goods_image" class="form-control" id="goods_image"><br>
							<input type="hidden" name="goods_image_1" class="form-control" id="goods_image" value="<%=gb.getGoods_image()%>"><%=gb.getGoods_image()%><br>
							
							<label>본문이미지2</label>
							<input type="file" name="goods_image2" class="form-control" id="goods_image2"><br>
							<input type="hidden" name="goods_image2_1" class="form-control" id="goods_image2" value="<%=gb.getGoods_image2()%>"><%=gb.getGoods_image2()%><br>
							
							<label>본문이미지3</label>
							<input type="file" name="goods_image3" class="form-control" id="goods_image3"><br>
							<input type="hidden" name="goods_image3_1" class="form-control" id="goods_image3" value="<%=gb.getGoods_image3()%>"><%=gb.getGoods_image3()%><br>
							
							<label>본문이미지4</label>
							<input type="file" name="goods_image4" class="form-control" id="goods_image4"><br>
							<input type="hidden" name="goods_image4_1" class="form-control" id="goods_image4" value="<%=gb.getGoods_image4()%>"><%=gb.getGoods_image4()%><br>
							
							<label>설명이미지</label>
							<input type="file" name="goods_content" class="form-control" id="goods_content"><br>
							<input type="hidden" name="goods_content_1" class="form-control" id="goods_content" value="<%=gb.getGoods_content()%>"><%=gb.getGoods_content()%><br>
							
							</div>
					</div>
					
					
					<div class="control-group form-group">
						<div class="name">
							<label>재고수</label>
							<input type="number" class="form-control" name="goods_stock" value="<%=gb.getGoods_stock() %>"><br>
						</div>
						
					</div>
					<div class="control-group form-group">
						<div class="name">
							<label>입고가격</label>
							<input type="number" class="form-control" name="goods_cost" value="<%=gb.getGoods_cost() %>"><br>
						</div>
						
					</div>
					
					
				<div id="success"></div>
				<!-- For success/fail messages -->
					<div id="buttonfield">
					<button type="button" id="joinbutton" onclick="check()">상품등록</button>　　
					<button type="reset" id="joinbutton">다시쓰기</button>
					</div>	
					</form>
				</div>				
				<!-- Contact Details Column -->		
				<div class="col-lg-4 mb-4">
				<img id="load" style="width: 340px" height="200px">
				</div>
			</div>			
		</div>
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
	
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>
</body>
</html>