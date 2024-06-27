package vo;

public class Product {
	private String prodName = null;
	private int id = 0;
	private int size = 0;
	private int pPrice = 0;
	private int sPrice = 0;
	
	public Product(String prodName, int id, int size, int pPrice, int sPrice) {
		this.prodName = prodName;
		this.id = id;
		this.size = size;
		this.pPrice = pPrice;
		this.sPrice = sPrice;
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
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getsPrice() {
		return sPrice;
	}
	public void setsPrice(int sPrice) {
		this.sPrice = sPrice;
	}


}
