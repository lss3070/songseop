<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript">
    function updatepost(form){   		
    		var order_status = form.order_status.value;
    		var order_waybill = form.order_waybill.value;
    		var payment_id = form.payment_id.value;
    		var pageNum =form.pageNum.value;
    		opener.parent.location.href = '../AdminOrderUpdate.ap?payment_id='+payment_id+'&order_status='+order_status+'&order_waybill='+order_waybill
    				+'&pageNum='+pageNum
    			window.close();

    }
</script>
</head>
<body>
<%
String payment_id = request.getParameter("payment_id");
int pageNum = Integer.parseInt(request.getParameter("pageNum"));
%>
<form method="post" name="fr">
<select name="order_status" id="order_status" class="selectBox" style="width: 16.5%">
							<option value="2">결제완료</option>
							<option value="3">배송준비</option>
							<option value="4">배송중</option>
							<option value="5">배송완료</option>
						</select>
						<input type="text" id="order_waybill" name="order_waybill" placeholder="등기번호를 입력해주세요">
						<input type="hidden"  name="payment_id" id="payment_id" value="<%=payment_id%>">
						<input type="hidden"  name="pageNum" id="pageNum" value="<%=pageNum%>">
						
						<input type="button" value="변경" onclick="updatepost(this.form);">
						
</form>
</body>
</html>