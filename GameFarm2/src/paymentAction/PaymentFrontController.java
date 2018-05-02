package paymentAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("OrderFrontController doProcess()");
		// 가상주소 http://localhost:8080/Model2/MemberJoin.me
		// 뽑아오기 /MemberJoin.me
		// URI 주소 뽑아오기 /Model2/MemberJoin.me
		String requestURI = request.getRequestURI();
		System.out.println("URI 주소: " + requestURI);
		// /Model2 뽑아오기
		String contextPath = request.getContextPath();
		System.out.println("프로젝트 경로: " + contextPath);
		System.out.println("프로젝트 길이: " + contextPath.length());
		// /MemberJoin.me 뽑아오기
		String command = requestURI.substring(contextPath.length());
		System.out.println("뽑아낸 가상주소: " + command);
		// 가상주소 비교하기
		// ActionForward 객체 선언 변수 생성
		ActionForward forward = null;
		// 부모 메소드인 Action 인터페이스를 객체 선언 변수 생성
		Action action = null;

		if (command.equals("/OrderList.pa")) { //주문조회

			action = new OrderListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} else if (command.equals("/Basket.pa")) {//상품을 장바구니로 이동
			action = new Basket();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BasketList.pa")) {//장바구니 리스트출력
			forward = new ActionForward();
			forward.setPath("./Payment/Basket_List.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/BasketDeleteAcion.pa")) {//장바구니 목록삭제
			action = new BasketDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/PaymentOrder.pa")) {//주문조회
			action = new PaymentOrder();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/PaymentInsertAction.pa")) {//결제시 주문조회에 결제된 상품을 넣음
			action = new PaymentInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/PaymentDeleteAction.pa")) {//조문조회에서 결제가 완료되고 고객이 상품을 받을경우 삭제
			action = new PaymentDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect() == true) { // me로 이동할 때
				// 이동방식이 response.sendRedirect
				response.sendRedirect(forward.getPath());
			} else { // jsp로 이동할 때
				// 이동방식이 false: forward
				// RequestDispatcher forward() 사용
				// 가상 주소를 사용하면 실제 주소로 사용되는 jsp 파일의 css의 이미지 파일 경로와 같은 정보도 실제 주소
				// 위치를 입력해줘야 적용된다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(request, response);
		System.out.println("MemberFrontController doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		System.out.println("MemberFrontController doPost()");
		doProcess(request, response);
	}
}
