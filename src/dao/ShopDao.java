package dao;

import java.io.FileNotFoundException;
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

public class ShopDao {

	Utility u = new Utility();
	ResourceBundle rb = u.DbConnect();

	// 選択した１つの商品情報取得
	public Shop select_shop(String loginName) {

		ResultSet rs = null;

		Shop s = new Shop();
		String sql = "SELECT * FROM shop WHERE shop_name=?";
		// データベース接続
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {
			// conn.setAutoCommit(false);

			st.setString(1, loginName);

			// SELECT文を実行
			rs = st.executeQuery();

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				int shopId = rs.getInt("shop_id");
				String shopName = loginName;
				String password = rs.getString("password");
				String tel = rs.getString("tel");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String businessHour = rs.getString("business_hour");
				String shopImg = Base64.getEncoder().encodeToString(rs.getBytes("shop_img"));
				String shopComment = rs.getString("shop_comment");
				String nearBy = rs.getString("nearBy");
			//	String dateTime = rs.getString("date_time");

				// conn.commit();

				s = new Shop(shopId, shopName, password, tel, address, email, businessHour, shopImg, shopComment,
						nearBy);

			}
			return s;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return s;
	}

	public Shop introduction_shop(String shopName) throws FileNotFoundException {

		ResultSet rs = null;

		Shop s = new Shop();
		String sql = "SELECT * FROM shop WHERE shop_name=?";
		// データベース接続
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// conn.setAutoCommit(false);

			st.setString(1, shopName);

			// SELECT文を実行
			rs = st.executeQuery();
			System.out.println("introshop dao" + shopName);

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				int shopId = rs.getInt("shop_id");
				shopName = rs.getString("shop_name");
				String password = rs.getString("password");
				String tel = rs.getString("tel");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String businessHour = rs.getString("business_hour");
				String shopImg = Base64.getEncoder().encodeToString(rs.getBytes("shop_img"));
				String shopComment = rs.getString("shop_comment");
				String nearBy = rs.getString("nearBy");
			//	String dateTime = rs.getString("date_time");

				// conn.commit();

				s = new Shop(shopId, shopName, password, tel, address, email, businessHour, shopImg, shopComment,
						nearBy);

			}
			return s;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return s;
	}

	public List<Shop> shop_info() {

		ResultSet rs = null;

		List<Shop> shopList = new ArrayList<Shop>();
		Shop s;
		String sql = "SELECT *  FROM shop";
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
			//	String dateTime = rs.getString("date_time");

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

	public void update_shop(String hashedCode, String tel, String address, String email, String businessHour, byte[] imageBytes,
			String shopComment, String nearBy, String loginName) throws FileNotFoundException {

//	ResultSet rs=null;
		String sql = "UPDATE shop SET(password,tel,address,email,business_hour,shop_img,shop_comment,nearBy)=(?,?,?,?,?,?,?,?)"
				+ " where shop_name=?";

//	Account create=null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// conn.setAutoCommit(false);

			st.setString(1, hashedCode);

			st.setString(2, tel);

			st.setString(3, address);

			st.setString(4, email);

			st.setString(5, businessHour);

			st.setBytes(6, imageBytes);

			st.setString(7, shopComment);

			st.setString(8, nearBy);

			st.setString(9, loginName);

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

	// ログイン
	public Shop getPwd(String shopName, String password, String inputShopName) {

		ResultSet rs = null;
		Shop s = new Shop();

		String sql = "SELECT shop_name,password FROM shop where shop_name=?";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			st.setString(1, inputShopName);
			// SELECT文を実行
			rs = st.executeQuery();

			while (rs.next()) {

				shopName = rs.getString("shop_name");
				password = rs.getString("password");
				s = new Shop(shopName, password);
				// conn.commit();
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return s;
	}

	// ユーザー新規登録
	public void insert_new_shop(String shopName, String hashedCode, String tel, String address, String email,
			String businessHour, byte[] imageBytes, String shopComment, String nearBy) {

//	    	ResultSet rs=null;
//	    	
		String sql = "INSERT INTO shop(shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy)"
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

			st.setBytes(7, imageBytes);

			st.setString(8, shopComment);

			st.setString(9, nearBy);

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

	public List<String> get_shop_name() {

		ResultSet rs = null;

		List<String> shopNameList = new ArrayList<String>();
		String sql = "SELECT shop_name FROM shop order by shop_name desc";
		// データベース接続

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// SELECT文を実行
			rs = st.executeQuery();

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				String shopNames = rs.getString("shop_name");

				shopNameList.add(shopNames);
				// conn.commit();
			}
			return shopNameList;
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return shopNameList;
	}

}
