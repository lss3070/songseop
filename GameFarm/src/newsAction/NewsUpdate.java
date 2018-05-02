package newsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newsDB.NewsBean;
import newsDB.NewsDAO;


public class NewsUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("NewsUpdate execute()");		
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 리퀘스트 값 가져오기
		int news_number = Integer.parseInt(request.getParameter("news_number"));
		String pageNum = request.getParameter("pageNum");
		// MemberDB 객체 생성
		NewsDAO ndao = new NewsDAO();
		// NewsBean nb = ndao.getDetail(comm_number) 값을 가져온다.
		NewsBean nb = ndao.getNews(news_number);		
		// request 자바빈 nb 저장
		request.setAttribute("nb", nb);
		// 이동 ./Community_Detail.jsp		
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();		
		forward.setPath("/News/News_Update.jsp");
		forward.setRedirect(false);		
		return forward;
	}

}
