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

import org.springframework.security.crypto.bcrypt.BCrypt;

import dao.ShopDao;
import model.Shop;
import pack.CheckParameter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ShopDao sDao = new ShopDao();
		List<Shop> shopList = sDao.shop_info();
		request.setAttribute("shopList", shopList);

		ArrayList<String> err = new ArrayList<String>();
		request.setAttribute("err", err);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String shopName = request.getParameter("shopName");
		String inputShopName = request.getParameter("inputShopName");
		String password = request.getParameter("password");
		String inputPassword = request.getParameter("inputPassword");

		ShopDao sDao = new ShopDao();
		// 店舗一覧を取得
		List<Shop> shopList = sDao.shop_info();
		request.setAttribute("shopList", shopList);

		// パラメーターチェック
		CheckParameter c = new CheckParameter();
		// c.requiredCheck(inputShopName, "店舗名");
		c.requiredCheck(inputPassword, "パスワード");
		c.lengthCheck(inputPassword, "パスワード");
		try {
			// エラーメッセージを取得
			if (c.hasErrors()) {
				request.setAttribute("err", c.getError());
				System.out.println(c.getError());

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);

			} // 店舗名が未選択の場合
			if (inputShopName.equals("選択してください")) {
				// メッセージを表示
				request.setAttribute("Msg", "店舗名を選択してください");

				ArrayList<String> err = new ArrayList<String>();
				request.setAttribute("err", err);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			} else {

				// Bcrypt ログイン処理の実行
				// 入力された店舗名とデータベースを照合し、一致した店舗を選択し、その店舗名とパスワードをデータベースから取得する
				Shop s = sDao.getPwd(shopName, password, inputShopName);

				// データベースでハッシュ化されているパスワードと入力されたパスワードを照合
				if (BCrypt.checkpw(inputPassword, s.getPassword())) {
					// Shopクラスのインスタンスをloginに代入
					Shop login = new Shop(shopName, password);
					// loginにパスワード照合結果を代入
					login = s;
					// ログイン成功時 セッションスコープに保存
					HttpSession session = request.getSession();
					session.setAttribute("login", login);
					String loginName = login.getShopName();
					System.out.println("ログインサーブレットのloginNameは" + loginName);

					// リダイレクト
					response.sendRedirect("/HomeServlet");

				} else {
					// ログイン失敗時 エラーメッセージを表示
					request.setAttribute("Msg", "店舗名とパスワードが一致しません");
					request.setAttribute("err", c.getError());

					ArrayList<String> err = new ArrayList<String>();
					request.setAttribute("err", err);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					dispatcher.forward(request, response);
				}

			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("nullは許容しません");
		}
	}

}
