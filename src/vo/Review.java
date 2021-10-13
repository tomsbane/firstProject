package vo;

public class Review {
	private int review_no;
	private int customer_no;
	private int car_no;
	private String review_name;
	private String review_contents;
	private int review_rating;
	
	public Review() {}
	
	public Review(int review_no, int customer_no, int car_no, String review_contents, int review_rating) {
		super();
		this.review_no = review_no;
		this.customer_no = customer_no;
		this.car_no = car_no;
		this.review_contents = review_contents;
		this.review_rating = review_rating;
	}

	public Review(int review_no, int customer_no, int car_no, String review_name, String review_contents,
			int review_rating) {
		super();
		this.review_no = review_no;
		this.customer_no = customer_no;
		this.car_no = car_no;
		this.review_name = review_name;
		this.review_contents = review_contents;
		this.review_rating = review_rating;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public int getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(int customer_no) {
		this.customer_no = customer_no;
	}

	public int getCar_no() {
		return car_no;
	}

	public void setCar_no(int car_no) {
		this.car_no = car_no;
	}

	public String getReview_name() {
		return review_name;
	}

	public void setReview_name(String review_name) {
		this.review_name = review_name;
	}

	public String getReview_contents() {
		return review_contents;
	}

	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}

	public int getReview_rating() {
		return review_rating;
	}

	public void setReview_rating(int review_rating) {
		this.review_rating = review_rating;
	}
	
	
}
