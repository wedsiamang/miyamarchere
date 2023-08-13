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

import model.Goods;
import pack.Utility;

public class EndGoodsDao {

	Utility u = new Utility();
	ResourceBundle rb = u.DbConnect();

	// 終了テーブル一覧を取得
	public List<Goods> endGoods_info() {

		// Connection conn = null;
		// PreparedStatement st = null;
		ResultSet rs = null;

		List<Goods> endGoodsList = new ArrayList<Goods>();
		Goods eg;
		String sql = "SELECT * FROM endgoods";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// データベース接続
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

				eg = new Goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				endGoodsList.add(eg);
			}
			return endGoodsList;

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return endGoodsList;
	}

//ログインしていない画面で各店舗毎の終了した商品を取得
	public List<Goods> myEndGoods_info(String shopName) {

		// Connection conn = null;
		// PreparedStatement st = null;
		ResultSet rs = null;

		List<Goods> myGoodsList = new ArrayList<Goods>();
		Goods g;
		String sql = "SELECT * FROM endgoods where shop_name=?";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// データベース接続
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
			e.printStackTrace();

		} catch (MissingResourceException e) {
			e.printStackTrace();

		}
		return myGoodsList;
	}

//ログイン状態で自分の店の終了商品を取得
	public List<Goods> myEndGoods_list(String loginName) {

		// Connection conn = null;
		// PreparedStatement st = null;
		ResultSet rs = null;

		List<Goods> myGoodsList = new ArrayList<Goods>();
		Goods g;
		String sql = "SELECT * FROM endgoods where shop_name=?";
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

			rs = st.executeQuery();

			while (rs.next()) {
				int goodsId = rs.getInt("goods_id");
				String shopName = rs.getString("shop_name");
				String kinds = rs.getString("kinds");
				String goods_name = rs.getString("goods_name");
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

				g = new Goods(goodsId, shopName, kinds, goods_name, quantity, listPrice, sellingPrice, goodsImg,
						goodsComment, discountRate, submitTime, startTime, endTime, unit);
				myGoodsList.add(g);

			}
			return myGoodsList;

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return myGoodsList;
	}

	// 終了した商品をendgoodsテーブルに移動させ終了表示にする
	public void insert_endGoods(int goodsId, String shopName, String kinds, String goodsName, int quantity,
			int listPrice, int sellingPrice, byte[] resizeBytes, String goodsComment, int discountRate, String startTime,
			String endTime, String unit) throws FileNotFoundException {
		// Connection conn = null;
		// PreparedStatement st = null;
		// デモ 最小のgoods_idの商品を１つendGoodsテーブルへ移動させる
	//	String sql = "INSERT INTO endgoods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,"
	//			+ "goods_img,goods_comment,start_time,end_time)select distinct goods_id,"
	//			+ "shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img, goods_comment,start_time,"
	//			+ "end_time from goods where goods_id in(select min(goods_id)from goods) ";
		// 本番 現在時刻より前の販売終了時間の商品を全てendGoodsテーブルへ移動させる
//		 String sql = "INSERT INTO"
	//	 +"endgoods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,"
	//	 + "goods_img,goods_comment,start_time,end_time)select"
	//	 +"goods_id,shop_name,kinds,goods_name,quantity,unit,"
	//	 + "list_price,selling_price,goods_img,goods_comment,start_time,end_time from"
	//	 +"goods where end_time < clock_timestamp() ";

		// 本番 現在時刻より前の販売終了時間の商品を全てendGoodsテーブルへ移動させる
				 String sql = "INSERT INTO endgoods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)select goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time from goods where end_time <= clock_timestamp()" ;

		 
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass")); PreparedStatement st = conn.prepareStatement(sql);) {
			// conn.setAutoCommit(false);
			// 最小のgoods_idを１つ選択する
			// String sql="INSERT INTO
			// endgoods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)select
			// distinct
			// goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time
			// from goods order by goods_id asc limit 2";

			st.executeUpdate();

			// conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}

	}

	// endgoodsテーブルに移動した商品を同時にgoodsテーブルから削除する
	public void delete_goods() {

		// Connection conn = null;
		// PreparedStatement st = null;
		int rs = 0;

		// 本番
		 String sql="DELETE FROM goods WHERE end_time < clock_timestamp() ";
		// デモ
		//String sql = "DELETE FROM goods where goods_id in(select min(goods_id)from goods) ";
		// "DELETE FROM goods where goods_id in(select goods_id from goods asc limit
		// 2)";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass")); PreparedStatement st = conn.prepareStatement(sql);) {

//		conn.setAutoCommit(false);

			// データベース接続
			// st = conn.prepareStatement(sql);

			// conn.setAutoCommit(false);

			// SELECT文を実行
			rs = st.executeUpdate();

			// conn.commit();
			if (rs != 1) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
	}

	// endgoodsからgoodsへ商品を戻す
	public void return_goods(int goodsId, String shopName, String kinds, String goodsName, int quantity, int listPrice,
			int sellingPrice, String goodsImg, String goodsComment, int discountRate, String startTime, String endTime,
			String unit) throws FileNotFoundException {

		String sql = "INSERT INTO goods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,"
				+ "goods_comment,start_time,end_time)select distinct goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,"
				+ "selling_price,goods_img,goods_comment,to_timestamp(start_time,'YYYY-MM-DD T HH24:MI:SS'),to_timestamp(end_time,"
				+ "'YYYY-MM-DD T HH24:MI:SS') from endgoods";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass")); PreparedStatement st = conn.prepareStatement(sql);) {

			// conn.setAutoCommit(false);

			st.executeUpdate();

			// conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
	}

	// すべてテーブルを移動し終えたら、endgoodsテーブルを削除する
	public void delete_endGoods() {

		// Connection conn = null;
		// PreparedStatement st = null;
		int rs = 0;
		String sql = "DELETE FROM endgoods";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass")); PreparedStatement st = conn.prepareStatement(sql);) {

//		conn.setAutoCommit(false);

			//
			//

			// SELECT文を実行
			rs = st.executeUpdate();

			// conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
	}
}
