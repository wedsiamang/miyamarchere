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

import dao.LogDao;
import dao.ShopDao;
import model.Goods;
import model.Shop;

/**
 * Servlet implementation class MyGoodsAllServlet
 */
@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープでログイン状態を保持
		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		String loginName = login.getShopName();

		try {
			LogDao lDao = new LogDao();
			// ログインしている自分の店舗のログを取得
			List<Goods> myLog = lDao.myLog_list(loginName);
			request.setAttribute("myLog", myLog);

			ShopDao sDao = new ShopDao();
			// ログインしている自分の店舗情報を取得
			Shop selectShop = sDao.select_shop(loginName);
			request.setAttribute("selectShop", selectShop);
		} catch (IllegalArgumentException e) {
			System.out.println("nullは許容しません");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/log.jsp");
		dispatcher.forward(request, response);
	}

}
