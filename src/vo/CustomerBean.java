package vo;

public class CustomerBean {
	//5개는 회원가입폼에 있음
	private String c_id;
	private String c_grade;//회원가입폼에서 제외
	private String c_password;
	private String c_name;
	private String c_gender;
	private String c_birth;
	private String c_email1;
	private String c_email2;
	private String c_tel;
	
	//3개는 회원가입폼에 없음
	private String c_joindate;
	
	private int order_quantity;//추가
	private int order_money;
	
	public CustomerBean() {}
	
	public CustomerBean(String c_id, String c_grade, String c_name, String c_email1, String c_email2, String c_tel,
			String c_joindate) {
		super();
		this.c_id = c_id;
		this.c_grade = c_grade;
		this.c_name = c_name;
		this.c_email1 = c_email1;
		this.c_email2 = c_email2;
		this.c_tel = c_tel;
		this.c_joindate = c_joindate;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_grade() {
		return c_grade;
	}
	public void setC_grade(String c_grade) {
		this.c_grade = c_grade;
	}
	public String getC_password() {
		return c_password;
	}
	public void setC_password(String c_password) {
		this.c_password = c_password;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_gender() {
		return c_gender;
	}
	public void setC_gender(String c_gender) {
		this.c_gender = c_gender;
	}
	public String getC_birth() {
		return c_birth;
	}
	public void setC_birth(String c_birth) {
		this.c_birth = c_birth;
	}
	public String getC_email1() {
		return c_email1;
	}
	public void setC_email1(String c_email1) {
		this.c_email1 = c_email1;
	}
	public String getC_email2() {
		return c_email2;
	}
	public void setC_email2(String c_email2) {
		this.c_email2 = c_email2;
	}
	public String getC_tel() {
		return c_tel;
	}
	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}
	public String getC_joindate() {
		return c_joindate;
	}
	public void setC_joindate(String c_joindate) {
		this.c_joindate = c_joindate;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public int getOrder_money() {
		return order_money;
	}
	public void setOrder_money(int order_money) {
		this.order_money = order_money;
	}
	
	//기본생성자
	

	
	
	
}
