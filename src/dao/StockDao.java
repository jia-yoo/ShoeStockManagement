package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.Stock;

public class StockDao implements IStockDao {
	public boolean selectDataById(int id) {
		String query = "SELECT * FROM v_stocktbl WHERE ID =? ;";
		boolean result = false;
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String prodName = rs.getString("prodName");
				id = rs.getInt("id");
				int pQuan = rs.getInt("pQuan");
				int sQuan = rs.getInt("sQuan");
				int stockQuan = rs.getInt("totalQuan");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Stock> getAllData() {
		String query = "SELECT * FROM v_stocktbl order by prodName ASC;";
		List<Stock> stockList = new ArrayList<>();
		try {
			Statement stmt = DBcon.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String prodName = rs.getString("prodName");
				int id = rs.getInt("id");
				int pQuan = rs.getInt("pQuan");
				int sQuan = rs.getInt("sQuan");
				int totalQuan = rs.getInt("totalQuan");
				stockList.add(new Stock(prodName,id, pQuan,sQuan,totalQuan));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockList;
	}


}
