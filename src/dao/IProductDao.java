package dao;

import java.util.List;

import vo.Product;

public interface IProductDao{
	void insertData(Product p);
	boolean selectDataById(int id);
	void updateData(int type, String newData, Product curP);
	void deleteData(int id);
	List<Product> getAllData();
}
