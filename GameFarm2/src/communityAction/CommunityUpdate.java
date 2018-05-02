package communityAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import communityDB.*;


public class CommunityUpdate implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommunityUpdate execute()");		
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 리퀘스트 값 가져오기
		int comm_number = Integer.parseInt(request.getParameter("comm_number"));
		String pageNum = request.getParameter("pageNum");
		// MemberDB 객체 생성
		CommunityDAO cdao = new CommunityDAO();
		// 해당 게시물의 정보를 커뮤니티 자바빈에 담는다.
		CommunityBean cb = cdao.getDetail(comm_number);		
		// request 자바빈 cb 저장
		request.setAttribute("cb", cb);
		// 이동 ./Community_Detail.jsp		
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();		
		forward.setPath("/Community/Community_Update.jsp");
		forward.setRedirect(false);		
		return forward;		 	
	}	
}
