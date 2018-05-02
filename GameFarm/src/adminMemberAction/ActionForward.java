package adminMemberAction;

public class ActionForward {
	// 이동경로정보
	private String path;
	// 이동방식정보
	// true: sendRedirect
	// false: forward
	private boolean isRedirect;		
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
		
}
