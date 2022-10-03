package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.GoodsDao;
import dao.LogDao;
import dao.ShopDao;
import model.Goods;
import model.Shop;
import pack.CheckParameter;

/**
 * Servlet implementation class AddGoodsServlet
 */
@WebServlet("/AddGoodsServlet")
@MultipartConfig(location = "/tmp", maxFileSize = 1048576)
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープでログイン状態保持
		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		String loginName = login.getShopName();

		ShopDao sDao = new ShopDao();
		// 自分の店舗情報取得
		Shop selectShop = sDao.select_shop(loginName);

		request.setAttribute("selectShop", selectShop);

		ArrayList<String> err = new ArrayList<String>();
		request.setAttribute("err", err);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/addGoods.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		String loginName = login.getShopName();

		request.setCharacterEncoding("UTF-8");

		// int goodsId = 0;
		String shopName = loginName;
		String kinds = request.getParameter("kinds");
		String goodsName = request.getParameter("goodsName");

		try {
			String charaQuantity = request.getParameter("charaQuantity");
			String charaListPrice = request.getParameter("charaListPrice");
			String charaSellingPrice = request.getParameter("charaSellingPrice");
			String goodsComment = request.getParameter("goodsComment");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String unit = request.getParameter("unit");

			GoodsDao gDao = new GoodsDao();
			ShopDao sDao = new ShopDao();
			// パラメータチェック
			CheckParameter c = new CheckParameter();
			c.requiredCheck(shopName, "店舗名");
			c.requiredCheck(kinds, "種類");
			c.requiredCheck(goodsName, "商品名");
			c.requiredCheck(goodsComment, "コメント");
			c.requiredCheck(startTime, "販売開始時間");
			c.requiredCheck(endTime, "販売終了時間");
			c.requiredCheck(unit, "単位");
			c.numCheck(charaQuantity, "個数");
			c.numCheck(charaListPrice, "定価");
			c.numCheck(charaSellingPrice, "出品価格");

			// ファイルアップロード
			try {
				Part part = request.getPart("img");
				// パラメーターチェックにエラーメッセージがあればメッセージを表示
				if (c.hasErrors()) {
					request.setAttribute("err", c.getError());
					System.out.println(c.getError());
					Shop selectShop = sDao.select_shop(loginName);
					request.setAttribute("selectShop", selectShop);

					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/addGoods.jsp");
					dispatcher.forward(request, response);

				} else {
					// 数値変換
					int quantity = Integer.parseInt(charaQuantity);
					int listPrice = Integer.parseInt(charaListPrice);
					int sellingPrice = Integer.parseInt(charaSellingPrice);

					// 登録
					gDao.insert_goods(shopName, kinds, goodsName, quantity, unit, listPrice, sellingPrice, part,
							startTime, endTime, goodsComment);
					LogDao lDao = new LogDao();
					lDao.insert_log(shopName, kinds, goodsName, quantity, unit, listPrice, sellingPrice, part,
							startTime, endTime, goodsComment);
					// フォーム表示
					Goods limitGoods = gDao.limit_goods(loginName);
					request.setAttribute("limitGoods", limitGoods);
					// プレビュー表示
					Goods limitGoodsPreview = gDao.limit_goods_preview(loginName);

					request.setAttribute("limitGoodsPreview", limitGoodsPreview);
					Shop selectShop = sDao.select_shop(loginName);
					request.setAttribute("selectShop", selectShop);

					ArrayList<String> err = new ArrayList<String>();
					request.setAttribute("err", err);

					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateLimitGoods.jsp");
					dispatcher.forward(request, response);
				}
			} catch (IllegalArgumentException e) {
				System.out.println("引数にnullは許容していません");
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			System.out.println("数値以外が入力されました");
			e.printStackTrace();

		}

	}

}