package adminGoodsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsDB.GoodsBean;
import goodsDB.GoodsDAO;

public class AdminGoodsModify implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminGoodsModify excute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		//goods_id 파라미터 가져오기
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));		
		//GoodsDAO 객체생성
		GoodsDAO gdao = new GoodsDAO();
		GoodsBean gb = gdao.getGoods(goods_id);		
		request.setAttribute("gb", gb);		
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();	
		// 이동
		forward.setPath("./AdminGoods/Admin_Goods_Modify.jsp");
		forward.setRedirect(false);		
		return forward;
	}
	
}
