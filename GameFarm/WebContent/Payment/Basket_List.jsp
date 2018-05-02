<%@page import="paymentAction.Basket"%>
<%@page import="paymentDB.BasketBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>::: 게임팜 :::</title>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/tabs.js"></script>
<!-- Custom styles for this template -->
<link href="css/blog-home.css" rel="stylesheet">
<style type="text/css">
#sum_text {
	background: transparent;
}

.jumbotron.my-4 {
	height: 800px;
}

.timage {
	width: 20%;
}

.count {
	width: 10%;
}

.money2 {
	width: 10%;
}

#notice th.timage, th.tgoods, th.tseller, th.count, th.money, th.money2
	{
	height: 20px;
	text-transform: capitalize;
	font-size: 14px;
	font-weight: bold;
	padding: 5px;
	background-image: url("img/center/t_back.jpg");
	background-repeat: repeat-x;
	background-position: center center;
	color: #FFF;
}

#notice td.timage, td.tgoods, td.tseller, td.count, td.money, td.money2
	{
	text-align: center;
}

.table2 {
	height: 80%;
}
</style>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//전체선택 체크박스 클릭
		$("#allCheck").click(function() {
			//만약 전체 선택 체크박스가 체크된상태일경우
			if ($("#allCheck").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다
				$("input[type=checkbox]").prop("checked", true);
				// 전체선택 체크박스가 해제된 경우
			} else {
				//해당화면에 모든 checkbox들의 체크를해제시킨다.
				$("input[type=checkbox]").prop("checked", false);
			}
			//전체 체크박스 클릭시 체크된 열들의 상품개수와 상품가격의 합계
			var sum = 0;
			var count = document.form.checkbox.length;
			if (count != undefined) {//체크된 열이 여러개일 경우
				for (var i = 0; i < count; i++) {
					if (form.checkbox[i].checked == true) {
						sum += parseInt(document.form.money2[i].value);
					}
				}
			} else {//체크된 열이 하나만 있을경우
				count = 1;
				if (form.checkbox.checked == true) {
					sum += parseInt(document.form.money2.value);
				}
			}
			document.form.total_sum.value = sum;
		})
	})

	//선택된 체크된 값 합계 구하기
	function itemSum(form) {
		var sum = 0;
		var count = form.checkbox.length;
		if (count != undefined) {//체크된 열이 여러개일 경우
			for (var i = 0; i < count; i++) {
				if (form.checkbox[i].checked == true) {
					sum += parseInt(document.form.money2[i].value);
				}
			}
		} else {//체크된 열이 하나만 있을경우
			count = 1;
			if (form.checkbox.checked == true) {
				sum += parseInt(document.form.money2.value);
			}
		}
		form.total_sum.value = sum;
	}
	//체크시 삭제
	function itemDel(form) {
		var list = new Array()
		var count = form.checkbox.length;
		if(confirm("정말삭제시키겠습니까?")==true){
		if (count != undefined) {//체크된 열이 여러개일 경우
			for (var i = 0; i < count; i++) {
				if (form.checkbox[i].checked == true) {
					list[i] = i;
				} else {
					list[i] = null;
				}
			}
			form.arr.value = list.join(',');

			document.form.action = './BasketDeleteAcion.pa'
			document.form.submit();
		} else {//체크된 열이 하나만 있을경우
			list = 0;
			form.arr.value = list;
			document.form.action = './BasketDeleteAcion.pa'
			document.form.submit();
			}
		}
	}
	//개수변경
	function countchange() {
		var count = form.checkbox.length;
		if (count != undefined) {//체크된 열이 여러개일 경우
			for (var i = 0; i < count; i++)
				document.form.money2[i].value = document.form.money[i].value
						* document.form.count[i].value
		} else {//체크된 열이 하나만 있을경우
			document.form.money2.value = document.form.money.value
					* document.form.count.value
		}for (i = 0; i < count; i++) {//개수 변경시 체크 초기화
			form.checkbox[i].checked = false;
		}//개수 변경시 합계 값 초기화
		document.form.total_sum.value = 0;
	}
	
	function payment(form) {//구매하기
		//값들을 담을 배열선언
		var namelist = new Array();
		var idlist = new Array();
		var countlist = new Array();
		var imagelist = new Array();
		var pricelist = new Array();
		var list = new Array()
		var count = form.checkbox.length;
		if(confirm("구매하시겠습니까?")==true){
		if (count != undefined) {//체크된 열이 여러개일 경우
			for (var i = 0; i < count; i++) {
				if (form.checkbox[i].checked == true) {//체크된 열의 값만 배열에 담음
					namelist[i] = document.form.name[i].value;
					idlist[i] = document.form.goods_id[i].value;
					countlist[i] = document.form.count[i].value;
					imagelist[i] = document.form.image[i].value;
					pricelist[i] = document.form.money[i].value;
					list[i] = i;
				} else {//체크되지 않은 열에는 null값
					list[i] = null;
				}
			}
			//배열을 히든값에 담는다
			form.arr1.value = namelist.join(',');
			form.arr2.value = idlist.join(',');
			form.arr3.value = countlist.join(',');
			form.arr4.value = pricelist.join(',');
			form.arr5.value = imagelist.join(',');
			form.arr.value = list.join(',');
			//보냄
			document.form.action = './PaymentOrder.pa'
			document.form.submit();
		} else {//체크된 열이 하나만 있을경우
			list = 0;
			//체크된 열의 값을 히든에 담는다
			form.arr1.value = form.name.value;
			form.arr2.value = form.goods_id.value;
			form.arr3.value = form.count.value;
			form.arr4.value = form.money.value;
			form.arr5.value = form.image.value;
			form.arr.value = list;
			//보낸다
			document.form.action = './PaymentOrder.pa'
			document.form.submit();
			}
		}
	}
