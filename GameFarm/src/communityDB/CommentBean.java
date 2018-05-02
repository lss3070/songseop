package communityDB;

import java.sql.Timestamp;

public class CommentBean {
	private int comment_number;
	private int comm_number;
	private String mem_id;
	private String comment_content;
	private Timestamp comment_reg_date;
	public int getComment_number() {
		return comment_number;
	}
	public void setComment_number(int comment_number) {
		this.comment_number = comment_number;
	}
	public int getComm_number() {
		return comm_number;
	}
	public void setComm_number(int comm_number) {
		this.comm_number = comm_number;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Timestamp getComment_reg_date() {
		return comment_reg_date;
	}
	public void setComment_reg_date(Timestamp comment_reg_date) {
		this.comment_reg_date = comment_reg_date;
	}
}
