package com.qianyouba.bean;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class ViewData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2680531681552444715L;
	private String id;
	private int imageUrl;
	private String summary;
	private String address;	
	private String publishPId;
	private String publishPName;
	private String publishPPhoto;
	private float price;
	
	public ViewData(int imageUrl, String summary, String publishPPhoto, float price) {
		this.imageUrl = imageUrl;
		this.summary = summary;
		this.publishPPhoto = publishPPhoto;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(int imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPublishPId() {
		return publishPId;
	}
	public void setPublishPId(String publishPId) {
		this.publishPId = publishPId;
	}
	public String getPublishPName() {
		return publishPName;
	}
	public void setPublishPName(String publishPName) {
		this.publishPName = publishPName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPublishPPhoto() {
		return publishPPhoto;
	}
	public void setPublishPPhoto(String publishPPhoto) {
		this.publishPPhoto = publishPPhoto;
	}
	
}
