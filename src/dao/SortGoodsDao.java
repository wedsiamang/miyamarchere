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

public class SortGoodsDao {

//	private final String JDBC_URL="jdbc:postgresql://ec2-52-71-23-11.compute-1.amazonaws.com:5432/d9md06chdcrlrj?sslmode=require&user=puesvfsvujbwzr&password=cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
//	   private final String DB_USER= "puesvfsvujbwzr";
//	    private final String DB_PASS = "cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
//	
	
	private final String JDBC_URL="jdbc:postgresql://localhost:5432/miyama";
	private final String DB_USER="postgres";
	private final String DB_PASS="test";
	
	
public List<Goods>goods_info_byShop(){
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Goods>goodsList=new ArrayList<Goods>();
		Goods g;
		
		try {
			conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			
			String sql="SELECT * FROM goods order by shop_name";
			
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
					
					
					
					
					g=new Goods(goods_id,shop_name,kinds,goods_name,quantity,list_price,selling_price,goods_img,goods_comment,discount_rate,submit_time,start_time,end_time,unit);
						goodsList.add(g);
					
				}return goodsList;
			
		}catch(SQLException e){
			e.printStackTrace();
			
			return goodsList;
			
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
public List<Goods>goods_info_half(){
	
	Connection conn=null;
	PreparedStatement st=null;
	ResultSet rs=null;
	
	try {
		Class.forName("org.postgresql.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	List<Goods>goodsList=new ArrayList<Goods>();
	Goods g;
	
	try {
		conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		
		String sql="SELECT * FROM goods where discount_rate>=50";
		
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
				
				
				
				
				g=new Goods(goods_id,shop_name,kinds,goods_name,quantity,list_price,selling_price,goods_img,goods_comment,discount_rate,submit_time,start_time,end_time,unit);
				goodsList.add(g);
			}return goodsList;
		
	}catch(SQLException e){
		e.printStackTrace();
		
		return goodsList;
		
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





