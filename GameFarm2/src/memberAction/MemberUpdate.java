package memberAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import memberDB.*;

public class MemberUpdate implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberUpdate execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 세션값 가져오기
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("sMem_id");	// id가 admin인지 확인하기 위한 id 변수 지정
		// MemberDB 객체 생성
		MemberDAO mdao = new MemberDAO();
		// MemberBean mb = getMember(세션값) 
		MemberBean mb = mdao.getMember(mem_id);		
		// request <- 자바빈 mb 저장
		// request.setAttribute("변수이름", 값)
		request.setAttribute("mb", mb);
		// ./Member/List.jsp 이동, 이동방식 forward()
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();		
		// 이동 정보 저장 /Member/updateForm.jsp
		forward.setPath("/Member/Member_Update.jsp");
		forward.setRedirect(false);
		return forward;		 	
	}	
}
