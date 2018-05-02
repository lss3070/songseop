package communityAction;

import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import communityDB.*;

public class CommentUpdateAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommentUpdateAction execute()");		
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 게시물 번호와 페이지 넘버에 대한 리퀘스트 값을 변수에 담는다
		int comment_number = Integer.parseInt(request.getParameter("comment_number"));		
		// CommentBean 객체 생성
		CommentBean cob = new CommentBean();		
		// 댓글 수정 폼에서 입력받은 값들을 댓글 자바빈에 넣는다.
		cob.setComment_number(comment_number);
		cob.setComment_content(request.getParameter("comment_content"));
		cob.setComment_reg_date(new Timestamp(System.currentTimeMillis()));
		// 댓글 수정 메소드가 담긴 CommunityDAO 객체를 생성하고 댓글 수정 메소드 실행
		CommunityDAO cdao = new CommunityDAO();
		cdao.updateComment(cob);
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('댓글을 수정하였습니다.');");
		// 현재 활성화 되어있는 댓글 수정창을 종료한다.
		out.println("window.close();");
		// 부모 페이지를 새로고침한다.
		out.println("window.opener.document.location.reload();");
		out.println("</script>");
		out.close();		
		return null;	
	}
}