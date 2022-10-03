package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.EndGoodsDao;
import model.Goods;

/**
 * Servlet implementation class CronStartServlet
 */
@WebServlet("/CronDeleteServlet")
public class CronDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		EndGoodsDao eDao = new EndGoodsDao();
		Part part = null;
		Goods g = new Goods();

		// endgoodsテーブルからgoodsテーブルにデータを戻す
		eDao.return_goods(g.getGoodsId(), g.getShopName(), g.getKinds(), g.getGoodsName(), g.getQuantity(),
				g.getListPrice(), g.getSellingPrice(), part, g.getGoodsComment(), g.getDiscountRate(), g.getStartTime(),
				g.getEndTime(), g.getUnit());
		// endgoodsテーブルを全削除する
		eDao.delete_endGoods();

	}

}
