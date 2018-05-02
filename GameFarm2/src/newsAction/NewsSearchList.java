package newsAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newsDB.NewsBean;
import newsDB.NewsDAO;

public class NewsSearchList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("NewsSearchList execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 검색어와 검색옵션에 대한 값을 변수에 담는다.
		String search = request.getParameter("search");
		//System.out.println(search);
		int searchOption = Integer.parseInt(request.getParameter("searchOption"));
		//System.out.println(searchOption);
		// NewsDAO 객체 생성
		NewsDAO ndao = new NewsDAO();
		// 검색된 게시물의 갯수를 searchcount 변수에 담는다.
		int searchCount = ndao.getSearchCount(search, searchOption);
		//System.out.println(searchCount);
		int pageSize = 10;
		// 페이지 번호 가져오기(페이지 번호가 없으면 "1" 페이지로 설정)		
		String pageNum = request.getParameter("pageNum");		
		if (pageNum==null) {	// 페이지 번호 값이 없으면 기본 페이지 번호를 1로 지정한다.
			pageNum = "1";				
		}
		// 10개로 나누어서 2번째 페이지를 시작하는 행 번호 구하기
		int currentPage = Integer.parseInt(pageNum);
		// int startRow = (현재 페이지 번호-1) * 한 화면에 보여지는 글 개수+1;
		int startRow = ((currentPage-1) * pageSize)+1;
		// 10개로 나누어서 2번째 페이지를 끝내는 행 번호 구하기
		// int endRow = 현재 페이지 번호 * 한 화면에 보여지는 글 개수
		int endRow = currentPage * pageSize;		
		// int maxPage = (글 갯수/한 페이지에 나오는 글 개수) + 1		(최대 페이지 넘버에 대한 알고리즘)
		int maxPage=(searchCount/pageSize)+1;
		// 게시물의 목록을 조회한다. 한 페이지 출력하는 목록은 각 페이지의 제일 낮은 게시물의 번호부터 제일 높은 게시물의 번호까지이다. 
		List<NewsBean> getSearchList = ndao.getSearchList(startRow, pageSize, search, searchOption);
		request.setAttribute("getSearchList", getSearchList);
		// 전체 페이지수 구하기 = (글 개수 / 페이지당 글 개수) + (글 개수 % 페이지당 글 개수==0 ? 나머지가 없으면 0: 나머지가 있으면 1);
		int pageCount = (searchCount/pageSize) + (searchCount%pageSize==0 ? 0:1);
		// 한 화면에 보여줄 페이지수 설정		 기본값: 10페이지
		int pageBlock = 10;
		// 한 화면에 보여줄 첫 페이지 번호 구하기 =
		// (((현재 페이지-1)/한 화면당 페이지 수) * 한 화면당 페이지 수) + 1
		int startPage = (((currentPage-1)/pageBlock) * pageBlock)+1;
		// 한 화면에 보여줄 끝 페이지 번호 구하기 =
		// 시작 페이지 + (한 화면당 페이지 수-1)
		int endPage = startPage+(pageBlock-1);
			if (pageCount < endPage) {// 전체 페이지 번호가 끝 페이지 번호보다 작을 때
				//끝 페이지 번호의 값에 전체 페이지 번호를 대입한다.
				endPage = pageCount;
			}
		// News_SearchList.jsp가 필요로 하는 request 값 저장						
		request.setAttribute("search", search);
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("searchCount", searchCount);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("getSearchList", getSearchList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("maxPage", maxPage);				
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();
		forward.setPath("/News/News_SearchList.jsp");
		forward.setRedirect(false);		
		return forward;
	}

}
