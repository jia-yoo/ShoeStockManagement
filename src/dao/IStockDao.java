package dao;

import java.util.List;

import vo.Stock;

public interface IStockDao{
	boolean selectDataById(int id);
	List<Stock> getAllData();
}
