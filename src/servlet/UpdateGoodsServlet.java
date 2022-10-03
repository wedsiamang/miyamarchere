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
import model.Goods;
import model.Shop;
import pack.CheckParameter;

/**
 * Servlet implementation class UpdateGoodsServlet
 */
@WebServlet("/UpdateGoodsServlet")
@MultipartConfig(location = "/tmp", maxFileSize = 1048576)
public class UpdateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープでログイン状態を保持
		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		String loginName = login.getShopName();

		ArrayList<String> err = new ArrayList<String>();
		request.setAttribute("err", err);
		try {
			int goodsId = 0;
			goodsId = Integer.parseInt(request.getParameter("goodsId"));

			GoodsDao dao = new GoodsDao();
			// 選択した商品情報を取得
			Goods selectGoods = dao.select_goods(goodsId, loginName);
			// 選択した商品のプレビューを取得
			Goods selectPreview = dao.select_goods_preview(goodsId, loginName);

			request.setAttribute("selectGoods", selectGoods);
			request.setAttribute("selectPreview", selectPreview);
		} catch (NumberFormatException e) {
			System.out.println("数値以外が入力されました");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		String loginName = login.getShopName();
		try {
			request.setCharacterEncoding("UTF-8");
			int goodsId = 0;
			goodsId = Integer.parseInt(request.getParameter("goodsId"));
			String shopName = loginName;
			String goodsName = request.getParameter("goodsName");
			String kinds = request.getParameter("kinds");
			String charaQuantity = request.getParameter("charaQuantity");
			String charaListPrice = request.getParameter("charaListPrice");
			String charaSellingPrice = request.getParameter("charaSellingPrice");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String unit = request.getParameter("unit");
			String goodsComment = request.getParameter("goodsComment");

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

			try {

				GoodsDao gDao = new GoodsDao();
				LogDao lDao = new LogDao();
				// ファイルアップロード
				Part part = request.getPart("img");
				// エラーメッセージがあれば取得
				if (c.hasErrors()) {
					request.setAttribute("err", c.getError());

					Goods selectGoods = gDao.select_goods(goodsId, loginName);
					Goods selectPreview = gDao.select_goods_preview(goodsId, loginName);

					request.setAttribute("selectGoods", selectGoods);
					request.setAttribute("selectPreview", selectPreview);

					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp");
					dispatcher.forward(request, response);

				} else {
					// 数値に変換
					int quantity = Integer.parseInt(charaQuantity);
					int listPrice = Integer.parseInt(charaListPrice);
					int sellingPrice = Integer.parseInt(charaSellingPrice);

					// 商品情報更新
					gDao.update_goods(kinds, goodsName, quantity, unit, listPrice, sellingPrice, part, startTime,
							endTime, goodsComment, goodsId);

					// ldao.update_log(goodsId,shopName,kinds,goodsName,quantity,unit,listPrice,sellingPrice,part,goodsComment,startTime,endTime);
					// ログを記録
					lDao.insert_log(shopName, kinds, goodsName, quantity, unit, listPrice, sellingPrice, part,
							startTime, endTime, goodsComment);

					Goods selectGoods = gDao.select_goods(goodsId, loginName);
					Goods selectPreview = gDao.select_goods_preview(goodsId, loginName);

					request.setAttribute("selectGoods", selectGoods);
					request.setAttribute("selectPreview", selectPreview);

					ArrayList<String> err = new ArrayList<String>();
					request.setAttribute("err", err);

					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateGoods.jsp");
					dispatcher.forward(request, response);
				}
			} catch (IllegalArgumentException e) {
				System.out.println("nullは許容しません");
			}
		} catch (NumberFormatException e) {
			System.out.println("数値以外が入力されました");
			e.printStackTrace();
		}

	}
}
