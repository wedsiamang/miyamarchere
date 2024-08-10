package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import model.Shop;
import pack.Utility;

public class ToBeApprovedDao {

	Utility u = new Utility();
	ResourceBundle rb = u.DbConnect();

	// 承認された店舗が24時間後にshopテーブルに反映される
	public void insert_shop(String shopName, String hashedCode, String tel, String address, String email,
			String businessHour, byte[] resizeBytes, String shopComment, String nearBy) {

//		    	ResultSet rs=null;
//		    	
		String sql = "INSERT INTO shop(shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy,"
				+ "date_time)select distinct shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,"
				+ "nearBy,date_time from to_be_approved tba where date_time <= NOW() - INTERVAL '24 hours'"
				+"AND NOT EXISTS (SELECT 1 FROM shop WHERE shop_name = tba.shop_name)";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			//
			// conn.setAutoCommit(false);
			st.executeUpdate();
			// conn.commit();

		} catch (SQLException e) {
			System.out.println("例外が発生しました");
			e.printStackTrace();

		} catch (MissingResourceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Shop> shop_info() {

		ResultSet rs = null;

		List<Shop> shopList = new ArrayList<Shop>();
		Shop s;
		String sql = "SELECT *  FROM to_be_approved";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			rs = st.executeQuery();

			while (rs.next()) {
				int shopId = rs.getInt("shop_id");
				String shopName = rs.getString("shop_name");
				String password = rs.getString("password");
				String tel = rs.getString("tel");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String businessHour = rs.getString("business_hour");
				String shopImg = Base64.getEncoder().encodeToString(rs.getBytes("shop_img"));
				String shopComment = rs.getString("shop_comment");
				String nearBy = rs.getString("nearBy");
			//	Timestamp dateTime = rs.getTimestamp("date_time");

				// conn.commit();

				s = new Shop(shopId, shopName, password, tel, address, email, businessHour, shopImg, shopComment,
						nearBy);
				shopList.add(s);

			}
			return shopList;

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return shopList;
	}

	public void delete_shop(int shopId) {

		int rs = 0;

		// SELECT文の準備
		String sql = "DELETE FROM to_be_approved WHERE shop_id=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			st.setInt(1, shopId);
			// st.setString(2, loginName);

			// conn.setAutoCommit(false);

			// SELECT文を実行
			rs = st.executeUpdate();
			System.out.println("daodelete" + shopId);
			// conn.commit();

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}

	}
	public void delete_to_be_approved() {

		int rs = 0;

		// SELECT文の準備
		String sql = "DELETE * FROM to_be_approved ";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			
			// st.setString(2, loginName);

			// conn.setAutoCommit(false);

			// SELECT文を実行
			rs = st.executeUpdate();
		
			// conn.commit();

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}

	}

	// ユーザー新規登録 まず承認前テーブルに登録する
	public void insert_toBeApproved(String shopName, String hashedCode, String tel, String address, String email,
			String businessHour, byte[] resizeBytes, String shopComment, String nearBy) {

//		    	ResultSet rs=null;
//		    	
		String sql = "INSERT INTO  to_be_approved(shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy)"
				+ "values(?,?,?,?,?,?,?,?,?)";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			//
			// conn.setAutoCommit(false);

			st.setString(1, shopName);

			st.setString(2, hashedCode);

			st.setString(3, tel);

			st.setString(4, address);

			st.setString(5, email);

			st.setString(6, businessHour);

			st.setBytes(7, resizeBytes);

			st.setString(8, shopComment);

			st.setString(9, nearBy);
			
		//	st.setTimestamp(10, dateTime);

			st.executeUpdate();
			System.out.println("dao"+shopName);
			// conn.commit();

		} catch (SQLException e) {
			System.out.println("例外が発生しました");
			e.printStackTrace();

		} catch (MissingResourceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
