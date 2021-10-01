package vo;

public class Rentcar {
	private int car_no;
	private String car_name;
	private String car_group;
	private int car_year;
	private String car_reserve;
	private int car_price;
	private String car_brand;
	private String car_image;
	private int car_readCount;
	//기본생성자는 안해도됨
	public Rentcar() {}

	public Rentcar(int car_no, String car_name, String car_group, int car_year, String car_reserve, int car_price,
			String car_brand, String car_image, int car_readCount) {
		super();
		this.car_no = car_no;
		this.car_name = car_name;
		this.car_group = car_group;
		this.car_year = car_year;
		this.car_reserve = car_reserve;
		this.car_price = car_price;
		this.car_brand = car_brand;
		this.car_image = car_image;
		this.car_readCount = car_readCount;
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

	public String getCar_group() {
		return car_group;
	}

	public void setCar_group(String car_group) {
		this.car_group = car_group;
	}

	public int getCar_year() {
		return car_year;
	}

	public void setCar_year(int car_year) {
		this.car_year = car_year;
	}

	public String getCar_reserve() {
		return car_reserve;
	}

	public void setCar_reserve(String car_reserve) {
		this.car_reserve = car_reserve;
	}

	public int getCar_price() {
		return car_price;
	}

	public void setCar_price(int car_price) {
		this.car_price = car_price;
	}

	public String getCar_brand() {
		return car_brand;
	}

	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}

	public String getCar_image() {
		return car_image;
	}

	public void setCar_image(String car_image) {
		this.car_image = car_image;
	}

	public int getCar_readCount() {
		return car_readCount;
	}

	public void setCar_readCount(int car_readCount) {
		this.car_readCount = car_readCount;
	}


	
}
