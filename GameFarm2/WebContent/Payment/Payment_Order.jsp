
<%@page import="java.text.NumberFormat"%>
<%@page import="paymentDB.BasketBean"%>
<%@page import="java.util.List"%>
<%@page import="memberDB.MemberBean"%>
<%@page import="goodsDB.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>결제 페이지</title>

<!-- Bootstrap core CSS -->
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript" src="./js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="./js/tabs.js"></script>

<!-- Custom styles for this template -->
<link href="./css/blog-home.css" rel="stylesheet">
<style type="text/css">
/*밑선색깔  */
.card.mb-41 {
	background: transparent;
	border: none;
}
/*테이블 */
.table1 {
	height: 200px;
}

th.timage {
	width: 100px;
}

th.tgoods {
	width: 200px;
}

th.tseller {
	width: 100px;
}

th.count {
	width: 50px;
}

th.money {
	width: 80px;
}

.page-link {
	width: 140px;
	height: 50px;
	font-size: 30px;
	font-style: #4374D9;
}

ul.list-unstyled.mb-0 li {
	width: 250px;
}

.form-control {
	width: 50%;
	display: inline;
}

.form-control2 {
	width: 20%;
}

/*결제 방법 크기  */
.card-footer.text-muted {
	height: 300px;
}

/* 탭 메뉴 */
ul.tabs li {
	width: 24%;
}

#container2 {
	width: 100%;
	margin: 40px auto;
}

ul.tabs {
	margin: 0;
	padding: 0;
	float: left;
	list-style: none;
	height: 32px;
	border-bottom: 1px solid #B2EBF4;
	width: 100%;
}

ul.tabs li {
	float: left;
	padding: 0;
	margin: 0 1px;
	height: 31px;
	line-height: 31px;
	border: 1px solid #999;
	overflow: hidden;
	background-color: #F5F5F5;
	border-top-left-radius: 7px;
	border-top-right-radius: 7px;
}

ul.tabs li a {
	text-decoration: none;
	color: #000;
	display: block;
	font-size: 12px;
	font-weight: bold;
	padding: 0 20px;
	outline: none;
}

ul.tabs li a:hover {
	background-color: #CAE4FF;
}

ul.tabs li.active, ul.tabs li.active a:hover {
	background: #fff;
	border-color: #F00;
}

/* 탭 메뉴 내부 내용 부분 */
.phone_select {
	height: 36px;
	width: 20%;
	border-radius: 5px;
	background-color: #efefef;
	border: 1px solid #333;
	padding-left: 5px;
}

.phone_select:FOCUS {
	border: none;
	border-radius: 5px;
	background-color: #FFFFFF;
}

.tab_container2 {
	border-top: none;
	clear: both;
	float: left;
	width: 100%;
	background: #fff;
}

.tab_content {
	padding: 10px 10px 20px 10px;
	min-height: 400px;
	font-size: 0.75em;
	line-height: 1.8em;
}

.tab_content h4 {
	margin: 10px 0;
	padding: 20px 0 5px 35px;
	font-family: 'NanumGothic', Serif;
	font-size: 16px;
	font-weight: bold;
	color: #FFF;
	background-image: url(../img/h4_back.png);
	background-repeat: no-repeat;
	background-position: left top;
	height: 30px;
	letter-spacing: 4px;
}

.tab_content p {
	margin: 10px 0 0 0;
}

.tab_content img {
	margin: 10px;
	padding: 5px;
}

.tab_content .writer {
	margin: 10px 0;
	padding: 5px;
	font-size: 1.2em;
	color: #000;
	font-weight: bold;
}

h2 {
	color: #0177A8;
	border-bottom: 1px;
	border-bottom-style: dotted;
}

.h22 {
	padding: 0px;
	margin: 0px;
	border-bottom: 1px;
	border-bottom-style: dotted;
	width: 720px;
}

/*결제방법  */
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

#phone {
	width: 20%;
}

#sample6_postcode {
	width: 5em;
}

#card {
	width: 9%;
}

#account {
	width: 40%;
}

#point {
	width: 20%;
}
/*테이블 헤더 */
.table th {
	height: 20px;
	text-transform: capitalize;
	font-size: 14px;
	font-weight: bold;
	padding: 5px;
	background-image: url("./img/center/t_back.jpg");
	background-repeat: repeat-x;
	background-position: center center;
	color: #FFF;
}

/*버튼 시작 Hover설정*/
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

.table1{

height:70%;
}
</style>

	<%
		List pList = (List) request.getAttribute("pb") ;
		int total = (Integer)request.getAttribute("total");
		MemberBean mb = (MemberBean) request.getAttribute("mb");
		GoodsBean gb = (GoodsBean) request.getAttribute("gb");
		String mem_id = (String) session.getAttribute("sMem_id");
		int platform = (Integer) request.getAttribute("platform");
		int type = (Integer) request.getAttribute("type");
		String list =(String) request.getAttribute("list");
