package dto.service;

import java.util.List;

import dao.ProductDao;
import dao.PurchaseDao;
import dao.SaleDao;
import dao.StockDao;
import vo.Stock;

public class Service {
	public ProductDao db_product = new ProductDao();
	public PurchaseDao db_purchase = new PurchaseDao();
	public SaleDao db_sale = new SaleDao();
	public StockDao db_stock = new StockDao();

	public boolean checkInputType (int type, int i) {
		boolean result = true;
		if(type > i || type <= 0 ) {
			System.err.println("💢💢❌❌❌유효한 입력값이 아닙니다 💢💢❌❌❌");
			result = false;
		}
		return result;
	}
	public boolean isNumeric(String input) {
		while(true) {
			try {
				if(input == null || input.isEmpty() || Integer.parseInt(input) <= 0) {
				System.err.println("💢💢❌❌❌유효한 입력값이 아닙니다 💢💢❌❌❌");
				return false;
				} 
				for(char c : input.toCharArray()) {
					if(!Character.isDigit(c)) {
						return false;
					}
				}
			}catch(NumberFormatException e) {
				System.err.println("💢💢❌❌❌유효한 입력값이 아닙니다 양수인 숫자를 입력해주세요💢💢❌❌❌");
				return false;
			}
			return true;
		}
	}
	public List<Stock> getStockData() {
		return 	db_stock.getAllData();
	}
}
