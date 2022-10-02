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

import dao.SortGoodsDao;
import model.Goods;
import model.Shop;

/**
 * Servlet implementation class PreviewGoodsAllServlet
 */
@WebServlet("/PreviewGoodsAll_sort_halfServlet")
public class PreviewGoodsAll_sort_halfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewGoodsAll_sort_halfServlet() {
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
		
		
		SortGoodsDao sdao=new SortGoodsDao();
		List<Goods>sortHalf=sdao.goods_info_half();
		request.setAttribute("sortHalf", sortHalf);
		
			
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/previewGoodsAll-sort-half.jsp");
		dispatcher.forward(request, response);
	}
	

}
