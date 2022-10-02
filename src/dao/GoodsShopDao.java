package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import model.GoodsShop;
public class GoodsShopDao {

	//private final String JDBC_URL="jdbc:postgresql://ec2-52-71-23-11.compute-1.amazonaws.com:5432/d9md06chdcrlrj?sslmode=require&user=puesvfsvujbwzr&password=cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
	//   private final String DB_USER= "puesvfsvujbwzr";
	//    private final String DB_PASS = "cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
	
	private final String JDBC_URL="jdbc:postgresql://localhost:5432/miyama";
	private final String DB_USER="postgres";
	private final String DB_PASS="test";
	
	
	  public GoodsShop customerSelect_shop(int goods_id)throws FileNotFoundException {
		  
		  	Connection conn =null;
	    	PreparedStatement st =null;
	    	ResultSet rs =null;
		  
	    	GoodsShop gs= new GoodsShop();	
		    
			try {
		        Class.forName("org.postgresql.Driver");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			//SELECT文の準備
				
		//データベース接続
			try{
				conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				
			//	conn.setAutoCommit(false);
				String sql=
						"SELECT goods_id, goods.shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, discount_rate, to_char(submit_time,'HH24時MI分')as submit_time, unit, to_char(start_time,'HH24時MI分')as start_time,to_char( end_time,'HH24時MI分')as end_time,shop.*  from goods left outer join shop on shop.shop_name=goods.shop_name  WHERE goods.goods_id=?";

				st = conn.prepareStatement(sql);
			
				
					st.setInt(1,goods_id);
			
					//SELECT文を実行
					rs=st.executeQuery();
			
					
					
				//SELECT文の結果をARRAYLIST に格納
				while(rs.next()) {
				
				
					int shop_id=rs.getInt("shop_id");
					String shop_name=rs.getString("shop_name");
					String password=rs.getString("password");
					String tel=rs.getString("tel");
					String address=rs.getString("address");
					String email=rs.getString("email");
					String business_hour=rs.getString("business_hour");
					String shop_img=Base64.getEncoder().encodeToString(rs.getBytes("shop_img"));
					//s.setMap(rs.getString("map"));
					String shop_comment=rs.getString("shop_comment");
					String nearBy=rs.getString("nearBy");
					String date_time=rs.getString("date_time");
					goods_id=rs.getInt("goods_id");
					shop_name=rs.getString("shop_name");
					String kinds=rs.getString("kinds");
					String goods_name=rs.getString("goods_name");
					int quantity=rs.getInt("quantity");
					int list_price=rs.getInt("list_price");
					int selling_price=rs.getInt("selling_price");
					String goods_img=Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
					String discount_rate=rs.getString("discount_rate");
					String goods_comment=rs.getString("goods_comment");
					String submit_time=rs.getString("submit_time");
					String start_time=rs.getString("start_time");
					String end_time=rs.getString("end_time");
					String unit=rs.getString("unit");
					
					
					System.out.println("dao gs goods_id:"+goods_id);
					
					gs=new GoodsShop(shop_id,shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy,date_time,goods_id,kinds,goods_name,quantity,list_price,selling_price,goods_img,discount_rate,goods_comment,submit_time,start_time,end_time,unit);
			
					
					
				}	return gs;
		    }catch(SQLException e) {
				System.out.println("SQLの例外が発生しました");
				e.printStackTrace();
		    }finally {
	    		
	    		if(st !=null) {
	    			try{
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
	    	}	return gs;
	  }
	  public GoodsShop customerSelect_goods(int shop_id)throws FileNotFoundException {
		  
		  	Connection conn =null;
	    	PreparedStatement st =null;
	    	ResultSet rs =null;
		  
	    	GoodsShop gs= new GoodsShop();	
		    
			try {
		        Class.forName("org.postgresql.Driver");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			//SELECT文の準備
				
		//データベース接続
			try{
				conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				
			//	conn.setAutoCommit(false);
				String sql=
						"SELECT goods_id, goods.shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, discount_rate, to_char(submit_time,'HH24時MI分')as submit_time, unit, to_char(start_time,'HH24時MI分')as start_time,to_char( end_time,'HH24時MI分')as end_time,shop.*  from goods left outer join shop on shop.shop_name=goods.shop_name WHERE goods.goods_id=?";

				st = conn.prepareStatement(sql);
			
				
					st.setInt(1,shop_id);
			
					//SELECT文を実行
					rs=st.executeQuery();
			
					
					
				//SELECT文の結果をARRAYLIST に格納
				while(rs.next()) {
				
				
					shop_id=rs.getInt("shop_id");
					String shop_name=rs.getString("shop_name");
					String password=rs.getString("password");
					String tel=rs.getString("tel");
					String address=rs.getString("address");
					String email=rs.getString("email");
					String business_hour=rs.getString("business_hour");
					String shop_img=Base64.getEncoder().encodeToString(rs.getBytes("shop_img"));
					//s.setMap(rs.getString("map"));
					String shop_comment=rs.getString("shop_comment");
					String nearBy=rs.getString("nearBy");
					String date_time=rs.getString("date_time");
					int goods_id=rs.getInt("goods_id");
					shop_name=rs.getString("shop_name");
					String kinds=rs.getString("kinds");
					String goods_name=rs.getString("goods_name");
					int quantity=rs.getInt("quantity");
					int list_price=rs.getInt("list_price");
					int selling_price=rs.getInt("selling_price");
					String goods_img=Base64.getEncoder().encodeToString(rs.getBytes("goods_img"));
					String discount_rate=rs.getString("discount_rate");
					String goods_comment=rs.getString("goods_comment");
					String submit_time=rs.getString("submit_time");
					String start_time=rs.getString("start_time");
					String end_time=rs.getString("end_time");
					String unit=rs.getString("unit");
					
					
					System.out.println("dao gs goods_id:"+goods_id);
					
					gs=new GoodsShop(shop_id,shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy,date_time,goods_id,kinds,goods_name,quantity,list_price,selling_price,goods_img,discount_rate,goods_comment,submit_time,start_time,end_time,unit);
			
					
					
				}	return gs;
		    }catch(SQLException e) {
				System.out.println("SQLの例外が発生しました");
				e.printStackTrace();
		    }finally {
	    		
	    		if(st !=null) {
	    			try{
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
	    	}	return gs;
	  }
}