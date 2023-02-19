package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import model.Shop;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// セッションスコープでログイン状態を保持
		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		String loginName = login.getShopName();
		try {
			ShopDao dao = new ShopDao();
			// 自分の店舗情報を取得
			Shop selectShop = dao.select_shop(loginName);
			request.setAttribute("selectShop", selectShop);

			GoodsDao gDao = new GoodsDao();
			// 自分の店舗の出品一覧を取得
			List<Goods> myGoodsList = gDao.myGoods_list(loginName);
			request.setAttribute("myGoodsList", myGoodsList);

			EndGoodsDao eDao = new EndGoodsDao();
			// 自分の店舗の終了商品一覧を取得
			List<Goods> myEndGoodsList = eDao.myEndGoods_list(loginName);
			request.setAttribute("myEndGoodsList", myEndGoodsList);
		} catch (IllegalArgumentException e) {
			System.out.println("nullは許容しません");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		String loginName = login.getShopName();

		ArrayList<String> err = new ArrayList<String>();
		request.setAttribute("err", err);

		request.setCharacterEncoding("UTF-8");
		try {
			int goodsId = 0;
			goodsId = Integer.parseInt(request.getParameter("goodsId"));

			GoodsDao dao = new GoodsDao();
			// 商品情報更新のため１つの商品を選択
			Goods selectGoods = dao.select_goods(goodsId, loginName);
			// 商品情報更新画面の選択された商品のプレビュー表示を取得
			Goods selectPreview = dao.select_goods_preview(goodsId, loginName);

			request.setAttribute("selectGoods", selectGoods);
			request.setAttribute("selectPreview", selectPreview);
		} catch (NumberFormatException e) {
			System.out.println("数値以外が入力されました");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp");
		dispatcher.forward(request, response);
	}

}
