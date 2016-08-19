package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * ViewPeopleReportId entity. @author MyEclipse Persistence Tools
 */

public class ViewPeopleReport implements java.io.Serializable {

	// Fields

	private Integer produceM_Id;
	private String produceM_MergerValue;
	private String produceM_Model;
	private String D_UserName;
	private Double avgnumber;
	private Double avgweight;
	private String D_End;
	private Integer D_Steps;
	private Double produceM_Price;
	private Double produceM_TotalPrice;
	private String produceM_BillNo;
	private String produceM_Spec;
	private String produceM_Name;
	private Integer produceM_EquipmentId;
	private Long P_Type;
	private String produceM_EquipmentName;

	// Constructors

	/** default constructor */
	/** default constructor */
	public ViewPeopleReport() {
	}

	public String getNameInDB() {
		return "view_people_report";
	}

	
	public void initFromRs(ResultSet rs) {
		Field[] fields = ViewPeopleReport.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = ViewPeopleReport.class.getDeclaredMethod(methodName, fieldType);
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

	public Integer getProduceM_Id() {
		return produceM_Id;
	}
	public void setProduceM_Id(Integer produceM_Id) {
		this.produceM_Id = produceM_Id;
	}
	public String getProduceM_MergerValue() {
		return produceM_MergerValue;
	}
	public void setProduceM_MergerValue(String produceM_MergerValue) {
		this.produceM_MergerValue = produceM_MergerValue;
	}
	public String getProduceM_Model() {
		return produceM_Model;
	}
	public void setProduceM_Model(String produceM_Model) {
		this.produceM_Model = produceM_Model;
	}
	public String getD_UserName() {
		return D_UserName;
	}
	public void setD_UserName(String d_UserName) {
		D_UserName = d_UserName;
	}
	public Double getAvgnumber() {
		return avgnumber;
	}
	public void setAvgnumber(Double avgnumber) {
		this.avgnumber = avgnumber;
	}
	public Double getAvgweight() {
		return avgweight;
	}
	public void setAvgweight(Double avgweight) {
		this.avgweight = avgweight;
	}
	public String getD_End() {
		return D_End;
	}
	public void setD_End(String d_End) {
		D_End = d_End;
	}
	public Integer getD_Steps() {
		return D_Steps;
	}
	public void setD_Steps(Integer d_Steps) {
		D_Steps = d_Steps;
	}
	public Double getProduceM_Price() {
		return produceM_Price;
	}
	public void setProduceM_Price(Double produceM_Price) {
		this.produceM_Price = produceM_Price;
	}
	public Double getProduceM_TotalPrice() {
		return produceM_TotalPrice;
	}
	public void setProduceM_TotalPrice(Double produceM_TotalPrice) {
		this.produceM_TotalPrice = produceM_TotalPrice;
	}
	public String getProduceM_BillNo() {
		return produceM_BillNo;
	}
	public void setProduceM_BillNo(String produceM_BillNo) {
		this.produceM_BillNo = produceM_BillNo;
	}
	public String getProduceM_Spec() {
		return produceM_Spec;
	}
	public void setProduceM_Spec(String produceM_Spec) {
		this.produceM_Spec = produceM_Spec;
	}
	public String getProduceM_Name() {
		return produceM_Name;
	}
	public void setProduceM_Name(String produceM_Name) {
		this.produceM_Name = produceM_Name;
	}
	public Integer getProduceM_EquipmentId() {
		return produceM_EquipmentId;
	}
	public void setProduceM_EquipmentId(Integer produceM_EquipmentId) {
		this.produceM_EquipmentId = produceM_EquipmentId;
	}
	public Long getP_Type() {
		return P_Type;
	}
	public void setP_Type(Long p_Type) {
		P_Type = p_Type;
	}
	public String getProduceM_EquipmentName() {
		return produceM_EquipmentName;
	}
	public void setProduceM_EquipmentName(String produceM_EquipmentName) {
		this.produceM_EquipmentName = produceM_EquipmentName;
	}
}