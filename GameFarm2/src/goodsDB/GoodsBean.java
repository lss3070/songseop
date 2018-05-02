package goodsDB;

import java.io.File;
import java.sql.Date;

public class GoodsBean {
	private int goods_id;
	private String goods_type;
	private String goods_name;
	private String goods_platform;
	private String goods_developer;
	private String goods_publisher;
	private int goods_price;
	private int goods_voice;
	private int goods_lang;
	private String goods_image;
	private String goods_image2;
	private String goods_image3;
	private String goods_image4;
	private String goods_image5;
	private Date goods_reg_date;
	private int goods_stock;
	private String goods_content;
	private int goods_cost;
	private String goods_video;
	
	

	public GoodsBean(){}
	public GoodsBean(int goods_id, String goods_type,String goods_name, String goods_platform,String goods_developer,String goods_video,

			String goods_publisher,int goods_price,int goods_voice,int goods_lang,String goods_image,
			Date goods_reg_date,int goods_stock,String goods_content,int goods_cost,String goods_image2,String goods_image3,String goods_image4,String goods_image5) {

			super();
	
			this.goods_id=goods_id;
			
			this.goods_type=goods_type;
			
			this.goods_name=goods_name;
			
			this.goods_platform=goods_platform;
			
			this.goods_developer=goods_developer;
			
			this.goods_publisher=goods_publisher;
			
			this.goods_price=goods_price;
			
			this.goods_voice=goods_voice;
			
			this.goods_lang=goods_lang;
			
			this.goods_image=goods_image;
			
			this.goods_reg_date=goods_reg_date;
			
			this.goods_stock=goods_stock;
			
			this.goods_content=goods_content;
			
			this.goods_cost=goods_cost;
			
			this.goods_image2=goods_image2;
			
			this.goods_image3=goods_image3;
			
			this.goods_image4=goods_image4;
			
			this.goods_image5=goods_image5;		
			
			this.goods_video = goods_video;
	}
	
	
	

	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_platform() {
		return goods_platform;
	}
	public void setGoods_platform(String goods_platform) {
		this.goods_platform = goods_platform;
	}
	public String getGoods_developer() {
		return goods_developer;
	}
	public void setGoods_developer(String goods_developer) {
		this.goods_developer = goods_developer;
	}
	public String getGoods_publisher() {
		return goods_publisher;
	}
	public void setGoods_publisher(String goods_publisher) {
		this.goods_publisher = goods_publisher;
	}
	public int getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(int goods_price) {
		this.goods_price = goods_price;
	}
	public int getGoods_voice() {
		return goods_voice;
	}
	public void setGoods_voice(int goods_voice) {
		this.goods_voice = goods_voice;
	}
	public int getGoods_lang() {
		return goods_lang;
	}
	public void setGoods_lang(int goods_lang) {
		this.goods_lang = goods_lang;
	}
	public String getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	public Date getGoods_reg_date() {
		return goods_reg_date;
	}
	public void setGoods_reg_date(Date goods_reg_date) {
		this.goods_reg_date = goods_reg_date;
	}
	public int getGoods_stock() {
		return goods_stock;
	}
	public void setGoods_stock(int goods_stock) {
		this.goods_stock = goods_stock;
	}
	public String getGoods_content() {
		return goods_content;
	}
	public void setGoods_content(String goods_content) {
		this.goods_content = goods_content;
	}
	public int getGoods_cost() {
		return goods_cost;
	}
	public void setGoods_cost(int goods_cost) {
		this.goods_cost = goods_cost;
	}
	public String getGoods_image2() {
		return goods_image2;
	}
	public void setGoods_image2(String goods_image2) {
		this.goods_image2 = goods_image2;
	}
	public String getGoods_image3() {
		return goods_image3;
	}
	public void setGoods_image3(String goods_image3) {
		this.goods_image3 = goods_image3;
	}
	public String getGoods_image4() {
		return goods_image4;
	}
	public void setGoods_image4(String goods_image4) {
		this.goods_image4 = goods_image4;
	}
	public String getGoods_image5() {
		return goods_image5;
	}
	public void setGoods_image5(String goods_image5) {
		this.goods_image5 = goods_image5;
	}
	public String getGoods_video() {
		return goods_video;
	}
	public void setGoods_video(String goods_video) {
		this.goods_video = goods_video;
	}
	

}
