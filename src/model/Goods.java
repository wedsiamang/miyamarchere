package model;

import java.io.Serializable;
public class Goods implements Serializable  {
	
	private int goodsId;  //商品ID
	private String shopName;//店舗名（SHOPテーブル共通）
	private String kinds;//食品種別
	private String goodsName;//商品名
	private int quantity;//個数
	private int listPrice;//定価
	private int sellingPrice;//値引き後価格
	private String goodsImg;//商品写真
	private String goodsComment;//出品理由
	private int discountRate;//値引率
	private String submitTime;//登録時間
	private String startTime;//いつから
	private String endTime;//いつまで
	private String unit;//単位
	
	
	public Goods() {}

		
		
	public Goods(int goodsId,String shopName,String kinds,String goodsName,int quantity,int listPrice,int sellingPrice,String goodsImg,String goodsComment,int discountRate,String submitTime,String startTime,String endTime,String unit) {
		
		this.goodsId=goodsId;
		this.shopName=shopName;
		this.kinds=kinds;
		this.goodsName=goodsName;
		this.quantity=quantity;
		this.listPrice=listPrice;
		this.sellingPrice=sellingPrice;
		this.goodsImg=goodsImg;
		this.goodsComment=goodsComment;
		this.discountRate=discountRate;
		this.submitTime=submitTime;
		this.startTime=startTime;
		this.endTime=endTime;
		this.unit=unit;
		
	}
	public int getGoodsId() {return goodsId;}
	public void setGoodsId(int goodsId) {this.goodsId=goodsId;}
	public String getShopName() {return shopName;}
	public void setShopName(String shopName) {this.shopName=shopName;}
	public String getKinds() {return kinds;}
	public void setKinds(String kinds) {this.kinds=kinds;}
	public String getGoodsName() {return goodsName;}
	public void setGoodsName(String goodsName) {this.goodsName=goodsName;}
	public int getQuantity() {return quantity;}
	public void setQuantity(int quantity) {this.quantity=quantity;}
	public int getListPrice() {return listPrice;}
	public void setListPrice(int listPrice) {this.listPrice=listPrice;}
	public int getSellingPrice() {return sellingPrice;}
	public void setSellingPrice(int sellingPrice) {this.sellingPrice=sellingPrice;}
	public String getGoodsImg() {return goodsImg;}
	public void setGoodsImg(String goods_img) {this.goodsImg=goodsImg;}
	

	public String getGoodsComment() {return goodsComment;}
	public void setGoodsComment(String goodsComment) {this.goodsComment=goodsComment;}
	public int  getDiscountRate() {return discountRate;}
	public void setDiscountRate(int discountRate) {this.discountRate=discountRate;}
	public String getSubmitTime() {return submitTime;}
	public void setSubmitTime(String submitTime) {this.submitTime=submitTime;}
	
	public String getStartTime() {return startTime;}
	public void setStartTime(String startTime) {this.startTime=startTime;}
	public String getEndTime() {return endTime;}
	public void setEndTime(String endTime) {this.endTime=endTime;}
	public String getUnit() {return unit;}
	public void setUnit(String unit) {this.unit=unit;}
	
	}
