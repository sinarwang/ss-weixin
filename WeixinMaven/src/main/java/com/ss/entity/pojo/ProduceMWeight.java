package com.ss.entity.pojo;

import java.math.BigDecimal;

/**
 * 工序生产异常重量pojo类
 * @author Administrator
 *
 */
public class ProduceMWeight {
	private String ProduceM_Name;
	private BigDecimal weight;
	
	public String getProduceM_Name() {
		return ProduceM_Name;
	}
	public void setProduceM_Name(String produceM_Name) {
		ProduceM_Name = produceM_Name;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
}
