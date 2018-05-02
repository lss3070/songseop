package memberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import memberDB.*;

public class MemberIdCheck implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		System.out.println("MemberIdCheck execute");
		// 폼에서 입력받은 아이디와 비밀번호를 변수에 저장한다.
		String dupId = request.getParameter("userId");		
		// MemberDAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		// int check = userCheck(id, pass) 	메소드 호출()
		int check = mdao.idCheck(dupId);		
		request.setAttribute("check", check);
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./Member/Member_IdCheck.jsp?check=check&dupId=userId");
		forward.setRedirect(false);
		return forward;			
	}

}
