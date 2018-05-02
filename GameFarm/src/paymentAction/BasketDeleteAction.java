package paymentAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paymentDB.BasketBean;

public class BasketDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BasketDelete입니다");
		// 한글선언
		request.setCharacterEncoding("utf-8");

		// backetlist세션을 불러옴
		HttpSession session = request.getSession(true);
		ArrayList<BasketBean> gList = (ArrayList<BasketBean>) session.getAttribute("backetlist");

		// 장바구니에서 체크된 열을 받아옴
		String list = request.getParameter("arr");

		// 배열선언
		String[] list2 = null;

		// 배열로 넣기위해 콤마
		list2 = list.split(",");

		// 체크된 값들을 받아와 역순으로 삭제
		for (int i = list2.length - 1; i >= 0; i--) {
			System.out.println("삭제순서" + i);
			if (list2[i].equals(String.valueOf(i))) {
				System.out.println("삭제되는거" + list2[i]);
				gList.remove(i);
			}
		}

		// 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Payment/Basket_List.jsp");
		forward.setRedirect(false);

		return forward;

	}

}
