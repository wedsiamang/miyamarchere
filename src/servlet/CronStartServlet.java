package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EndGoodsDao;
import model.Goods;

/**
 * Servlet implementation class CronStartServlet
 */
@WebServlet("/CronStartServlet")
public class CronStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		EndGoodsDao eDao = new EndGoodsDao();
		//Part part = null;
		
		Goods g = new Goods();
		// 登録
		eDao.insert_endGoods(g.getGoodsId(), g.getShopName(), g.getKinds(), g.getGoodsName(), g.getQuantity(),
				g.getListPrice(), g.getSellingPrice(), g.getGoodsImg(), g.getGoodsComment(), g.getDiscountRate(), g.getStartTime(),
				g.getEndTime(), g.getUnit());
		// アクティブカードを削除
		eDao.delete_goods();
	}
}
