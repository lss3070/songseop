package memberAction;

import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import memberDB.*;


public class MemberDeleteAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
		// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			System.out.println("MemberDeleteAction execute()");
			//한글 출력이 가능하도록!
			request.setCharacterEncoding("utf-8");
			// DAO 객체 생성
			MemberDAO mdao = new MemberDAO();			
			// MemberBean 자바빈에 값을 입력할 변수들을 가져옴
			String mem_id = request.getParameter("mem_id");
			String mem_pass = request.getParameter("mem_pass");						
			// mb로부터 대입한 아이디와 비밀번호가 일치하는지에 대한 조건문을 위해 updateCheck 변수를 만든다.
			// 아이디와 비밀번호가 모두 일치하면 1로 리턴된다.
			// 비밀번호가 틀리면 0으로 리턴된다.
			// 아이디가 틀리면 -1로 리턴된다.
			int check = mdao.userCheck(mem_id, mem_pass);
			if (check == 1) {	// 아이디와 비밀번호가 일치할 때								
				// 회원정보를 삭제한다.				
				mdao.deleteMember(mem_id);	
				// 세션 초기화
		     	request.getSession().invalidate();
				// 자바 스크립트 사용
				// 자바 -> text/html 변경
				response.setContentType("text/html; charset=UTF-8");
				// 스크립트를 출력할 객체 생성
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('탈퇴하였습니다. Bye~ ㅜㅜ');");
				out.println("location.href='./Main.ma'");
				out.println("</script>");
				out.close();						
				return null;
			}
			else if (check == 0) { // 비밀번호가 틀림
				response.setContentType("text/html; charset=UTF-8");
				// 스크립트를 출력할 객체 생성
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸어요!');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			else {	// 아이디가 틀림
				response.setContentType("text/html; charset=UTF-8");
				// 스크립트를 출력할 객체 생성
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디가 틀렸어요!');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}		
		}
}