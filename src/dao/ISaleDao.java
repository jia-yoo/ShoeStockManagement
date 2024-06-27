package dao;

import java.util.List;

import vo.SaleLedger;

public interface ISaleDao {
	
	void insertData(SaleLedger sl);
	boolean selectDataByNum(int num);
	void updateData(int type, String newData, SaleLedger sl);
	void deleteData(int num);

	
}
