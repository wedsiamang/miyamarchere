package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import model.Goods;

public class EndGoodsDao {

	private final String JDBC_URL="jdbc:postgresql://ec2-52-20-166-21.compute-1.amazonaws.com:5432/ddut774g75mkbq?sslmode=require&user=cjnskunbozeqkw&password=5e10397c35da38dadce7082b9252a8c9b0a2f3ad6750aa429c22064c3e6c227d";
	   private final String DB_USER= "cjnskunbozeqkw";
	    private final String DB_PASS = "5e10397c35da38dadce7082b9252a8c9b0a2f3ad6750aa429c22064c3e6c227d";
		
	
//	private final String JDBC_URL="jdbc:postgresql://localhost:5432/miyama";
//	private final String DB_USER="postgres";
//	private final String DB_PASS="test";
	//終了テーブル一覧を取得
public List<Goods>endgoods_info(){
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Goods>endgoodsList=new ArrayList<Goods>();
		Goods eg;
		
		try {
			conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			
			String sql="SELECT * FROM endgoods";
			
			st=conn.prepareStatement(sql);
			
			
			rs=st.executeQuery();
			
				while(rs.next()) {
				
					int goods_id=rs.getInt("goods_id");
					String shop_name=rs.getString("shop_name");
					String kinds=rs.getString("kinds");
					String goods_name=rs.getString("goods_name");
					int quantity=rs.getInt("quantity");
					int list_price=rs.getInt("list_price");
					int selling_price=rs.getInt("selling_price");
					String goods_img=Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
					String goods_comment=rs.getString("goods_comment");
					int discount_rate=rs.getInt("discount_rate");
					String submit_time=rs.getString("submit_time");
					String start_time=rs.getString("start_time");
					String end_time=rs.getString("end_time");
					String unit=rs.getString("unit");
					
					
					eg=new Goods(goods_id,shop_name,kinds,goods_name,quantity,list_price,selling_price,goods_img,goods_comment,discount_rate,submit_time,start_time,end_time,unit);
						endgoodsList.add(eg);
				}return endgoodsList;
			
		}catch(SQLException e){
			e.printStackTrace();
			
			return endgoodsList;
			
		}finally {
			
			if(st !=null) {
				try {
					st.close();
					
				}catch(SQLException e) {
					
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				}catch(SQLException e) {
				}
			}
		}	
		
	}
//ログインしていない画面で各店舗毎の終了した商品を取得
public List<Goods>myendGoods_info(String shop_name){
	
	Connection conn=null;
	PreparedStatement st=null;
	ResultSet rs=null;
	
	try {
		Class.forName("org.postgresql.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	List<Goods>myGoodsList=new ArrayList<Goods>();
	Goods g;
	
	try {
		conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		
		String sql="SELECT * FROM endgoods where shop_name=?";
		
		st=conn.prepareStatement(sql);
		
		st.setString(1, shop_name);
		
		
		rs=st.executeQuery();
		
			while(rs.next()) {
				int goods_id=rs.getInt("goods_id");
				 shop_name=rs.getString("shop_name");
				String kinds=rs.getString("kinds");
				String goods_name=rs.getString("goods_name");
				int quantity=rs.getInt("quantity");
				int list_price=rs.getInt("list_price");
				int selling_price=rs.getInt("selling_price");
				String goods_img=Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goods_comment=rs.getString("goods_comment");
				int discount_rate=rs.getInt("discount_rate");
				String submit_time=rs.getString("submit_time");
				String start_time=rs.getString("start_time");
				String end_time=rs.getString("end_time");
				String unit=rs.getString("unit");
				
				
				
				
				g=new Goods(goods_id,shop_name,kinds,goods_name,quantity,list_price,selling_price,goods_img,goods_comment,discount_rate,submit_time,start_time,end_time,unit);
				myGoodsList.add(g);
				
			}return myGoodsList;
		
	}catch(SQLException e){
		e.printStackTrace();
		
		return myGoodsList;
		
	}finally {
		
		if(st !=null) {
			try {
				st.close();
				
			}catch(SQLException e) {
				
			}
		}
		if(conn !=null) {
			try {
				conn.close();
			}catch(SQLException e) {
			}
		}
	}	
	
}
//ログイン状態で自分の店の終了商品を取得
public List<Goods>myendGoods_list(String loginName){
	
	Connection conn=null;
	PreparedStatement st=null;
	ResultSet rs=null;
	
	try {
		Class.forName("org.postgresql.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	List<Goods>myGoodsList=new ArrayList<Goods>();
	Goods g;
	
	try {
		conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		
		String sql="SELECT * FROM endgoods where shop_name=?";
		
		st=conn.prepareStatement(sql);
		
		st.setString(1, loginName);
		
		
		rs=st.executeQuery();
		
			while(rs.next()) {
				int goods_id=rs.getInt("goods_id");
				String shop_name=rs.getString("shop_name");
				String kinds=rs.getString("kinds");
				String goods_name=rs.getString("goods_name");
				int quantity=rs.getInt("quantity");
				int list_price=rs.getInt("list_price");
				int selling_price=rs.getInt("selling_price");
				String goods_img=Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
				String goods_comment=rs.getString("goods_comment");
				int discount_rate=rs.getInt("discount_rate");
				String submit_time=rs.getString("submit_time");
				String start_time=rs.getString("start_time");
				String end_time=rs.getString("end_time");
				String unit=rs.getString("unit");
				
				
				
				
				g=new Goods(goods_id,shop_name,kinds,goods_name,quantity,list_price,selling_price,goods_img,goods_comment,discount_rate,submit_time,start_time,end_time,unit);
					myGoodsList.add(g);
				
			}return myGoodsList;
		
	}catch(SQLException e){
		e.printStackTrace();
		
		return myGoodsList;
		
	}finally {
		
		if(st !=null) {
			try {
				st.close();
				
			}catch(SQLException e) {
				
			}
		}
		if(conn !=null) {
			try {
				conn.close();
			}catch(SQLException e) {
			}
		}
	}	
	
}
}
