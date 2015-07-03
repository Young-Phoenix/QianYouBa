package com.qianyouba.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String avatarUrl = "";
	private int created;
	private String name = "";
	private String nickName = "";
	private int onlineStatus;
	private int roleStatus;
	private String token = "";
	private int updated;
	private String userId = null;

	public User() {
	}

	public User(String paramString) {
		this.userId = paramString;
	}

	public User(String avatarUrl, int created, String name, String nickName,
			int onlineStatus, int roleStatus, String token, int updated,
			String userId) {
		super();
		this.avatarUrl = avatarUrl;
		this.created = created;
		this.name = name;
		this.nickName = nickName;
		this.onlineStatus = onlineStatus;
		this.roleStatus = roleStatus;
		this.token = token;
		this.updated = updated;
		this.userId = userId;
	}

	public String getAvatarUrl() {
		return this.avatarUrl;
	}

	public int getCreated() {
		return this.created;
	}

	public String getName() {
		return this.name;
	}

	public String getNickName() {
		return this.nickName;
	}

	public int getOnlineStatus() {
		return this.onlineStatus;
	}

	public int getRoleStatus() {
		return this.roleStatus;
	}

	public String getToken() {
		return this.token;
	}

	public int getUpdated() {
		return this.updated;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setAvatarUrl(String paramString) {
		this.avatarUrl = paramString;
	}

	public void setCreated(int paramInt) {
		this.created = paramInt;
	}

	public void setName(String paramString) {
		this.name = paramString;
	}

	public void setNickName(String paramString) {
		this.nickName = paramString;
	}

	public void setOnlineStatus(int paramInt) {
		this.onlineStatus = paramInt;
	}

	public void setRoleStatus(int paramInt) {
		this.roleStatus = paramInt;
	}

	public void setToken(String paramString) {
		this.token = paramString;
	}

	public void setUpdated(int paramInt) {
		this.updated = paramInt;
	}

	public void setUserId(String paramString) {
		this.userId = paramString;
	}
}
