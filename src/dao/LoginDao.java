package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import model.Goods;
import model.Shop;

public class LoginDao {
		
	//データベース接続に使用する情報
//	private final String JDBC_URL="jdbc:postgresql://ec2-52-71-23-11.compute-1.amazonaws.com:5432/d9md06chdcrlrj?sslmode=require&user=puesvfsvujbwzr&password=cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
//	   private final String DB_USER= "puesvfsvujbwzr";
//	    private final String DB_PASS = "cbcdc0cb41f42351290e727db1536cd0f03871daa01a85a9e82708b42ef50fed";
		
			private final String JDBC_URL="jdbc:postgresql://localhost:5432/miyama";
		    private final String DB_USER= "postgres";
		    private final String DB_PASS = "test";
	
	   //ログイン
    public Shop findByLogin(String shop_name,String password) {
    	
    	Connection conn =null;
    	PreparedStatement st =null;
    	ResultSet rs =null;
    	
    	Shop sh=null;
    	try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	try {
    		conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
    		//conn.setAutoCommit(false);
    		//SELECT文の準備
    		String sql=
    				"SELECT * FROM shop WHERE shop_name=? AND password = ?";
    		st= conn.prepareStatement(sql);
    		st.setString(1, shop_name);
    		st.setString(2, password);
    		
    		//SELECT文を実行
    		rs =st.executeQuery();
    		
    		//一致したユーザーが存在した場合そのユーザーを表すアカウントインスタンスを生成
    		while(rs.next()) {
    			//結果からデータを取得
    			
    			shop_name=shop_name;
    			password=password;
    			
    			sh = new Shop(shop_name,password);
    			//conn.commit();
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    		
    		return null;
    	}finally {
    		if(rs!=null) {
    			try {
    				rs.close();
    			}catch(SQLException e) {
    				
    			}
    		}
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
    	return sh;
    }
    //ユーザー新規登録
    public void insert_new_shop(int shop_id,String shop_name,String password,String tel,String address,String email,String business_hour,Part part,String shop_comment,String nearBy){
		
    	Connection conn =null;
    	PreparedStatement st =null;
//    	ResultSet rs=null;
//    	
//    	Account create=null;
		  try {
	            Class.forName("org.postgresql.Driver");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			try{
				conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
				
		//		File file1 =new File(shop_img);
//				File file2 =new File(map);
		//		FileInputStream fis1 = new FileInputStream(file1);
//				FileInputStream fis2 = new FileInputStream(file2);
				
			//	conn.setAutoCommit(false);
				String sql = "INSERT INTO shop(shop_name,password,tel,address,email,business_hour,shop_img,shop_comment,nearBy)"
						+ "values(?,?,?,?,?,?,?,?,?)";
				
				st = conn.prepareStatement(sql);
				String a="";	
				
				
				
				if(shop_name!=null) {
					st.setString(1,shop_name);
				}
				
					if(password!=null) {
					st.setString(2,password);
					}	
					
					if(tel!=null) {
					st.setString(3,tel);
					}
					if(address!=null) {
					st.setString(4,address);
					}
					
					if(email!=null) {
					st.setString(5,email);
					}
					
					if(business_hour!=null) {
					st.setString(6,business_hour);
					}
					
					if(part!=null) {
					st.setBinaryStream(7,part.getInputStream());
					
					}
////					if(map==null) {
////					st.setBinaryStream(count,fis2,file2.length());
////					}
					if(shop_comment!=null) {
					st.setString(8,shop_comment);
					}
					
					if(nearBy!=null) {
					st.setString(9,nearBy);
					}
					
					st.executeUpdate();
				//	if(fis1!=null) {
				//		fis1.close();
				//	}
//					if(fis2!=null) {
//						fis2.close();
//					}
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
    //ユーザー名一覧取得
    public List<String>get_shop_name(){
     	
    	Connection conn =null;
    	PreparedStatement st =null;
    	ResultSet rs =null;
    	
		try {
         Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
         e.printStackTrace();
		}
		
		List<String>shopNameList = new ArrayList<String>();
		
		//データベース接続
		try {
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		//	conn.setAutoCommit(false);
			//SELECT文の準備
			String sql=
				"SELECT shop_name FROM shop order by shop_name desc";
				st= conn.prepareStatement(sql);	
			//SELECT文を実行
				rs =st.executeQuery();
		
			//SELECT文の結果をARRAYLIST に格納
				while(rs.next()) {
			
				String shopNames=rs.getString("shop_name");
			
				shopNameList.add(shopNames);
			//	conn.commit();
				}return shopNameList;
		}catch(SQLException e){
			e.printStackTrace();
			return shopNameList;
		}finally {
    		if(rs!=null) {
    			try {
    				rs.close();
    			}catch(SQLException e) {
    				
    			}
    		}
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
    //店舗新規登録時に商品サンプルを登録する
    public void insert_goods_sample(String shop_name) {
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
			String sql = "INSERT INTO goods(shop_name,kinds,goods_name,quantity,unit,list_price,selling_price,goods_img,goods_comment,start_time,end_time)"
					+ "values(?,'食品','サンプル','1','個','100','50','','編集してください',('2022-07-01 T 16:00:00'),('2022-07-01 T 18:00:00'))";
			
				st= conn.prepareStatement(sql);
				
				
				
				st.setString(1, shop_name);
				
				
				
				
			
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

	
}

