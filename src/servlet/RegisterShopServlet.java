package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.LoginDao;
import dao.logDao;
import model.Shop;

/**
 * Servlet implementation class RegisterShopServlet
 */
@WebServlet("/RegisterShopServlet")
@MultipartConfig(location="/", maxFileSize=1048576)
public class RegisterShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/registerShop.jsp");
		dispatcher.forward(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int shop_id=0;
		String shop_name =request.getParameter("shop_name");
		String password=request.getParameter("password");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String business_hour=request.getParameter("business_hour");
		String shop_img=request.getParameter("shop_img");
		
		String shop_comment=request.getParameter("shop_comment");
		String nearBy=request.getParameter("nearBy");
		String date_time=request.getParameter("date_time");	
		
		//ファイルアップロード
				Part part = request.getPart("img");
				
				
					Shop shop =new Shop();
					LoginDao dao =new LoginDao();
					dao.insert_new_shop(shop_id,shop_name,password,tel,address, email,business_hour,part,shop_comment,nearBy);
		
					dao.insert_goods_sample(shop_name);
					
					logDao ldao=new logDao();
					ldao.insert_log_sample(shop_name);
					
					RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/registerShop.jsp");
					dispatcher.forward(request, response);
	
						
	}

}
