package dao;

import java.util.List;


public interface CompoundDao {
	 void insertData(Object obj);
	 boolean selectDataById(int id);
	 void updateData(int type, String newData, Object obj);
	 void deleteData(int id);
}
