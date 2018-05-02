package paymentAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paymentDB.BasketBean;

public class BasketList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BasketList입니다");
		request.setCharacterEncoding("utf-8");

		// 세션에서 상품 리스트를 받아옴
		HttpSession session = request.getSession(true);
		ArrayList<BasketBean> gList = (ArrayList<BasketBean>) session.getAttribute("backetlist");

		// 리스트가 없을경우 리스트를 생성
		if (gList == null)
			gList = new ArrayList<BasketBean>();

		// 세션에 리스트를 추가
		session.setAttribute("backetlist", gList);

		// 반복문을 통해 bean에 세션값들을 넣는다.
		for (int i = 0; i < gList.size(); i++) {
			BasketBean basket = (BasketBean) gList.get(i);
		}

		// 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Basket_List.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
