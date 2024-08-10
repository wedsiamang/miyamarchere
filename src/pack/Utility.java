package pack;

import java.io.File;
import java.util.ResourceBundle;

public class Utility {

	public ResourceBundle DbConnect() {

		ResourceBundle rb = null;

		File heroku = new File("\"C:\\pleiades\\workspace\\miyama1\\src\\heroku.properties\"");
		File psql = new File("\"C:\\pleiades\\workspace\\miyama1\\src\\psql.properties\"");

	//	if (heroku.length() != 0L) {
			rb = ResourceBundle.getBundle("heroku");			
	//	} else if (psql.length() != 0L) {
	//		rb = ResourceBundle.getBundle("psql");	
//		}
		return rb;

	}
}
