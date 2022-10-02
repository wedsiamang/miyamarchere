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

import dao.GoodsDao;
import dao.ShopDao;
import model.Goods;
import model.Shop;

/**
 * Servlet implementation class UpdateGoodsServlet
 */
@WebServlet("/UpdateGoodsServlet")
@MultipartConfig(location="/", maxFileSize=1048576)
public class UpdateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		request.setAttribute("selectPreview", selectPreview);
		request.setAttribute("selectShop",selectShop);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp");
		dispatcher.forward(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
try {
			
		
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
		quantity=Integer.parseInt(request.getParameter("quantity"));
		list_price=Integer.parseInt(request.getParameter("list_price"));
		 selling_price=Integer.parseInt(request.getParameter("selling_price"));
		String start_time=request.getParameter("start_time");
		String end_time=request.getParameter("end_time");
		String unit=request.getParameter("unit");
		 String goods_img="";
		String goods_comment=request.getParameter("goods_comment");
			
		// discount_rate=Integer.parseInt(request.getParameter("discount_rate"));
		
		//ファイルアップロード
		Part part = request.getPart("img");
		
	
		GoodsDao dao=new GoodsDao();
		ShopDao dao2=new ShopDao();
		
		dao.update_goods(goods_id,kinds,goods_name,quantity,unit,list_price,selling_price,part,goods_comment,start_time,end_time);
		Shop selectShop=dao2.select_shop(loginName);
		
		//logDao ldao=new logDao();
	//	ldao.update_log(loginName,goods_id,kinds,goods_name,quantity,unit,list_price,selling_price,part,goods_comment,start_time,end_time);
		
		
		Goods selectGoods=dao.select_goods(goods_id,loginName);
		Goods selectPreview=dao.select_goods_preview(goods_id, loginName);
		
		request.setAttribute("selectGoods", selectGoods);
		request.setAttribute("selectShop",selectShop);
		request.setAttribute("selectPreview", selectPreview);
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp");
		dispatcher.forward(request, response);
		
	}catch(NumberFormatException e) {
		e.printStackTrace();
	}
	
	}
}
