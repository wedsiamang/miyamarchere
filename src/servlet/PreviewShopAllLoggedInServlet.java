package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShopDao;
import model.Shop;

/**
 * Servlet implementation class PreviewShopAllServlet
 */
@WebServlet("/PreviewShopAllLoggedInServlet")
public class PreviewShopAllLoggedInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int shop_id=0;
		
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		
		ShopDao dao = new ShopDao();
		 List<Shop>shopList=dao.shop_info();
		
		request.setAttribute("shopList", shopList);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/previewShopAllLoggedIn.jsp");
		dispatcher.forward(request, response);
		
		
	}

		
}
