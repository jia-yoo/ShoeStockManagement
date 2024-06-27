package vo;

import java.sql.Date;

public class SaleLedger{

	private Date date = null;
	private String prodName = null;
	private int id = 0;
	private int size = 0;
	private int price = 0;
	private int quantity = 0;
	private int total = 0;
	private int num = 0;
	
	public SaleLedger(Date date, String prodName, int id, int size, int price, int quantity) {
		this.date = date;
		this.prodName = prodName;
		this.id = id;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
		total = price * quantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
