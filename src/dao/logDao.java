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
import pack.Utility;

public class LogDao {

	Utility u=new Utility();
	ResourceBundle rb=u.DbConnect();

	public void insert_log(String shopName,String kinds,String goodsName,int quantity,String unit,int listPrice,int sellingPrice, Part part,String startTime,String endTime,String goodsComment) throws FileNotFoundException {

		String sql = "INSERT INTO log(shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,"
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

			st.setInt(4,quantity);

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
		} catch (MissingResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Goods> myLog_list(String loginName) {

		ResultSet rs = null;

		List<Goods> myGoodsList = new ArrayList<Goods>();
		Goods g;
		String sql = "SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment,"
				+ " discount_rate, to_char(submit_time,'YYYY:MM:DD:HH24:MI')as submit_time, unit, start_time, end_time "
				+ " FROM log where shop_name=?";

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
			e.printStackTrace();

			return myGoodsList;

		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return myGoodsList;
	}

	public void insert_log_sample(String shopName) {

		Goods goods = new Goods();
		String sql = "INSERT INTO log(shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,"
				+ "start_time,end_time)values(?,'食品','サンプル','1','個','100','50','','編集してください',('2022-07-01 T 16:00:00'),"
				+ "('2022-07-01 T 18:00:00'))";

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

			e.printStackTrace();
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}

	}

	public void update_log(int goodsId,String shopName,String kinds,String goodsName,int quantity,String unit,int listPrice,int sellingPrice, Part part,String startTime,String endTime,String goodsComment) throws FileNotFoundException {

		// int rs =0;
		String sql = "UPDATE log SET(kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)="
				+ "(?,?,?,?,?,?,?,?,to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS'),to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS')) "
				+ "where  shop_name=? and goods_id=?";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));

				PreparedStatement st = conn.prepareStatement(sql);) {

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

			st.setString(11, shopName);
			
			st.setInt(12, goodsId);

//				st.setString(count,loginName);

			st.executeUpdate();

			// conn.commit();

		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました。");

			e.printStackTrace();
		} catch (MissingResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
