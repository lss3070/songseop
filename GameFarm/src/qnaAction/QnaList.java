package qnaAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnaDb.QnaDAO;

public class QnaList implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaList execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		//QnaDAO객체생성
		QnaDAO qdao = new QnaDAO();
		//int count = getQnaCount()메서드 호출 count(*)
		int count = qdao.getQnaCount();		
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
		// 게시물의 목록을 조회한다.
		List getQnaList = qdao.getQnaList(startRow, pageSize);
		int maxPage=(count/pageSize)+1;
		// 전체 페이지수 구하기
		// ex/ 게시판 전체 글개수 50개 한화면에 10개씩 -> 50/10 = 5+나머지없으면 0=5페이지 
		// 게시판 전체 글개수 55개 한화면에 10개씩 보이기 -> 55/10 = 5+나머지있으면 1페이지=6페이지
		int pageCount=count/pageSize + (count%pageSize==0?0:1);
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
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("getQnaList", getQnaList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("maxPage", maxPage);
		//./Qna/QnaList.jsp이동
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./Qna/QnaList.jsp");
		return forward;
	} 
}
