<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- Navigation -->
 <!-- Custom styles for this template -->
 <link href="css/modern-business.css?ver=1" rel="stylesheet">
 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
 <style type="text/css"> 
  .container {
    min-width: 94% !important;
}
 #navbarDropdownBlog {
 	padding-left: 1em; 	
 	font-size: 1.4em;
 }
 #navbarDropdownPortfolio {
 	padding-left: 1em;
 	font-size: 1.4em; 	
 }
 #headerLabel {
 	margin-left: 0.1em;
 	margin-right: 0.1em;
 	margin-top: 1em;
 	font-family: "Jeju Hallasan";
	color: #eee;
	font-size: 1.1em;
	/* 한 줄에 있는 것들을 하나의 블록으로 묶는다 */
	text-align: right;
	display: inline-block;
  	width: 50px;
}
#connectedLabel {
 	margin-left: 0.1em;
 	margin-right: 0.1em;
 	margin-top: 1em;
 	font-family: "Jeju Hallasan";
	color: #eee;
	font-size: 1.1em;
	/* 한 줄에 있는 것들을 하나의 블록으로 묶는다 */
	text-align: right;
	display: inline-block;  
}
#logintext {
	font-family: "맑은 고딕";
	display: inline-block;
	background-color: rgba(255,255,255,0);
	border: none;
	border-bottom: 3px solid #3993A4;
	font-size: 1.1em;
	font-weight: bold;
	width: 200px;
	padding-left: 0.3em;
	padding-top: 0.1em;
	padding-bottom: 0.1em;
	color: #eee;	
	position:relative;
}
#logintext:FOCUS {
	border-bottom: 4px solid red;
	background-color: rgba(66, 77, 256, 0.3);
} 
.btn-primary {
	background-color: #5375ce;
	font-family: 'Jeju Hallasan';
	width: 100px  !IMPORTANT; 
    font-size: 22px   !IMPORTANT; 
    text-align: center  !IMPORTANT; 
    padding: 4px 2px  !IMPORTANT; 
    margin:20px 10px 10px 0 ;
    cursor: pointer;
}
/* 로그인 텍스트 */

/* 장바구니 */
.cart_text { 
	border:3px solid red; 
  	border-radius: 50%;
  	background-color: red;
  	color: white;  
  	font-family: 'Jeju Gothic';
  	font-weight: bold;	
}
/* 장바구니 */
 </style>
<% 
	request.setCharacterEncoding("utf-8");
 	String mem_id = (String)session.getAttribute("sMem_id");
 	String mem_name = (String)session.getAttribute("sMem_name");
%>	
 <header>
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="index.jsp"><span style="color: red;">G</span><span style="color: #edd;">ame</span> <span style="color: red;">F</span><span style="color: #edd;">arm</span></a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        	<% if (mem_id==null) { // 로그인하지 않았다면? %>
                  	
          	<li class="nav-item dropdown">          	
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">로그인</a>
             	 <div class="dropdown-menu dropdown-menu-login" aria-labelledby="navbarDropdownBlog">
             	 <form action="./LoginAction.me" name="login" id="join" method="post">
            	 	<label id="headerLabel">I D　</label><input type="text" name="mem_id" id="logintext" placeholder="아이디를 입력하세요" required><br>
				    <label id="headerLabel">P W　</label><input type="password" name="mem_pass" id="logintext" placeholder="비밀번호를 입력하세요" required><br>
               		<button type="submit" class="btn btn-primary">로그인</button><button type="button" onclick="location.href='./MemberJoin.me'" class="btn btn-primary">회원가입</button>
              	 </form>
              	 </div>
              </li>	 
              <% 
              } else { // 로그인했을 경우 나머지 세션값들을 모두 가져온다.
            	  // 장바구니에 들어있는 물품의 수를 표시한다.
            	  int cart = 10;
                  if (mem_id.equals("admin") || mem_id.equals("adminLee") || mem_id.equals("adminShin") || mem_id.equals("adminSon") || mem_id.equals("adminGang") || mem_id.equals("adminJang") || mem_id.equals("adminPark")) {	// 관리자 id로 접속했을 때
              %>
	              <li class="nav-item dropdown">
	              	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	              	<span style="color:red"><%=mem_name%>(관리자)</span>
	              	</a>              	
	              		<div class="dropdown-menu dropdown-menu-right cart" aria-labelledby="navbarDropdownPortfolio">
	              			<a class="dropdown-item" href="./AdminGoodsInsert.ag">상품 등록</a>
	              			<a class="dropdown-item" href="./AdminGoodsList.ag">상품 목록</a>
	              			<a class="dropdown-item" href="./AdminMemberList.am">회원 목록</a>
	              			<a class="dropdown-item" href="./AdminOrderList.ap">주문 조회</a>	
			               	<a class="dropdown-item" href="./MyPage.me">마이페이지</a>		               			      
			                <a class="dropdown-item" href="./LogoutAction.me">로그아웃</a>
	              		</div>
	              </li>
              <%		  
                  } else { // 관리자 계정이 아닌 일반 사용자 계정일 때
              %>	  
	              <li class="nav-item dropdown">
	                	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                	<%=mem_name%><%-- 　<span class="cart_text"><%=cart%></span> --%>
	                	</a>              	
	                		<div class="dropdown-menu dropdown-menu-right cart" aria-labelledby="navbarDropdownPortfolio">
	  		               	<a class="dropdown-item" href="./BasketList.pa">장바구니<%-- 　<span class="cart_text"><%=cart%></span> --%></a>	  		               	
	  		               	<a class="dropdown-item" href="./MyPage.me">마이페이지</a>
	  		               	<a class="dropdown-item" href="./OrderList.pa">주문 조회</a>		      
	  		                <a class="dropdown-item" href="./LogoutAction.me">로그아웃</a>
	                		</div>
	              </li>             
              <%
                  }
              }
              %>
              
                 
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                PS4
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                <a class="dropdown-item" href="./GoodsList.go?platform=1&type=1">H/W</a>                
                <a class="dropdown-item" href="./GoodsList.go?platform=1&type=2">S/W</a>
                <a class="dropdown-item" href="./GoodsList.go?platform=1&type=3">주변기기</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                SWITCH
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                <a class="dropdown-item" href="./GoodsList.go?platform=2&type=1">H/W</a>                
                <a class="dropdown-item" href="./GoodsList.go?platform=2&type=2">S/W</a>
                <a class="dropdown-item" href="./GoodsList.go?platform=2&type=3">주변기기</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                XBOX One
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                <a class="dropdown-item" href="./GoodsList.go?platform=3&type=1">H/W</a>                
                <a class="dropdown-item" href="./GoodsList.go?platform=3&type=2">S/W</a>
                <a class="dropdown-item" href="./GoodsList.go?platform=3&type=3">주변기기</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                커뮤니티
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                <a class="dropdown-item" href="CommunityList.co">게임 이야기</a>
                 <a class="dropdown-item" href="NewsList.nw">공지사항</a>
                <a class="dropdown-item" href="QnaList.qn">고객센터</a>                                
              </div>
            </li>                        
          </ul>
        </div>
      </div>
    </nav>
    </header>