package model;

import java.io.Serializable;
public class Goods implements Serializable  {
	
	private int goods_id;  //商品ID
	private String shop_name;//店舗名（SHOPテーブル共通）
	private String kinds;//食品種別
	private String goods_name;//商品名
	private int quantity;//個数
	private int list_price;//定価
	private int selling_price;//値引き後価格
	private String goods_img;//商品写真
	private String goods_comment;//出品理由
	private int discount_rate;//値引率
	private String submit_time;//登録時間
	private String start_time;//いつから
	private String end_time;//いつまで
	private String unit;//単位
	
	
	public Goods() {}

		
		
	public Goods(int goods_id,String shop_name,String kinds,String goods_name,int quantity,int list_price,int selling_price,String goods_img,String goods_comment,int discount_rate,String submit_time,String start_time,String end_time,String unit) {
		
		this.goods_id=goods_id;
		this.shop_name=shop_name;
		this.kinds=kinds;
		this.goods_name=goods_name;
		this.quantity=quantity;
		this.list_price=list_price;
		this.selling_price=selling_price;
		this.goods_img=goods_img;
		this.goods_comment=goods_comment;
		this.discount_rate=discount_rate;
		this.submit_time=submit_time;
		this.start_time=start_time;
		this.end_time=end_time;
		this.unit=unit;
		
	}
	public int getGoods_id() {return goods_id;}
	public void setGoods_id(int goods_id) {this.goods_id=goods_id;}
	public String getShop_name() {return shop_name;}
	public void setShop_name(String shop_name) {this.shop_name=shop_name;}
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
	

	public String getGoods_comment() {return goods_comment;}
	public void setGoods_comment(String goods_comment) {this.goods_comment=goods_comment;}
	public int  getDiscount_rate() {return discount_rate;}
	public void setDiscount_rate(int discount_rate) {this.discount_rate=discount_rate;}
	public String getSubmit_time() {return submit_time;}
	public void setSubmit_time(String submit_time) {this.submit_time=submit_time;}
	
	public String getStart_time() {return start_time;}
	public void setSince_when(String since_when) {this.start_time=since_when;}
	public String getEnd_time() {return end_time;}
	public void setEnd_time(String end_time) {this.end_time=end_time;}
	public String getUnit() {return unit;}
	public void setUnit(String unit) {this.unit=unit;}
	
	}
