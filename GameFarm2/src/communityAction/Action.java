package communityAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 처리작업 파일의 틀 제시
	// 추상메소드 -> 메소드를 만들기 위한 틀
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
