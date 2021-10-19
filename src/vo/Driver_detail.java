package vo;

public class Driver_detail {

	private String c_id;
	private String c_name;
	private	String c_birth;
	private int c_tel;

	private String rental_place1;
	private String rental_place2;
	private String rental_place3;

	private String return_place1;
	private String return_place2;
	private String return_place3;

	private String request;

	public Driver_detail() {}

	public Driver_detail(String c_id, String c_name, String c_birth, int c_tel, String rental_place1, String rental_place2,
			String rental_place3, String return_place1, String return_place2, String return_place3, String request) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_birth = c_birth;
		this.c_tel = c_tel;
		this.rental_place1 = rental_place1;
		this.rental_place2 = rental_place2;
		this.rental_place3 = rental_place3;
		this.return_place1 = return_place1;
		this.return_place2 = return_place2;
		this.return_place3 = return_place3;
		this.request = request;
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

	public String getC_birth() {
		return c_birth;
	}

	public void setC_birth(String c_birth) {
		this.c_birth = c_birth;
	}

	public int getC_tel() {
		return c_tel;
	}

	public void setC_tel(int c_tel) {
		this.c_tel = c_tel;
	}

	public String getRental_place1() {
		return rental_place1;
	}

	public void setRental_place1(String rental_place1) {
		this.rental_place1 = rental_place1;
	}

	public String getRental_place2() {
		return rental_place2;
	}

	public void setRental_place2(String rental_place2) {
		this.rental_place2 = rental_place2;
	}

	public String getRental_place3() {
		return rental_place3;
	}

	public void setRental_place3(String rental_place3) {
		this.rental_place3 = rental_place3;
	}

	public String getReturn_place1() {
		return return_place1;
	}

	public void setReturn_place1(String return_place1) {
		this.return_place1 = return_place1;
	}

	public String getReturn_place2() {
		return return_place2;
	}

	public void setReturn_place2(String return_place2) {
		this.return_place2 = return_place2;
	}

	public String getReturn_place3() {
		return return_place3;
	}

	public void setReturn_place3(String return_place3) {
		this.return_place3 = return_place3;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	
	
}
