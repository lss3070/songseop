package newsDB;

import java.sql.Timestamp;

public class NewsBean {
	private int news_number;
	private String mem_id;
	private String news_subject;
	private String news_content;	
	private String news_image;
	private int news_readcount;
	private Timestamp news_reg_date;
	
	public String getNews_image() {
		return news_image;
	}
	public void setNews_image(String news_image) {
		this.news_image = news_image;
	}
	public int getNews_number() {
		return news_number;
	}
	public void setNews_number(int news_number) {
		this.news_number = news_number;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getNews_subject() {
		return news_subject;
	}
	public void setNews_subject(String news_subject) {
		this.news_subject = news_subject;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}	
	public int getNews_readcount() {
		return news_readcount;
	}
	public void setNews_readcount(int news_readcount) {
		this.news_readcount = news_readcount;
	}
	public Timestamp getNews_reg_date() {
		return news_reg_date;
	}
	public void setNews_reg_date(Timestamp news_reg_date) {
		this.news_reg_date = news_reg_date;
	}
	
	
	
	
	

}
