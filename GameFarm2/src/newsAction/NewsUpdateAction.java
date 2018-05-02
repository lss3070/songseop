package newsAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import newsDB.NewsBean;
import newsDB.NewsDAO;


public class NewsUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("NewsUpdateAction execute()");
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
		int news_number = Integer.parseInt(multi.getParameter("news_number"));
		String pageNum = multi.getParameter("pageNum");
		// NewsBean 객체 생성
		NewsBean nb = new NewsBean();
		System.out.println(multi.getParameter("news_subject"));
		System.out.println(multi.getParameter("news_content"));
		System.out.println(multi.getFilesystemName("news_image"));
		// 업데이트 폼에서 입력받은 값들을 cb에 넣는다.
		/*if (request.getParameter("news_image")!=null) {	// 수정하는 파일이 있을 때
		*/	nb.setNews_number(news_number);;
			nb.setMem_id(multi.getParameter("mem_id"));
			nb.setNews_subject(multi.getParameter("news_subject"));;
			nb.setNews_content(multi.getParameter("news_content"));
			nb.setNews_image(multi.getFilesystemName("news_image"));			
		/*} else {	// 수정하는 파일이 없을 때
			nb.setNews_number(news_number);
			nb.setNews_subject(multi.getParameter("news_subject"));
			nb.setNews_content(multi.getParameter("news_content"));
		}*/		
		// NewsDAO 객체 생성
		NewsDAO ndao = new NewsDAO();
		ndao.updateNews(nb);
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('글 내용을 수정하였습니다.');");
		out.println("location.href='./NewsList.nw'");
		out.println("</script>");
		out.close();		
		return null;
	}

}
