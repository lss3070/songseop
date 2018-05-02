package communityAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import communityDB.CommunityBean;
import communityDB.CommunityDAO;

public class imageDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("imageDeleteAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 게시물의 번호와 페이지 번호가 담긴 리퀘스트 값을 변수에 담는다.
		int comm_number = Integer.parseInt(request.getParameter("comm_number"));
		String pageNumber = request.getParameter("pageNumber");		
		// CommunityDB 관련 객체들을 선언한다.
		CommunityDAO cdao = new CommunityDAO();
		// 이미지 삭제 처리				
		cdao.imageDelete(comm_number);
		// 해당 게시물의 정보를 다시 커뮤니티 자바빈에 담는다.
		CommunityBean cb = cdao.getDetail(comm_number);		
		// request 자바빈 cb 저장
		request.setAttribute("cb", cb);		
		// ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();		
		// 이동 정보 저장 ./Community_List.jsp
		forward.setPath("/Community/Community_Update.jsp");
		forward.setRedirect(false);		
		return forward;
	}	
}
