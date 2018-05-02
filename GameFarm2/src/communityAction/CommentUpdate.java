package communityAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import communityDB.*;


public class CommentUpdate implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommentUpdate execute()");		
		// 한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 리퀘스트 값 가져오기		
		int comment_number = Integer.parseInt(request.getParameter("comment_number")); 
		// 커뮤니티 객체와 댓글 자바빈 객체 생성
		CommunityDAO cdao = new CommunityDAO();
		CommentBean cob = cdao.getCommentDetail(comment_number);		
		// 댓글 자바빈 값을 리퀘스트로 저장
		request.setAttribute("cob", cob);
		// 이동 처리
		ActionForward forward = new ActionForward();		
		forward.setPath("/Community/Comment_Update.jsp");
		forward.setRedirect(false);		
		return forward;		 	
	}	
}
