package servlet;

import java.io.IOException;

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
import dao.ShopDao;
import model.Goods;
import model.Shop;

/**
 * Servlet implementation class UpdateGoodsServlet
 */
@WebServlet("/UpdatelimitGoodsServlet")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class UpdatelimitGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatelimitGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		request.setCharacterEncoding("UTF-8");
		int goods_id=0;
	//	goods_id=Integer.parseInt(request.getParameter("goods_id"));
		
		
		int shop_id=0;
		String shop_name=loginName;
		
		GoodsDao dao=new GoodsDao();
		Goods limitGoods=dao.limit_goods(loginName);
		Goods limitGoodsPreview=dao.limit_goods_preview(loginName);
		
		Shop shop=new Shop();
		ShopDao dao2=new ShopDao();
		Shop selectShop=dao2.select_shop(loginName);
		
		request.setAttribute("limitGoods", limitGoods);
		request.setAttribute("limitGoodsPreview", limitGoodsPreview);
		request.setAttribute("selectShop",selectShop);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/updatelimitGoods.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Shop login=(Shop)session.getAttribute("login");
		String loginName=login.getShop_name();
		
		request.setCharacterEncoding("UTF-8");
		int goods_id=0;
		int shop_id=0;
		//shop_id=Integer.parseInt(request.getParameter("shop_id"));)
		goods_id=Integer.parseInt(request.getParameter("goods_id"));
		String shop_name=loginName;
		String goods_name=request.getParameter("goods_name");
		String kinds=request.getParameter("kinds");
		int quantity=0;
		int list_price=0;
		int selling_price=0;
		int discount_rate=0;
		quantity=Integer.parseInt(request.getParameter("quantity"));
		list_price=Integer.parseInt(request.getParameter("list_price"));
		 selling_price=Integer.parseInt(request.getParameter("selling_price"));
		String start_time=request.getParameter("start_time");
		String end_time=request.getParameter("end_time");
		String unit=request.getParameter("unit");
		 String goods_img="";
		String goods_comment=request.getParameter("goods_comment");
			
	//	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	//	simpleDateFormat.format(since_when);
	//	simpleDateFormat.format(util_when);
			
		 
		
		// discount_rate=Integer.parseInt(request.getParameter("discount_rate"));
		
		//ファイルアップロード
		Part part = request.getPart("img");
		
//		long timeStamp = new Date().getTime(); //時間取得
//		
//		String fileName = "";
//		String dir = "";
//		String name= "icon"+ timeStamp + goods_id + ".img";
//		
//		if (part!=null) {
//			fileName = getFileName(part);
//
//			if(fileName!=null && fileName.length()!=0) {
//				dir =  getServletContext().getRealPath("\\pic");
//				goods_img = dir +"\\"+ name;
//				part.write(goods_img);
//				
//			}
//		}

		
		
		
		GoodsDao dao=new GoodsDao();
		ShopDao dao2=new ShopDao();
		
		dao.update_goods(goods_id,kinds,goods_name,quantity,unit,list_price,selling_price,part,goods_comment,start_time,end_time);
		
		
		Goods limitGoods=dao.limit_goods(loginName);
		Goods limitGoodsPreview=dao.limit_goods_preview(loginName);
		
		request.setAttribute("limitGoods", limitGoods);
		request.setAttribute("limitGoodsPreview", limitGoodsPreview);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/updatelimitGoods.jsp");
		dispatcher.forward(request, response);
		
			
	
//	private String getFileName(Part part) {
//        String name = null;
//        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
//            if (dispotion.trim().startsWith("filename")) {
//                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
//                name = name.substring(name.lastIndexOf("\\") + 1);
//                break;
//            }
//        }
//        return name;
//    }
	}
}
