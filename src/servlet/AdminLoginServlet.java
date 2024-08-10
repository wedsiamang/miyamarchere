package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import model.Admin;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminLogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String adminName = request.getParameter("adminName");
		String adminPass = request.getParameter("adminPass");

		AdminDao dao = new AdminDao();
		Admin ad = new Admin(adminName, adminPass);
		ad = dao.getPwd(adminName, adminPass);

		try {
			if (adminName.equals(ad.getAdminName())) {

				// if (BCrypt.checkpw(adminPass, ad.getAdminPass()))

				if (ad.getAdminPass().equals(adminPass)) {

					Admin adLogin = new Admin(adminName, adminPass);
					adLogin = ad;

					// Shop login = new Shop(shopName, password);
					// LoginLogic bo = new LoginLogic();
					// System.out.println("s" + login.getPassword());
					// System.out.println("input" + inputPassword);
					// login = bo.execute(shopName, password, inputShopName, inputPassword);

					// if (login != null) {
					// ログイン成功時 セッションスコープにユーザーIDを保存
					// HttpSession session = request.getSession();
					// session.setAttribute("login", login);

					HttpSession session = request.getSession();

					session.setAttribute("adLogin", adLogin);

					System.out.println("loginServlet dbname:" + adminName);
					response.sendRedirect("/AdminControllServlet");
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminLogin.jsp");
					dispatcher.forward(request, response);
				}

			} else {

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminLogin.jsp");
				dispatcher.forward(request, response);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("nullは許容しません");
		}
	}

}
