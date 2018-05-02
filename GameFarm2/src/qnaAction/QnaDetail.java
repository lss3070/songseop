package qnaAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import qnaDb.QnaBean;
import qnaDb.QnaDAO;

public class QnaDetail implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaDetail execute()");
		//한글출력
		request.setCharacterEncoding("utf-8");
		// 리퀘스트 값 가져오기
		int qna_number = Integer.parseInt(request.getParameter("qna_number"));
		String pageNum = request.getParameter("pageNum");
		//QnaDAO 객체 생성
		QnaDAO qdao = new QnaDAO();
		// 조회수를 1회 추가한다.
		qdao.setReadCount(qna_number);
		// 게시물들을 조회하기 위한 메소드 호출
		QnaBean qb = qdao.getContent(qna_number);
		//request 자바빈 qb 저장
		request.setAttribute("qb", qb);
		//./Qna/QnaDetail.jsp로 이동
		ActionForward forward = new ActionForward();		
		forward.setPath("./Qna/QnaDetail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
