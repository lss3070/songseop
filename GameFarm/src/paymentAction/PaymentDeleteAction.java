package paymentAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paymentDB.PaymentDAO;

public class PaymentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//한글
		request.setCharacterEncoding("utf-8");
		
		//결제 아이디를 받아온다
		int payment_id = Integer.parseInt(request.getParameter("payment_id"));
		
		//객체생성
		PaymentDAO pdao = new PaymentDAO();
		
		//받아온 결제아이디를 db상에서 삭제
		pdao.deletePayment(payment_id);
		
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('구매가 완료되었습니다.');");
		out.println("location.href='./OrderList.pa'");
		out.println("</script>");
		out.close();
		return null;
	}

}
