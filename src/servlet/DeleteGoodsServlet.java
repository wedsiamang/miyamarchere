package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoodsDao;
import model.Shop;

/**
 * Servlet implementation class DeleteGoodsServlet
 */
@WebServlet("/DeleteGoodsServlet")
public class DeleteGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");

		request.setCharacterEncoding("UTF-8");
		try {
			int goodsId = 0;
			goodsId = Integer.parseInt(request.getParameter("goodsId"));

			GoodsDao dao = new GoodsDao();
			dao.delete_goods(goodsId);
			response.sendRedirect("/HomeServlet");

		} catch (NumberFormatException e) {
			System.out.println("数値以外が入力されました");
			e.printStackTrace();
		}

	}

}
