package model;

	import java.io.Serializable;

	public class GoodsShop implements Serializable {

		
		private int shop_id;
		private String shop_name;
		private String password;
		private String tel;
		private String address;
		private String email;
		private String business_hour;
		private String shop_img;
//		private String map;
		private String shop_comment;
		private String nearBy;
		private String date_time;
		
		private int goods_id;  //商品ID
		//店舗名（SHOPテーブル共通）
		private String kinds;//食品種別
		private String goods_name;//商品名
		private int quantity;//個数
		private int list_price;//定価
		private int selling_price;//値引き後価格
		private String goods_img;//商品写真
		private String discount_rate;//値引率
		private String goods_comment;//出品理由
		private String submit_time;//登録時間
		private String start_time;//いつから
		private String end_time;//いつまで
		private String unit;//単位
		
		
		public GoodsShop() {}
		
		
		
		public GoodsShop(int shop_id,String shop_name,String password,String tel,String address,String email,String business_hour,String shop_img,String shop_comment,String nearBy,String date_time,int goods_id,String kinds,String goods_name,int quantity,int list_price,int selling_price,String goods_img,String discount_rate,String goods_comment,String submit_time,String start_time,String end_time,String unit) {
			this.shop_id=shop_id;
			this.shop_name=shop_name;
			this.password=password;
			this.tel=tel;
			this.address=address;
			this.email=email;
			this.business_hour=business_hour;
			this.shop_img=shop_img;
		//	this.map=map;
			this.shop_comment=shop_comment;
			this.nearBy=nearBy;
			this.date_time=date_time;
			
			this.goods_id=goods_id;
			this.shop_name=shop_name;
			this.kinds=kinds;
			this.goods_name=goods_name;
			this.quantity=quantity;
			this.list_price=list_price;
			this.selling_price=selling_price;
			
			this.discount_rate=discount_rate;
			this.goods_comment=goods_comment;
			this.submit_time=submit_time;
			this.start_time=start_time;
			this.end_time=end_time;
			this.unit=unit;
		
		
		}
		
		public int getShop_id() {return shop_id;}
		public void setShop_id(int shop_id) {this.shop_id=shop_id;}
		
		public String getShop_name() {return shop_name;}
		public void setShop_name(String shop_name) {this.shop_name=shop_name;}
		
		public String getPassword() {return password;}
		public void setPassword(String password) {this.password=password;}
		
		public String getTel() {return tel;}
		public void setTel(String tel) {this.tel=tel;}
		
		public String getAddress() {return address;}
		public void setAddress(String address) {this.address=address;}
		
		public String getEmail() {return email;}
		public void setEmail(String email) {this.email=email;}
		
		public String getBusiness_hour() {return business_hour;}
		public void setBusiness_hour(String business_hour) {this.business_hour=business_hour;}
		
		
		public String getShop_img() {return shop_img;}
		public void setShop_img(String shop_img) {this.shop_img=shop_img;}
		
//		public String getMap() {return map;}
//		public void setMap(String map) {this.map=map;}
		
		public String getShop_comment() {return shop_comment;}
		public void setShop_comment(String shop_comment) {this.shop_comment=shop_comment;}
		
		public String getNearBy() {return nearBy;}
		public void setNearBy(String nearBy) {this.nearBy=nearBy;}
		
		public String getDate_time() {return date_time;}
		public void setDate_time(String date_time) {this.date_time=date_time;}
		
		public int getGoods_id() {return goods_id;}
		public void setGoods_id(int goods_id) {this.goods_id=goods_id;}
		
		public String getKinds() {return kinds;}
		public void setKinds(String kinds) {this.kinds=kinds;}
		public String getGoods_name() {return goods_name;}
		public void setGoods_name(String goods_name) {this.goods_name=goods_name;}
		public int getQuantity() {return quantity;}
		public void setQuantity(int quantity) {this.quantity=quantity;}
		public int getList_price() {return list_price;}
		public void setList_price(int list_price) {this.list_price=list_price;}
		public int getSelling_price() {return selling_price;}
		public void setSelling_price(int selling_price) {this.selling_price=selling_price;}
		public String getGoods_img() {return goods_img;}
		public void setGoods_img(String goods_img) {this.goods_img=goods_img;}
		
		public String  getDiscount_rate() {return discount_rate;}
		public void setDiscount_rate(String discount_rate) {this.discount_rate=discount_rate;}
		public String getGoods_comment() {return goods_comment;}
		public void setGoods_comment(String goods_comment) {this.goods_comment=goods_comment;}
		public String getSubmit_time() {return submit_time;}
		public void setSubmit_time(String submit_time) {this.submit_time=submit_time;}
		
		public String getStart_time() {return start_time;}
		public void setStart_time(String start_time) {this.start_time=start_time;}
		public String getEnd_time() {return end_time;}
		public void setEnd_time(String end_time) {this.end_time=end_time;}
		public String getUnit() {return unit;}
		public void setUnit(String unit) {this.unit=unit;}
		
			
	}

