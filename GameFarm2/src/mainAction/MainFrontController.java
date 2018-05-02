package mainAction;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 자바파일 -> 서블릿파일 만듬(서블릿 상속)
public class MainFrontController extends HttpServlet{
	// 메소드 오버라이딩(상속한 부모의 메소드를 재정의)
	// 알트 쉬프트 s -> v로 doGet(), doPost() 메소드 가져옴.
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MainFrontController doProcess()");		
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
		if (command.equals("/Main.ma")) {	 // 메인 페이지
			action = new MainAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
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
		System.out.println("MainFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		System.out.println("MainFrontController doPost()");
		doProcess(request, response);
	}

	
}
