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

import dao.GoodsDao;
import dao.ShopDao;
import dao.logDao;
import model.Goods;
import model.Shop;

/**
 * Servlet implementation class MyGoodsAllServlet
 */
@WebServlet("/MyGoodsAllServlet")
public class MyGoodsAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyGoodsAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		int goods_id=0;
		String shop_name=loginName;
		
		logDao ldao=new logDao();
		List<Goods> mylog=ldao.mylog_list(loginName);
		request.setAttribute("mylog", mylog);
		
		ShopDao dao2 = new ShopDao();
		 Shop selectShop=dao2.select_shop(loginName);
		request.setAttribute("selectShop", selectShop);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/myGoodsAll.jsp");
		dispatcher.forward(request, response);
	}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		int goods_id=0;
		String shop_name=loginName;
		
		GoodsDao dao = new GoodsDao();
		 List<Goods>myGoodsList=dao.myGoods_info(shop_name);
		request.setAttribute("myGoodsList", myGoodsList);
		
		ShopDao dao2 = new ShopDao();
		 Shop selectShop=dao2.select_shop(loginName);
		request.setAttribute("selectShop", selectShop);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/myGoodsAll.jsp");
		dispatcher.forward(request, response);
	}
	
}
