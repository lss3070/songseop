package qnaAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnaDb.QnaBean;
import qnaDb.QnaDAO;

public class QnaDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaDeleteAction execute()");
		//한글 출력
		request.setCharacterEncoding("utf-8");
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		//db객체생성
		QnaBean qb = new QnaBean();
		QnaDAO qdao = new QnaDAO();
		//qna_number가져오기
		qb.setQna_number(qna_number);
		//삭제메소드 불러오기
		qdao.deleteQna(qb.getQna_number());
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('게시물을 삭제하였습니다.');");
		out.println("location.href='./QnaList.qn'");
		out.println("</script>");
		out.close();	
		return null;
	}

}
