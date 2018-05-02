<%@page import="java.text.SimpleDateFormat"%>
<%@page import="paymentDB.PaymentBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>::: 게임팜 :::</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/modern-business.css" rel="stylesheet">
<style type="text/css">
.card-text {
	font-family: 맑은 고딕;
	font-size: 1.2em;
	font-weight: 444;
	color: #666;
}

.card-header {
	text-align: center;
}

.btn-block {
	font-weight: 444;
	font-size: 1.5em;
}

.my-4 {
	font-family: Jeju Gothic;
	font-stretch: wider;
	color: #777 !important;
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

article table#notice td#admin2 {
	text-align: left;
	padding: 6px 0;
	background-color: #ccc;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999;
	color: #449;
}

article table#notice td#admin2:HOVER {
	cursor: default;
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
	text-align: left;
	padding-left: 1.5em;
	text-decoration: none;
	color: #555;
}

article table#notice td.left:HOVER {
	cursor: default;
}

article th.ONumber {
	width: 5%;
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
}

article th.ODate {
	width: 10%;
}

article th.GId {
	width: 15%;
}

article th.timage {
	width: 20%;
	letter-spacing: 0.7em;
}

article th.GThumb {
	width: 10%;
	letter-spacing: 0.7em;
}
article th.Address {
	width: 30%;
	letter-spacing: 0.7em;
}
article th.Receiver {
	width: 7%;
	letter-spacing: 0.7em;
}
article th.GName {
	width: 4%;
}

article th.PriceVolume {
	width: 9%;
}

article th.OStatus {
	width: 8%;
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

#img1 {
	/* width:40%; */
	height: 40%;
}

/* 게시판 모양 */
</style>
<script language="javascript">
	//등기번호 등록시 배송상태를 클릭하면 우체국 배송조회가 나오는 기능
	function postnum() {
		var dnumber = document.getElementById("hidden").value;
		var addr = "https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm?sid1=";
		var trcWin1 = window.open(addr + dnumber,"openWin1",
					"toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,left=120,top=130,width=1000,height=630");

	}
	function paymentDeleteAction(payment_id) {	//구매 완료 삭제 
		if (confirm("구매를 확정하십니까?") == true){  //확인
			location.href='./PaymentDeleteAction.pa?payment_id='+payment_id;
		} else { //취소
			return;
		}
	}	
