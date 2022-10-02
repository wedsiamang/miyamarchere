package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ShopDao;
import model.Shop;

/**
 * Servlet implementation class EditShopInfoServlet
 */
@WebServlet("/EditShopInfoServlet")
@MultipartConfig(location="/", maxFileSize=1048576)
public class EditShopInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		
		ShopDao dao =new ShopDao();
		Shop select_shop=dao.select_shop(loginName);
		request.setAttribute("select_shop",select_shop);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/editShopInfo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		request.setCharacterEncoding("UTF-8");
			int shop_id=0;
			String shop_name =loginName;
			String password=request.getParameter("password");
			String tel=request.getParameter("tel");
			String address=request.getParameter("address");
			String email=request.getParameter("email");
			String business_hour=request.getParameter("business_hour");
			String shop_img=request.getParameter("shop_img");
			String map=request.getParameter("map");
			String shop_comment=request.getParameter("shop_comment");
			String nearBy=request.getParameter("nearBy");
			String date_time=request.getParameter("date_time");	
			
			//ファイルアップロード
			Part part = request.getPart("img");
			
			
				
					Shop shop =new Shop();
					ShopDao dao =new ShopDao();
					dao.update_shop(loginName,shop_name,password,tel,address, email,business_hour,part,shop_comment,nearBy);
					Shop select_shop=dao.select_shop(loginName);
					request.setAttribute("select_shop",select_shop);
					
				RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/editShopInfo.jsp");
				dispatcher.forward(request, response);
			
		}	
			
}
