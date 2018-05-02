package adminPaymentAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import adminPaymentDB.AdminPaymentDAO;




public class AdminOrderUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//한글 출력이 가능하도록!
				request.setCharacterEncoding("utf-8");
				System.out.println("AdminOrderUpdate execute");
				// 폼에서 입력받은 아이디와 비밀번호를 변수에 저장한다.
				int payment_id = Integer.parseInt(request.getParameter("payment_id"));
				int order_status = Integer.parseInt(request.getParameter("order_status"));
				String order_waybill = request.getParameter("order_waybill");
				int pageNum = Integer.parseInt(request.getParameter("pageNum"));
				
		

				
				// MemberDAO 객체 생성
				AdminPaymentDAO adao = new AdminPaymentDAO();
				
				adao.UpdatePost(order_status, order_waybill, payment_id);
				 
				// ActionForward forward 객체 생성
				ActionForward forward = new ActionForward();
				forward.setPath("/AdminOrderList.ap?pageNum="+pageNum);
				forward.setRedirect(false);
				return forward;		
	}

}
