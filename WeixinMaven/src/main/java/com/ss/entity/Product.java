package com.ss.entity;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Product {
	private int productId;
	private String productName;
	private int productCategory;
	private String productDes;
	private BigDecimal productPrice;

	public String getNameInDB() {
		return "weixin_product";
	}
	/**
	 * 根据ResultSet对产品（Product）进行初始化
	 * @param rs 结果集ResultSet
	 */
	public void initFromRs(ResultSet rs) {
		try {
			setProductId(rs.getInt("productId"));
			setProductName(rs.getString("productName"));
			setProductCategory(rs.getInt("productCategory"));
			setProductDes(rs.getString("productDes"));
			setProductPrice(rs.getBigDecimal("productPrice"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductDes() {
		return productDes;
	}
	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
}
