package vo;

public class Stock {
	private String prodName = null;
	private int id = 0;
	private int pQuan = 0;
	private int sQuan = 0;
	private int totalQuan = 0;

	
	public Stock(String prodName, int id ,int pQuan, int sQuan, int totalQuan) {
		this.prodName = prodName;
		this.id = id;
		this.pQuan = pQuan;
		this.sQuan = sQuan;
		this.totalQuan = totalQuan;
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

	public int getpQuan() {
		return pQuan;
	}

	public void setpQuan(int pQuan) {
		this.pQuan = pQuan;
	}

	public int getsQuan() {
		return sQuan;
	}

	public void setsQuan(int sQuan) {
		this.sQuan = sQuan;
	}

	public int getTotalQuan() {
		return totalQuan;
	}

	public void setTotalQuan(int totalQuan) {
		this.totalQuan = totalQuan;
	}
}
