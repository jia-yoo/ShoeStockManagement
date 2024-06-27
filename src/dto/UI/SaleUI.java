package dto.UI;

import java.util.InputMismatchException;

import vo.SaleLedger;

public class SaleUI extends UI{
	public void showSale(int selMenu) {
		while(true) {
			try {
				while(true) {
					//2. 출고대장 관리
					System.out.println("    <<<       출고대장 관리페이지      >>>    ");
					System.out.println();
					System.out.println(" 1. 출고기록 등록 \n 2. 출고기록 조회 \n 3. 출고기록 수정 \n 4. 출고기록 삭제 \n >>");
					selMenu = sc.nextInt();
					if(selMenu ==1 ) {
						showInsert();
					}else if(selMenu == 2) {
						//출고기록 조회
						getAllSaleLedger(); 
						sc.nextLine();
						System.out.println();
						System.out.println("계속하려면 엔터를 치세요");
						sc.nextLine();
					}else if(selMenu == 3) {
						//출고기록 전체조회
						getAllSaleLedger(); 
						while(true) {
							System.out.println("수정을 원하는 출고기록의 번호키를 입력해주세요 >> ");
							//수정원하는 기록 선택
							int num = sc.nextInt();
							///유효한 번호키 넣었는지 체크
							if(ss.findLedger(num) !=null) {
								showUpdate(num);
								break;
							}
						}
					}else if(selMenu == 4) {
						//출고기록 전체조회
						getAllSaleLedger(); 
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
			//출고기록등록
			System.out.println("<<     출고 기록 등록     >>");
			System.out.println("제품 아이디를 입력하세요  >> ");
			int id = sc.nextInt();
			//출고하려는 상품이 상품등록이 되어있는지 체크
			if(ss.checkId(id) != null) {
				//수량은 재고에서 남은 갯수보다 더 많이 입력할 수 없으므로 수량 체크
				System.out.println("제품 수량을 입력하세요  >> ");
				int quantity = sc.nextInt();
				//출고대장 기록 db에 넣어주기
				if(ss.checkInsert(id, quantity)) {
					System.out.println("성공적으로 등록되었습니다");
					break;
				}
			}
		}
	}
	private void showUpdate(int num) {
		while(true) {
			System.out.println(" 1. 상품 수정 \n 2. 수량 수정  \n 원하는 기능의 번호를 입력해주세요 >>");
			int type = sc.nextInt();
			if(service.checkInputType(type, 2)) {
				System.out.println("변경할 데이터를 입력해주세요 >> ");
				sc.nextLine();
				String newData = sc.nextLine().trim();
				if(ss.isNumeric(newData)) {
					//수정사항 db에 넣어주기
					boolean result = ss.checkUpdate(type, newData, ss.findLedger(num));
					//수정한 출고기록 최종확인
					if(result == true) {
						System.out.println("성공적으로 수정되었습니다"); 
						findSaleLedger(ss.findLedger(num));
						break;
					}
				}
			
			}
		}
	}
	private void showDelete() {
		while(true) {
			System.out.println("삭제를 원하는 출고기록의 번호키를 입력해주세요 >> ");
			//삭제원하는 기록 선택
			int num = sc.nextInt();
			//삭제사항 db에 넣어주기
			boolean result = ss.checkDelete(num);
			if(result == true) {
				System.out.println("성공적으로 삭제되었습니다"); 
				break;
			}
		}
	}
	private void getAllSaleLedger() {
		System.out.println("     <<  출고 대장  >>      ");
		for(SaleLedger sl : ss.getSaleLedgerData()) {
			System.out.println(" 날짜 : " + sl.getDate()+ " || 품명 : " + sl.getProdName() + " || id : " + sl.getId() +" || 사이즈 : " + sl.getSize() + " || 가격 : " + sl.getPrice() + " || 수량 : " + sl.getQuantity()+ " || 합계 : " + sl.getTotal() + " || 번호키 : " + sl.getNum());
		}
	}
	private void findSaleLedger(SaleLedger sl) {
		System.out.println("출고 대장  \n"+ " 날짜 : " + sl.getDate()+ " || 품명 : " + sl.getProdName() + " || id : " + sl.getId() +" || 사이즈 : " + sl.getSize() + " || 가격 : " + sl.getPrice()+ " || 수량 : " + sl.getQuantity()+ " || 합계 : " + sl.getTotal() + " || 번호키 : " + sl.getNum());
	}
	

}
