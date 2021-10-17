package vo;

public class Review {
	private int review_num;
	private String c_id;
	private String c_name;
	private int car_no;
	private String car_name;
	private int rating;
	private String text;

	
	public Review() {}
	
	public Review(int review_num, String c_id, String c_name, int car_no, String car_name, int rating, String text) {
		super();
		this.review_num = review_num;
		this.c_id = c_id;
		this.c_name = c_name;
		this.car_no = car_no;
		this.car_name = car_name;
		this.rating = rating;
		this.text = text;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public int getCar_no() {
		return car_no;
	}

	public void setCar_no(int car_no) {
		this.car_no = car_no;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
