package memberAction;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberDB.*;

public class MemberJoinAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberJoinAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// MemberBean 자바빈에 값을 입력할 변수들을 가져옴
		String mem_id = request.getParameter("mem_id");
		String mem_pass = request.getParameter("mem_pass");
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
		// 자바빈 MemberBean 객체 호출
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
		mb.setMem_reg_date(new Timestamp(System.currentTimeMillis()));		
		// MemberDB 객체 생성
		MemberDAO mdao = new MemberDAO();
		// mdao 클래스의 insertMember(자바빈 주소값) 실행 -> 입력받은 회원의 값들을 DB에 insert한다.
		mdao.insertMember(mb);	
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('게임팜의 회원이 되신 것을 환영합니다!');");
		out.println("location.href='./Main.ma'");
		out.println("</script>");
		out.close();
		return null;	
	}	
}
