package communityAction;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import communityDB.*;

public class CommentInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommentInsertAction execute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 자바빈 CommunityBean 객체 호출
		CommentBean cob = new CommentBean();
		// 현재 게시물에 대한 리퀘스트값을 변수에 담는다.
		int comm_number = Integer.parseInt(request.getParameter("comm_number"));
		String pageNum = request.getParameter("pageNum");		
		// 현재 접속한 유저의 아이디가 담긴 세션값을 사용하기 위한 객체 생성
		HttpSession session=request.getSession();
		String mem_id = (String)session.getAttribute("sMem_id");		
		// CommentBean 자바빈에 값을 입력할 변수들을 가져옴		
		cob.setComm_number(comm_number);		
		cob.setComment_content(request.getParameter("comment_content"));				
		cob.setComment_reg_date(new Timestamp(System.currentTimeMillis()));
		// 댓글 입력을 위한 메소드가 있는 CommunityDAO 객체를 생성한다.
		CommunityDAO cdao = new CommunityDAO();
		// 댓글을 입력한다. 댓글 입력에 필요한 값(commentBean값, 게시물 번호, 회원 아이디)
		cdao.insertComment(cob, comm_number, mem_id);	
		// 자바 스크립트 사용		
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");		
		out.println("location.href='./CommunityDetail.co?comm_number="+comm_number+"&pageNum="+pageNum+"'");
		out.println("</script>");
		out.close();
		return null;
	}
}
