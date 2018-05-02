package goodsAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsDB.GoodsDAO;
import goodsDB.ReviewBean;

public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ReviewDeleteAction execute()");
		// 한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");

		// jsp에서 값을 받아온 리뷰번호,플랫폼,타입,상품번호들을 변수에 넣어준다.
		int review_number = Integer.parseInt(request.getParameter("review_number"));
		int platform = Integer.parseInt(request.getParameter("platform"));
		int type = Integer.parseInt(request.getParameter("type"));
		String goods_id = request.getParameter("goods_id");

		// goodsDB 관련 객체들을 생성한다.
		GoodsDAO rdao = new GoodsDAO();
		ReviewBean rb = new ReviewBean();
		
		// 게시물 삭제 처리
		rdao.deleteReview(review_number);

		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();
		// 이동 정보 저장
		forward.setPath("/Goods.go?&goods_id" + goods_id + "&platform=" + platform + "&type=" + type);
		forward.setRedirect(false);
		return forward;
	}

}
