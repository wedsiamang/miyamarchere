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

import dao.EndGoodsDao;
import dao.GoodsDao;
import model.Goods;
import model.Shop;

@WebServlet("/PreviewGoodsAllServlet")
//@MultipartConfig(location="/", maxFileSize=1048576)
public class PreviewGoodsAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープでログイン状態を保持
		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");

		request.setCharacterEncoding("UTF-8");
		// ソート分岐パラメーター
		String neo = request.getParameter("neo");
		String shop = request.getParameter("shop");
		String half = request.getParameter("half");

		GoodsDao dao = new GoodsDao();
		EndGoodsDao eDao = new EndGoodsDao();

		// 新着順
		if (neo != null) {
			// 商品一覧を取得
			List<Goods> goodsList = dao.goods_info();
			request.setAttribute("goodsList", goodsList);
			// 終了商品一覧を取得
			List<Goods> endGoodsList = eDao.endGoods_info();
			request.setAttribute("endGoodsList", endGoodsList);
			//
			request.setAttribute(neo, "neo");
			// 店舗別
		} else if (shop != null) {
			// 商品一覧w店舗別に取得
			List<Goods> sortShop = dao.goods_info_byShop();
			request.setAttribute("sortShop", sortShop);
			request.setAttribute(shop, "shop");

			// 半額以下
		} else if (half != null) {
			// 商品一覧を半額以下のみ取得
			List<Goods> sortHalf = dao.goods_info_half();
			request.setAttribute("sortHalf", sortHalf);
			request.setAttribute(half, "half");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/previewGoodsAll.jsp");
		dispatcher.forward(request, response);

	}

}
