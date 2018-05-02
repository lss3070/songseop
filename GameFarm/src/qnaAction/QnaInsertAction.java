package qnaAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import qnaDb.QnaBean;
import qnaDb.QnaDAO;

public class QnaInsertAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaInsertAction execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");		
		//자바빈 객체생성
		QnaBean qb = new QnaBean();
		//폼 -> 자바빈저장
		qb.setMem_id(request.getParameter("mem_id"));
		qb.setQna_subject(request.getParameter("Qna_subject"));
		qb.setQna_content(request.getParameter("Qna_content"));		
		//QnaDAO 객체생성
		QnaDAO qdao = new QnaDAO();		
		//qdao.InsertQna(qb);실행
		qdao.InsertQna(qb);		
		//./QnaList.qn로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./QnaList.qn");
		forward.setRedirect(true);
		return forward;
	}
}
