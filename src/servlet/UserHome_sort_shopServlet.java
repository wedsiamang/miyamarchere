package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import dao.SortGoodsDao;
import model.Goods;

/**
 * Servlet implementation class UserHome_sort_shopServlet
 */
@WebServlet("/UserHome_sort_shopServlet")
public class UserHome_sort_shopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserHome_sort_shopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Goods goods=new Goods();
		GoodsDao dao = new GoodsDao();
		 List<Goods>goodsList=dao.goods_info();
		request.setAttribute("goodsList", goodsList);
		
		SortGoodsDao sdao=new SortGoodsDao();
		
		List<Goods>sortShop=sdao.goods_info_byShop();
		request.setAttribute("sortShop", sortShop);
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/userHome-sort-shop.jsp");
		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
