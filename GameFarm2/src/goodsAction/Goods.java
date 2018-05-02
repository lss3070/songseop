package goodsAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goodsDB.GoodsBean;
import goodsDB.GoodsDAO;
import goodsDB.ReviewBean;

public class Goods implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("Goods execute()");
		// 한글처리
		request.setCharacterEncoding("utf-8");
		
		//객체 불러옴
		GoodsDAO gdao = new GoodsDAO();

		// list에서 id,platform,type불러옴
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		int platform = Integer.parseInt(request.getParameter("platform"));
		int type = Integer.parseInt(request.getParameter("type"));

		// 리뷰의 갯수를 count 변수에 담는다.
		int count = 0;
		count = gdao.getReviewCount(goods_id);
		
		// 한 페이지에 보여지는 글 개수는 10개로 설정한다.
		int pageSize = 5;
		
		// 페이지 번호 가져오기(페이지 번호가 없으면 "1" 페이지로 설정)
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) { // 페이지 번호 값이 없으면 기본 페이지 번호를 1로 지정한다.
			pageNum = "1";
		}
		
		// 10개로 나누어서 2번째 페이지를 시작하는 행 번호 구하기
		int currentPage = Integer.parseInt(pageNum);
		
		// int startRow = (현재 페이지 번호-1) * 한 화면에 보여지는 글 개수+1;
		int startRow = ((currentPage - 1) * pageSize) + 1;
		
		// 10개로 나누어서 2번째 페이지를 끝내는 행 번호 구하기
		// int endRow = 현재 페이지 번호 * 한 화면에 보여지는 글 개수
		int endRow = currentPage * pageSize;
		
		// int maxPage = (글 갯수/한 페이지에 나오는 글 개수) + 1 (최대 페이지 넘버에 대한 알고리즘)
		int maxPage = (count / pageSize) + 1;
		
		// 리뷰리스트를 받아온다
		List<ReviewBean> getReviewList = gdao.getReviewList(goods_id, startRow - 1, pageSize);
		
		// 전체 페이지수 구하기 = (글 개수 / 페이지당 글 개수) + (글 개수 % 페이지당 글 개수==0 ? 나머지가 없으면 0:
		// 나머지가 있으면 1);
		int pageCount = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
		
		// 한 화면에 보여줄 페이지수 설정 기본값: 10페이지
		int pageBlock = 10;
		
		// 한 화면에 보여줄 첫 페이지 번호 구하기 =
		// (((현재 페이지-1)/한 화면당 페이지 수) * 한 화면당 페이지 수) + 1
		int startPage = (((currentPage - 1) / pageBlock) * pageBlock) + 1;
		
		// 한 화면에 보여줄 끝 페이지 번호 구하기 =
		// 시작 페이지 + (한 화면당 페이지 수-1)
		int endPage = startPage + (pageBlock - 1);
		
		if (pageCount < endPage) {// 전체 페이지 번호가 끝 페이지 번호보다 작을 때
			// 끝 페이지 번호의 값에 전체 페이지 번호를 대입한다.
			endPage = pageCount;
		}

		// gb에 sql문으로 출력한 정보들을 담는다.
		GoodsBean gb = gdao.getGoods(goods_id);
		
		//변수들을 담는다.
		request.setAttribute("gb", gb);
		request.setAttribute("getReviewList", getReviewList);
		request.setAttribute("platform", platform);
		request.setAttribute("type", type);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("maxPage", maxPage);

		// 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Goods/Goods.jsp");
		forward.setRedirect(false);
		return forward;

	}

}
