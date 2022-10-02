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

import dao.CronDao;
import dao.GoodsDao;
import dao.GoodsShopDao;
import dao.ShopDao;
import model.Goods;
import model.GoodsShop;
import model.Shop;

/**
 * Servlet implementation class PreviewGoodsAllServlet
 */
@WebServlet("/PreviewGoodsAllServlet")
public class PreviewGoodsAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		int goods_id=0;
		String shop_name=loginName;
		
		GoodsDao dao = new GoodsDao();
		 List<Goods>goodsList=dao.goods_info();
		request.setAttribute("goodsList", goodsList);
		
		CronDao cdao=new CronDao();
		List<Goods>endgoodsList=cdao.endgoods_info();
		request.setAttribute("endgoodsList", endgoodsList);		
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/previewGoodsAll.jsp");
		dispatcher.forward(request, response);
	}

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
		
		GoodsShopDao dao3=new GoodsShopDao();
		GoodsShop gs=dao3.customerSelect_shop(goods_id);
		request.setAttribute("gs",gs);
		
		GoodsDao dao=new GoodsDao();
		Goods selectGoods=dao.select_goods(goods_id);
		
		Shop shop=new Shop();
		ShopDao dao2=new ShopDao();
		Shop selectShop=dao2.select_shop(loginName);
		
		
		request.setAttribute("selectGoods", selectGoods);
		request.setAttribute("selectShop",selectShop);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/previewGoods.jsp");
		dispatcher.forward(request, response);	
	}

}
