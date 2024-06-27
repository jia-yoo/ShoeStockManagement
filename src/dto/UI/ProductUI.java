package dto.UI;

import java.util.InputMismatchException;

import vo.Product;

public class ProductUI extends UI{
	public void showProduct(int selMenu) {
		while(true) {
			try {
				while(true) {
					//상품목록 관리
					System.out.println(" 1. 상품 등록 \n 2. 상품 조회 \n 3. 상품정보 수정 \n 4. 상품 삭제 >>");
					selMenu = sc.nextInt();
					service.checkInputType(selMenu, 4);
					if(selMenu ==1 ) {
						showInsert();
					}else if(selMenu ==2) {
						//상품조회
						getAllProd();
					}else if(selMenu ==3) {
						//상품정보수정
						//먼저 전체 상품조회 후,
						getAllProd();
						
						while(true) {
							//수정원하는 기록 선택
							System.out.println("수정을 원하는 상품의 id를 입력해주세요 >> ");
							int id = sc.nextInt();
							///유효한 ID를 넣었는지 확인해주기
							if(prods.findProd(id) != null) {
								showUpdate(id);
								break;
							}
						}
					}else if(selMenu == 4) {
						//상품 전체조회
						getAllProd();
						showDelete();
					}
					if(service.checkInputType(selMenu, 4)) {
						break;
					}
				}
			}catch(InputMismatchException e) {
				System.err.println("💢💢❌❌❌유효한 입력값이 아닙니다 💢💢❌❌❌");
				sc.nextLine();
			}
		}
		
	}
	private void showInsert() {
		while(true) {
			//상품등록
			System.out.println(" <<     상품등록      >> ");
			System.out.println("제품명을 입력하세요 >> ");
			sc.nextLine();
			String prodName = sc.nextLine().trim();
			System.out.println("제품 사이즈를 입력하세요  >> ");
			int size = sc.nextInt();
			System.out.println("제품 구매가격을 입력하세요  >> ");
			int pprice = sc.nextInt();
			System.out.println("제품 판매가격을 입력하세요  >> ");
			int sprice = sc.nextInt();
			//상품목록 db에 넣어주기
			if(prods.checkInsert(prodName, size, pprice, sprice)) {
				System.out.println("상품이 성공적으로 등록되었습니다.");
			}
		}
	}
	private void showUpdate(int id) {
		while(true) {
			System.out.println(" 1. 상품명 수정 \n 2. 사이즈 수정 \n 3. 입고가 수정 \n 4. 출고가 수정 \n 원하는 기능의 번호를 입력해주세요 >>");
			int type = sc.nextInt();
			//타입체크
			if(service.checkInputType(type, 4)) {
				System.out.println("변경할 데이터를 입력해주세요 >> ");
				sc.nextLine();
				String newData = sc.nextLine().trim();
				//수정사항 db에 넣어주기
				boolean result = prods.checkUpdate(type, newData, prods.findProd(id));
				//수정한 출고기록 최종확인
				if(result == true) {
					System.out.println("성공적으로 수정되었습니다"); 
					findProduct(prods.findProd(id));
					break;
				}
			}
		
		}
	}
	private void showDelete() {
		while(true) {
			System.out.println("삭제를 원하는 상품의 아이디를 입력해주세요 >> ");
			//삭제원하는 기록 선택
			int id = sc.nextInt();
			//삭제사항 db에 넣어주기
			boolean result = prods.checkDelete(id);
			if(result == true) {
				System.out.println("상품이 성공적으로 삭제되었습니다");  
				break;
			}
		}
	}
	public void getAllProd() {
		System.out.println("     <<  상품 대장  >>      ");
		for(Product p : prods.getProdData()) {
			System.out.println(" 품명 : " + p.getProdName() + " || id : " + p.getId() +" || 사이즈 : " + p.getSize() + " || 구입가격 : " + p.getpPrice()+ " || 판매가격 : " + p.getsPrice());
		}
	}
	private void findProduct(Product p) {
		System.out.println(" 품명 : " + p.getProdName()+ " || id : " + p.getId() +" || 사이즈 : " + p.getSize() + " || 입고가격 : " + p.getpPrice()+ " || 출고가격 : " + p.getsPrice());
	}

}
