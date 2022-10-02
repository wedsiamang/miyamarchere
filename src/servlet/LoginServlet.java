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
import model.LoginLogic;
import model.Shop;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShopDao dao2 = new ShopDao();
		 List<Shop>shopList=dao2.shop_info();
		request.setAttribute("shopList", shopList);
		System.out.println("loginservlet shopList:"+shopList);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int shop_id=0;
		String shop_name=request.getParameter("shop_name");
		String password=request.getParameter("password");
		
		
		ShopDao dao2 = new ShopDao();
		 List<Shop>shopList=dao2.shop_info();
		request.setAttribute("shopList", shopList);
		
		//空白か、そうではないか
		if(shop_name.equals("") || password.equals("")) {
			request.setAttribute("Msg","パスワードを入力してください");
			request.setAttribute("Msg", "店舗名とパスワードが一致しません");
		
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);	
		}else {
				//ログイン処理の実行
			Shop login= new Shop(shop_name,password);
			LoginLogic bo = new LoginLogic();
			login = bo.execute(shop_name,password);
			
			

			if(login!=null) {
				//ログイン成功時　セッションスコープにユーザーIDを保存
				HttpSession session=request.getSession();
				session.setAttribute("login", login);
				String loginName=login.getShop_name();
				System.out.println("ログインサーブレットのloginNameは"+loginName);
				request.setAttribute("Msg","ログイン成功しました");
				
					//リダイレクト
				response.sendRedirect("/miyama1/HomeServlet");
				
				}else {
					//ログイン失敗時 redirect
					request.setAttribute("Msg", "店舗名とパスワードが一致しません");
					RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					dispatcher.forward(request, response);
				}
			
		}
	}

}
