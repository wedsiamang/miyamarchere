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

public class logDao {

//	private final String JDBC_URL="jdbc:postgresql://ec2-52-71-23-11.compute-1.amazonaws.com:5432/d9md06chdcrlrj?sslmode=require&user=puesvfsvujbwzr&password=cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
//	   private final String DB_USER= "puesvfsvujbwzr";
//	    private final String DB_PASS = "cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
//	
	
	private final String JDBC_URL="jdbc:postgresql://localhost:5432/miyama";
	private final String DB_USER="postgres";
	private final String DB_PASS="test";
	
	
	 public void insert_log(String loginName,String shop_name,String kinds,String goods_name,int quantity,String unit,int list_price,int selling_price,Part part,String goods_comment,int discount_rate,String start_time,String end_time)throws FileNotFoundException {
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
				
		//		File file =null;
		//		FileInputStream fis=null;
				
			//	file=new File(goods_img);
			//	fis=new FileInputStream(file);
			//	conn.setAutoCommit(false);
				String sql = "INSERT INTO log(shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)values(?,?,?,?,?,?,?,?,?,to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS'),to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS'))";
				
					st= conn.prepareStatement(sql);
					String a="";
					
					
					st.setString(1, shop_name);
					
					
					if(kinds!=null) {
					st.setString(2, kinds);
					
					}else {
						st.setString(2, a);
					
					}
					if(goods_name!=null) {
					st.setString(3, goods_name);
					
					}else {
						st.setString(3, a);
						
					}
						
				
						st.setInt(4, quantity);
						
						st.setString(5,unit);
				//	}
				//	if(list_price==0) {
					st.setInt(6,list_price);
					
				//	}
					
				//	if(selling_price==0) {
					st.setInt(7, selling_price);
					
				//	}
					if(part!=null) {
						st.setBinaryStream(8,part.getInputStream());
						
					}
					if(goods_comment!=null) {
						st.setString(9,goods_comment);
						
					}
					
						st.setString(10,start_time);
					
					
						st.setString(11,end_time);
				
					
				
					st.executeUpdate();
					
				//	if(fis!=null) {
				//		fis.close();
				//	}
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
	 public List<Goods>mylog_list(String loginName){
			
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
				
				String sql="SELECT goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, discount_rate, to_char(submit_time,'YYYY:MM:DD:HH24:MI')as submit_time, unit, start_time, end_time  FROM log where shop_name=?";
				
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
	 public void insert_log_sample(String loginName) {
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
				
		//		File file =null;
		//		FileInputStream fis=null;
				
			//	file=new File(goods_img);
			//	fis=new FileInputStream(file);
			//	conn.setAutoCommit(false);
				String sql = "INSERT INTO log(shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)"
						+ "values(?,'食品','サンプル','1','個','100','50','','編集してください',('2022-07-01 T 16:00:00'),('2022-07-01 T 18:00:00'))";
				
					st= conn.prepareStatement(sql);
					
					
					
					st.setString(1, loginName);
					
					
					
					
				
					st.executeUpdate();
					
				//	if(fis!=null) {
				//		fis.close();
				//	}
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

	 
	 
	 public void update_log(String loginName,int goods_id,String kinds,String goods_name,int quantity,String unit,int list_price,int selling_price,Part part,String goods_comment,String start_time,String end_time)throws FileNotFoundException {

		  Connection conn =null;
	    	PreparedStatement st =null;
	    	//int rs =0;
		  
			try {
		        Class.forName("org.postgresql.Driver");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }	
			//データベース接続
			try {
				conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				
			//	File file =new File(goods_img);
			//	FileInputStream fis=new FileInputStream(file);
				
		//		conn.setAutoCommit(false);
				//SELECT文の準備
				String sql="UPDATE log SET(kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)=(?,?,?,?,?,?,?,?,to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS'),to_timestamp(?,'YYYY-MM-DD T HH24:MI:SS')) where  shop_name=? and goods_id=?";
				
				//SELECT文を実行
				 st= conn.prepareStatement(sql);
				 
					
				
				if(kinds!=null) {
					st.setString(1,kinds);
					
				}
				if(goods_name!=null) {
					st.setString(2, goods_name);
				
				
				}
				if(quantity!=0) {
					st.setInt(3, quantity);
				}	
				
					st.setString(4,unit);
					
				
				
				if(list_price!=0) {
					st.setInt(5,list_price);
					
				}
				if(selling_price!=0) {
					st.setInt(6,selling_price);
					
				}
				if(part!=null) {
					st.setBinaryStream(7,part.getInputStream());
					
				}
				if(goods_comment!=null) {
					st.setString(8,goods_comment);
					
				}
				
					st.setString(9,start_time);
				
				
				
					st.setString(10,end_time);
					
				
				
				
				st.setString(11, loginName);
				st.setInt(12, goods_id);
				
//				st.setString(count,loginName);

				 st.executeUpdate();
					
			//		conn.commit();
			//		if(fis!=null) {
			//			fis.close();
			//		}
			
			}catch(Exception e) {
				System.out.println("SQLの例外が発生しました。");
			
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
	    	}		
			
			
	  }

	
}





