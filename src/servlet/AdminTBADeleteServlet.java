package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ToBeApprovedDao;
import model.Admin;

/**
 * Servlet implementation class AdminTBADeleteServlet
 */
@WebServlet("/AdminTBADeleteServlet")
public class AdminTBADeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTBADeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Admin adLogin = (Admin) session.getAttribute("adLogin");

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
