package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import model.Admin;
import pack.Utility;

public class AdminDao {

	Utility u=new Utility();
	ResourceBundle rb=u.DbConnect();
	
	public Admin getPwd(String adminName,String adminPass) {

		// conn = null;
		// st = null;
		ResultSet rs = null;

		//List<GameRank> nameList = new ArrayList<GameRank>();
		Admin ad = new Admin();
		// start、endタイム表記が出力用YYYY-MM-DD HH:MI
		String sql = "SELECT * FROM admin";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("jdbc_url"), rb.getString("db_user"),
				rb.getString("db_pass"));
	
				PreparedStatement st = conn.prepareStatement(sql);) {

			//st.setString(1,adminName);
			

			// properties.load(s);

			// conn.setAutoCommit(false);

			// SELECT文を実行
			rs = st.executeQuery();

			// SELECT文の結果をARRAYLIST に格納
			while (rs.next()) {

				
				adminName = rs.getString("adminName");
				adminPass=rs.getString("adminPass");	
				System.out.println("daoname:" + adminName);
				ad = new Admin(adminName,adminPass);
				// conn.commit();
				

			}
			return ad;
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		} catch (MissingResourceException e) {
		}
		return ad;
	}
	
}
