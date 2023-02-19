package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EndGoodsDao;
import dao.GoodsDao;
import dao.ShopDao;
import model.Goods;
import model.Shop;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/IntroductionShopServlet")
public class IntroductionShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			String shopName = request.getParameter("shopName");

			ShopDao dao = new ShopDao();
			// 選択された店舗の情報を取得
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
			System.out.println("nullは許容しません");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/introductionShop.jsp");
		dispatcher.forward(request, response);
	}

}
