package com.ss.entity.pojo;

import java.math.BigDecimal;
/**
 * 类别生产异常重量pojo类
 * @author Administrator
 *
 */
public class DTypeWeight {
	private String D_Type;
	private BigDecimal weight;
	
	public String getD_Type() {
		return D_Type;
	}
	public void setD_Type(String d_Type) {
		D_Type = d_Type;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
}
