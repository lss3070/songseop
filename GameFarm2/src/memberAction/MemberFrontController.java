package memberAction;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 자바파일 -> 서블릿파일 만듬(서블릿 상속)
public class MemberFrontController extends HttpServlet{
	// 메소드 오버라이딩(상속한 부모의 메소드를 재정의)
	// 알트 쉬프트 s -> v로 doGet(), doPost() 메소드 가져옴.
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MemberFrontController doProcess()");
		// 가상주소	http://localhost:8080/Model2/MemberJoin.me
		// 뽑아오기		/MemberJoin.me
		// URI 주소 뽑아오기	/Model2/MemberJoin.me
		String requestURI = request.getRequestURI();
		System.out.println("URI 주소: "+requestURI);
		// /Model2 뽑아오기 
		String contextPath = request.getContextPath();
		System.out.println("프로젝트 경로: "+contextPath);
		System.out.println("프로젝트 길이: "+contextPath.length());
		// /MemberJoin.me 뽑아오기
		String command = requestURI.substring(contextPath.length());
		System.out.println("뽑아낸 가상주소: "+command);
		// 가상주소 비교하기
		// ActionForward 객체 선언 변수 생성
		ActionForward forward = null;
		// 부모 메소드인 Action 인터페이스를 객체 선언 변수 생성
		Action action = null;
		if (command.equals("/LoginAction.me")) { // 로그인
			// 로그인 처리 작업 
			// action 인터페이스를 생성한다.
			// 처리작업틀을 받아서 만든 자바파일 메소드(추상메소드 - 인터페이스) 호출
			// Action 틀을 상속받은 자바 파일(MemberJoinAction.java)을 만든다.
			// MemberJoinAction 객체 생성  action에서 상속
			action = new LoginAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}		
		} else if (command.equals("/LogoutAction.me")) { // 로그아웃
			action = new LogoutAction();
			// execute() 메소드 호출(이 메소드는 예외처리가 존재하므로 try catch 구문을 사용해서 호출한다)
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/MemberJoin.me")) {	// 회원가입
			forward = new ActionForward();
			forward.setPath("./Member/Member_Join.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberIdCheck.me")) {	// 아이디 중복 확인
			action = new MemberIdCheck();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberJoinAction.me")) {	// 회원가입처리
			action = new MemberJoinAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MyPage.me")) {	// 마이페이지
			action = new MyPage();
			// execute() 메소드 호출(이 메소드는 예외처리가 존재하므로 try catch 구문을 사용해서 호출한다)
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/MemberUpdate.me")) {	// 회원정보수정
			action = new MemberUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberUpdateAction.me")) {	// 회원정보 수정 처리
			action = new MemberUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberDelete.me")) {	// 회원탈퇴 폼으로 이동
			forward = new ActionForward();
			forward.setPath("./Member/Member_Delete.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberDeleteAction.me")) {	// 회원탈퇴 처리
			action = new MemberDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (forward!=null) {
			if (forward.isRedirect()==true) {	// me로 이동할 때
				// 이동방식이 response.sendRedirect
				response.sendRedirect(forward.getPath());
			} else {		// jsp로 이동할 때
				// 이동방식이 false: forward
				// RequestDispatcher forward() 사용		
				// 가상 주소를 사용하면 실제 주소로 사용되는 jsp 파일의 css의 이미지 파일 경로와 같은 정보도 실제 주소 위치를 입력해줘야 적용된다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(request, response);
		System.out.println("MemberFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		System.out.println("MemberFrontController doPost()");
		doProcess(request, response);
	}

	
}
