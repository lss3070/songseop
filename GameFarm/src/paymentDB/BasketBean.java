package paymentDB;

public class BasketBean {
	private String goods_name;
	private int goods_price;
	private String goods_image;
	private int goods_count;
	private int goods_id;
	private int goods_platform;
	private int goods_type;

	public BasketBean(String goods_name, int goods_price,String goods_image,int goods_count,int goods_id,int goods_platform,int goods_type) {
		// TODO Auto-generated constructor stub
		this.goods_name=goods_name;
		this.goods_price=goods_price;
		this.goods_image=goods_image;
		this.goods_count=goods_count;
		this.goods_id=goods_id;
		this.goods_platform=goods_platform;
		this.goods_type=goods_type;
		
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public int getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(int goods_price) {
		this.goods_price = goods_price;
	}

	public String getGoods_image() {
		return goods_image;
	}

	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}

	public int getGoods_count() {
		return goods_count;
	}

	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getGoods_platform() {
		return goods_platform;
	}

	public void setGoods_platform(int goods_platform) {
		this.goods_platform = goods_platform;
	}

	public int getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(int goods_type) {
		this.goods_type = goods_type;
	}


	




}
