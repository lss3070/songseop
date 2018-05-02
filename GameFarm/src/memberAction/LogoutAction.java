package memberAction;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 세션 초기화
     	request.getSession().invalidate();
     	// 자바 -> text/html 변경
     	response.setContentType("text/html; charset=UTF-8");
     	// 스크립트를 출력할 객체 생성
     	PrintWriter out = response.getWriter();
     	out.println("<script>");
     	out.println("alert('로그아웃하였습니다.');");		
     	out.println("location.href='./Main.ma'");
     	out.println("</script>");
     	out.close();		
     	return null;
	}		
}

