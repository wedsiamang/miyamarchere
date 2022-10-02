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
 * Servlet implementation class DeleteGoodsServlet
 */
@WebServlet("/DeleteGoodsServlet")
public class DeleteGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
	
		
		request.setCharacterEncoding("UTF-8");
		
		int goods_id=0;
		int shop_id=0;
		//shop_id=Integer.parseInt(request.getParameter("shop_id"));)
		goods_id=Integer.parseInt(request.getParameter("goods_id"));
		String shop_name=loginName;
		String goods_name=request.getParameter("goods_name");
		String kinds=request.getParameter("kinds");
		int quantity=0;
		int list_price=0;
		int selling_price=0;
		int discount_rate=0;
		//quantity=Integer.parseInt(request.getParameter("quantity"));
	//	list_price=Integer.parseInt(request.getParameter("list_price"));
	//	 selling_price=Integer.parseInt(request.getParameter("selling_price"));
		String start_time=request.getParameter("start_time");
		String end_time=request.getParameter("end_time");
		String unit=request.getParameter("unit");
		 String goods_img="";
		String goods_comment=request.getParameter("goods_comment");
		
		
		
		GoodsDao dao = new GoodsDao();
		dao.delete_goods(goods_id,loginName);
		
		ShopDao dao2=new ShopDao();
		Shop selectShop =dao2.select_shop(loginName);
		request.setAttribute("selectShop", selectShop);
		
		 List<Goods>myGoodsList=dao.myGoods_list(loginName);
		request.setAttribute("myGoodsList", myGoodsList);
		
		EndGoodsDao dao3 = new EndGoodsDao();
		List<Goods>myendgoodsList = dao3.myendGoods_list(loginName);
		request.setAttribute("myendgoodsList", myendgoodsList);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
		
	}

}

