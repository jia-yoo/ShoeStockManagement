package dto.UI;

import java.util.InputMismatchException;

import vo.PurchaseLedger;

public class PurchaseUI extends UI{
	public void showPurchase(int selMenu) {
		while(true) {
			try {
				while(true) {
					//1. 입고대장 관리
					System.out.println("    <<<      입고대장 관리페이지      >>>    ");
					System.out.println();
					System.out.println(" 1. 입고기록 등록 \n 2. 입고기록 조회 \n 3. 입고기록 수정 \n 4. 입고기록 삭제 \n >>");
					selMenu = sc.nextInt();
					if(selMenu == 1) {
						showInsert();
					}else if(selMenu == 2) {
						//입고기록 조회
						getAllPurLedger();
						sc.nextLine();
						System.out.println();
						System.out.println("계속하려면 엔터를 치세요");
						sc.nextLine();
					}else if(selMenu == 3) {
						//수정 전 입고기록 전체조회
						getAllPurLedger();
						while(true) {
							System.out.println("수정을 원하는 입고기록의 번호키를 입력해주세요 >> ");
							//수정원하는 기록 선택
							int num = sc.nextInt();
						    ///유효한 번호키 넣었는지 체크
							if(ps.findLedger(num) != null) {
								showUpdate(num);
								break;
							}
						}
					}else if(selMenu == 4) {
						//입고기록 전체조회
						getAllPurLedger();
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
			//입고기록등록
			System.out.println("<<     입고 기록 등록     >>");
			System.out.println("제품 아이디를 입력하세요  >> ");
			int id = sc.nextInt();
			//입고하려는 상품이 상품등록이 되어있는지 체크
			if(ps.checkId(id) != null) {
				System.out.println("제품 수량을 입력하세요  >> ");
				int quantity = sc.nextInt();
				//입고대장 기록 db에 넣어주기
				if(ps.checkInsert(id, quantity)) {
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
				System.out.println("변경할 데이터를 입력해주세요 >> (변경할 상품 아이디 or 변경할 수량)");
				sc.nextLine();
				String newData = sc.nextLine().trim();
				if(ps.isNumeric(newData)) {
					//수정사항 db에 넣어주기
					boolean result = ps.checkUpdate(type, newData, ps.findLedger(num));
					//수정한 입고기록 최종확인
					if(result == true) {
						System.out.println("성공적으로 수정되었습니다"); 
						findPurLedger(ps.findLedger(num));
						break;
					}
				}
			}
		}
	}
	private void showDelete() {
		while(true) {
			System.out.println("삭제를 원하는 입고기록의 번호키를 입력해주세요 >> ");
			//삭제원하는 기록 선택
			int num = sc.nextInt();
			//삭제사항 db에 넣어주기
			boolean result = ps.checkDelete(num);
			if(result == true) {
				System.out.println("성공적으로 삭제되었습니다");  
				break;
			}
		}
	}
	private void getAllPurLedger() {
		System.out.println("     <<  입고 대장  >>      ");
		for(PurchaseLedger pl : ps.getPurLedgerData()) {
			System.out.println(" 날짜 : " + pl.getDate()+ " || 품명 : " + pl.getProdName() + " || id : " + pl.getId() +" || 사이즈 : " + pl.getSize() + " || 가격 : " + pl.getPrice() + " || 수량 : " + pl.getQuantity()+ " || 합계 : " + pl.getTotal() + " || 번호키 : " + pl.getNum());
		}
	}
	private void findPurLedger(PurchaseLedger pl) {
		System.out.println("입고 대장  \n"+ " 날짜 : " + pl.getDate()+ " || 품명 : " + pl.getProdName() + " || id : " + pl.getId() +" || 사이즈 : " + pl.getSize() + " || 가격 : " + pl.getPrice()+ " || 수량 : " + pl.getQuantity()+ " || 합계 : " + pl.getTotal() + " || 번호키 : " + pl.getNum());
	}

}
