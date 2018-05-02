


<%@page import="paymentDB.PaymentBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문내역</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>::: GameFarm :::</title>
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
article th.ONumber{
	width: 50px;
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
}
article th.ODate{
	width: 100px;
	
}
article th.GId {
	width: 100px;
	
}
article th.timage {
	width: 120px;
	letter-spacing: 0.7em;	
} 
article th.GThumb {
	width: 120px;
	letter-spacing: 0.7em;
}
article th.GName {
	width: 80px;
}
article th.PriceVolume {
	width: 100px;
}
article th.OStatus {
	width: 80px;
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
}
article th.GMem_Id{
	width: 100px;
}
#subject {
	color: #449;
}
#subject:HOVER {
	color: red;
	text-decoration: none;
}
#img1{
/* width:40%; */
height:40%;
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
.update{

	background-color: #ccc;
	border: none;
	color: #007bff;
	height: 20px;
	width: 40px;	

	
}
/* 검색 파트 */
</style>
<script language="javascript">
    function postnum(){
        var dnumber = document.getElementById("hidden").value;
/*         if(  dnumber == null || dnumber == ""){
            alert("조회할 등기번호를 입력하여 주십시오!");
            return false;
        }
        if (dnumber.length < 13) {
        alert("등기번호 13자리를 입력하여 주십시오");
        return false;
        } */
  var addr = "https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm?sid1=";

        var trcWin1 = window.open(addr+dnumber,"openWin1","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,left=120,top=130,width=1000,height=630");

    }
    
/*     function update(){
    	var payment_id=this.closest("#payment_id")
    	alert(this.value)
    window.open("./AdminPayment/Ad_Order_Update.jsp?payment_id="+payment_id,"update","width=420, height=160, left=600, top=200, menubar=no, status=no, toolbar=no");
    } */
</script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
    $(".update").click(function(e){ 
    	var payment_id= $(this).siblings("#payment_id").val();
    	var pageNum = $("#pageNum").val();
    	  window.open("./AdminPayment/Admin_Order_Update.jsp?payment_id="+payment_id+"&pageNum="+pageNum,"update","width=420, height=160, left=600, top=200, menubar=no, status=no, toolbar=no");
    })
    $(".waybill").click(function(e){ 
    	var dnumber = $(this).siblings("#waybill").val();
    	 var addr = "https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm?sid1=";
    	 var trcWin1 = window.open(addr+dnumber,"openWin1","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,left=120,top=130,width=1000,height=630");
    })
    	
    })
    </script>
    
<!-- 검색 파트 -->
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
		int count = (Integer) request.getAttribute("count");
		String pageNum = (String) request.getAttribute("pageNum");
		List getAdOrderList = (List) request.getAttribute("getAdOrderList");
		int pageCount = (Integer) request.getAttribute("pageCount");
		int pageBlock = (Integer) request.getAttribute("pageBlock");
		int currentPage = (Integer) request.getAttribute("currentPage");
		int startPage = (Integer) request.getAttribute("startPage");
		int endPage = (Integer) request.getAttribute("endPage");
		int startRow = (Integer) request.getAttribute("startRow");
		int endRow = (Integer) request.getAttribute("endRow");
		int maxPage = (Integer) request.getAttribute("maxPage");
	%>
<!-- 헤더 -->
<jsp:include page="../inc/header.jsp"></jsp:include><br>
<!-- 헤더 -->


