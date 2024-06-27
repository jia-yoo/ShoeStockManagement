package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.Product;



public class ProductDao implements IProductDao{
	public void insertData(Product p) {
		String query = "INSERT INTO prod_tbl (prodName, size, pprice, sprice) values (?,?,?,?);";
		boolean result = false;
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			stmt.setString(1, p.getProdName());
			stmt.setInt(2, p.getSize());
			stmt.setInt(3, p.getpPrice());
			stmt.setInt(4, p.getsPrice());
			//db에 넣어주기
			stmt.executeUpdate();
			//arrayList 업데이트해주기
			getAllData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean selectDataById(int id) {
		String query = "SELECT * FROM prod_tbl WHERE ID =? ;";
		boolean result = false;
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String prodName = rs.getString("prodName");
				id = rs.getInt("id");
				int size = rs.getInt("size");
				int pPrice = rs.getInt("pPrice");
				int sPrice = rs.getInt("sPrice");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}	
	public void updateData(int type, String newData, Product curP) {
		String query = "UPDATE prod_tbl SET PRODNAME = ?, SIZE = ?, PPRICE = ?, SPRICE =? WHERE ID = ?;";
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			
			if(type == 1) {
				stmt.setString(1, newData);
				stmt.setInt(2, curP.getSize());
				stmt.setInt(3, curP.getpPrice());
				stmt.setInt(4, curP.getsPrice());
				stmt.setInt(5, curP.getId());
			}else if(type == 2) {
				stmt.setInt(1, curP.getId());
				stmt.setInt(2, Integer.parseInt(newData));
				stmt.setInt(3, curP.getpPrice());
				stmt.setInt(4, curP.getsPrice());
				stmt.setInt(5, curP.getId());
			}else if(type == 3) {
				stmt.setInt(1, curP.getId());
				stmt.setInt(2, curP.getSize());
				stmt.setInt(3, Integer.parseInt(newData));
				stmt.setInt(4, curP.getsPrice());
				stmt.setInt(5, curP.getId());
			}else if(type == 4) {
				stmt.setInt(1, curP.getId());
				stmt.setInt(2, curP.getSize());
				stmt.setInt(3, curP.getpPrice());
				stmt.setInt(4, Integer.parseInt(newData));
				stmt.setInt(5, curP.getId());
			}
			//db에 넣어주기
			stmt.executeUpdate();
			//arrayList 업데이트해주기
			getAllData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteData(int id) {
		String query = "Delete prod_tbl WHERE ID = ?;";
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			//db에 넣어주기
			stmt.executeUpdate();
			//arrayList 업데이트해주기
			getAllData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Product> getAllData() {
		String query = "SELECT * FROM prod_tbl order by prodName ASC;";
		List<Product> prodList = new ArrayList<>();
		try {
			Statement stmt = DBcon.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String prodName = rs.getString("prodName");
				int id = rs.getInt("id");
				int size = rs.getInt("size");
				int pPrice = rs.getInt("pprice");
				int sPrice = rs.getInt("sprice");
				Product p = new Product(prodName, id, size,pPrice, sPrice);
				prodList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodList;
	}
}
