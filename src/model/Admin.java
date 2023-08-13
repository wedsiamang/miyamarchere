package model;

	import java.io.Serializable;

	public class Admin implements Serializable   {
		
		private int id;
		private String adminName;
		private String adminPass;
	
			
		public Admin() {
			
		}
	public Admin(String adminName,String adminPass) {
		this.adminName=adminName;
		this.adminPass=adminPass;
			
		}
		public Admin(int id,String adminName, String adminPass) {	
			this.id=id;
			this.adminName=adminName;
			this.adminPass=adminPass;
		}
		public int getId() {return id;}
		public String getAdminName() {return adminName;}
		public String getAdminPass() {return adminPass;}
		
}
