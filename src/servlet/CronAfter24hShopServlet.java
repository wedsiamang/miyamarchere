package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ToBeApprovedDao;
import model.Shop;

/**
 * Servlet implementation class CronAfter24hShopServlet
 */
@WebServlet("/CronAfter24hShopServlet")
public class CronAfter24hShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CronAfter24hShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//Timestamp dateTimeValue = resultSet.getTimestamp("date_time");
		

		
		Shop s = new Shop();
		ToBeApprovedDao dao = new ToBeApprovedDao();
	//	GoodsDao gDao = new GoodsDao();
	//	Goods g = new Goods();
		byte[] resizeBytes=null;
		dao.insert_shop(s.getShopName(), s.getHashedCode(), s.getTel(), s.getAddress(), s.getEmail(), s.getBusinessHour(), resizeBytes, s.getShopComment(), s.getNearBy());		// 商品サンプルを登録
	//	dao.delete_to_be_approved();
		//	gDao.insert_goods_sample(g.getShopName());
		// ログに記録
	//	LogDao lDao = new LogDao();
	//	lDao.insert_log_sample(g.getShopName());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
