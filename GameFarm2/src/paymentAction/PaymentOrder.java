package paymentAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memberDB.MemberBean;
import memberDB.MemberDAO;
import paymentDB.BasketBean;


public class PaymentOrder implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		// id 세션값 받아옴
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("sMem_id");

		// 회원정보를 불러옴
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = mdao.getMember(mem_id);

		// 장바구니에서 선택된 목록을 받아옴
		String list = request.getParameter("arr");
		String namelist = request.getParameter("arr1");
		String idlist = request.getParameter("arr2");
		String countlist = request.getParameter("arr3");
		String pricelist = request.getParameter("arr4");
		String imagelist = request.getParameter("arr5");

		// 배열을 선언
		String[] list2 = null;
		String[] namelist2 = null;
		String[] idlist2 = null;
		String[] countlist2 = null;
		String[] pricelist2 = null;
		String[] imagelist2 = null;

		// 배열에 장바구니에서 선택된 값들을 넣음
		list2 = list.split(",");
		namelist2 = namelist.split(",");
		idlist2 = idlist.split(",");
		countlist2 = countlist.split(",");
		pricelist2 = pricelist.split(",");
		imagelist2 = imagelist.split(",");

		// bean에 넣을 배열선언
		ArrayList<BasketBean> pList = new ArrayList<BasketBean>();

		// 장바구니의 값이 한개일때랑 여러개일때랑 틀림
		if (idlist2.length != 1) {
			for (int i = 0; i < idlist2.length; i++) {
				if (list2[i].equals(String.valueOf(i))) {// 장바구니 값이 여러개일경우
					pList.add(new BasketBean(namelist2[i],Integer.parseInt(pricelist2[i]), imagelist2[i],
							Integer.parseInt(countlist2[i]),Integer.parseInt(idlist2[i]), 0,0));
				}
			} // for끝
		} else {// 장바구니 값이 하나밖에없을경우
			pList.add(new BasketBean(namelist,Integer.parseInt(pricelist), imagelist,Integer.parseInt(countlist),
					Integer.parseInt(idlist),0,0));
		}

		// 결제금액 합계
		int sum = 0;
		for (int i = 0; i < pList.size(); i++) {
			BasketBean pb = (BasketBean) pList.get(i);
			sum = sum + pb.getGoods_price() * pb.getGoods_count();
		}

		// 객체를 담음
		request.setAttribute("total", sum);// 결제금액합계
		request.setAttribute("pb", pList);// 결제리스트
		request.setAttribute("list", list);// 결제되면 장바구니에서 삭제될 것들.
		request.setAttribute("mb", mb);// 회원정보

		// 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Payment/Payment_Order.jsp");
		forward.setRedirect(false);
		return forward;

	}

}
