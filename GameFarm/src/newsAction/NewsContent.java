package newsAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newsDB.NewsBean;
import newsDB.NewsDAO;

public class NewsContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/* 게시물의 내용을 가져오는 부분 */
		System.out.println("NewsContent execute()");		
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 리퀘스트 값 가져오기
		int news_number = Integer.parseInt(request.getParameter("news_number"));
		String pageNum = request.getParameter("pageNum");			
		// NewsDAO 객체 생성
		NewsDAO ndao = new NewsDAO();
		
		ndao.setReadCount(news_number);
		// 게시물들을 조회하기 위한 메소드 호출
		NewsBean nb = ndao.getNews(news_number);
		// request 자바빈 nb 저장
		request.setAttribute("nb", nb);
		/* 게시물의 내용을 가져오는 부분 */
		
		// 이동 ./News_Content.jsp		
		// ActionForward forward 객체 생성		
		ActionForward forward = new ActionForward();		
		forward.setPath("/News/News_Content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
