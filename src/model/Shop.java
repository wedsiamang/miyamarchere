package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Shop implements Serializable {

	
	private int shopId;
	private String shopName;
	private String password;
	private String tel;
	private String address;
	private String email;
	private String businessHour;
	private String shopImg;
	private String shopComment;
	private String nearBy;
	private Timestamp dateTime;
	private String hashedCode;
	
	
	public Shop() {}
	
	public Shop(String shopName,String password) {
	
	this.shopName=shopName;
	this.password=password;
	}
	
	public Shop(int shopId,String shopName,String password,String tel,String address,String email,String businessHour,String shopImg,String shopComment,String nearBy,Timestamp dateTime) {
		this.shopId=shopId;
		this.shopName=shopName;
		this.password=password;
		this.tel=tel;
		this.address=address;
		this.email=email;
		this.businessHour=businessHour;
		this.shopImg=shopImg;
		this.shopComment=shopComment;
		this.nearBy=nearBy;
		this.dateTime=dateTime;
	//	this.hashedCode=hashedCode;
	
	}
	public Shop(int shopId,String shopName,String password,String tel,String address,String email,String businessHour,String shopImg,String shopComment,String nearBy) {
		this.shopId=shopId;
		this.shopName=shopName;
		this.password=password;
		this.tel=tel;
		this.address=address;
		this.email=email;
		this.businessHour=businessHour;
		this.shopImg=shopImg;
		this.shopComment=shopComment;
		this.nearBy=nearBy;
	//	this.dateTime=dateTime;
	//	this.hashedCode=hashedCode;
	}
	
	public int getShopId() {return shopId;}
	public void setShopId(int shopId) {this.shopId=shopId;}
	
	public String getShopName() {return shopName;}
	public void setShopName(String shopName) {this.shopName=shopName;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password=password;}
	
	public String getTel() {return tel;}
	public void setTel(String tel) {this.tel=tel;}
	
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address=address;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email=email;}
	
	public String getBusinessHour() {return businessHour;}
	public void setBusinessHour(String businessHour) {this.businessHour=businessHour;}
	
	
	public String getShopImg() {return shopImg;}
	public void setShopImg(String shopImg) {this.shopImg=shopImg;}
		
	public String getShopComment() {return shopComment;}
	public void setShopComment(String shopComment) {this.shopComment=shopComment;}
	
	public String getNearBy() {return nearBy;}
	public void setNearBy(String nearBy) {this.nearBy=nearBy;}
	
	public Timestamp getDateTime() {return dateTime;}
	public void setDateTime(Timestamp dateTime) {this.dateTime=dateTime;}
	
	public String getHashedCode() {return hashedCode;}
	public void setHashedCode(String hashedCode) {this.hashedCode=hashedCode;}
	
	
	
	
	
}
