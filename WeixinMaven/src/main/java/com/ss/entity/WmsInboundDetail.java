package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * WmsInboundDetail entity. @author MyEclipse Persistence Tools
 */

public class WmsInboundDetail implements java.io.Serializable {

	// Fields

	private Integer D_Id;
	private String parentId;
	private Integer D_Order;
	private String contractNo;
	private String pro_No;
	private String pro_Name;
	private String D_Type;
	private String D_Steel;
	private String D_Spec;
	private String D_FurnaceNo;
	private String D_Unit;
	private Integer P_Quantity;
	private Double P_Weight;
	private Double P_Numbers;
	private Integer D_Quantity;
	private Double D_Weight;
	private Double D_Numbers;
	private String D_Create;
	private String D_CreateDate;
	private String D_Update;
	private String D_UpdateDate;
	private Integer S_State;
	private String D_RelationNo;
	private String binCode;

	// Constructors

	/** default constructor */
	public WmsInboundDetail() {
	}

	public String getNameInDB() {
		return "wms_inbound_detail";
	}
	
	// Property accessors
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = WmsInboundDetail.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = WmsInboundDetail.class.getDeclaredMethod(methodName, fieldType);
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getD_Order() {
		return D_Order;
	}

	public void setD_Order(Integer d_Order) {
		D_Order = d_Order;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getPro_No() {
		return pro_No;
	}

	public void setPro_No(String pro_No) {
		this.pro_No = pro_No;
	}

	public String getPro_Name() {
		return pro_Name;
	}

	public void setPro_Name(String pro_Name) {
		this.pro_Name = pro_Name;
	}

	public String getD_Type() {
		return D_Type;
	}

	public void setD_Type(String d_Type) {
		D_Type = d_Type;
	}

	public String getD_Steel() {
		return D_Steel;
	}

	public void setD_Steel(String d_Steel) {
		D_Steel = d_Steel;
	}

	public String getD_Spec() {
		return D_Spec;
	}

	public void setD_Spec(String d_Spec) {
		D_Spec = d_Spec;
	}

	public String getD_FurnaceNo() {
		return D_FurnaceNo;
	}

	public void setD_FurnaceNo(String d_FurnaceNo) {
		D_FurnaceNo = d_FurnaceNo;
	}

	public String getD_Unit() {
		return D_Unit;
	}

	public void setD_Unit(String d_Unit) {
		D_Unit = d_Unit;
	}

	public Integer getP_Quantity() {
		return P_Quantity;
	}

	public void setP_Quantity(Integer p_Quantity) {
		P_Quantity = p_Quantity;
	}

	public Double getP_Weight() {
		return P_Weight;
	}

	public void setP_Weight(Double p_Weight) {
		P_Weight = p_Weight;
	}

	public Double getP_Numbers() {
		return P_Numbers;
	}

	public void setP_Numbers(Double p_Numbers) {
		P_Numbers = p_Numbers;
	}

	public Integer getD_Quantity() {
		return D_Quantity;
	}

	public void setD_Quantity(Integer d_Quantity) {
		D_Quantity = d_Quantity;
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

	public String getD_Create() {
		return D_Create;
	}

	public void setD_Create(String d_Create) {
		D_Create = d_Create;
	}

	public String getD_CreateDate() {
		return D_CreateDate;
	}

	public void setD_CreateDate(String d_CreateDate) {
		D_CreateDate = d_CreateDate;
	}

	public String getD_Update() {
		return D_Update;
	}

	public void setD_Update(String d_Update) {
		D_Update = d_Update;
	}

	public String getD_UpdateDate() {
		return D_UpdateDate;
	}

	public void setD_UpdateDate(String d_UpdateDate) {
		D_UpdateDate = d_UpdateDate;
	}

	public Integer getS_State() {
		return S_State;
	}

	public void setS_State(Integer s_State) {
		S_State = s_State;
	}

	public String getD_RelationNo() {
		return D_RelationNo;
	}

	public void setD_RelationNo(String d_RelationNo) {
		D_RelationNo = d_RelationNo;
	}

	public String getBinCode() {
		return binCode;
	}

	public void setBinCode(String binCode) {
		this.binCode = binCode;
	}
}