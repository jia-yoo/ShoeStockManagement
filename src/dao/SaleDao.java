package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.SaleLedger;

public class SaleDao implements ISaleDao {
	private ProductDao db_product = new ProductDao();
	
	public void insertData(SaleLedger sl) {
		String query = "INSERT INTO sale_list(prodName, id, size, price, quantity, total) VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			stmt.setString(1, sl.getProdName());
			stmt.setInt(2, sl.getId());
			stmt.setInt(3, sl.getSize());
			stmt.setInt(4, sl.getPrice());
			stmt.setInt(5, sl.getQuantity());
			stmt.setInt(6, sl.getTotal());
			//db에 넣어주기
			stmt.executeUpdate();
			//arrayList 업데이트해주기
			getAllData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean selectDataByNum(int num) {
		String query = "SELECT * FROM sale_list WHERE num = ?;";
		boolean result = false;
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			stmt.setInt(1, num);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String date = rs.getString("date");
				String prodName = rs.getString("prodName");
				int id = rs.getInt("id");
				int size = rs.getInt("size");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				int total = rs.getInt("total");
				result = true;
				System.out.println("출고 대장  \n"+ " 날짜 : " + date+ " || 품명 : " + prodName + " || id : " + id +" || 사이즈 : " + size + " || 가격 : " + price + " || 수량 : " + quantity+ " || 합계 : " + total + " || 번호키 : " + num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		}
	public void updateData(int type, String newData, SaleLedger sl) {
		String query = "UPDATE sale_list SET DATE = ?, PRODNAME = ?, ID = ?, SIZE = ?, PRICE = ?, QUANTITY = ?, total =? WHERE NUM = ?;";
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			if(type == 1) {
				stmt.setString(1, newData);
				stmt.setString(2, sl.getProdName());
				stmt.setInt(3, sl.getId());
				stmt.setInt(4, sl.getSize());
				stmt.setInt(5, sl.getPrice());
				stmt.setInt(6, sl.getQuantity());
				stmt.setInt(7, sl.getTotal());
				stmt.setInt(8, sl.getNum());
			}else if(type == 2) {
				String[] prodInfo = getProdInfo(Integer.parseInt(newData));
				stmt.setDate(1, sl.getDate());
				stmt.setString(2, prodInfo[0]);
				stmt.setInt(3, Integer.parseInt(newData));
				stmt.setInt(4, 	Integer.parseInt(prodInfo[1]));
				stmt.setInt(5,	Integer.parseInt(prodInfo[2]));
				stmt.setInt(6, sl.getQuantity());
				stmt.setInt(7,Integer.parseInt(prodInfo[2]) * sl.getQuantity() );
				stmt.setInt(8, sl.getNum());
			}else if(type == 3) {
				stmt.setDate(1, sl.getDate());
				stmt.setString(2, sl.getProdName());
				stmt.setInt(3, sl.getId());
				stmt.setInt(4, sl.getSize());
				stmt.setInt(5, sl.getPrice());
				stmt.setInt(6, Integer.parseInt(newData));
				stmt.setInt(7, sl.getTotal());
				stmt.setInt(8, sl.getNum());
			}
			//db에 넣어주기
			stmt.executeUpdate();
			//arrayList 업데이트해주기
			getAllData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void deleteData(int num) {
		String query = "DELETE FROM sale_list WHERE NUM = ?;";
		try {
			PreparedStatement stmt = DBcon.getConnection().prepareStatement(query);
			stmt.setInt(1, num);
			//db에 넣어주기
			stmt.executeUpdate();
			//arrayList 업데이트해주기
			getAllData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<SaleLedger> getAllData() {
		String query = "SELECT * FROM sale_list order by prodName ASC;";
		List<SaleLedger> saleList = new ArrayList<>();
		try {
			Statement stmt = DBcon.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Date date = rs.getDate("date");
				String prodName = rs.getString("prodName");
				int id = rs.getInt("id");
				int size = rs.getInt("size");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				int num = rs.getInt("num");
				SaleLedger sl = new SaleLedger(date,prodName,id, size,price,quantity);
				sl.setNum(num);
				saleList.add(sl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saleList;
	}

	private String[] getProdInfo (int id) {
		db_product.selectDataById(id);
		String [] prodInfo = new String[3];
		String query = "SELECT * FROM prod_tbl WHERE ID =? ;";
		
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
				prodInfo[0] = prodName;
				prodInfo[1] = Integer.toString(size);
				prodInfo[2] = Integer.toString(sPrice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodInfo ;
	}
}