//		int cart_count = Integer.parseInt(request.getParameter("cart_count"));
	%>



<!--다음 api 생성-->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							//법정동명이 있을 경우 추가한다.
							if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('sample6_address').value = fullAddr;

						// 커서를 상세주소 필드로 이동한다.
						document.getElementById('sample6_address2').focus();
					}
				}).open();
	}
</script>
<script type="text/javascript">
	function check(form) {
		if (document.Payment_Pay.receiver_name.value.length == 0) {
			alert("수취인을 입력하세요!");
			document.Payment_Pay.receiver_name.focus();
			return; // 함수 처음으로 돌아감
		}
		if (document.Payment_Pay.receiver_phone2.value.length == 0) {
			alert("휴대폰의 중간 번호를 입력해주세요!");
			document.Payment_Pay.receiver_phone2.focus();
			return; // 함수 처음으로 돌아감
		}
		if (document.Payment_Pay.receiver_phone3.value.length == 0) {
			alert("휴대폰의 마지막 번호를 입력해주세요!");
			document.Payment_Pay.receiver_phone3.focus();
			return; // 함수 처음으로 돌아감
		}
		if (document.Payment_Pay.receiver_post.value.length == 0) {
			alert("우편번호를 입력하세요!");
			document.Payment_Pay.receiver_post.focus();
			return; // 함수 처음으로 돌아감
		}
		if (document.Payment_Pay.receiver_address1.value.length == 0) {
			alert("주소를 입력하세요!");
			document.Payment_Pay.receiver_address1.focus();
			return; // 함수 처음으로 돌아감
		}
		if (document.Payment_Pay.receiver_address2.value.length == 0) {
			alert("상세주소를 입력하세요!");
			document.Payment_Pay.receiver_address2.focus();
			return; // 함수 처음으로 돌아감
		}	
		if (document.Payment_Pay.mem_point.value<0){
			alert("포인트가 부족합니다.")
			return;
		}
		if (confirm("상품을 구입하시겠습니까.") == true){    //확인
			
		} else { //취소
			return;
		}
		
		var idlist = new Array();
		var countlist = new Array();	
		var pricelist = new Array();
		
		var count = form.goods_id.length;
		
		if(count!=undefined){
			for (var i = 0; i < count; i++) {
				
					idlist[i] = form.goods_id[i].value;
		
					countlist[i] = form.order_volume[i].value;

					pricelist[i] = form.goods_price[i].value;
					
			}
			
			 form.goods_id_list.value = idlist.join(','); 
			 form.goods_volume_list.value = countlist.join(','); 
			 form.goods_price_list.value = pricelist.join(','); 
			 
			 
			form.action = './PaymentInsertAction.pa'
			form.submit();
	}else{
//하나일때설정

 			 form.goods_id_list.value = form.goods_id.value;
			 form.goods_volume_list.value = form.order_volume.value;
			 form.goods_price_list.value = form.goods_price.value;
		
			 
		form.action = './PaymentInsertAction.pa'
		form.submit();
	}
		document.Payment_Pay.submit();
	}
</script>
</head>

