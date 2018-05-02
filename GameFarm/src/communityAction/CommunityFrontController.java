package communityAction;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import communityAction.ActionForward;

// 자바파일 -> 서블릿파일 만듬(서블릿 상속)
public class CommunityFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommunityFrontController doProcess()");
		// 가상주소	http://localhost:8080/GameFarm/Community_InsertForm.co
		// 가상주소 뽑아오기
		// URI 주소 뽑아오기
		String requestURI = request.getRequestURI();
		System.out.println("URI 주소: "+requestURI);
		// /GameFarm 뽑아오기 
		String contextPath = request.getContextPath();
		System.out.println("프로젝트 경로: "+contextPath);
		System.out.println("프로젝트 길이: "+contextPath.length());
		// /*.co 뽑아오기
		String command = requestURI.substring(contextPath.length());
		System.out.println("뽑아낸 가상주소: "+command);
		// 가상주소 비교하기
		// ActionForward 객체 선언 변수 생성
		ActionForward forward = null;
		// 부모 메소드인 Action 인터페이스를 객체 선언 변수 생성
		Action action = null;
		if (command.equals("/CommunityInsertForm.co")) {	 // 글 작성 폼			
			// ActionForward 객체 생성
			forward = new ActionForward();
			// 이동할 경로를 지정
			forward.setPath("./Community/Community_Insert.jsp");
			// forward 방식으로 이동하므로 false로 지정		jsp면 false고 me면 true
			forward.setRedirect(false);			
		} else if (command.equals("/CommunityInsertAction.co")) {	// 글 작성 처리			
			action = new CommunityInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/CommunityList.co")) {	// 게시물들을 조회하기
			action = new CommunityList();
			// execute() 메소드 호출(이 메소드는 예외처리가 존재하므로 try catch 구문을 사용해서 호출한다)
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/CommunitySearchList.co")) {	// 게시물들을 조회하기
			action = new CommunitySearchList();
			// execute() 메소드 호출(이 메소드는 예외처리가 존재하므로 try catch 구문을 사용해서 호출한다)
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/CommunityDetail.co")) {	// 글 내용 보기
			action = new CommunityDetail();
			// execute() 메소드 호출(이 메소드는 예외처리가 존재하므로 try catch 구문을 사용해서 호출한다)
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} else if (command.equals("/CommunityUpdate.co")) {	// 글수정
			action = new CommunityUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CommunityUpdateAction.co")) {	// 글수정 처리
			action = new CommunityUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/imageDeleteAction.co")) {	// 이미지 삭제 처리
			action = new imageDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CommunityDeleteAction.co")) {	// 글삭제 처리
			action = new CommunityDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CommentInsertAction.co")) {	// 댓글입력 처리
			action = new CommentInsertAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CommentDeleteAction.co")) {	// 댓글삭제 처리
			action = new CommentDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/CommentUpdate.co")) {	// 댓글수정
			action = new CommentUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if (command.equals("/CommentUpdateAction.co")) {	// 댓글수정 처리
			action = new CommentUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// forward값이 없을 때					
		if (forward!=null) {
			if (forward.isRedirect()==true) {	// 가상주소로 이동할 때
				// 이동방식이 true: sendRedirect()
				response.sendRedirect(forward.getPath());
			} else {		// jsp로 이동할 때
				// 이동방식이 false: forward()
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
		System.out.println("CommunityFrontController doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CommunityFrontController doPost()");
		doProcess(request, response);
	}
}