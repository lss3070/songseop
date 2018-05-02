package adminGoodsAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoodsFrontController extends HttpServlet{	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AdminGoodsFrontController doProcess()");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;		
		if (command.equals("/AdminGoodsInsert.ag")) {	// 상품 등록
			forward = new ActionForward();
			forward.setPath("./AdminGoods/Admin_Goods_Insert.jsp");
			forward.setRedirect(false);	
		}else if (command.equals("/AdminGoodsInsertAction.ag")) { // 상품 등록 처리		
			action = new AdminGoodsInsertAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/AdminGoodsList.ag")) {//  관리자가 보는 상품 목록
			action = new AdminGoodsList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (command.equals("/AdminGoodsSearchList.ag")) {//  관리자가 보는 상품 검색 목록
			action = new AdminGoodsSearchList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (command.equals("/AdminGoodsModify.ag")) {//	상품 내용 수정
			action = new AdminGoodsModify();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (command.equals("/AdminGoodsModifyAction.ag")) {	//		상품 내용 수정 처리
			action = new AdminGoodsModifyAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if (command.equals("/AdminGoodsDeleteAction.ag")) {	//		상품 삭제 처리
			action = new AdminGoodsDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (forward!=null) {
			if (forward.isRedirect()==true) {
				response.sendRedirect(forward.getPath());
			} else {		
	
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AdminGoodsFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AdminGoodsFrontController doPost()");
		doProcess(request, response);
	}		
}


