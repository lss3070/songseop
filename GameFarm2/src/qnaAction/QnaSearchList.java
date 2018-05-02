package qnaAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import communityDB.CommunityBean;
import communityDB.HotkeyBean;
import qnaDb.QnaBean;
import qnaDb.QnaDAO;

public class QnaSearchList implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaSearchList execute()");
		//한글출력
		request.setCharacterEncoding("utf-8");
		// 검색어와 검색옵션에 대한 값을 변수에 담는다.
		String search = request.getParameter("search");		
		int searchOption = Integer.parseInt(request.getParameter("searchOption"));
		QnaDAO qdao = new QnaDAO();
		// 검색된 게시물의 갯수를 searchcount 변수에 담는다.
		int searchCount = qdao.getSearchCount(search, searchOption);				
		//int count = getQnaCount()메서드 호출 count(*)
		//한페이지에 보여줄 글개수 설정
		int pageSize=10;
		//페이지번호 가져오기 페이지번호가 없으면 "1"페이지로 설정
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}				
		//10개로 나눠서 2번째페이지 시작하는 행번호 구하기3
		int currentPage = Integer.parseInt(pageNum);
		int startRow=((currentPage-1)*pageSize)+1;
		//10개로 나눠서 2번째페이지 끝나는 행번호 구하기
		int endRow = currentPage * pageSize;				
		//List getQnaList = qdao.getqnaList(startRow, pageSize, search);
		int maxPage=(searchCount/pageSize)+1;
		// 게시물의 목록을 조회한다. 한 페이지 출력하는 목록은 각 페이지의 제일 낮은 게시물의 번호부터 제일 높은 게시물의 번호까지이다. 
		List<QnaBean> getSearchList = qdao.getSearchList(startRow, pageSize, search, searchOption);				
		// 전체 페이지수 구하기
		// ex/ 게시판 전체 글개수 50개 한화면에 10개씩 -> 50/10 = 5+나머지없으면 0=5페이지 
		// 게시판 전체 글개수 55개 한화면에 10개씩 보이기 -> 55/10 = 5+나머지있으면 1페이지=6페이지
		int pageCount=searchCount/pageSize + (searchCount%pageSize==0?0:1);
		//조건이 참일때 값:조건이
		// 한화면에 보여줄 페이지수 설정
		int pageBlock=10;
		//한화면에 보여줄 첫페이지번호 구하기 현페이지 1~10 -> 1  11~20 -> 11
		int startPage=((currentPage-1)/pageBlock)*pageBlock+1;
		//한화면에 보여줄 끝페이지번호 구하기 1~10 -> 10	11~20->20
		//startpage pageBlock
		int endPage = startPage+pageBlock-1;
		if(pageCount < endPage){
			endPage=pageCount;
		}						
		//list.jsp 필요로 하는값 저장
		// count,pageNum,boardList,pageCount,pageBlock
		// startPage,endPage
		//request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("maxPage", maxPage);	
		request.setAttribute("search", search);
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("getSearchList", getSearchList);					
		request.setAttribute("searchCount", searchCount);					
		///Qna/QnaSearchList.jsp 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/Qna/QnaSearchList.jsp");
		forward.setRedirect(false);		
		return forward;
	}

}
