package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import dao.GoodsShopDao;
import dao.ShopDao;
import model.Goods;
import model.GoodsShop;
import model.Shop;

/**
 * Servlet implementation class PreviewGoodsServlet
 */
@WebServlet("/CustomerPreviewGoodsServlet")
public class CustomerPreviewGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerPreviewGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		int goods_id=0;
		goods_id=Integer.parseInt(request.getParameter("goods_id"));
		String shop_name=request.getParameter("shop_name");
		String goods_name=request.getParameter("goods_name");
		int shop_id=0;
		String goods_img=request.getParameter("goods_img");
		System.out.println(goods_id);
		
		GoodsShopDao dao=new GoodsShopDao();
		GoodsShop gs=dao.customerSelect_shop(goods_id);
		request.setAttribute("gs",gs);
		
		
		GoodsDao dao2=new GoodsDao();
		Goods selectGoods=dao2.select_goods(goods_id);
		request.setAttribute("selectGoods", selectGoods);
		
		
		ShopDao dao3=new ShopDao();
		Shop selectShop=dao3.introduction_shop(shop_name);
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/customerpreviewGoods.jsp");
		dispatcher.forward(request, response);
	}
		

}
