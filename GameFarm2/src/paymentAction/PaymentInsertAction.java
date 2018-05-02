package paymentAction;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adminGoodsDB.AdminGoodsDAO;
import adminMemberDB.AdminMemberDAO;
import goodsDB.GoodsDAO;
import memberDB.MemberBean;
import memberDB.MemberDAO;
import paymentDB.BasketBean;
import paymentDB.PaymentBean;
import paymentDB.PaymentDAO;

public class PaymentInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		// basketlist,sMem_id 세션값을 받아옴
		HttpSession session = request.getSession();
		ArrayList<BasketBean> gList = (ArrayList<BasketBean>) session.getAttribute("backetlist");
		String mem_id = (String) session.getAttribute("sMem_id");

		// jsp페이지에서 결제정보를 받아옴
		int receiver_post = Integer.parseInt(request.getParameter("receiver_post"));
		String receiver_address1 = request.getParameter("receiver_address1");
		String receiver_address2 = request.getParameter("receiver_address2");
		String receiver_name = request.getParameter("receiver_name");
		String receiver_phone1 = request.getParameter("receiver_phone1");
		String receiver_phone2 = request.getParameter("receiver_phone2");
		String receiver_phone3 = request.getParameter("receiver_phone3");
		int mem_point = Integer.parseInt(request.getParameter("mem_point"));
		int order_status = Integer.parseInt(request.getParameter("order_status"));
		String idlist = request.getParameter("goods_id_list");//상품id배열
		String countlist = request.getParameter("goods_volume_list");//상품개수배열
		String pricelist = request.getParameter("goods_price_list");//상품가격배열
		String list = request.getParameter("arr");//상품순번배열

		// 결제될 상품들의 배열선언
		String[] list2 = null;
		String[] idlist2 = null;
		String[] countlist2 = null;
		String[] pricelist2 = null;

		// 결제될 상품들을 배열로 넣기위해 콤마
		idlist2 = idlist.split(",");
		countlist2 = countlist.split(",");
		pricelist2 = pricelist.split(",");
		list2 = list.split(",");

		// 객체 호출
		PaymentBean pb = new PaymentBean();
		PaymentDAO pdao = new PaymentDAO();
		GoodsDAO gdao = new GoodsDAO();
		AdminGoodsDAO agdao = new AdminGoodsDAO();

		// 결제정보에 값들을 다 넣음
		for (int i = 0; i < idlist2.length; i++) {
			pb.setGoods_id(Integer.parseInt(idlist2[i]));
			pb.setReceiver_post(receiver_post);
			pb.setReceiver_address1(receiver_address1);
			pb.setReceiver_address2(receiver_address2);
			pb.setReceiver_name(receiver_name);
			pb.setReceiver_phone1(receiver_phone1);
			pb.setReceiver_phone2(receiver_phone2);
			pb.setReceiver_phone3(receiver_phone3);
			pb.setMem_id(mem_id);
			pb.setOrder_volume(Integer.parseInt(countlist2[i]));
			pb.setPayment_price(Integer.parseInt(pricelist2[i]));
			pb.setOrder_status(order_status);
			//전체 재고수에서 구매한 개수를 빼준다
			int stock = agdao.selectStock(Integer.parseInt(idlist2[i])) - Integer.parseInt(countlist2[i]);
			
			// 주문한 것보다 재고수가 작을 경우
			if (stock < 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('재고가 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			// 재고수를 db에 갱신시킴
			agdao.updateStock(stock, Integer.parseInt(idlist2[i]));
			pdao.insertPayment(pb, mem_id);
		}

		// 장바구니삭제,역순으로 삭제시켜준다
		for (int i = list2.length - 1; i >= 0; i--) {
			System.out.println("삭제순서" + i);
			if (list2[i].equals(String.valueOf(i))) {
				System.out.println("삭제되는거" + list2[i]);
				gList.remove(i);
			}
		}

		// point갱신시킴
		MemberBean mb = new MemberBean();
		AdminMemberDAO amdao = new AdminMemberDAO();
		mb.setMem_point(mem_point);
		mb.setMem_id(mem_id);
		MemberDAO mdao = new MemberDAO();
		amdao.updatePoint(mb);

		// 스크립트를 출력할 객체 생성
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('구매가 완료되었습니다.');");
		out.println("location.href='./Main.ma'");
		out.println("</script>");
		out.close();

		return null;
	}

}
