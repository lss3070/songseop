package newsAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewsFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NewsFrontController doProcess()");
		// 가상의 주소 http://localhost:8080/Model2/NewsWrite.nw
		// 가상주소 뽑아오기
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=requestURI.substring(contextPath.length());
		//  /NewsWrite.nw
		System.out.println("뽑아온 주소 : "+command); 
		// 뽑아온 가상주소 비교
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/NewsWrite.nw")){
			// forward 객체생성
			forward=new ActionForward();
			// 이동경로 ./News/writeForm.jsp
			forward.setPath("./News/News_Write.jsp");
			// 이동방식  저장
			forward.setRedirect(false);
		}else if(command.equals("/NewsWriteAction.nw")){
			// NewsWriteAction 만들기  // Action 인터페이스 상속
			// NewsWriteAction 객체 생성 // execute() 메서드호출
			action=new NewsWriteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/NewsList.nw")){
			// NewsList 만들기 인터페이스 상속
			// 객체 생성 // 메서드호출
			action=new NewsList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/NewsSearchList.nw")) {	// 게시물들을 조회하기
			action = new NewsSearchList();
			// execute() 메소드 호출(이 메소드는 예외처리가 존재하므로 try catch 구문을 사용해서 호출한다)
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/NewsContent.nw")) {	// 게시물들을 조회하기
			action = new NewsContent();
			// execute() 메소드 호출(이 메소드는 예외처리가 존재하므로 try catch 구문을 사용해서 호출한다)
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NewsUpdate.nw")){
			action=new NewsUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/NewsUpdateAction.nw")){
			action=new NewsUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/NewsDeleteAction.nw")) {	// 글삭제 처리
			action = new NewsDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 이동
		if(forward!=null){
			if(forward.isRedirect()){
				// sendRedirect()
				response.sendRedirect(forward.getPath());
			}else{
				// forward()
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NewsFrontController doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NewsFrontController doPost()");
		doProcess(request, response);
	}

}
