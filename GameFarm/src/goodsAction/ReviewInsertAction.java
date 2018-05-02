package goodsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goodsDB.GoodsDAO;
import goodsDB.ReviewBean;

public class ReviewInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ReViewInsertAction execute()");
		// 한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		
		// 자바빈 CommunityBean 객체 호출
		ReviewBean rb = new ReviewBean();

		// 현재 접속한 유저의 아이디가 담긴 세션값을 사용하기 위한 객체 생성,객체를 출력한다.
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("sMem_id");
		rb.setMem_id(mem_id);

		// jsp에서 platform이랑 type을 받아온다.
		int platform = Integer.parseInt(request.getParameter("platform"));
		int type = Integer.parseInt(request.getParameter("type"));

		// jsp에 받아온 값들을 bean에 저장시킨다.
		rb.setGoods_id(Integer.parseInt(request.getParameter("goods_id")));
		rb.setReview_content(request.getParameter("review_content"));
		System.out.println(request.getParameter("review_score"));
		rb.setReview_score(Integer.parseInt(request.getParameter("review_score")));

		// DAO선언
		GoodsDAO gdao = new GoodsDAO();
		// ,DB상에 데이터를 담는다.
		gdao.insertReview(rb);

		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();
		forward.setPath("/Goods.go?goods_id=" + rb.getGoods_id() + "&platform=" + platform + "&type=" + type);
		forward.setRedirect(false);
		return forward;
	}

}
