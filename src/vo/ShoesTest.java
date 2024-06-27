package vo;

import dao.ProductDao;
import dto.UI.ProductUI;
import dto.UI.PurchaseUI;
import dto.UI.SaleUI;
import dto.UI.UI;

public class ShoesTest {
	public static void main(String[] args){
		UI ui = new UI();
		PurchaseUI uiPur = new PurchaseUI();
		ProductUI uiProd = new ProductUI();
		SaleUI uiSale = new SaleUI();
	
		

		
		int selMenu = ui.showHome();
		if(selMenu ==1) {
			//1.입고대장관리 화면
			uiPur.showPurchase(selMenu);
		}else if(selMenu == 2) {
			//2. 출고대장관리 화면
			uiSale.showSale(selMenu);
		}else if(selMenu == 3) {
			//3. 재고조사표 관리 (조회)
			ui.showStock();
		}else if(selMenu == 4) {
			//4. 상품목록 관리
			uiProd.showProduct(selMenu);
		}
			
	}

}