<!-- 게시판 -->
	<div class="container">
	
	<!-- Page Heading/Breadcrumbs -->
		<h1 class="mt-4 mb-3" > 주문내역</h1>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
			<li class="breadcrumb-item active">주문내역</li>
		</ol>
	<br>
	<table id="notice">
	<tr >
	<td id=adminsubject>
	회원님들이 저희 쇼핑몰에서 주문하신 내역입니다.
	</td>
	</tr>
	</table>
	<br>
		<article>
			<%-- <h2><marquee behavior="alternate" scrolldelay="200" scrollamount="25" direction="left">[<%=count %>개의 글이 있습니다! ]</marquee></h2> --%>

			<%
			int z = 1;
			if (getAdOrderList != null) {%>
						<table id="notice">
				<tr>
					<th class="ONumber">주문번호</th>	
					<th class="GId">상품이름</th>
					<th class="GThumb">이미지</th>
					<th class="GName">개수</th>
					<th class="PriceVolume">가격(수량)</th>
					<th class="GMem_Id">고객아이디</th>
					<th class="ODate">주문일자</th>
					<th class="OStatus">주문상태</th>
					<th class="GName">수정</th>
					
				</tr>
			<% 
				for (int i = 0; i < getAdOrderList.size(); i++) {
					//PaymentBean pb = (PaymentBean) getAdOrderList.get(i);
					PaymentBean pb = (PaymentBean) getAdOrderList.get(i);

		%>
					<tr>
								<td  id="admin"><%=pb.getPayment_id() %></td>
								<td id="admin"><%=pb.getGoods_name() %></a></td>
								<td id="admin"><img src="./upload/<%=pb.getGoods_image()%>" id="img1"></td>
								<td id="admin"><%=pb.getOrder_volume()+"개"%></td>
								<td id="admin"><%=pb.getOrder_volume()*pb.getPayment_price() +"원"%></td>
								<td id="admin"><%=pb.getMem_id() %></td>
								<td id="admin"><%=pb.getOrder_date() %></td>
								<td id="admin"><input type="hidden" id="waybill" value="<%=pb.getOrder_waybill() %>">
								
								<%if(pb.getOrder_status()==1){%>
								<a href="#" class="waybill" ">
								입금대기</a>
								<%}else if(pb.getOrder_status()==2){ %>
								결제완료
								<%}else if(pb.getOrder_status()==3){ %>
								<a href="#" class="waybill"">
								배송준비</a>
								<%}else if(pb.getOrder_status()==4){ %>
								<a href="#" class="waybill"">
								배송중</a>
								<%}else if(pb.getOrder_status()==5){ %>
								<a href="#" class="waybill"">
								배송완료</a>
								<%} %></td>
								
								<td id="admin"> <input type="button" class="update"  value="변경">
								<input type="hidden" id="payment_id" value="<%=pb.getPayment_id()%>">
								</td>
					</tr>	
					<input type="hidden" id="pageNum" value="<%=pageNum %>">
					<input type="hidden" id="hidden" value="1234567890123">
			<%
					} // for loop
			}//if
			else{%>
				<center>
				<div>
				주문하신 상품이 없습니다.
				</div>
				</center>
				<% 
			}
		
		%>							
			</table>		
			
				<ul class="pagination justify-content-center">
	<% if(startPage>pageBlock){%>
		<li class="page-item">
		<a class="page-link" href="AdminOrderList.ap?pageNum=<%=startPage-pageBlock%>" aria-label="Previous"> 
		<span aria-hidden="true">&laquo;</span> 
			<span class="sr-only">Previous</span>
		</a></li>
		<%}
		for(int i=startPage; i<=endPage; i++){%>
		<li class="page-item"><a class="page-link" href="AdminOrderList.ap?pageNum=<%=i%>"><%=i%></a></li>
		<% }
		if(endPage<pageCount){%>
		<li class="page-item">
		<a class="page-link" href="AdminOrderList.ap?pageNum=<%=startPage+pageBlock%>"aria-label="Next"> 
		<span aria-hidden="true">&raquo;</span> <span
				class="sr-only">Next</span>
		</a></li>
		<%} %>
	</ul>

			</article>		
			<br>
		<!-- 목록 -->
		<div id="buttonfield">
		<button type="button" id="joinbutton" onclick="location.href='./AdminOrderList.ap'">목록</button>		
		</div>
		<!-- 목록 -->	
		
		<!-- 검색 파트 -->
		<form action="./AdminOrderSearchList.ap" name="fr">
		<div class="input-group">
			<select name="searchOption" class="search_select">
				<option value="0" <%-- <%=searchOption==0 ? "selected" : "" %> --%>>상품이름</option>
				<option value="1" >고객아이디</option>
				
			</select>
		<input type="text" class="form-control" 
		  placeholder="검색어를 입력하세요." name="search"> <span class="input-group-btn">
		<button class="btn btn-secondary" type="button" onclick="searchCheck()">검색</button>
		</span>
		</div>
		</form>		
		<!-- 검색 파트 -->
		</div>		
	<br>
	<article>
	<div>
	· 물품구입후 발급되는 마일리지는 상품가격의 5%입니다.<br>
	· 이 주문으로 발급되는 마일리지는 배송 처리 이후에 '회원정보 조회'에서 확인하실 수 있습니다.<br><br>

	</div>
	<br>
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