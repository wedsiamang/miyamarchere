//package pack;
//
//import java.io.FileNotFoundException;
//import java.time.LocalDateTime;
//
//import javax.servlet.http.Part;
//
//import dao.EndGoodsDao;
//import it.sauronsoftware.cron4j.Scheduler;
//public class QuickStart {
//
//	//public static void main(String[] args)throws FileNotFoundException {
//	
//	public void Cron(int goodsId,String shopName,String kinds,String goodsName,int quantity,int listPrice,int sellingPrice,Part part,String goodsComment,int discountRate,String startTime,String endTime,String unit) {
//	Scheduler s = new Scheduler();
//	
//	s.schedule("4-19,21-39,41-59 9-23 * * *", new Runnable() {
//	//本番　14時から19時の0分に1時間ごとに実行	s.schedule("0 14-19 * * *", new Runnable() {
//		
//		public void run() {
//			LocalDateTime l=LocalDateTime.now();
//				
//			try {
//				System.out.println(l.now());
//				EndGoodsDao dao=new EndGoodsDao();
//			
//				dao.insert_endGoods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, part, goodsComment, discountRate, startTime, endTime, unit);
//				dao.delete_goods();
//		//		dao.insertselect_goods(goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, discount_rate, start_time, end_time, unit);
//				
//				
//			}catch(FileNotFoundException e) {
//				e.printStackTrace();
//			}
//			//System.out.println("Another minute ticked away...");
//		}
//	});
//		s.start();
//		
//		try {
//			Thread.sleep(1000);
//		}catch(InterruptedException e) {
//			e.printStackTrace();
//		}
//		//s.stop();
//	}
//	
//	public void CronDelete(int goodsId,String shopName,String kinds,String goodsName,int quantity,int listPrice,int sellingPrice,Part part,String goodsComment,int discountRate,String startTime,String endTime,String unit) {
//		Scheduler s = new Scheduler();
//		//9時から17時の毎時0分に起動
//		s.schedule("3,20,40 9-23 * * *", new Runnable() {
//		//本番　14時から19時の0分に1時間ごとに実行	s.schedule("0 14-19 * * *", new Runnable() {
//			
//			public void run() {
//				LocalDateTime l=LocalDateTime.now();
//					
//				try {
//					System.out.println(l.now());
//					EndGoodsDao dao=new EndGoodsDao();
//					
//					dao.return_goods(goodsId, shopName, kinds, goodsName, quantity, listPrice, sellingPrice, part, goodsComment, discountRate, startTime, endTime, unit);
//					dao.delete_endGoods();
//					
//				}catch(FileNotFoundException e) {
//					e.printStackTrace();
//				}
//				//System.out.println("Another minute ticked away...");
//			}
//		});
//			s.start();
//			
//			try {
//				Thread.sleep(1000);
//			}catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//			//s.stop();
//		}
//}
