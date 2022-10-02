package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CronDao;
import dao.GoodsDao;
import model.Goods;

@WebServlet("/UserHomeServlet")
//@MultipartConfig(location="/", maxFileSize=1048576)
public class UserHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		int goods_id=0;
		String shop_name=request.getParameter("shop_name");
		String kinds=request.getParameter("kinds");
		String goods_name=request.getParameter("goods_name");
		int quantity=0;
		int list_price=0;
		int selling_price=0;
		//quantity=Integer.parseInt(request.getParameter("quantity"));
	//	list_price=Integer.parseInt(request.getParameter("list_price"));
	//	selling_price=Integer.parseInt(request.getParameter("selling_price"));
		String goods_img=request.getParameter("goods_img");
		String goods_comment=request.getParameter("goods_comment");
		int discount_rate=0;
		
		String start_time=request.getParameter("start_time");
		String end_time=request.getParameter("end_time");
		String unit=request.getParameter("unit");
		Part part=null;
		
		
		GoodsDao dao = new GoodsDao();
		 List<Goods>goodsList=dao.goods_info();
		request.setAttribute("goodsList", goodsList);
		
		
		CronDao cdao=new CronDao();
		List<Goods>endgoodsList=cdao.endgoods_info();
		request.setAttribute("endgoodsList", endgoodsList);		
		
		
	//	QuickStart quickstart = new QuickStart();
	//	quickstart.Cron(goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, part, goods_comment, discount_rate, start_time, end_time, unit);
	//	quickstart.CronDelete(goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, part, goods_comment, discount_rate, start_time, end_time, unit);
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/userHome.jsp");
		dispatcher.forward(request, response);
	}
	
}
