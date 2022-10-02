package model;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import javax.servlet.http.Part;

import dao.CronDao;
import it.sauronsoftware.cron4j.Scheduler;
public class QuickStart {

	//public static void main(String[] args)throws FileNotFoundException {
	
	public void Cron(int goods_id,String shop_name,String kinds,String goods_name,int quantity,int list_price,int selling_price,Part part,String goods_comment,int discount_rate,String start_time,String end_time,String unit) {
	Scheduler s = new Scheduler();
	
	s.schedule("4-19,21-39,41-59 9-23 * * *", new Runnable() {
	//本番　14時から19時の0分に1時間ごとに実行	s.schedule("0 14-19 * * *", new Runnable() {
		
		public void run() {
			LocalDateTime l=LocalDateTime.now();
				
			try {
				System.out.println(l.now());
				CronDao cdao=new CronDao();
			
				cdao.insert_endgoods(goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, part, goods_comment, discount_rate, start_time, end_time, unit);
				cdao.delete_goods();
		//		dao.insertselect_goods(goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, goods_img, goods_comment, discount_rate, start_time, end_time, unit);
				
				
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			//System.out.println("Another minute ticked away...");
		}
	});
		s.start();
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		//s.stop();
	}
	
	public void CronDelete(int goods_id,String shop_name,String kinds,String goods_name,int quantity,int list_price,int selling_price,Part part,String goods_comment,int discount_rate,String start_time,String end_time,String unit) {
		Scheduler s = new Scheduler();
		//9時から17時の毎時0分に起動
		s.schedule("3,20,40 9-23 * * *", new Runnable() {
		//本番　14時から19時の0分に1時間ごとに実行	s.schedule("0 14-19 * * *", new Runnable() {
			
			public void run() {
				LocalDateTime l=LocalDateTime.now();
					
				try {
					System.out.println(l.now());
					CronDao dao=new CronDao();
					
					dao.return_goods(goods_id, shop_name, kinds, goods_name, quantity, list_price, selling_price, part, goods_comment, discount_rate, start_time, end_time, unit);
					dao.delete_endgoods();
					
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}
				//System.out.println("Another minute ticked away...");
			}
		});
			s.start();
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			//s.stop();
		}
}
