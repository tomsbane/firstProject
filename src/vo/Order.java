package vo;

public class Order {

	private int order_no;
	private String c_id;
	private int car_no;
	private String rental_date;
	private String return_date;
	private int rental_price;
	private String order_status;
	
	public Order() {}
	
	public Order(int order_no, String c_id, int car_no, String rental_date, String return_date, int rental_price,
			String order_status) {
		super();
		this.order_no = order_no;
		this.c_id = c_id;
		this.car_no = car_no;
		this.rental_date = rental_date;
		this.return_date = return_date;
		this.rental_price = rental_price;
		this.order_status = order_status;
	}

	public Order(int order_no, String c_id, int car_no, String rental_date, String return_date, int rental_price) {
		super();
		this.order_no = order_no;
		this.c_id = c_id;
		this.car_no = car_no;
		this.rental_date = rental_date;
		this.return_date = return_date;
		this.rental_price = rental_price;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public int getCar_no() {
		return car_no;
	}

	public void setCar_no(int car_no) {
		this.car_no = car_no;
	}

	public String getRental_date() {
		return rental_date;
	}

	public void setRental_date(String rental_date) {
		this.rental_date = rental_date;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public int getRental_price() {
		return rental_price;
	}

	public void setRental_price(int rental_price) {
		this.rental_price = rental_price;
	}

	

}
