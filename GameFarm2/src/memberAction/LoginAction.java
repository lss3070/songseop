package memberAction;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import memberDB.*;

public class LoginAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
        System.out.println("LoginAction execute");
		// 폼에서 입력받은 아이디와 비밀번호를 변수에 저장한다.
		String mem_id = request.getParameter("mem_id");
		String mem_pass = request.getParameter("mem_pass");
		// MemberDB 객체 생성
		MemberDAO mdao = new MemberDAO();
		// int check = userCheck(id, pass) 	메소드 호출()
		int check = mdao.userCheck(mem_id, mem_pass);
		//check == 1 이면 세션값을 생성("sId", id) 	./Main.me 이동
		//check == 0 이면 "비밀번호 틀림" 뒤로 이동
		//check == -1 이면 "아이디 없음" 뒤로 이동		
		if (check == 1) {	// 아이디와 비밀번호 모두 일치할 때
			MemberBean mb = mdao.getMember(mem_id);
			// 세션 객체 생성
			HttpSession session=request.getSession();
			// 변수값을 세션값에 저장한다.	자바에서 세션값 지정은 request.getSession().setAttribute("세션명", 세션에 넣을 값);
			session.setAttribute("sMem_id", mb.getMem_id());
			session.setAttribute("sMem_name", mb.getMem_name());			
			String sname=(String)session.getAttribute("sMem_name"); 
			// 로그인 성공 메세지
			// 자바 스크립트 사용
			// 자바 -> text/html 변경
			response.setContentType("text/html; charset=UTF-8");
			// 스크립트를 출력할 객체 생성
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+sname+"님! 환영합니다!');");
			out.println("location.href='./Main.ma'");
			out.println("</script>");
			out.close();			
			return null;
		}
		else if (check == 0) {	// 비밀번호가 일치하지 않을 때
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
		else {	// 아이디가 일치하지 않을 때
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

