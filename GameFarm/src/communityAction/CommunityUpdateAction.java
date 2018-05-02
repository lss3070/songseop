package communityAction;

import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import communityDB.*;

public class CommunityUpdateAction implements Action {
	// implements 인터페이스명    인터페이스를 상속받는다.
	// alt shift s -> v 		인터페이스 메소드 오버라이드: 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommunityUpdateAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 파일이 있는 게시판이므로 multi 객체를 가져올 준비를 한다.
		String realPath = request.getRealPath("/upload");
		System.out.println("업로드 폴더: "+realPath);
		// 파일 크기 제한: 10메가
		int maxSize=10*1024*1024;
		// MultipartRequest multi = new MultipartRequest(request, 파일을 업로드할 물리적 경로 폴더, 파일 크기, 한글 처리, 업로드하는 파일 이름이 동일할 경우 이름 변경 파일 생성);
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		// 게시물 번호와 페이지 넘버에 대한 리퀘스트 값을 변수에 담는다
		int comm_number = Integer.parseInt(multi.getParameter("comm_number"));
		String pageNum = multi.getParameter("pageNum");
		// CommunityBean 객체 생성
		CommunityBean cb = new CommunityBean();		
		// 업데이트 폼에서 입력받은 값들을 에 넣는다.
		cb.setComm_number(comm_number);
		cb.setComm_subject(multi.getParameter("comm_subject"));
		cb.setComm_content(multi.getParameter("comm_content"));
		cb.setComm_image(multi.getFilesystemName("comm_image"));				
		// CommunityDAO 객체 생성
		CommunityDAO cdao = new CommunityDAO();
		cdao.updateCommunity(cb);
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('글 내용을 수정하였습니다.');");
		out.println("location.href='./CommunityList.co'");
		out.println("</script>");
		out.close();		
		return null;	
	}
}