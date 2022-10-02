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
import dao.logDao;
import model.Goods;
import model.Shop;
/**
 * Servlet implementation class AddGoodsServlet
 */
@WebServlet("/AddGoodsServlet")
@MultipartConfig(location="/", maxFileSize=1048576)
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		int goods_id=0;
		
		
		GoodsDao dao=new GoodsDao();
		Goods limitGoods=dao.limit_goods(loginName);
		
		
		ShopDao dao2=new ShopDao();
		Shop selectShop=dao2.select_shop(loginName);
		
		
		request.setAttribute("limitGoods", limitGoods);
		request.setAttribute("selectShop",selectShop);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/addGoods.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		request.setCharacterEncoding("UTF-8");
		int goods_id=0;
		String shop_name=loginName;
		String kinds=request.getParameter("kinds");
		String goods_name=request.getParameter("goods_name");
	//	int quantity=0;
	//	int list_price=0;
	//	int selling_price=0;
		try {
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			int list_price=Integer.parseInt(request.getParameter("list_price"));
			int selling_price=Integer.parseInt(request.getParameter("selling_price"));
			String goods_img= "";
			String goods_comment=request.getParameter("goods_comment");
			int discount_rate=0;
			String start_time=request.getParameter("start_time");
			String end_time=request.getParameter("end_time");
			String unit=request.getParameter("unit");
		
			System.out.println("addGoodsのrequest"+request);
			//ファイルアップロード
			Part part = request.getPart("img");
			
		
				GoodsDao dao = new GoodsDao();
				dao.insert_goods(loginName,shop_name, kinds, goods_name,quantity,unit, list_price, selling_price,part, goods_comment,discount_rate,start_time,end_time);
				Goods limitGoodsPreview=dao.limit_goods_preview(loginName);
				Goods limitGoods=dao.limit_goods(loginName);
				request.setAttribute("limitGoods", limitGoods);
				request.setAttribute("limitGoodsPreview", limitGoodsPreview);
				
				
				ShopDao dao2=new ShopDao();
				Shop selectShop=dao2.select_shop(loginName);
				request.setAttribute("selectShop", selectShop);
				
				logDao ldao=new logDao();
				ldao.insert_log(loginName, shop_name, kinds, goods_name, quantity, unit, list_price, selling_price, part, goods_comment, discount_rate, start_time, end_time);
				
				
				RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/updatelimitGoods.jsp");
				dispatcher.forward(request, response);
			
		}catch(NumberFormatException e) {
			System.out.println("数値以外が入力されました");
			e.printStackTrace();
		
		
		}

	}
		
}
	
	



