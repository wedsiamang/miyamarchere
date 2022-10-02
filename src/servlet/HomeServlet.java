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

import dao.EndGoodsDao;
import dao.GoodsDao;
import dao.ShopDao;
import model.Goods;
import model.Shop;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		
		request.setCharacterEncoding("UTF-8");
		int shop_id=0;
		String shop_name=loginName;
		
		ShopDao dao=new ShopDao();
		Shop selectShop =dao.select_shop(loginName);
		request.setAttribute("selectShop", selectShop);
	
		Goods goods = new Goods();
		GoodsDao dao2 = new GoodsDao();
		 List<Goods>myGoodsList=dao2.myGoods_list(loginName);
		request.setAttribute("myGoodsList", myGoodsList);
		
		EndGoodsDao dao3 = new EndGoodsDao();
		List<Goods>myendgoodsList = dao3.myendGoods_list(loginName);
		request.setAttribute("myendgoodsList", myendgoodsList);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
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
		int goods_id=0;
		goods_id=Integer.parseInt(request.getParameter("goods_id"));
		System.out.println(goods_id);
		int shop_id=0;
		String shop_name=loginName;
		
		GoodsDao dao=new GoodsDao();
		Goods selectGoods=dao.select_goods(goods_id,loginName);
		Goods selectPreview=dao.select_goods_preview(goods_id, loginName);
		
		ShopDao dao2=new ShopDao();
		Shop selectShop=dao2.select_shop(loginName);
		
		
		request.setAttribute("selectGoods", selectGoods);
		request.setAttribute("selectShop",selectShop);
		request.setAttribute("selectPreview", selectPreview);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp");
		dispatcher.forward(request, response);
	}
		
	
}
