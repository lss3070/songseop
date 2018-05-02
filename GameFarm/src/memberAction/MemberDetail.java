package memberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberDB.MemberBean;
import memberDB.MemberDAO;

public class MemberDetail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberDetail execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		//mem_id 파라미터 가져오기
		String mem_id = request.getParameter("mem_id");			
		// MemberDB 객체 생성
		MemberDAO mdao = new MemberDAO();
		// MemberBean mb = getMember(파라미터값)
		MemberBean mb = mdao.getMember(mem_id);
		// 리퀘스트 값에 가져온 회원정보를 담는다.
		request.setAttribute("mb", mb );		
		// ActionForward forward 객체 생성
		ActionForward forward=new ActionForward();
		// 이동 정보 저장 ./Member/Member_Detail.jsp
		forward.setPath("./Member/Member_Detail.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
