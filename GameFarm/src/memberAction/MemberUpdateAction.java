package memberAction;

import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import memberDB.*;

public class MemberUpdateAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
		// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			System.out.println("MemberUpdateAction execute()");
			//한글 출력이 가능하도록!
			request.setCharacterEncoding("utf-8");
			// member.MemberDB에서 updateMember() 메소드를 불러오기 위해 member.MemberDB 클래스 객체를 가져온다.
			MemberDAO mdao = new MemberDAO();			
			// MemberBean 자바빈에 값을 입력할 변수들을 가져옴
			String mem_id = request.getParameter("mem_id");
			String mem_pass = request.getParameter("mem_pass");				
			// 폼에서 입력받은 아이디와 비밀번호가 DB와 일치하는지에 대한 조건문을 위해 updateCheck 변수를 만든다.
			// 아이디와 비밀번호가 모두 일치하면 updateCheck = 1로 리턴된다.
			// 비밀번호가 틀리면 updateCheck = 0로 리턴된다.
			// 아이디가 틀리면 updateCheck = -1로 리턴된다.
			int check = mdao.userCheck(mem_id, mem_pass);
			if (check == 1) {	// 아이디와 비밀번호가 일치할 때
				//불러온 아이디 값을 MemberDB 클래스의 getMember() 메소드에 대입하고, 결과물을 불러온다.
				// 받아온 id와 pass 값과 DB 상의 id와 pass값이 일치하면 member.MemberDB 자바빈의 updateMember() 메소드를 실행한다.
				// MemberBean 자바빈에 값을 입력할 변수들을 가져옴
				mem_pass = request.getParameter("newpass");
				String mem_name = request.getParameter("mem_name");
				String mem_gender = request.getParameter("mem_gender");
				String mem_age = request.getParameter("mem_age");
				String mem_email1 = request.getParameter("mem_email1");
				String mem_email2 = request.getParameter("mem_email2");
				String mem_post = request.getParameter("mem_post");		
				String mem_address1 = request.getParameter("mem_address1");
				String mem_address2 = request.getParameter("mem_address2");
				String mem_phone1 = request.getParameter("mem_phone1");
				String mem_phone2 = request.getParameter("mem_phone2");
				String mem_phone3 = request.getParameter("mem_phone3");
				// 기존 회원 정보를 불러오기 위해 MemberBean 객체를 생성한다.
				MemberBean mb = new MemberBean();	
				mb.setMem_id(mem_id);
				mb.setMem_pass(mem_pass);
				mb.setMem_name(mem_name);
				mb.setMem_gender(mem_gender);
				mb.setMem_age(mem_age);
				mb.setMem_email1(mem_email1);
				mb.setMem_email2(mem_email2);
				mb.setMem_post(mem_post);
				mb.setMem_address1(mem_address1);
				mb.setMem_address2(mem_address2);
				mb.setMem_phone1(mem_phone1);
				mb.setMem_phone2(mem_phone2);
				mb.setMem_phone3(mem_phone3);		
				// MDB 클래스의 updateMember(자바빈 주소값) 실행 -> 입력받은 회원의 값들을 DB에 update한다.				
				mdao.updateMember(mb);				
				// 자바 스크립트 사용
				// 자바 -> text/html 변경
				response.setContentType("text/html; charset=UTF-8");
				// 스크립트를 출력할 객체 생성
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보를 수정하였습니다.');");
				out.println("location.href='./MyPage.me'");
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