</script>
</head>
<body>
	<%
		int count = (Integer) request.getAttribute("count");
		String pageNum = (String) request.getAttribute("pageNum");
		List getOrderList = (List) request.getAttribute("getOrderList");
		int pageCount = (Integer) request.getAttribute("pageCount");
		int pageBlock = (Integer) request.getAttribute("pageBlock");
		int currentPage = (Integer) request.getAttribute("currentPage");
		int startPage = (Integer) request.getAttribute("startPage");
		int endPage = (Integer) request.getAttribute("endPage");
		int startRow = (Integer) request.getAttribute("startRow");
		int endRow = (Integer) request.getAttribute("endRow");
		int maxPage = (Integer) request.getAttribute("maxPage");
		String mem_id = (String) session.getAttribute("sMem_id");
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	%>
	<!-- 헤더 -->
	<jsp:include page="../inc/header.jsp"></jsp:include><br>
	<!-- 헤더 -->


	<!-- 게시판 -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<h1 class="mt-4 mb-3">주문내역</h1>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">주문내역</li>
		</ol>
		<br>
		<table id="notice">
			<tr>
				<td id=adminsubject><%=mem_id%>님이 저희 쇼핑몰에서 주문하신 내역입니다.</td>
			</tr>
		</table>
		<br>
		<article> <%-- <h2><marquee behavior="alternate" scrolldelay="200" scrollamount="25" direction="left">[<%=count %>개의 글이 있습니다! ]</marquee></h2> --%>

		<%
			int z = 1;
			if (getOrderList != null) {
		%>
		<table id="notice">
			<tr><!-- 최상단 열 -->
				<th class="ONumber">주문번호</th>
				<th class="GId">상품이름</th>
				<th class="GThumb">이미지</th>
				<th class="GName">개수</th>
				<th class="Address">배송지</th>
				<th class="Receiver">수취인</th>
				<th class="PriceVolume">가격(수량)</th>
				<th class="ODate">주문일자</th>
				<th class="OStatus">주문상태</th>
			</tr>
			<%for (int i = 0; i < getOrderList.size(); i++) {//반복문을 이용해 주문리스트를 출력
				PaymentBean pb = (PaymentBean) getOrderList.get(i);%>
			<tr>
				<td id="admin"><%=pb.getPayment_id()%></td>
				<td id="admin"><a href="./Goods.go?goods_id=<%=pb.getGoods_id()%>&platform=<%=pb.getPlatform()%>&type=<%=pb.getType()%>"><%=pb.getGoods_name()%></a></td>
				<td id="admin"><a href="./Goods.go?goods_id=<%=pb.getGoods_id()%>&platform=<%=pb.getPlatform()%>&type=<%=pb.getType()%>"><img src="./upload/<%=pb.getGoods_image()%>"id="img1"></a></td>
				<td id="admin"><%=pb.getOrder_volume() + "개"%></td>
				<td id="admin"><%="("+pb.getReceiver_post()+") "+pb.getReceiver_address1()+" "+pb.getReceiver_address2()%></td>
				<td id="admin"><%=pb.getReceiver_name()%></td>
				<td id="admin"><%=pb.getOrder_volume() * pb.getPayment_price() + "원"%></td>
				<td id="admin"><%=simpledate.format(pb.getOrder_date())%></td>
				<td id="admin">
					<%//결제상태에 따라 틀려짐
						if (pb.getOrder_status() == 1) {
					%> <a href="#" onclick="postnum()">
						입금대기</a> <%
 					} else if (pb.getOrder_status() == 2) {
 					%> 결제완료 <%
 					} else if (pb.getOrder_status() == 3) {
 					%>
					배송준비 / <a href="#" onclick="postnum()"> 배송조회</a> <%
 					} else if (pb.getOrder_status() == 4) {
 					%>
					배송중 / <a href="#" onclick="postnum()">배송조회</a><%
 					} else if (pb.getOrder_status() == 5) {
 					%>
					<a href="#" onclick="postnum()"> 배송완료</a> / <a href="#" onclick="paymentDeleteAction(<%=pb.getPayment_id()%>)">구매확정</a><%
 					}
 					%>
				</td>
			</tr>
			<input type="hidden" id="hidden" value="1234567890123">
			<%
				} // for loop
				} //if 
				else {
			%>
			<center>
				<div>주문하신 상품이 없습니다.</div>
			</center>
			<%
				}
			%>
		</table>
	
		<ul class="pagination justify-content-center">
			<%if(endPage>1){//페이지가 하나밖에 없으면 나오지 않게끔 if문 넣음
				if (startPage > pageBlock) {
			%>
			<li class="page-item"><a class="page-link"
				href="OrderList.pa?pageNum=<%=startPage - pageBlock%>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%
				}for (int i = startPage; i <= endPage; i++) {
			%>
			<li class="page-item"><a class="page-link"
				href="OrderList.pa?pageNum=<%=i%>"><%=i%></a></li>
			<%
				}if (endPage < pageCount) {
			%>
			<li class="page-item"><a class="page-link"
				href="OrderList.pa?pageNum=<%=startPage + pageBlock%>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
			<%
				}
			}
			%>
		</ul>
		</article>
	</div>
	
	
	<article>
		· 물품구입후 발급되는 마일리지는 상품가격의 5%입니다.<br> 
		· 이 주문으로 발급되는 마일리지는 배송 처리 이후에
'회원정보 조회'에서 확인하실 수 있습니다.<br><br><br>
	</article>
	<!-- 게시판 -->


	<!-- 푸터 -->
	<jsp:include page="../inc/footer.jsp"></jsp:include>
	<!-- 푸터 -->
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>