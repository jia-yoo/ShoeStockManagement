package dto.service;

import java.util.List;

import vo.Product;

public class ProductService extends Service {
	public boolean checkInsert(String prodName, int size, int pprice, int sprice) {
		//상품명, 사이즈, 가격의 입력값이 유효한 값인지 체크
		boolean result = false;
		
		//상품명이 그냥 빈칸인지 아닌지 걸러내기 (사이즈와 가격은 여기서 숫자타입으로 받으므로 타입체크필요없음)
		if(prodName.isEmpty()) {
			System.err.println("💢💢❌❌❌상품명은 빈칸일 수 없습니다 유효한 상품명을 넣어주세요 💢💢❌❌❌");
		}else if(size <= 0 || pprice <=0 || sprice <=0){
			System.err.println("💢💢❌❌❌사이즈나 가격은 최소 0보다 커야합니다. 💢💢❌❌❌");
		}else{
			Product p = new Product(prodName, 0 ,size, pprice, sprice);
			db_product.insertData(p);
		}
		return result;
	}
	public boolean checkUpdate(int type, String newData, Product curP) {
		boolean result = false;
		//type이 1-4이 아닌숫자를 입력할경우 오류 내보내기
		if(type == 1) {
			//상품명수정 --> 새로 입력된 상품명이 valid한지 (그냥 빈칸이면 안됨)
			if(newData.isEmpty()) {
				System.err.println("💢💢❌❌❌상품명은 빈칸일 수 없습니다 유효한 상품명을 넣어주세요 💢💢❌❌❌");	
			}else {
				db_product.updateData(type, newData, curP);
			}
		}else if(type == 2 || type == 3 || type == 4) {
			//사이즈, 입고가, 출고가 수정 --> 새로 입력된 사이즈, 입고가, 출고가가 valid한지(오직 숫자로만 이루어져야함)
			if(isNumeric(newData)) {
				db_product.updateData(type, newData, curP);
			}else {
				System.err.println("💢💢❌❌❌숫자만 들어갈 수 있습니다. 유효한 값 넣어주세요 💢💢❌❌❌");
			}
		}else {
			System.err.println("💢💢❌❌❌유효한 입력값이 아닙니다 💢💢❌❌❌");	
		}
		return result;
	}
	public boolean checkDelete(int id) {
		boolean result = false;
		//유효한 아이디를 받았는지 체크해주기
		if(hasProd(id)) {
		// 실제 db에서 삭제해주기
			db_product.deleteData(id);
			result = true;
			}	
		return result;
	}
	public List<Product> getProdData(){
		return db_product.getAllData();
	}
	public Product findProd(int id) {
		//아이디가 상품대장에 존재하는지 찾기
		Product curP = null;
		if(hasProd(id)){
			//있는경우
				for(Product p : getProdData()) {
					if(p.getId() == id) {
						curP = p;
					}
				}	
			}
		return curP;
	}
	private boolean hasProd(int id) {
		db_product.getAllData();
		//번호키가 입고대장에 존재하는지 찾기
	
		boolean result = db_product.selectDataById(id);
		//기록을 arrayList에서 찾아주기
		if(result == false) {
			//입력한 번호키에 해당하는 입고기록이 없는경우
				System.err.println("입력한 아이디에 해당하는 상품이 없습니다 다시 한번 아이디를 확인해주세요");	
			}
		return result;
	}
	
}
