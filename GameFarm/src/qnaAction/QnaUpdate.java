package qnaAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnaDb.QnaBean;
import qnaDb.QnaDAO;

public class QnaUpdate implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaUpdate execute()");		
		//한글 출력
		request.setCharacterEncoding("utf-8");
		//리퀘스트값 받아오기
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		String pageNum = request.getParameter("pageNum");
		//QnaDAO qdao 객체생성
		QnaDAO qdao = new QnaDAO();
		//자바빈qb에 getContent(qna_number)를 담아 객체생성
		QnaBean qb = qdao.getContent(qna_number);
		//request 자바빈qb저장
		request.setAttribute("qb", qb);		
		//QnaUpdate.jsp이동 ActionForward forward 객체 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./Qna/QnaUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
