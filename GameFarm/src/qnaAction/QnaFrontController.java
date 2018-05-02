package qnaAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class QnaFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("QnaFrontController doProcess()");		
		//가상주소  http://localhost:8080/GameFarm/BoardWrite.bo
		//가상주소 뽑아오기 
		String requestURI=request.getRequestURI();			
		String contextPath=request.getContextPath();
		String command = requestURI.substring(contextPath.length());		
		System.out.println("뽑아온 주소 : "+command);
		//뽑아온 가상주소 비교
		ActionForward forward=null;
		Action action=null;
		if(command.equals("/QnaInsert.qn")){
			//forward 객체생성
			forward = new ActionForward();
			//이동경로 .//FaqInsert.jsp
			forward.setPath("./Qna/QnaInsert.jsp");
			//이동방식저장
			forward.setRedirect(false);
		}
		else if(command.equals("/QnaInsertAction.qn")){
			//QnaInsertAction 만들기 //Action 인터페이스 상속
			//QnaInsertAction 객체생성 //execute()메서드호출
			action = new QnaInsertAction();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}								
		}
		else if(command.equals("/QnaList.qn")){
			//QnaList 만들기 //Action 인터페이스 상속
			//QnaList 객체생성 //execute()메서드호출
			action = new QnaList();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}								
		}
		else if(command.equals("/QnaSearchList.qn")){
			//QnaSearchList 만들기 //Action 인터페이스 상속
			//QnaSearchList 객체생성 //execute()메서드호출
			action = new QnaSearchList();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}								
		}
		else if(command.equals("/QnaDetail.qn")){
			//QnaDetail 만들기 //Action 인터페이스 상속
			//QnaDetail 객체생성 //execute()메서드호출
			action = new QnaDetail();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}								
		}
		else if(command.equals("/QnaUpdate.qn")){
			//QnaUpdate 만들기 //Action 인터페이스 상속
			//QnaUpdate 객체생성 //execute()메서드호출
			action = new QnaUpdate();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}								
		}
		else if(command.equals("/QnaUpdateAction.qn")){
			//QnaUpdateAction 만들기 //Action 인터페이스 상속
			//QnaUpdateAction 객체생성 //execute()메서드호출
			action = new QnaUpdateAction();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}								
		}
		else if(command.equals("/QnaDeleteAction.qn")){
			//QnaDeleteAction 만들기 //Action 인터페이스 상속
			//QnaDeleteAction 객체생성 //execute()메서드호출
			action = new QnaDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}								
		}
		else if(command.equals("/QnaReInsert.qn")){
			//이동 ./board/ReInsert.jsp
			forward = new ActionForward();
			forward.setPath("./Qna/QnaReInsert.jsp");
			forward.setRedirect(false);
			 
		}
		else if(command.equals("/QnaReInsertAction.qn")){
			//QnaReInsertAction 만들기 //Action 인터페이스 상속
			//QnaReInsertAction 객체생성 //execute()메서드호출
			action = new QnaReInsertAction();
			try{
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}	
		//이동
		if(forward!=null){
			if(forward.isRedirect()){
				//sendRedirect()
				response.sendRedirect(forward.getPath());
			}else{
				//forward()
				RequestDispatcher dispatcher
				=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}		
	}	
		@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}		
}
