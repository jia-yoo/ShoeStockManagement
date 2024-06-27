package dto.service;

import java.util.List;

import vo.Product;
import vo.SaleLedger;
import vo.Stock;

public class SaleService extends Service {
	public Product checkId(int id) {
		//입고가 안된 제품은 출고가 안되야 하고, 재고보다 많은 양이 출고가 되어서는 안됨
		//==> 입고가 안된 제품을 출고등록 못하게 하는건, 재고테이블의 아이디를 확인해주면됨
		//==> 재고의 갯수보다 많은 양을 출고 못하게 하는건 재고 테이블의 STOCKQUAN을 확인 후, 그보다 많이 등록할 수 없게 제한을 걸어주면됨
		//여전히 상품 정보는 상품 테이블에서 가져와서 자동으로 채워줘야함.
		//모든 상품 정보 불러주기
		db_product.getAllData();
		Product curP = null;
		//재고테이블에 해당 아이디가 존재하는지 확인(입고되지 않은 상품은 출고될수 없으므로)
		boolean result = db_stock.selectDataById(id);
		if(result == false) {
		//재고테이블에 아이디가 없는경우(입고된적이없는경우)
			System.err.println("입고된적 없는 상품으로, 출고등록이 불가합니다. 상품id를 다시 확인해주세요");
		}else {
		//재고테이블에 아이디가 있는 경우(입고된적있는경우 --> 출고가능)
		//하지만 남은 재고 갯수 보다 많은 양을 출고등록할수는 없음!
			
			//우선 해당 아이디에 맞는 상품을 찾아서 상품 정보 전달해주기
			for(Product p : db_product.getAllData()) {
				if(p.getId() == id){
				curP = p;
				}
			}	
		}
		return curP;
	}
	private boolean checkAvailQuan(int id, int quantity) {
		boolean result = true;
		//재고 갯수보다 더 많은 갯수의 출고를 등록할수 없으므로 출고 가능한 수량인지 체크해주는 메서드
		int availQuan = 0;
		for(Stock s : db_stock.getAllData()) {
			if(s.getId() == id) {
				availQuan = s.getTotalQuan();
			}
	    }
		if(quantity > availQuan) {
			System.out.println("현 보유 재고 : " + availQuan);
			System.err.println("출고 등록 상품의 갯수는 현 보유 재고 갯수보다 많을 수 없습니다. 현 재고 갯수를 다시 확인해주세요");
			result = false;
		}
		return result;
	}
	public boolean checkInsert(int id, int quantity) {
		boolean result = false;
		if(quantity >0 && checkAvailQuan(id, quantity)) {
			SaleLedger sl = new SaleLedger(null, checkId(id).getProdName(), id, checkId(id).getSize() ,checkId(id).getsPrice(), quantity);
			db_sale.insertData(sl);
			result = true;
		}else {
			System.err.println("수량은 최소 1개 이상이어야 합니다.");	
		}
		return result;
	}
	public boolean checkUpdate(int type, String newData, SaleLedger curSl) {
		boolean result = false;
		//type이 1-2이 아닌숫자를 입력할경우 오류 내보내기
		if(type == 1){
			//newdata가 valid한 data인지 확인 (상품수정일때는 등록된상품인지)
			if(checkId(Integer.parseInt(newData)) !=null) {
				db_sale.updateData(type, newData, curSl);
				result = true;
			}
		}else if(type == 2){
			//newdata가 valid한 data인지 확인(수량수정일때는 숫자인지아닌지)
			if(isNumeric(newData)) {
				db_sale.updateData(type, newData,curSl);
				result = true;
				}
		}
		return result;
	}
	public boolean checkDelete(int num) {
		boolean result = false;
		if(hasLedger(num)) {
			//입력된 번호키에 맞는 입고기록이있는경우
			db_sale.deleteData(num);
			result = true;
			}	
		return result;
	}
	public List<SaleLedger> getSaleLedgerData() {
		return 	db_sale.getAllData();
	}
	public SaleLedger findLedger(int num) {
		SaleLedger curSl = null;
		if(hasLedger(num)) {
			for(SaleLedger sl : getSaleLedgerData()) {
				if(sl.getNum() == num) {
					curSl = sl;
				}
			}	
		}
		return curSl;
	}
	private boolean hasLedger(int num) {
		db_sale.getAllData();
		//번호키가 입고대장에 존재하는지 찾기
		SaleLedger curSl = null;
		boolean result = db_sale.selectDataByNum(num);
		//기록을 arrayList에서 찾아주기
		if(result == false) {
			//입력한 번호키에 해당하는 입고기록이 없는경우
				System.err.println("입력한 번호키에 해당하는 출고 기록이 없습니다 다시 한번 번호키를 확인해주세요");	
			}
		return result;
	}
}