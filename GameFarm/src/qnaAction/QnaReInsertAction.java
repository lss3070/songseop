package qnaAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnaDb.QnaBean;
import qnaDb.QnaDAO;

public class QnaReInsertAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaReInsertAction excute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		//qnabean 객체생성
		QnaBean qb = new QnaBean();
		//qnaDAO 객체생성
		QnaDAO qdao = new QnaDAO();		
		//자바빈 멤버변수 파라미터로 가져와서저장
		qb.setMem_id(request.getParameter("mem_id"));
		qb.setQna_subject(request.getParameter("qna_subject"));		
		qb.setQna_content(request.getParameter("qna_content"));		
		qb.setQna_re_ref(Integer.parseInt(request.getParameter("qna_re_ref")));
		qb.setQna_re_seq(Integer.parseInt(request.getParameter("qna_re_seq")));
		qb.setQna_re_lev(Integer.parseInt(request.getParameter("qna_re_lev")));		
		//답글메서드실행
		qdao.ReInsertQna(qb);
		//게시판목록이동
		//이동 ./QnaList.qn
		ActionForward forward = new ActionForward();
		forward.setPath("/QnaList.qn");
		forward.setRedirect(false);
		return forward;
	}

}
