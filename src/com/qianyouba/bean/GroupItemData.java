package com.qianyouba.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
public class GroupItemData implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6879997920506528811L;
	private String id;
    private String imageUrl;
    private String summary;
    private String address;
    private String publishPId;
    private String publishPName;
    private String type;
    private String status;
    private float price;

    public GroupItemData(String imageUrl, String summary, String type, String status, float price) {
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
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
}
