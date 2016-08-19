package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * ViewScrapReportId entity. @author MyEclipse Persistence Tools
 */

public class ViewScrapReport implements java.io.Serializable {

	// Fields

	private Integer D_ParentId;
	private String D_Date;
	private String D_Type;
	private Integer N_Quantity;
	private String N_Unit;
	private Double N_Weight;
	private Double N_Numbers;
	private String N_Remark;
	private String contractNo;
	private String cardNo;
	private String D_Steel;
	private String D_Spec1;
	private String D_Spec2;
	private String D_Standards;
	private String D_FurnaceNo;
	private Integer P_Type;
	private String feedingDate;
	private String deliveryDate;
	private Double D_Weights;
	private Integer D_Quantity;
	private Double D_TotalWeight;
	private String produceM_Name;
	private Integer D_Id;
	private String D_Remark;
	private String D_User;
	private String D_Typed;
	private Integer D_Quantity1;
	private Double D_Weight;
	private Double D_Numbers;
	private String ncardNo;

	// Constructors

	/** default constructor */
	public ViewScrapReport() {
	}

	public String getNameInDB() {
		return "view_scrap_report";
	}
	
	// Property accessors
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = ViewScrapReport.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = ViewScrapReport.class.getDeclaredMethod(methodName, fieldType);
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

	public Integer getD_ParentId() {
		return D_ParentId;
	}
	public void setD_ParentId(Integer d_ParentId) {
		D_ParentId = d_ParentId;
	}
	public String getD_Date() {
		return D_Date;
	}
	public void setD_Date(String d_Date) {
		D_Date = d_Date;
	}
	public String getD_Type() {
		return D_Type;
	}
	public void setD_Type(String d_Type) {
		D_Type = d_Type;
	}
	public Integer getN_Quantity() {
		return N_Quantity;
	}
	public void setN_Quantity(Integer n_Quantity) {
		N_Quantity = n_Quantity;
	}
	public String getN_Unit() {
		return N_Unit;
	}
	public void setN_Unit(String n_Unit) {
		N_Unit = n_Unit;
	}
	public Double getN_Weight() {
		return N_Weight;
	}
	public void setN_Weight(Double n_Weight) {
		N_Weight = n_Weight;
	}
	public Double getN_Numbers() {
		return N_Numbers;
	}
	public void setN_Numbers(Double n_Numbers) {
		N_Numbers = n_Numbers;
	}
	public String getN_Remark() {
		return N_Remark;
	}
	public void setN_Remark(String n_Remark) {
		N_Remark = n_Remark;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getD_Steel() {
		return D_Steel;
	}
	public void setD_Steel(String d_Steel) {
		D_Steel = d_Steel;
	}
	public String getD_Spec1() {
		return D_Spec1;
	}
	public void setD_Spec1(String d_Spec1) {
		D_Spec1 = d_Spec1;
	}
	public String getD_Spec2() {
		return D_Spec2;
	}
	public void setD_Spec2(String d_Spec2) {
		D_Spec2 = d_Spec2;
	}
	public String getD_Standards() {
		return D_Standards;
	}
	public void setD_Standards(String d_Standards) {
		D_Standards = d_Standards;
	}
	public String getD_FurnaceNo() {
		return D_FurnaceNo;
	}
	public void setD_FurnaceNo(String d_FurnaceNo) {
		D_FurnaceNo = d_FurnaceNo;
	}
	public Integer getP_Type() {
		return P_Type;
	}
	public void setP_Type(Integer p_Type) {
		P_Type = p_Type;
	}
	public String getFeedingDate() {
		return feedingDate;
	}
	public void setFeedingDate(String feedingDate) {
		this.feedingDate = feedingDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Double getD_Weights() {
		return D_Weights;
	}
	public void setD_Weights(Double d_Weights) {
		D_Weights = d_Weights;
	}
	public Integer getD_Quantity() {
		return D_Quantity;
	}
	public void setD_Quantity(Integer d_Quantity) {
		D_Quantity = d_Quantity;
	}
	public Double getD_TotalWeight() {
		return D_TotalWeight;
	}
	public void setD_TotalWeight(Double d_TotalWeight) {
		D_TotalWeight = d_TotalWeight;
	}
	public String getProduceM_Name() {
		return produceM_Name;
	}
	public void setProduceM_Name(String produceM_Name) {
		this.produceM_Name = produceM_Name;
	}
	public Integer getD_Id() {
		return D_Id;
	}
	public void setD_Id(Integer d_Id) {
		D_Id = d_Id;
	}
	public String getD_Remark() {
		return D_Remark;
	}
	public void setD_Remark(String d_Remark) {
		D_Remark = d_Remark;
	}
	public String getD_User() {
		return D_User;
	}
	public void setD_User(String d_User) {
		D_User = d_User;
	}
	public String getD_Typed() {
		return D_Typed;
	}
	public void setD_Typed(String d_Typed) {
		D_Typed = d_Typed;
	}
	public Integer getD_Quantity1() {
		return D_Quantity1;
	}
	public void setD_Quantity1(Integer d_Quantity1) {
		D_Quantity1 = d_Quantity1;
	}
	public Double getD_Weight() {
		return D_Weight;
	}
	public void setD_Weight(Double d_Weight) {
		D_Weight = d_Weight;
	}
	public Double getD_Numbers() {
		return D_Numbers;
	}
	public void setD_Numbers(Double d_Numbers) {
		D_Numbers = d_Numbers;
	}
	public String getNcardNo() {
		return ncardNo;
	}
	public void setNcardNo(String ncardNo) {
		this.ncardNo = ncardNo;
	}
}