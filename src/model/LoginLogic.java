package model;

import dao.LoginDao;

public class LoginLogic {
		
	
	public Shop execute(String shop_name,String password) {
		LoginDao dao =new LoginDao();
		Shop sh =dao.findByLogin(shop_name,password);
			
		if(sh != null) {
			if(sh.getPassword().equals(password)) {
				
				return sh;
			}
		}
		return null;
		
	}	
}

