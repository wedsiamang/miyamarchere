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

import dao.ShopDao;
import model.Shop;

/**
 * Servlet implementation class PreviewShopAllServlet
 */
@WebServlet("/PreviewShopAllServlet")
public class PreviewShopAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープでログイン状態を保持
		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");

		try {
			ShopDao dao = new ShopDao();
			// 店舗一覧を取得
			List<Shop> shopList = dao.shop_info();
			request.setAttribute("shopList", shopList);
		} catch (IllegalArgumentException e) {
			System.out.println("nullは許容しません");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/previewShopAll.jsp");
		dispatcher.forward(request, response);

	}
}
