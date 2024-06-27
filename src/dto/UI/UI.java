package dto.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import dto.service.ProductService;
import dto.service.PurchaseService;
import dto.service.SaleService;
import dto.service.Service;
import vo.Product;
import vo.PurchaseLedger;
import vo.SaleLedger;
import vo.Stock;

public class UI {
	public Scanner sc = new Scanner(System.in);
	public PurchaseService ps = new PurchaseService();
	public SaleService ss = new SaleService();
	public ProductService prods = new ProductService();
	public Service service = new Service();
	
	public int showHome() {
		int selMenu = 0;
		while(true) {
			try {
				System.out.println("    <<<      신발가게 관리시스템      >>>    ");
				System.out.println();
				System.out.println(" 1. 입고대장 관리 \n 2. 출고대장 관리 \n 3. 재고조사표 관리 \n 4. 상품목록 관리 \n >> ");
				selMenu = sc.nextInt();
				if(service.checkInputType(selMenu, 4)) {
					break;
				}
			}catch(InputMismatchException e) {
				System.err.println("💢💢❌❌❌유효한 입력값이 아닙니다 💢💢❌❌❌");
				sc.nextLine();
			}
		}
		return selMenu;
	}
	public void showStock() {
		getAllStock();
		if(service.getStockData() == null) {
			System.out.println("재고표가 비어있습니다.");
		}
	}
	private void getAllStock() {
		System.out.println("     <<  재고 대장  >>      ");
		for(Stock st : service.getStockData()) {
			System.out.println(" 품명 : " + st.getProdName() + " || id : " + st.getId() +" || 사입갯수 : " + st.getpQuan() + " || 판매갯수 : " + st.getsQuan() + " || 총재고수량 : " + st.getTotalQuan());
		}
	}
}
