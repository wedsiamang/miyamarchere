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
import dao.ShopDao;
import model.Goods;
import model.GoodsShop;
import model.Shop;

/**
 * Servlet implementation class PreviewGoodsServlet
 */
@WebServlet("/PreviewGoodsServlet")
public class PreviewGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			// セッションスコープでログイン状態を保持
			HttpSession session = request.getSession();
			Shop login = (Shop) session.getAttribute("login");
			int goodsId = 0;
			goodsId = Integer.parseInt(request.getParameter("goodsId"));
			try {

				GoodsDao dao = new GoodsDao();
				// 選択された１つの商品情報を取得
				Goods selectGoods = dao.select_goods(goodsId);
				request.setAttribute("selectGoods", selectGoods);
				// 選択された１つの商品情報から付随する店舗情報を取得
				GoodsShop gs = dao.customerSelect_shop(goodsId);
				request.setAttribute("gs", gs);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				System.out.println("nullは許容できません");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("数字以外が入力されました");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/previewGoods.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		try {

			String shopName = request.getParameter("shopName");

			ShopDao dao = new ShopDao();
			// 選択された店舗の店舗情報を取得
			Shop introShop = dao.introduction_shop(shopName);
			request.setAttribute("introShop", introShop);

			GoodsDao gDao = new GoodsDao();
			// 選択された店舗の商品一覧を取得
			List<Goods> myGoodsList = gDao.myGoods_info(shopName);
			request.setAttribute("myGoodsList", myGoodsList);

			EndGoodsDao eDao = new EndGoodsDao();
			// 選択された店舗の終了商品一覧を取得
			List<Goods> myEndGoodsList = eDao.myEndGoods_info(shopName);
			request.setAttribute("myEndGoodsList", myEndGoodsList);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/introductionShop.jsp");
		dispatcher.forward(request, response);
	}
}