<body>
	<jsp:include page="../inc/header.jsp"></jsp:include>


	<form action="./PaymentInsert.pa" name="Payment_Pay">

		<div class="container">
			<h1 class="mt-4 mb-3">결제페이지</h1>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
				<li class="breadcrumb-item active">Payment</li>
			</ol>
			<div class="table1">
				<table border="0" width="100%" align="center">
					<!--나중에 border="0"-->
					<tr align="center" class="table">
						<th class="timage">상품이미지</th>
						<th class="tgoods">상품정보</th>
						<th class="tseller">판매자</th>
						<th class="count">수량</th>
						<th class="money">주문금액</th>
					</tr>
					<%			
					for (int i = 0; i < pList.size(); i++) {
					 BasketBean pb = (BasketBean)pList.get(i);
					%>
					<tr align="center">
						<th><img src="./upload/<%=pb.getGoods_image()%>" alt=""
							style="width: 50% "height="40%"></th>
						<!--이전페이지 에서 넘긴 상품이미지 값을 나태냄  -->
						<th><%=pb.getGoods_name()%></th>
						<!--이전페이지 에서 넘긴 상품정보 값을 나태냄  -->
						<th>게임팜</th>
						<!--이전페이지 에서 넘긴 판매자를 나태냄  -->
						<th><%=pb.getGoods_count() + "개"%></th>
						<!--이전페이지 에서 넘긴 판매수량을 나태냄  -->
						<th><%= NumberFormat.getInstance().format(pb.getGoods_price())+"원"%></th>
						<!--이전페이지 에서 넘긴 판매금액 을 나태냄  -->
					</tr>
						<input type="hidden" id="goods_id" name="goods_id" value="<%=pb.getGoods_id()%>">
						<input type="hidden" id="order_volume" name="order_volume" value="<%=pb.getGoods_count()%>">
						<input type="hidden" id="goods_price" name="goods_price" value="<%=pb.getGoods_price()%>">
						
					<%} %>
				</table>
			</div>
		</div>
		<div class="container">

			<div class="row">

				<!-- 배송지 정보 시작 -->
				<div class="col-lg-8 mb-4">
					<h2>배송지 정보</h2>
					<div class="control-group form-group">
						<div class="controls">
							<label>수취인</label> <input type="text" name="receiver_name"
								class="form-control" id="receiver_name"
								placeholder="받는 사람을 입력하세요">
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<label>수취인 연락처</label> <select name="receiver_phone1"
								class="phone_select" size="1">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="019">019</option>
							</select>&nbsp;-&nbsp;<input type="text" name="receiver_phone2" size="1"
								class="form-control" id="phone">&nbsp;-&nbsp;<input
								type="text" name="receiver_phone3" size="1" class="form-control"
								id="phone"><br>
						</div>
					</div>
					<div class="control-group form-group">
						<div class="controls">
							<label>우편번호</label> <input type="text" name="receiver_post"
								class="form-control" id="sample6_postcode"> <input
								type="button" onclick="sample6_execDaumPostcode()" value="주소 검색"
								id="post"><br> <label>주소</label> <input type="text"
								size="61" class="form-control" id="sample6_address"
								name="receiver_address1"><br> <label>상세 주소</label>
							<input type="text" size="61" class="form-control"
								id="sample6_address2" name="receiver_address2">
						</div>
					</div>
					<br>

					<!-- 결제 정보 시작 -->
					<div class="col-lg-8 mb-4">
						<h2 class="h22">결제방법</h2>
					</div>
					<div class="card mb-4">
						<div class="card-footer text-muted">
							<div id="container2">
								<ul class="tabs">
									<li><a href="#tab1">포인트</a></li>
									<li><a href="#tab2">휴대폰</a></li>
								</ul>
								<br> <br>

								<div id="tab1" class="tab_content">
								
									<h3>잔여포인트 : <%=NumberFormat.getInstance().format(mb.getMem_point())+"포인트"%></h3>
									
									<%if(mb.getMem_point()-total<0){
									%>
									<h3><%=total-mb.getMem_point()+"포인트 부족합니다."%></h3>
									<% 
									}else{								
									%>
									<h3>구매후 포인트 : <%=NumberFormat.getInstance().format(mb.getMem_point()-total)+"포인트"%></h3>
									<%
									}
									%>
								</div>
								<input type="hidden"value="<%=mb.getMem_point()-total%>" name="mem_point">
								<div id="tab2" class="tab_content">
									<div class="control-group form-group">
										<h3>준비중입니다.</h3>
									</div>
								</div>
							</div> 
						</div>

					</div>
					<!-- 결제 버튼 -->
 					<div class="card mb-41">
						<div id="buttonfield">
						<input type="hidden" id="arr" name="arr" value=<%=list %>>
 							<button type="button" id="joinbutton" onclick="check(this.form);">결제하기</button>
							<input type="hidden" name="goods_id_list" id="goods_id_list" value=""> <input type="hidden"name="goods_volume_list" value="">
							<input type="hidden"name="goods_price_list" value="">
							<button type="reset" id="joinbutton">다시쓰기</button> 
						</div>
					</div> 
				</div>

				<!-- 주문자 정보 -->
				<div class="col-md-4">
					<div class="card my-4">
						<h5 class="card-header">주문자 정보</h5>
						<div class="card-body">
							<div class="row">
								<div class="col-lg-6">
									<ul class="list-unstyled mb-0">
										<li>
											<p><%="회원아이디 : "+mem_id%></p>
										</li>
										<li>
											<p><%="회원 나이 : "+mb.getMem_age()+"살"%></p>
										</li>

										<li>
											<p><%="회원 이메일 : "+mb.getMem_email1()+"@"+mb.getMem_email2()%></p>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<br> <br> <br> <br>
					<!-- 결제금액 -->
					
 					<div class="card my-4">
						<h5 class="card-header">결제금액</h5>
						<div class="card-body"><%= NumberFormat.getInstance().format(total)+"원"%></div>
					</div>
				</div>

			</div>
			<input type="hidden" name="order_status" value=2>
			<!-- /.row -->
<%-- 			
			<input type="hidden" name="goods_stock" value="<%=gb.getGoods_stock()-cart_count%>"> --%>
		</div>
	</form>
	<!-- /.container -->

	<!-- 푸터 -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
	<!-- 푸터 -->

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
