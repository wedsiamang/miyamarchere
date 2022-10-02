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
@WebServlet("/UserHome_sort_halfServlet")
public class UserHome_sort_halfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		GoodsDao dao = new GoodsDao();
		 List<Goods>goodsList=dao.goods_info();
		request.setAttribute("goodsList", goodsList);
		
		SortGoodsDao sdao=new SortGoodsDao();
		
		
		List<Goods>sortHalf=sdao.goods_info_half();
		request.setAttribute("sortHalf", sortHalf);
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/userHome-sort-half.jsp");
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
