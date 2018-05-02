package adminGoodsAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import adminGoodsDB.AdminGoodsDAO;

public class AdminGoodsDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminGoodsDeleteAction excute()");		
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// DAO 객체 생성
		AdminGoodsDAO agdao = new AdminGoodsDAO();			
		// request.getParameter("goods_id")
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		agdao.deleteGoods(goods_id);			
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('상품을 삭제하였습니다.');");
		out.println("location.href='./AdminGoodsList.ag'");
		out.println("</script>");
		out.close();		
		return null;
	}
	
}
