package com.ss.entity.pojo;

import java.text.SimpleDateFormat;

import com.ss.entity.User;

public class UserPojo {
	private Integer id;
	private String openId;
	private String userId;
	private String userName;
	private String password;
	private String createDate;
	private Integer audit;
	private Integer disable;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public Integer getDisable() {
		return disable;
	}
	public void setDisable(Integer disable) {
		this.disable = disable;
	}
	/**
	 * 根据User对象初始化UserPojo对象，用于构建Json
	 * @param user User用户对象
	 */
	public void initFromUser(User user) {
		this.id = user.getId();
		this.openId = user.getOpenId();
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createDate = sdf.format(user.getCreateDate());
		this.audit = user.getAudit();
		this.disable = user.getDisable();
	}
}
