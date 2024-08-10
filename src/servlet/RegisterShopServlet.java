package servlet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.security.crypto.bcrypt.BCrypt;

import dao.ToBeApprovedDao;
import pack.CheckParameter;

/**
 * Servlet implementation class RegisterShopServlet
 */
@WebServlet("/RegisterShopServlet")
@MultipartConfig(location = "/tmp", maxFileSize = 1024 * 1024 * 10)
public class RegisterShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<String> err = new ArrayList<String>();
		request.setAttribute("err", err);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerShop.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String shopName = request.getParameter("shopName");
		String password = request.getParameter("password");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String businessHour = request.getParameter("businessHour");
		String shopComment = request.getParameter("shopComment");
		String nearBy = request.getParameter("nearBy");
		String shopImg = request.getParameter("shopImg");
		String dateTimeStr = request.getParameter("dateTime");
		// パラメータチェック
		CheckParameter c = new CheckParameter();
		c.requiredCheck(shopName, "店舗名");
		c.requiredCheck(password, "パスワード");
		System.out.println(shopName);
		c.telNumberCheck(tel, "電話番号");
		c.requiredCheck(address, "住所");
		c.emailCheck(email, "email");
		c.requiredCheck(businessHour, "営業時間");
		c.requiredCheck(shopComment, "コメント");
		c.requiredCheck(nearBy, "近くの建物からお店まで何分？");
		try {
			//Get the upload image file from the request
			Part filePart = request.getPart("sImg");
		//	InputStream fileInput = filePart.getInputStream();
			
			//Load the image from the input stream
		//	BufferedImage image = ImageIO.read(fileInput);
			BufferedImage inputImage = ImageIO.read(filePart.getInputStream());	
			//Save the image to a byte array output stream
		//	ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		//	ImageIO.write(image,"jpeg",byteOutput);
			
		//	 byte[] imageBytes = byteOutput.toByteArray();
			// 新しいサイズを設定
			int scaledWidth = 640;
			int scaledHeight = 360;

			// 画像をリサイズ
			Image scaledImage = inputImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
			BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = outputImage.createGraphics();
			g2d.drawImage(scaledImage, 0, 0, null);
			g2d.dispose();

			// リサイズした画像をバイト配列に変換
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(outputImage, "jpg", baos);
			byte[] resizeBytes = baos.toByteArray();
			
		//	try {
			//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//	Date parseDate = dateFormat.parse(dateTimeStr);
			//	
			//	Timestamp dateTime = new Timestamp(parseDate.getTime());
			
			
			
			

			// バイト配列をデータベースに保存 
			
			// エラーメッセージがあれば取得
			if (c.hasErrors()) {
				request.setAttribute("err", c.getError());
				System.out.println(c.getError());

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerShop.jsp");
				dispatcher.forward(request, response);

			} else {

				ToBeApprovedDao dao = new ToBeApprovedDao();
				//GoodsDao gDao = new GoodsDao();
				// パスワードをハッシュ化
				String hashedCode = BCrypt.hashpw(password, BCrypt.gensalt());
				// 登録
				dao.insert_toBeApproved(shopName, hashedCode, tel, address, email, businessHour, resizeBytes, shopComment, nearBy);
				

				ArrayList<String> err = new ArrayList<String>();
				request.setAttribute("err", err);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerShop.jsp");
				dispatcher.forward(request, response);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("nullは許容しません");
		}
		//}catch(ParseException e) {
		//	e.printStackTrace();
		//	}
	}

}
