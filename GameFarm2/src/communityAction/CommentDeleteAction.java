package communityAction;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import communityDB.*;

public class CommentDeleteAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
		// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommentDeleteAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 리퀘스트 값을 변수에 담는다.
		int comment_number = Integer.parseInt(request.getParameter("comment_number"));
		String commentPageNum = request.getParameter("commentPageNum");
		int comm_number = Integer.parseInt(request.getParameter("comm_number"));
		String pageNum = request.getParameter("pageNum");		
		// CommunityDB 관련 객체들을 선언한다.
		CommunityDAO cdao = new CommunityDAO();
		// 댓글의 자바빈 값들에 대한 객체 선언
		CommentBean cob = new CommentBean();
		// 게시물을 삭제하기 위한 조건값인 comm_number 값을 자바빈에 담는다.
		cob.setComment_number(comment_number);		
		// 게시물 삭제 처리				
		cdao.deleteComment(cob.getComment_number());				
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");		
		out.println("location.href='./CommunityDetail.co?comm_number="+comm_number+"&pageNum="+pageNum+"&commentPageNum="+commentPageNum+"'");
		out.println("</script>");
		out.close();
		return null;
	}
}
