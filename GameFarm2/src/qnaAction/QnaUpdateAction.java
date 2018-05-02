package qnaAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnaDb.QnaBean;
import qnaDb.QnaDAO;

public class QnaUpdateAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaUpdateAction execute()");		
		//한글 출력
		request.setCharacterEncoding("utf-8");
		//리퀘스트값 받아오기
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		String pageNum = request.getParameter("pageNum");
		//QnaBean qb 객체생성
		QnaBean qb = new QnaBean();
		// 업데이트 폼에서 입력받은 값들을 에 넣는다.
		qb.setQna_number(qna_number);
		qb.setQna_subject(request.getParameter("qna_subject"));
		qb.setQna_content(request.getParameter("qna_content"));		
		//QnaDAO qdao 객체생성
		QnaDAO qdao = new QnaDAO();
		//qdao.updateqna(qb)실행
		qdao.updateQna(qb);		
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('글 내용을 수정하였습니다.');");
		out.println("location.href='./QnaList.qn'");
		out.println("</script>");
		out.close();
		return null;
	}
}
