<%@page import="memberDB.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>::: 게임팜 :::</title>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

@import url(//fonts.googleapis.com/earlyaccess/jejuhallasan.css);

body {
	background-color: #eee;
}
table {
	border-color: orange;	
	font-family: 'Jeju Gothic';
	text-align: center;
	font-size: 1.2em;	
	font-weight: bold;
}
button {
	background-color: #555;
	border: none;
	border-radius: 5px;
	color: #fff;
	height: 42px;
	width: 150px;	
	font-family: 'Jeju Hallasan';
	font-size: 20px;
}
button:HOVER {
	background-color: #777;
	font-weight: bold;
	cursor: pointer;	
}
#idselect {
	background-color: #555;
	border: none;
	border-radius: 5px;
	color: #fff;
	height: 42px;
	width: 150px;	
	font-family: 'Jeju Hallasan';
	font-size: 20px;
}
#idselect:HOVER {
	background-color: #777;
	font-weight: bold;
	cursor: pointer;	
}
#contents {
	border: none;
	background-color: rgba(200, 200, 200, 0);
	font-family: 'Jeju Gothic', Geneva, sans-serif;
	color: #444;
	padding: 0.1em;
	font-size: 1em;	
}
</style>
<script type="text/javascript">
	function ok() {
		// join.jsp 아이디 상자	<= idCheck.jsp에서 찾은 아이디
		opener.document.join.mem_id.value=document.cfr.userId.value;
		opener.document.join.dup.value=document.cfr.dup.value;
		// idCheck.jsp 창닫기
		window.close();
	}
</script>
</head>
<body>
	<%		
		// String userId = 파라미터 userId 가져오기
		String userId = request.getParameter("userId");
		System.out.println(userId);
		int check = (Integer)request.getAttribute("check");
		// int checkOk = (Integer) session.getAttribute("sCheckOk");		
		// check에 대한 조건문
	if (check == 1) { // check==1 아이디가 존재함. 다른 아이디 선택하도록 함.
	%>
	<script type="text/javascript">
		alert("이미 존재하는 아이디입니다. 다른 아이디를 입력하세요");
		document.cfr.userId.select();
	</script>
<div>
	<h2><marquee behavior="alternate" scrolldelay="200" scrollamount="25"direction="left">아이디를 입력해주세요~</marquee></h2>	
	<form action="MemberIdCheck.me" method="post" name="cfr">
		<table border="1" id="check_Table">
			<tr>
				<td style="width: 100px; font-family: 'Jeju Gothic'">&nbsp;아이디&nbsp;</td>
				<td id="check_Value"><input type="text" style="width: 200px;" name="userId" id="contents" value="<%=userId%>"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">중복 체크</button>
				</td>
			</tr>
		</table>
		<input type="hidden" value="0" name="dup">
	</form>
	<%
		// 아이디 중복 체크가 안 끝남
		// checkOk = 0;
	}
	
	if (check == 0) { // check==0 아이디가 중복되지 않음. 선택 가능.
	%>
	<script type="text/javascript">
		alert("사용 가능한 아이디입니다!");
	</script><br>
	<form action="MemberidCheck.me" method="post" name="cfr">
		<table border="1" id="check_Table">
			<tr>
				<td style="width: 100px;">&nbsp;아이디&nbsp;</td>
				<td id="check_Value"><input type="text" style="width: 200px;" name="userId" id="contents" value="<%=userId%>" readonly></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="아이디 선택" onclick="ok()" id="idselect"></td>
			</tr>
		</table>
		<input type="hidden" value="1" name="dup">		
	</form>
	<%
		// 선택 가능한 아이디를 만들었으면 아이디 중복 체크를 안해도되도록 변수 지정
		// checkOk = 1;
	}
	%>
</div>
</body>
</html>