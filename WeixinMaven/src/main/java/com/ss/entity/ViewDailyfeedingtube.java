package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * ViewDailyfeedingtubeId entity. @author MyEclipse Persistence Tools
 */

public class ViewDailyfeedingtube implements java.io.Serializable {

	// Fields

	private Integer D_Id;
	private Integer D_Order;
	private String D_OrderNo;
	private String D_TubeNo;
	private String D_Quantity;
	private String D_Weight;
	private String D_TotalWeight;
	private String produceM_BillNo;
	private String D_Supply;
	private String D_PunchFactory;
	private String D_FurnaceNo;
	private String D_Spec;
	private String feedingDate;
	private String cardNo;
	private String contractNo;
	private Integer P_Type;

	// Constructors

	/** default constructor */
	public ViewDailyfeedingtube() {
	}
	
	public String getNameInDB() {
		return "view_dailyfeedingtube";
	}
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = ViewDailyfeedingtube.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = ViewDailyfeedingtube.class.getDeclaredMethod(methodName, fieldType);
				if(fieldType.equals(Integer.class)) {
					try {
						m.invoke(this, rs.getInt(fieldName));
					} catch (Exception e) {
						m.invoke(this, 0);
					}
				} else if(fieldType.equals(String.class)) {
					try {
						m.invoke(this, rs.getString(fieldName));
					} catch (Exception e) {
						m.invoke(this, "");
					}
				} else if(fieldType.equals(Long.class)) {
					try {
						m.invoke(this, rs.getLong(fieldName));
					} catch (Exception e) {
						m.invoke(this, (long)0);
					}
				} else if(fieldType.equals(Double.class)) {
					try {
						m.invoke(this, rs.getDouble(fieldName));
					} catch (Exception e) {
						m.invoke(this, (double)0);
					}
				} else if(fieldType.equals(Float.class)) {
					try {
						m.invoke(this, rs.getFloat(fieldName));
					} catch (Exception e) {
						m.invoke(this, (float)0);
					}
				} else if(fieldType.equals(BigDecimal.class)) {
					try {
						m.invoke(this, rs.getBigDecimal(fieldName));
					} catch (Exception e) {
						m.invoke(this, null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public Integer getD_Id() {
		return D_Id;
	}
	public void setD_Id(Integer d_Id) {
		D_Id = d_Id;
	}
	public Integer getD_Order() {
		return D_Order;
	}
	public void setD_Order(Integer d_Order) {
		D_Order = d_Order;
	}
	public String getD_OrderNo() {
		return D_OrderNo;
	}
	public void setD_OrderNo(String d_OrderNo) {
		D_OrderNo = d_OrderNo;
	}
	public String getD_TubeNo() {
		return D_TubeNo;
	}
	public void setD_TubeNo(String d_TubeNo) {
		D_TubeNo = d_TubeNo;
	}
	public String getD_Quantity() {
		return D_Quantity;
	}
	public void setD_Quantity(String d_Quantity) {
		D_Quantity = d_Quantity;
	}
	public String getD_Weight() {
		return D_Weight;
	}
	public void setD_Weight(String d_Weight) {
		D_Weight = d_Weight;
	}
	public String getD_TotalWeight() {
		return D_TotalWeight;
	}
	public void setD_TotalWeight(String d_TotalWeight) {
		D_TotalWeight = d_TotalWeight;
	}
	public String getProduceM_BillNo() {
		return produceM_BillNo;
	}
	public void setProduceM_BillNo(String produceM_BillNo) {
		this.produceM_BillNo = produceM_BillNo;
	}
	public String getD_Supply() {
		return D_Supply;
	}
	public void setD_Supply(String d_Supply) {
		D_Supply = d_Supply;
	}
	public String getD_PunchFactory() {
		return D_PunchFactory;
	}
	public void setD_PunchFactory(String d_PunchFactory) {
		D_PunchFactory = d_PunchFactory;
	}
	public String getD_FurnaceNo() {
		return D_FurnaceNo;
	}
	public void setD_FurnaceNo(String d_FurnaceNo) {
		D_FurnaceNo = d_FurnaceNo;
	}
	public String getD_Spec() {
		return D_Spec;
	}
	public void setD_Spec(String d_Spec) {
		D_Spec = d_Spec;
	}
	public String getFeedingDate() {
		return feedingDate;
	}
	public void setFeedingDate(String feedingDate) {
		this.feedingDate = feedingDate;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public Integer getP_Type() {
		return P_Type;
	}
	public void setP_Type(Integer p_Type) {
		P_Type = p_Type;
	}
}