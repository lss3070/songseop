package adminMemberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberDB.MemberDAO;

public class AdminMemberDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AdMemDeleteAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// DAO 객체 생성
		MemberDAO mdao = new MemberDAO();			
		// request.getParameter("mem_id")
		String mem_id = request.getParameter("mem_id");		
		mdao.deleteMember(mem_id);	
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('회원정보를 삭제하였습니다.');");
		out.println("location.href='./AdminMemberList.am'");
		out.println("</script>");
		out.close();		
		return null;
	}
	
}
