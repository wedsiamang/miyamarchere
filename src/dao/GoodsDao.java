package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
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

import javax.servlet.http.Part;

import model.Goods;
import model.GoodsShop;
import pack.Utility;

public class GoodsDao {
//	Properties properties = new Properties();
//	String path = "psql.properties";

	Utility u=new Utility();
	ResourceBundle rb=u.DbConnect();

	// 選択した１つの商品情報取得
	public Goods select_goods(int goodsId, String loginName) {

		// conn = null;
		// st = null;
		ResultSet rs = null;

		Goods g = new Goods();
		// start、endタイム表記が出力用YYYY-MM-DD HH:MI
		String sql = "SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment,"
				+ " discount_rate, to_char(submit_time,'YYYY-MM-DD HH24:MI')as submit_time, unit,"
				+ " to_char(start_time,'YYYY-MM-DD HH24:MI')as start_time, to_char(end_time,'YYYY-MM-DD HH24:MI')as end_time "
				+ " FROM goods WHERE goods_id=? AND shop_name=?";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// properties.load(s);

			// conn.setAutoCommit(false);

			st.setInt(1, goodsId);
			st.setString(2, loginName);

			// SELECT文を実行
			rs = st.executeQuery();

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				// conn.commit();
				System.out.println("dao.selectGoods_id:" + goodsId);

			}
			return g;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return g;
	}

	public Goods select_goods_preview(int goodsId, String loginName) {

		// Connection conn = null;
		// st = null;
		ResultSet rs = null;

		Goods g = new Goods();

		// SELECT文の準備
		String sql = "SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, "
				+ "discount_rate, to_char(submit_time,'YYYY-MM-DD HH24:MI')as submit_time, unit, "
				+ "to_char(start_time,'HH24時MI分')as start_time, to_char(end_time,'HH24時MI分')as end_time "
				+ " FROM goods WHERE goods_id=? AND shop_name=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// データベース接続
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// conn.setAutoCommit(false);

			st.setInt(1, goodsId);
			st.setString(2, loginName);

			// SELECT文を実行
			rs = st.executeQuery();

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				// conn.commit();
				System.out.println("dao.selectGoods_id:" + goodsId);

			}
			return g;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return g;
	}

	public Goods limit_goods(String loginName) {

		// Connection conn = null;
		// PreparedStatement st = null;
		ResultSet rs = null;

		Goods g = new Goods();

		String sql = "SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, "
				+ "discount_rate, to_char(submit_time,'YYYY-MM-DD HH24:MI')as submit_time, unit, "
				+ "to_char(start_time,'HH24時MI分')as start_time, to_char(end_time,'HH24時MI分')as end_time "
				+ " from goods where shop_name=? order by  goods_id desc limit 1";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			st.setString(1, loginName);

			// SELECT文を実行
			rs = st.executeQuery();

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				int goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				// conn.commit();
				System.out.println("dao.selectGoods_id:" + goodsId);

			}
			return g;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return g;
	}

	public Goods limit_goods_preview(String loginName) {

		ResultSet rs = null;

		Goods g = new Goods();

		// SELECT文の準備
		String sql = "SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment,"
				+ " discount_rate, to_char(submit_time,'YYYY-MM-DD HH24:MI')as submit_time, unit,"
				+ " to_char(start_time,'YYYY-MM-DD HH24:MI')as start_time, to_char(end_time,'YYYY-MM-DD HH24:MI')as end_time "
				+ " from goods where shop_name=? order by  goods_id desc limit 1";

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

				int goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				System.out.println("dao.selectGoods_id:" + goodsId);

			}
			return g;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return g;
	}

	public Goods select_goods(int goodsId) {

		// Connection conn = null;
		// PreparedStatement st = null;
		ResultSet rs = null;

		Goods g = new Goods();

		// SELECT文の準備
		String sql = "SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, "
				+ "goods_comment, discount_rate, to_char(submit_time,'HH24時MI分')as submit_time, unit,"
				+ " to_char(start_time,'HH24時MI分')as start_time,to_char( end_time,'HH24時MI分')as end_time "
				+ " FROM goods WHERE goods_id=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// データベース接続
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// conn.setAutoCommit(false);

			st.setInt(1, goodsId);

			// SELECT文を実行
			rs = st.executeQuery();

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				System.out.println("dao.selectGoods_id:" + goodsId);

			}
			return g;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return g;
	}

	public List<Goods> goods_info() {

		ResultSet rs = null;

		List<Goods> goodsList = new ArrayList<Goods>();
		Goods g;
		String sql = "SELECT * FROM goods order by goods_id desc";

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

				int goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				goodsList.add(g);
			}
			return goodsList;

		} catch (MissingResourceException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		}
		return goodsList;
	}

	public List<Goods> myGoods_info(String shopName) {

		ResultSet rs = null;

		List<Goods> myGoodsList = new ArrayList<Goods>();
		Goods g;
		String sql = "SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, "
				+ "goods_comment, discount_rate, to_char(submit_time,'YYYY:MM:DD:HH24:MI')as submit_time , unit, start_time,"
				+ " end_time  FROM goods where shop_name=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			st.setString(1, shopName);

			rs = st.executeQuery();

			while (rs.next()) {
				int goodsId = rs.getInt("goods_id");
				shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				myGoodsList.add(g);

			}
			return myGoodsList;

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return myGoodsList;
	}

	public List<Goods> myGoods_list(String loginName) {

		ResultSet rs = null;

		String sql = "SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, discount_rate, to_char(submit_time,'YYYY:MM:DD:HH24:MI')as submit_time, unit, start_time, end_time  FROM goods where shop_name=?";

		List<Goods> myGoodsList = new ArrayList<Goods>();
		Goods g;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			st.setString(1, loginName);

			rs = st.executeQuery();

			while (rs.next()) {
				int goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				myGoodsList.add(g);

			}
			return myGoodsList;

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return myGoodsList;
	}

	public void insert_goods(String shopName, String kinds, String goodsName, int quantity, String unit, int listPrice,
			int sellingPrice, Part part, String startTime, String endTime, String goodsComment)
			throws FileNotFoundException {

		String sql = "INSERT INTO goods(shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,"
				+ "start_time,end_time)values(?,?,?,?,?,?,?,?,?,to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS'),"
				+ "to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS'))";
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

			st.setString(2, kinds);

			st.setString(3, goodsName);

			st.setInt(4, quantity);

			st.setString(5, unit);

			st.setInt(6, listPrice);

			st.setInt(7, sellingPrice);

			st.setBinaryStream(8, part.getInputStream());

			st.setString(9, goodsComment);

			st.setString(10, startTime);

			st.setString(11, endTime);

			st.executeUpdate();

			// conn.commit();

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	public void update_goods(String kinds, String goodsName, int quantity, String unit, int listPrice, int sellingPrice,
			Part part, String startTime, String endTime, String goodsComment, int goodsId)
			throws FileNotFoundException {

		// int rs =0;
		// SELECT文の準備
		String sql = "UPDATE goods SET(kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)"
				+ "=(?,?,?,?,?,?,?,?,to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS'),to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS')) where goods_id=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// データベース接続
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// conn.setAutoCommit(false);

			// SELECT文を実行

			st.setString(1, kinds);

			st.setString(2, goodsName);

			st.setInt(3, quantity);

			st.setString(4, unit);

			st.setInt(5, listPrice);

			st.setInt(6, sellingPrice);

			st.setBinaryStream(7, part.getInputStream());

			st.setString(8, goodsComment);

			st.setString(9, startTime);

			st.setString(10, endTime);

			st.setInt(11, goodsId);

			st.executeUpdate();

			// conn.commit();

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (IOException e) {
		}

	}

	public void delete_goods(int goodsId) {

		int rs = 0;

		// SELECT文の準備
		String sql = "DELETE FROM goods WHERE goods_id=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			st.setInt(1, goodsId);
			// st.setString(2, loginName);

			// conn.setAutoCommit(false);

			// SELECT文を実行
			rs = st.executeUpdate();
			System.out.println("daodelete" + goodsId);
			// conn.commit();

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}

	}

	public List<Goods> goods_info_byShop() {

		// Connection conn = null;
		// PreparedStatement st = null;
		ResultSet rs = null;

		List<Goods> goodsList = new ArrayList<Goods>();
		Goods g;
		String sql = "SELECT * FROM goods order by shop_name";

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
				int goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				goodsList.add(g);

			}
			return goodsList;

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return goodsList;
	}

	public List<Goods> goods_info_half() {


		// Connection conn = null;
		// PreparedStatement st = null;
		ResultSet rs = null;

		List<Goods> goodsList = new ArrayList<Goods>();
		Goods g;
		String sql = "SELECT * FROM goods where discount_rate>=50";

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
				int goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goodsComment = rs.getString("goods_comment");
				int discountRate = rs.getInt("discount_rate");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				g = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				goodsList.add(g);
			}
			return goodsList;

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return goodsList;
	}
	public GoodsShop customerSelect_shop(int goodsId) throws FileNotFoundException {

		// Connection conn =null;
		// PreparedStatement st =null;
		ResultSet rs = null;

		GoodsShop gs = new GoodsShop();
		String sql = "SELECT goods_id, goods.shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment,"
				+ " discount_rate, to_char(submit_time,'HH24時MI分')as submit_time, unit, to_char(start_time,'HH24時MI分')"
				+ "as start_time,to_char( end_time,'HH24時MI分')as end_time,shop.*  from goods left outer join shop on shop.shop_name"
				+ "=goods.shop_name  WHERE goods.goods_id=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// conn.setAutoCommit(false);

			st.setInt(1, goodsId);

			// SELECT文を実行
			rs = st.executeQuery();
			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				int shopId = rs.getInt("shop_id");
				String shopName = rs.getString("shop_name");
				String password = rs.getString("password");
				String tel = rs.getString("tel");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String business_hour = rs.getString("business_hour");
				String shopImg = Base64.getEncoder().encodeToString(rs.getBytes("shop_img"));
				String shopComment = rs.getString("shop_comment");
				String nearBy = rs.getString("nearBy");
				String dateTime = rs.getString("date_time");
				goodsId = rs.getInt("goods_id");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				int discountRate = rs.getInt("discount_rate");
				String goodsComment = rs.getString("goods_comment");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				System.out.println("dao gs goods_id:" + goodsId);

				gs = new GoodsShop(shopId, shopName, password, tel, address, email, business_hour, shopImg, shopComment,
						nearBy, dateTime, goodsId, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						discountRate, goodsComment, submitTime, startTime, endTime, unit);

			}
			return gs;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return gs;
	}

	public GoodsShop customerSelect_goods(int shopId) throws FileNotFoundException {

		// Connection conn =null;
		// PreparedStatement st =null;
		ResultSet rs = null;

		GoodsShop gs = new GoodsShop();
		String sql = "SELECT goods_id, goods.shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, "
				+ "discount_rate, to_char(submit_time,'HH24時MI分')as submit_time, unit, to_char(start_time,'HH24時MI分')"
				+ "as start_time,to_char( end_time,'HH24時MI分')as end_time,shop.*  from goods left outer join shop on shop.shop_name"
				+ "=goods.shop_name WHERE goods.goods_id=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

			// conn.setAutoCommit(false);

			st.setInt(1, shopId);

			// SELECT文を実行
			rs = st.executeQuery();

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				shopId = rs.getInt("shop_id");
				String shopName = rs.getString("shop_name");
				String password = rs.getString("password");
				String tel = rs.getString("tel");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String business_hour = rs.getString("business_hour");
				String shopImg = Base64.getEncoder().encodeToString(rs.getBytes("shop_img"));
				String shop_comment = rs.getString("shop_comment");
				String nearBy = rs.getString("nearBy");
				String date_time = rs.getString("date_time");
				int goodsId = rs.getInt("goods_id");
				String kinds = rs.getString("kinds");
				String goodsName = rs.getString("goods_name");
				int quantity = rs.getInt("quantity");
				int listPrice = rs.getInt("list_price");
				int sellingPrice = rs.getInt("selling_price");
				String goodsImg = Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				int discountRate = rs.getInt("discount_rate");
				String goodsComment = rs.getString("goods_comment");
				String submitTime = rs.getString("submit_time");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				String unit = rs.getString("unit");

				System.out.println("dao gs goods_id:" + goodsId);

				gs = new GoodsShop(shopId, shopName, password, tel, address, email, business_hour, shopImg,
						shop_comment, nearBy, date_time, goodsId, kinds, goodsName, quantity, listPrice, sellingPrice,
						goodsImg, discountRate, goodsComment, submitTime, startTime, endTime, unit);

			}
			return gs;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
			e.printStackTrace();

		}
		return gs;
	}
	// 店舗新規登録時に商品サンプルを登録する
			public void insert_goods_sample(String shopName) {

				Goods goods = new Goods();
				String sql = "INSERT INTO goods(shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)"
						+ "values(?,'食品','サンプル','1','個','100','50','','編集してください',('2022-07-01 T 16:00:00'),('2022-07-01 T 18:00:00'))";

				try {
					Class.forName("org.postgresql.Driver");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
						rb.getString("db_pass"));

						PreparedStatement st = conn.prepareStatement(sql);) {

					st.setString(1, shopName);

					st.executeUpdate();

					// conn.commit();

				} catch (SQLException e) {
					System.out.println("SQLの例外が発生しました");
					e.printStackTrace();
				} catch (MissingResourceException e) {
					e.printStackTrace();
				}
			}

}
