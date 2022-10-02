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

import javax.servlet.http.Part;

import model.Goods;

public class CronDao {

//	private final String JDBC_URL="jdbc:postgresql://ec2-52-71-23-11.compute-1.amazonaws.com:5432/d9md06chdcrlrj?sslmode=require&user=puesvfsvujbwzr&password=cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
//	   private final String DB_USER= "puesvfsvujbwzr";
//	    private final String DB_PASS = "cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
//	
	
	private final String JDBC_URL="jdbc:postgresql://localhost:5432/miyama";
	private final String DB_USER="postgres";
	private final String DB_PASS="test";
	//エンドテーブルの一覧取得
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
//終了した商品をendgoodsテーブルに移動させ終了表示にする
public void insert_endgoods(int goods_id,String shop_name,String kinds,String goods_name,int quantity,int list_price,int selling_price,Part part,String goods_comment,int discount_rate,String start_time,String end_time,String unit)throws FileNotFoundException {
  	Connection conn =null;
	PreparedStatement st =null;
	
	Goods goods=new Goods();
  try {
        Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
	try {

		conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		
		//File file =null;
		//FileInputStream fis=null;
		
	//	file=new File(goods_img);
	//	fis=new FileInputStream(file);
	//	conn.setAutoCommit(false);
//本番		String sql = "INSERT INTO endgoods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)select goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time from goods where end_time < now() ";				
	//最小のgoods_idを１つ選択する
		String sql="INSERT INTO endgoods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)select distinct goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time from goods  where goods_id in(select min(goods_id)from goods) ";				
	//	String sql="INSERT INTO endgoods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)select distinct goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time from goods  order by goods_id  asc limit 2";			
		
		
		st= conn.prepareStatement(sql);
			
			st.executeUpdate();
			
		
		//	conn.commit();
			
	}catch(Exception e) {
		System.out.println("SQLの例外が発生しました");
		e.printStackTrace();
	}finally {
		
		}
		if(st !=null) {
			try{
				st.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		if(conn !=null) {
			try {
				conn.close();
			
			}catch(SQLException e) {
		}
		}
	}	
//endgoodsテーブルに移動した商品を同時にgoodsテーブルから削除する
public  void delete_goods() {
	  
	Connection conn =null;
	PreparedStatement st =null;
	int rs =0;
  
try {
    Class.forName("org.postgresql.Driver");
} catch (Exception e) {
    e.printStackTrace();
}
//SELECT文の準備
try{
	conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

//	conn.setAutoCommit(false);
//本番			String sql="DELETE FROM goods WHERE end_time <now() ";
	String sql="DELETE FROM goods where goods_id in(select min(goods_id)from goods) ";
	//"DELETE FROM goods where goods_id in(select goods_id from goods asc limit 2)";
	
	
	
	//データベース接続
		st = conn.prepareStatement(sql);
		
	//	conn.setAutoCommit(false);
	
		//SELECT文を実行
		 rs=st.executeUpdate();
	
	//	conn.commit();
		if(rs !=1) {
			
		}
	}catch(SQLException e) {

	e.printStackTrace();
	System.out.println("SQLの例外が発生しました。");
	
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
	}	

}
	
//endgoodsからgoodsへ商品を戻す　
public void return_goods(int goods_id,String shop_name,String kinds,String goods_name,int quantity,int list_price,int selling_price,Part part,String goods_comment,int discount_rate,String start_time,String end_time,String unit)throws FileNotFoundException {
  	Connection conn =null;
	PreparedStatement st =null;
	
	Goods goods=new Goods();
  try {
        Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
	try {

		conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		
		//File file =null;
		//FileInputStream fis=null;
		
	//	file=new File(goods_img);
	//	fis=new FileInputStream(file);
	//	conn.setAutoCommit(false);
		String sql = "INSERT INTO goods(goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)select distinct goods_id,shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time from endgoods";				
			st= conn.prepareStatement(sql);
			
			st.executeUpdate();
			
		
		//	conn.commit();
			
	}catch(Exception e) {
		System.out.println("SQLの例外が発生しました");
		e.printStackTrace();
	}finally {
		
		}
		if(st !=null) {
			try{
				st.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		if(conn !=null) {
			try {
				conn.close();
			
			}catch(SQLException e) {
		}
		}
		
	}	
//すべてテーブルを移動し終えたら、endgoodsテーブルを削除する
public  void delete_endgoods() {
	  
	Connection conn =null;
	PreparedStatement st =null;
	int rs =0;

try {
  Class.forName("org.postgresql.Driver");
} catch (Exception e) {
  e.printStackTrace();
}
//SELECT文の準備
try{
	conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);

//	conn.setAutoCommit(false);
	String sql="TRUNCATE table endgoods";
//本番	データを全削除する		"DELETE FROM endgoods WHERE end_time <now() ";
//
			
	//データベース接続
		st = conn.prepareStatement(sql);
		
	//	conn.setAutoCommit(false);
	
		//SELECT文を実行
		 rs=st.executeUpdate();
	
	//	conn.commit();
		if(rs !=1) {
			
		}
	}catch(SQLException e) {

	e.printStackTrace();
	System.out.println("SQLの例外が発生しました。");
	
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
	}	
}
}
