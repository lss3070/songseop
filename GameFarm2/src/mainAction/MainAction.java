package mainAction;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import adminGoodsDB.AdminGoodsDAO;
import goodsDB.GoodsDAO;
import newsDB.NewsDAO;

public class MainAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MainAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 필요한 DAO 객체 생성
		GoodsDAO gdao = new GoodsDAO();
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		NewsDAO ndao = new NewsDAO();
		// 공지사항 개수 가져오기
		int newsCount = ndao.getNewsCount();		
		List mainNewsList = ndao.getMainNewsList();
		// 물품 개수 가져오기
		int goodsCount = gdao.getGoodsCount();		
		// PS4 신제품 가져오기
		int platform = 1;
		List newPs4GoodsList=agdao.getNewGoodsList(platform);
		// 스위치 신제품 가져오기
		platform = 2;
		List newSwitchGoodsList=agdao.getNewGoodsList(platform);
		// 엑스박스 신제품 가져오기
		platform = 3;
		List newXboxGoodsList=agdao.getNewGoodsList(platform);		
		// 담아온 리스트들을 리퀘스트 변수에 담는다.
		request.setAttribute("newsCount", newsCount);
		request.setAttribute("mainNewsList", mainNewsList);
		request.setAttribute("goodsCount", goodsCount);
		request.setAttribute("newPs4GoodsList", newPs4GoodsList);
		request.setAttribute("newSwitchGoodsList", newSwitchGoodsList);
		request.setAttribute("newXboxGoodsList", newXboxGoodsList);		
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.jsp");
		forward.setRedirect(false);
		return forward;		
	}	
}
