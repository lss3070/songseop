package paymentAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paymentDB.BasketBean;

public class Basket implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Basket");
		// 한글선언
		request.setCharacterEncoding("utf-8");

		// 세션으로 처리하기위해 세션에 넣을 값을 jsp에서 값을 받아옴
		String goods_name = request.getParameter("goods_name");
		int goods_price = Integer.parseInt(request.getParameter("goods_price"));
		String goods_image = request.getParameter("goods_image");
		int goods_count = Integer.parseInt(request.getParameter("cart_count"));
		int goods_id2 = Integer.parseInt(request.getParameter("goods_id"));
		int goods_platform = Integer.parseInt(request.getParameter("platform"));
		int goods_type = Integer.parseInt(request.getParameter("type"));

		// 세션에서 상품 리스트를 받아옴
		HttpSession session = request.getSession(true);
		ArrayList<BasketBean> gList = (ArrayList<BasketBean>) session.getAttribute("backetlist");

		// 리스트가 없을경우 리스트를 생성
		if (gList == null)
			gList = new ArrayList<BasketBean>();

		// 추가된 상품을 리스트에 담음
		gList.add(new BasketBean(goods_name, goods_price, goods_image, goods_count, goods_id2, goods_platform, goods_type));
		
		
		
		// 세션에 리스트를 추가
				session.setAttribute("backetlist", gList);
		

		// 스크립트
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("if(confirm('상품이 장바구니에 담겼습니다.장바구니 페이지로 가시겠습니까?')==true){");
		out.println("location.href='./BasketList.pa'}else{history.back()}");
		out.println("</script>");
		out.close();

		return null;
	}

}
