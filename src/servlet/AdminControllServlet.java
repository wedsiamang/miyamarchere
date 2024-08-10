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

import dao.ToBeApprovedDao;
import model.Admin;
import model.Shop;

/**
 * Servlet implementation class AdminControllServlet
 */
@WebServlet("/AdminControllServlet")
public class AdminControllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// セッションスコープでログイン状態を保持
		HttpSession session = request.getSession();
		Admin adLogin = (Admin) session.getAttribute("adLogin");
		
		try {
			ToBeApprovedDao dao = new ToBeApprovedDao();
			// 自分の店舗情報を取得
			List<Shop> toBeApproved  = dao.shop_info();
			request.setAttribute("toBeApproved", toBeApproved);

			
			
		} catch (IllegalArgumentException e) {
			System.out.println("nullは許容しません");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminControll.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		try {
			int shopId = 0;
			shopId = Integer.parseInt(request.getParameter("shopId"));

			ToBeApprovedDao dao = new ToBeApprovedDao();
			dao.delete_shop(shopId);
			response.sendRedirect("/AdminControllServlet");

		} catch (NumberFormatException e) {
			System.out.println("数値以外が入力されました");
			e.printStackTrace();
		}

	}
		
	}


