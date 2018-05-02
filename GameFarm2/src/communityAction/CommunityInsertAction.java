package communityAction;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import communityDB.*;

public class CommunityInsertAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommunityInsertAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		String realPath = request.getRealPath("/upload");		
		// 파일 크기 제한: 10메가
		int maxSize=10*1024*1024;
		// MultipartRequest multi = new MultipartRequest(request, 파일을 업로드할 물리적 경로 폴더, 파일 크기, 한글 처리, 업로드하는 파일 이름이 동일할 경우 이름 변경 파일 생성);
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		// 자바빈 CommunityBean 객체 호출
		CommunityBean cb = new CommunityBean();
		// CommunityBean 자바빈에 값을 입력할 변수들을 가져옴		
		cb.setComm_subject(multi.getParameter("comm_subject"));				
		cb.setMem_id(multi.getParameter("mem_id"));
		cb.setComm_content(multi.getParameter("comm_content"));
		cb.setComm_image(multi.getFilesystemName("comm_image"));		
		cb.setComm_reg_date(new Timestamp(System.currentTimeMillis()));
		// 파일의 실제 경로는 차후에 바꾸고 임시적으로 comm_file과 똑같은 값을 넣는다.		
		CommunityDAO cdao = new CommunityDAO();
		// cdao 클래스의 insertCommunity(자바빈 변수값) 실행 -> 입력받은 회원의 값들을 DB에 insert한다.
		cdao.insertCommunity(cb);	
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('게시물을 작성하였습니다!');");
		out.println("location.href='./CommunityList.co'");
		out.println("</script>");
		out.close();		
		return null;	
	}	
}
