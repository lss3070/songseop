package adminMemberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberDB.MemberBean;
import memberDB.MemberDAO;

public class AdminMemberDetail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminMemberDetail execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		//mem_id 파라미터 가져오기
		String mem_id = request.getParameter("mem_id");	
		// MemberDB 객체 생성
		MemberDAO mdao = new MemberDAO();
		// MemberBean mb = getMember(파라미터값)
		MemberBean mb = mdao.getMember(mem_id);
		request.setAttribute("mb", mb );
		// ActionForward forward 객체 생성
		ActionForward forward=new ActionForward();
		// 이동 
		forward.setPath("./AdminMember/Admin_Member_Detail.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
