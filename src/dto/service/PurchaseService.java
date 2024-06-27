package dto.service;

import java.util.List;

import vo.Product;
import vo.PurchaseLedger;

public class PurchaseService extends Service{
	

	public Product checkId(int id) {
		//prod에 없는 id라면 입고를 할 수 없게 check해야함
		Product curP = null;
		boolean result = db_product.selectDataById(id);
		if(result == false) {
		//상품 등록이 안되어있는 경우
			System.err.println("등록되어있지 않은 상품입니다. 상품등록을 먼저해주세요");	
		}else {
		//상품등록이 되어있는 경우
			for(Product p : db_product.getAllData()) {
				if(p.getId() == id){
				curP = p;
				}
			}	
		}
		return curP;
	}
	public boolean checkInsert(int id, int quantity){
		boolean result = false;
		if(quantity > 0) {
			PurchaseLedger pl = new PurchaseLedger(null, checkId(id).getProdName(), id, checkId(id).getSize() ,checkId(id).getpPrice(), quantity);
			//입고대장 기록 db에 넣어주기
			db_purchase.insertData(pl);
			result = true;
		}else {
			System.err.println("수량은 최소 1개 이상이어야 합니다.");	
		}
		return result;
	}
	public boolean checkUpdate(int type, String newData, PurchaseLedger curPl){
		boolean result = false;
		//type이 1-2이 아닌숫자를 입력할경우 오류 내보내기
			if(type == 1){
				//newdata가 valid한 data인지 확인 (상품수정일때는 등록된상품인지)
				if(checkId(Integer.parseInt(newData)) !=null) {
					db_purchase.updateData(type, newData,curPl);
					result = true;
				}
			}else if(type == 2){
				//newdata가 valid한 data인지 확인(수량수정일때는 숫자인지아닌지)
				if(isNumeric(newData)) {
					db_purchase.updateData(type, newData,curPl);
					result = true;
				}
			}
		return result;
	}
	public boolean checkDelete(int num) {
		boolean result = false;
		if(hasLedger(num)) {
			//입력된 번호키에 맞는 입고기록이있는경우
				db_purchase.deleteData(num);
				result = true;
				}	
		return result;
	}
	
	public List<PurchaseLedger> getPurLedgerData() {
		return db_purchase.getAllData();
	}
	public PurchaseLedger findLedger(int num) {
		//번호키가 입고대장에 존재하는지 찾기
		PurchaseLedger curPl = null;
		if(hasLedger(num)) {
			for(PurchaseLedger pl : getPurLedgerData()) {
				if(pl.getNum() == num) {
					curPl = pl;
				}
			}	
		}
		return curPl;
	}
	private boolean hasLedger(int num) {
		db_purchase.getAllData();
		//번호키가 입고대장에 존재하는지 찾기
		boolean result = db_purchase.selectDataByNum(num);
		//기록을 arrayList에서 찾아주기
		if(result == false) {
			//입력한 번호키에 해당하는 입고기록이 없는경우
				System.err.println("입력한 번호키에 해당하는 입고 기록이 없습니다 다시 한번 번호키를 확인해주세요");	
			}
		return result;
	}
	

}
