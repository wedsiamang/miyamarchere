package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoodsDao;
import model.Shop;

/**
 * Servlet implementation class EndGoodsServlet
 */
@WebServlet("/EndGoodsServlet")
public class EndGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndGoodsServlet() {
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
		//終了表示にする
		HttpSession session = request.getSession();
		Shop login = (Shop) session.getAttribute("login");
		String loginName = login.getShopName();

		ArrayList<String> err = new ArrayList<String>();
		request.setAttribute("err", err);

		request.setCharacterEncoding("UTF-8");
		try {
			int goodsId = 0;
			goodsId = Integer.parseInt(request.getParameter("goodsId"));
			//String shopName = request.getParameter("shopName");

			GoodsDao dao = new GoodsDao();
			// 商品情報更新のため１つの商品を選択
			//Goods selectGoods = dao.select_goods(goodsId, loginName);
			// 商品情報更新画面の選択された商品のプレビュー表示を取得
			dao.moveToEndGoods(goodsId);
			dao.delete_goods(goodsId);
			response.sendRedirect("/HomeServlet");
			
		} catch (NumberFormatException e) {
			System.out.println("数値以外が入力されました");
			e.printStackTrace();
		}
	}

}
