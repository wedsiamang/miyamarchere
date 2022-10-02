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

import model.Shop;

public class ShopDao {

//	private final String JDBC_URL="jdbc:postgresql://ec2-52-71-23-11.compute-1.amazonaws.com:5432/d9md06chdcrlrj?sslmode=require&user=puesvfsvujbwzr&password=cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
//	   private final String DB_USER= "puesvfsvujbwzr";
//	    private final String DB_PASS = "cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
	
	private final String JDBC_URL="jdbc:postgresql://localhost:5432/miyama";
	private final String DB_USER="postgres";
	private final String DB_PASS="test";
	
	//選択した１つの商品情報取得
	  public Shop select_shop(String loginName) {
		  
		  	Connection conn =null;
	    	PreparedStatement st =null;
	    	ResultSet rs =null;
		  
	    	Shop s= new Shop();	
		    	
			try {
		        Class.forName("org.postgresql.Driver");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			//SELECT文の準備
				
		//データベース接続
			try{
				conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				System.out.println("dao:"+loginName);
			//	conn.setAutoCommit(false);
				String sql=
						"SELECT * FROM shop WHERE shop_name=?";

				st = conn.prepareStatement(sql);
			
				
					st.setString(1, loginName);
			
					//SELECT文を実行
					rs=st.executeQuery();
			
					
					
				//SELECT文の結果をARRAYLIST に格納
				while(rs.next()) {
				
				
					int shop_id=rs.getInt("shop_id");
					String shop_name=loginName;
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
					
					
					//	conn.commit();
					
			
					s=new Shop(shop_id,shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy,date_time);
					
				}	return s;
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
	    	}	return s;
	  }
	 
	
	  public Shop introduction_shop(String shop_name)throws FileNotFoundException {
		  
		  	Connection conn =null;
	    	PreparedStatement st =null;
	    	ResultSet rs =null;
		  
	    	Shop s= new Shop();	
		    	
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
						"SELECT * FROM shop WHERE shop_name=?";

				st = conn.prepareStatement(sql);
			
				st.setString(1,shop_name);
					
			
					//SELECT文を実行
					rs=st.executeQuery();
					System.out.println("introshop dao"+shop_name);
					
					
				//SELECT文の結果をARRAYLIST に格納
				while(rs.next()) {
				
				
					int shop_id=rs.getInt("shop_id");
					 shop_name=rs.getString("shop_name");
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
					
					
					//	conn.commit();
					
			
					s=new Shop(shop_id,shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy,date_time);
					
				}	return s;
		    }catch(Exception e) {
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
	    	}	return s;
	  }
	 
	

	
	public List<Shop>shop_info(){
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Shop>shopList=new ArrayList<Shop>();
		Shop s;
		
		try {
			conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			
			String sql="SELECT *  FROM shop";
			
			st=conn.prepareStatement(sql);
			
			
			
			rs=st.executeQuery();
			
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
					
					//	conn.commit();
					
					s=new Shop(shop_id,shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy,date_time);
					shopList.add(s);
					
				}return shopList;
			
		}catch(SQLException e){
			e.printStackTrace();
			
			return shopList;
			
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
	//偶数のショップ一覧
public List<Shop>shop_info_even(){
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Shop>shopList=new ArrayList<Shop>();
		Shop s;
		
		try {
			conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			
			String sql="SELECT * FROM shop where shop_id  %2 = 0";
			
			st=conn.prepareStatement(sql);
			
			
			
			rs=st.executeQuery();
			
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
					
					//	conn.commit();
					
					s=new Shop(shop_id,shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy,date_time);
					shopList.add(s);
					
					
					
				}return shopList;
			
		}catch(SQLException e){
			e.printStackTrace();
			
			return shopList;
			
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

//奇数のショップ一覧
public List<Shop>shop_info_odd(){
	
	Connection conn=null;
	PreparedStatement st=null;
	ResultSet rs=null;
	
	try {
		Class.forName("org.postgresql.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	List<Shop>shopList=new ArrayList<Shop>();
	Shop s;
	
	try {
		conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		
		String sql="SELECT * FROM shop where shop_id % 2 = 1";
		
		st=conn.prepareStatement(sql);
		
		
		
		rs=st.executeQuery();
		
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
				
				//	conn.commit();
				
				s=new Shop(shop_id,shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy,date_time);
				shopList.add(s);
				
			}return shopList;
		
	}catch(SQLException e){
		e.printStackTrace();
		
		return shopList;
		
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
public void update_shop(String loginName,String shop_name,String password,String tel,String address,String email,String business_hour,Part part,String shop_comment,String nearBy)throws FileNotFoundException{
	
	Connection conn =null;
	PreparedStatement st =null;
//	ResultSet rs=null;
//	
//	Account create=null;
	  try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			
		//	File file1 =new File(shop_img);
		//	File file2 =new File(map);
	//		FileInputStream fis1 = new FileInputStream(file1);
	//		FileInputStream fis2 = new FileInputStream(file2);
			
		//	conn.setAutoCommit(false);
			String sql = "UPDATE shop SET(password,tel,address,email,business_hour,shop_img,shop_comment,nearBy)=(?,?,?,?,?,?,?,?) where shop_name=?";
			
			st = conn.prepareStatement(sql);
				
				
				if(password!=null) {
				st.setString(1,password);
				}	
			if(tel!=null) {
				st.setString(2,tel);
			
			}
				if(address!=null) {
				st.setString(3,address);
				}
				//		
				if(email!=null) {
				st.setString(4,email);
				}
				//			
				if(business_hour!=null) {
				st.setString(5,business_hour);
				
				}			
				if(part!=null) {
				st.setBinaryStream(6,part.getInputStream());
				}
		//		if(map!=null) {
		//		st.setBinaryStream(count,fis2,file2.length());				}
				if(shop_comment!=null) {
				st.setString(7,shop_comment);
				}
				if(nearBy!=null) {
				st.setString(8,nearBy);
				}
				st.setString(9, loginName);
				
				st.executeUpdate();
				
			//	if(fis1!=null) {
			//		fis1.close();
			//	}
//				if(fis2!=null) {
//					fis2.close();
//				}
//				
			//	conn.commit();
		
		}catch(Exception e) {
			System.out.println("例外が発生しました");
			e.printStackTrace();
			
		}finally {
		
    		if(st !=null) {
    			try{
    				st.close();
    				
    			}catch(SQLException e1) {
    				e1.printStackTrace();
    			}	
    		}
    		if(conn !=null) {
    			try {
    				conn.close();
    			
    			}catch(SQLException e2) {
    		}
    		
    		}
		}
}



}
