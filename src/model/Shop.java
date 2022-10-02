package model;

import java.io.Serializable;

public class Shop implements Serializable {

	
	private int shop_id;
	private String shop_name;
	private String password;
	private String tel;
	private String address;
	private String email;
	private String business_hour;
	private String shop_img;
//	private String map;
	private String shop_comment;
	private String nearBy;
	private String date_time;
	
	
	public Shop() {}
	
	public Shop(String shop_name,String password) {
	
	this.shop_name=shop_name;
	this.password=password;
	}
	
	public Shop(int shop_id,String shop_name,String password,String tel,String address,String email,String business_hour,String shop_img,String shop_comment,String nearBy,String date_time) {
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
	
//	public String getMap() {return map;}
//	public void setMap(String map) {this.map=map;}
	
	public String getShop_comment() {return shop_comment;}
	public void setShop_comment(String shop_comment) {this.shop_comment=shop_comment;}
	
	public String getNearBy() {return nearBy;}
	public void setNearBy(String nearBy) {this.nearBy=nearBy;}
	
	public String getDate_time() {return date_time;}
	public void setDate_time(String date_time) {this.date_time=date_time;}
	
	
	
	
	
}
