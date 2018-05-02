package communityAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import communityDB.*;


public class CommunityDetail implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/* 게시물의 내용을 가져오는 부분 */
		System.out.println("CommunityDetail execute()");		
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 리퀘스트 값 가져오기
		int comm_number = Integer.parseInt(request.getParameter("comm_number"));
		String pageNum = request.getParameter("pageNum");			
		// CommunityDAO 객체 생성
		CommunityDAO cdao = new CommunityDAO();
		// 조회수를 1회 추가한다.
		cdao.setReadCount(comm_number);
		// 게시물들을 조회하기 위한 메소드 호출
		CommunityBean cb = cdao.getDetail(comm_number);
		// request 자바빈 cb 저장
		request.setAttribute("cb", cb);
		/* 게시물의 내용을 가져오는 부분 */
		
		/* 현재 게시물의 댓글리스트를 가져오는 부분 */
		// 댓글의 갯수를 commentCount 변수에 담는다.
		int commentCount = 0;
		commentCount = cdao.getCommentCount(comm_number);		
		// 한 페이지에 보여지는 댓글 개수는 10개로 설정한다.
		int commentPageSize = 10;
		// 댓글 페이지 번호 가져오기(페이지 번호가 없으면 "1" 페이지로 설정)		
		String commentPageNum = request.getParameter("commentPageNum");		
		if (commentPageNum==null) {	// 페이지 번호 값이 없으면 기본 페이지 번호를 1로 지정한다.
			commentPageNum = "1";				
		}
		// 10개로 나누어서 2번째 페이지를 시작하는 행 번호 구하기
		int commentCurrentPage = Integer.parseInt(commentPageNum);
		// 매 페이지마다 첫번째로 출력되는 댓글의 글번호 구하기
		int commentStartRow = ((commentCurrentPage-1) * commentPageSize)+1;
		// 10개로 나누어서 2번째 페이지를 끝내는 행 번호 구하기
		// 매 페이지마다 제일 끝에 출력되는 댓글의 글번호 구하기
		int commentEndRow = commentCurrentPage * commentPageSize;		
		// (최대 페이지 넘버에 대한 알고리즘)
		int commentMaxPage=(commentCount/commentPageSize)+1;
		// 댓글의 목록을 조회한다. 목록에 필요한 값은 각 페이지의 제일 낮은 게시물의 번호, 제일 높은 게시물의 번호, 그리고 현재 게시물의 번호이다. 
		List<CommentBean> getCommentList = cdao.getCommentList(commentStartRow, commentPageSize, comm_number);
		request.setAttribute("getCommentList", getCommentList);
		// 전체 페이지수 구하기 = (글 개수 / 페이지당 글 개수) + (글 개수 % 페이지당 글 개수==0 ? 나머지가 없으면 0: 나머지가 있으면 1);
		int commentPageCount = (commentCount/commentPageSize) + (commentCount%commentPageSize==0 ? 0:1);
		// 한 화면에 보여줄 페이지수 설정		 기본값: 10페이지
		int commentPageBlock = 10;
		// 한 화면에 보여줄 첫 페이지 번호 구하기 =
		// (((현재 페이지-1)/한 화면당 페이지 수) * 한 화면당 페이지 수) + 1
		int commentStartPage = (((commentCurrentPage-1)/commentPageBlock) * commentPageBlock)+1;
		// 한 화면에 보여줄 끝 페이지 번호 구하기 = 시작 페이지 + (한 화면당 페이지 수-1)
		int commentEndPage = commentStartPage+(commentPageBlock-1);
		if (commentPageCount < commentEndPage) {// 전체 페이지 번호가 끝 페이지 번호보다 작을 때
			//끝 페이지 번호의 값에 전체 페이지 번호를 대입한다.
			commentEndPage = commentPageCount;
		}
		// Community_List.jsp가 필요로 하는 request 값 저장
		request.setAttribute("commentCount", commentCount);
		request.setAttribute("commentPageNum", commentPageNum);
		request.setAttribute("getCommentList", getCommentList);
		request.setAttribute("commentPageCount", commentPageCount);
		request.setAttribute("commentPageBlock", commentPageBlock);
		request.setAttribute("commentCurrentPage", commentCurrentPage);
		request.setAttribute("commentStartPage", commentStartPage);
		request.setAttribute("commentEndPage", commentEndPage);		
		request.setAttribute("commentStartRow", commentStartRow);
		request.setAttribute("commentEndRow", commentEndRow);
		request.setAttribute("commentMaxPage", commentMaxPage);
		/* 현재 게시물의 댓글리스트를 가져오는 부분 */
		
		// 이동 ./Community_Detail.jsp		
		// ActionForward forward 객체 생성		
		ActionForward forward = new ActionForward();		
		forward.setPath("/Community/Community_Detail.jsp");
		forward.setRedirect(false);
		return forward;		 	
	}	
}