</script>
<!--check box 스타일-->
<style type="text/css">
@import
	url("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css")
	;

label {
	font-size: 20px;
}

/*Check box*/
input[type="checkbox"]+.label-text:before {
	content: "\f096";
	font-family: "FontAwesome";
	padding-right: 12px;
}

input[type="checkbox"]:checked+.label-text:before {
	content: "\f14a";
}

th.tcheck {
	width: 1px;
}

.total_sum {
	width: 100%;
}

/*button*/
#buttonfield {
	text-align: right;
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

#count, #money, #money2 {
	width: 60px;
}
</style>
</head>
<body>
	<%
		String mem_id = (String) session.getAttribute("sMem_id");
		List blist = (ArrayList<BasketBean>) session.getAttribute("backetlist");
	%>

	<!-- 헤더 -->
	<jsp:include page="../inc/header.jsp"></jsp:include><br>
	<!-- 헤더 -->


	<div class="container">
		<h1 class="mt-4 mb-3">장바구니</h1>
		<!-- 상단 페이지의 위치 -->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">Basket</li>
		</ol>
		<div class="table2">
			<form action="gg.jsp" name=form method="post">
				<!-- 체크값을 받아놓는 배열박스 -->
				<input type="hidden" name="arr" id="arr" value=""> 
				<input type="hidden" name="arr1" id="arr1" value=""> 
				<input type="hidden" name="arr2" id="arr2" value=""> 
				<input type="hidden" name="arr3" id="arr3" value=""> 
				<input type="hidden" name="arr4" id="arr4" value=""> 
				<input type="hidden" name="arr5" id="arr5" value="">
				<%
					if (blist != null) {
				%>
				<table border="0" width="100%" align="center">
					<!--장바구니 리스트-->
					<tr align="center" id="notice">
						<th class="tcheck"><div class="form-check">
								<label> <input type="checkbox" id="allCheck"
									onClick="itemSum(this.form);"> <span class="label-text"></span>
								</label>
							</div></th>
						<th class="timage">상품이미지</th>
						<th class="tgoods">상품이름</th>
						<th class="count">수량</th>
						<th class="money">가격</th>
						<th class="money2">총가격</th>
					</tr>
					<%//배열로 리스트를 출력한다
						for (int i = 0; i < blist.size(); i++) {
								BasketBean basket = (BasketBean) blist.get(i);
					%>
					<tr><!-- 앞에 체크박스 -->
						<td class="tcheck"><div class="form-check">
								<label> <input type="checkbox" name="checkbox" onClick="itemSum(this.form);">
								<span class="label-text"></span></label>
							</div></td>
						<!-- 이미지 -->
						<td class="timage"><a href="./Goods.go?goods_id=<%=basket.getGoods_id()%>&platform=<%=basket.getGoods_platform()%>&type=<%=basket.getGoods_type()%>">
						<img class="img-fluid" src="./upload/<%=basket.getGoods_image()%>"	alt=""></a></td>
						<!-- 상품아이디 -->
						<td class="tgoods"><a href="./Goods.go?goods_id=<%=basket.getGoods_id()%>&platform=<%=basket.getGoods_platform()%>&type=<%=basket.getGoods_type()%>"><%=basket.getGoods_name()%></a>
						<input type="hidden" id="goods_id" id="goods_id" value="<%=basket.getGoods_id()%>"></td>
						<!-- 개수 -->
						<td class="count"><input type="text" id="count" name="count" value="<%=basket.getGoods_count()%>"><br>
						<input type="button" onclick="countchange()" value="변경">
						</td>
						<!-- 가격 -->
						<td class="money"><input type="text" id="money" name="money" value="<%=basket.getGoods_price()%>" readonly
							style="background-color: transparent; border: 0px" readonly></td>
						<!-- 총가격 -->	
						<td class="money2"><input id="money2" name="money2" readonly value="<%=basket.getGoods_price() * basket.getGoods_count()%>"
							readonly style="background-color: transparent; border: 0px">
							<input type="hidden" id="name" value="<%=basket.getGoods_name()%>"> <input type="hidden" id="image" value="<%=basket.getGoods_image()%>"></td>
					</tr>

					<%
						}
					%>
					<tr align="center">
						<td align="right"><a href="javascript:itemDel(this.form);">삭제</a>
						</td>
					</tr>


				</table>
				<%
					} else {
				%>
				상품이 없습니다.
				<%
					}
				%>

				<div class="total_sum" align="right">
					&nbsp;합계금액 :&nbsp;<input name="total_sum" type="text" readonly
						value="0" id="sum_text">원
				</div>
				<br>

				<div id="buttonfield">
					<a href="javascript:payment(this.form);"><button type="button">결제하기</button></a>
				</div>
			</form>
		</div>
		<br>

		<div class="row"></div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
	<!-- 푸터 -->

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>