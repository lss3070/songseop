package communityDB;

import java.sql.Timestamp;

public class CommunityBean {
	private int comm_number;
	private String comm_subject;
	private String mem_id;
	private String comm_content;
	private String comm_image;	
	private int comm_readcount;	
	private Timestamp comm_reg_date;
	public int getComm_number() {
		return comm_number;
	}
	public void setComm_number(int comm_number) {
		this.comm_number = comm_number;
	}
	public String getComm_subject() {
		return comm_subject;
	}
	public void setComm_subject(String comm_subject) {
		this.comm_subject = comm_subject;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getComm_content() {
		return comm_content;
	}
	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	public String getComm_image() {
		return comm_image;
	}
	public void setComm_image(String comm_image) {
		this.comm_image = comm_image;
	}
	public int getComm_readcount() {
		return comm_readcount;
	}
	public void setComm_readcount(int comm_readcount) {
		this.comm_readcount = comm_readcount;
	}
	public Timestamp getComm_reg_date() {
		return comm_reg_date;
	}
	public void setComm_reg_date(Timestamp comm_reg_date) {
		this.comm_reg_date = comm_reg_date;
	}	
}
