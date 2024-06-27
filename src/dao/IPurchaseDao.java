package dao;

import java.util.List;

import vo.PurchaseLedger;

public interface IPurchaseDao{
	 void insertData(PurchaseLedger pl);
	 boolean selectDataByNum(int num);
	 boolean selectDataById(int id);
	 void updateData(int type, String newData ,PurchaseLedger pl);
	 void deleteData(int num);
	 List<PurchaseLedger> getAllData();
}
