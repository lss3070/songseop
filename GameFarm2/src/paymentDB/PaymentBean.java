package paymentDB;



import java.sql.Timestamp;

public class PaymentBean {
	
	private int payment_id;
	private String mem_id;
	private int goods_id;
	
	private String goods_name;
	private String goods_image;
	
	
	private int order_status;
	private Timestamp order_date;
	private String payment_type;
	private int order_volume;
	private int payment_price;
	private int receiver_post;
	private String receiver_address1;
	private String receiver_address2;
	private String receiver_name;
	private String receiver_phone1;
	private String receiver_phone2;
	private String receiver_phone3;
	private String order_waybill;
	private int platform;
	private int type;
	
	
	public String getOrder_waybill() {
		return order_waybill;
	}
	public void setOrder_waybill(String order_waybill) {
		this.order_waybill = order_waybill;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp date) {
		this.order_date = date;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public int getOrder_volume() {
		return order_volume;
	}
	public void setOrder_volume(int order_volume) {
		this.order_volume = order_volume;
	}
	public int getPayment_price() {
		return payment_price;
	}
	public void setPayment_price(int payment_price) {
		this.payment_price = payment_price;
	}
	public int getReceiver_post() {
		return receiver_post;
	}
	public void setReceiver_post(int receiver_post) {
		this.receiver_post = receiver_post;
	}
	public String getReceiver_address1() {
		return receiver_address1;
	}
	public void setReceiver_address1(String receiver_address1) {
		this.receiver_address1 = receiver_address1;
	}
	public String getReceiver_address2() {
		return receiver_address2;
	}
	public void setReceiver_address2(String receiver_address2) {
		this.receiver_address2 = receiver_address2;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_phone1() {
		return receiver_phone1;
	}
	public void setReceiver_phone1(String receiver_phone1) {
		this.receiver_phone1 = receiver_phone1;
	}
	public String getReceiver_phone2() {
		return receiver_phone2;
	}
	public void setReceiver_phone2(String receiver_phone2) {
		this.receiver_phone2 = receiver_phone2;
	}
	public String getReceiver_phone3() {
		return receiver_phone3;
	}
	public void setReceiver_phone3(String receiver_phone3) {
		this.receiver_phone3 = receiver_phone3;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	
	
	
	
	


}
