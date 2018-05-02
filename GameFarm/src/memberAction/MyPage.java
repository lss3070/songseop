package memberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memberDB.*;

public class MyPage implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyPage execute()");
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
		// request.setAttribute("변수이름", mb)		
		request.setAttribute("mb", mb);
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();		
		forward.setPath("./Member/Member_MyPage.jsp");
		forward.setRedirect(false);
		return forward;		 	
	}	
}
