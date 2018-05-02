package communityAction;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import communityDB.*;

public class CommunityDeleteAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
		// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommunityDeleteAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 게시물의 번호가 담긴 리퀘스트 값을 변수에 담는다.
		int comm_number = Integer.parseInt(request.getParameter("comm_number"));
		// CommunityDB 관련 객체들을 선언한다.
		CommunityDAO cdao = new CommunityDAO();
		CommunityBean cb = new CommunityBean();
		// 게시물을 삭제하기 위한 필요값인 comm_number 값을 가져온다.
		cb.setComm_number(comm_number);								
		// 게시물 삭제 처리				
		cdao.deleteCommunity(cb.getComm_number());				
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('게시물을 삭제하였습니다.');");
		out.println("location.href='./CommunityList.co'");
		out.println("</script>");
		out.close();						
		return null;
	}
}