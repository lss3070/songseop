package newsAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newsDB.NewsBean;
import newsDB.NewsDAO;

public class NewsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("NewsDeleteAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 게시물의 번호가 담긴 리퀘스트 값을 변수에 담는다.
		int news_number = Integer.parseInt(request.getParameter("news_number"));
		// NewsDB 관련 객체들을 선언한다.
		NewsDAO ndao = new NewsDAO();
		NewsBean nb = new NewsBean();
		// 게시물을 삭제하기 위한 조건값인 news_number 값을 가져온다.
		nb.setNews_number(news_number);								
		// 게시물 삭제 처리				
		ndao.deleteNews(news_number);				
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('게시물을 삭제하였습니다.');");
		out.println("location.href='./NewsList.nw'");
		out.println("</script>");
		out.close();						
		return null;
	}

}
