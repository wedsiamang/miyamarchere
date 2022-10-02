package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EndGoodsDao;
import dao.GoodsDao;
import dao.ShopDao;
import model.Goods;
import model.Shop;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/IntroductionShopServlet")
public class IntroductionShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int shop_id=0;
		
		String shop_name=request.getParameter("shop_name");
		ShopDao dao=new ShopDao();
		Shop introShop =dao.introduction_shop(shop_name);
		request.setAttribute("introShop", introShop);
		
		GoodsDao dao2 = new GoodsDao();
		 List<Goods>myGoodsList=dao2.myGoods_info(shop_name);
		request.setAttribute("myGoodsList", myGoodsList);
		
		EndGoodsDao dao3 = new EndGoodsDao();
		List<Goods>myendgoodsList = dao3.myendGoods_info(shop_name);
		request.setAttribute("myendgoodsList", myendgoodsList);
	
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/introductionShop.jsp");
		dispatcher.forward(request, response);
	}
	
	

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int shop_id=0;
		
		String shop_name=request.getParameter("shop_name");
		
		EndGoodsDao dao3 = new EndGoodsDao();
		List<Goods>myendgoodsList = dao3.myendGoods_info(shop_name);
		request.setAttribute("myendgoodsList", myendgoodsList);
		
		ShopDao dao=new ShopDao();
		Shop introShop =dao.introduction_shop(shop_name);
		request.setAttribute("introShop", introShop);
	
		GoodsDao dao2 = new GoodsDao();
		 List<Goods>myGoodsList=dao2.myGoods_info(shop_name);
		request.setAttribute("myGoodsList", myGoodsList);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/introductionShop.jsp");
		dispatcher.forward(request, response);
		
	}
}
