package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * ViewOnepieceflowId entity. @author MyEclipse Persistence Tools
 */

public class ViewOnepieceflow implements java.io.Serializable {

	// Fields

	private Integer produceM_Id;
	private Integer produceM_Order;
	private Integer produceM_RouteNo;
	private String produceM_Name;
	private String produceM_BillNo;
	private String produceM_BeginDate;
	private String produceM_EndDate;
	private String produceM_Spec;
	private Integer produceM_Number;
	private Double produceM_Weight;
	private Integer P_Type;
	private Integer deptId;
	private String produceM_StartTime;
	private String produceM_EndTime;
	private Integer produceM_Warehouse;
	private String produceM_WarehouseName;
	private Long realTime;
	private Long timeOut;
	private String ContractNo;

	/** default constructor */
	public ViewOnepieceflow() {
	}

	public String getNameInDB() {
		return "view_onepieceflow";
	}
	
	// Property accessors
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = ViewOnepieceflow.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = ViewOnepieceflow.class.getDeclaredMethod(methodName, fieldType);
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
	public Integer getProduceM_Order() {
		return produceM_Order;
	}
	public void setProduceM_Order(Integer produceM_Order) {
		this.produceM_Order = produceM_Order;
	}
	public Integer getProduceM_RouteNo() {
		return produceM_RouteNo;
	}
	public void setProduceM_RouteNo(Integer produceM_RouteNo) {
		this.produceM_RouteNo = produceM_RouteNo;
	}
	public String getProduceM_Name() {
		return produceM_Name;
	}
	public void setProduceM_Name(String produceM_Name) {
		this.produceM_Name = produceM_Name;
	}
	public String getProduceM_BillNo() {
		return produceM_BillNo;
	}
	public void setProduceM_BillNo(String produceM_BillNo) {
		this.produceM_BillNo = produceM_BillNo;
	}
	public String getProduceM_BeginDate() {
		return produceM_BeginDate;
	}
	public void setProduceM_BeginDate(String produceM_BeginDate) {
		this.produceM_BeginDate = produceM_BeginDate;
	}
	public String getProduceM_EndDate() {
		return produceM_EndDate;
	}
	public void setProduceM_EndDate(String produceM_EndDate) {
		this.produceM_EndDate = produceM_EndDate;
	}
	public String getProduceM_Spec() {
		return produceM_Spec;
	}
	public void setProduceM_Spec(String produceM_Spec) {
		this.produceM_Spec = produceM_Spec;
	}
	public Integer getProduceM_Number() {
		return produceM_Number;
	}
	public void setProduceM_Number(Integer produceM_Number) {
		this.produceM_Number = produceM_Number;
	}
	public Double getProduceM_Weight() {
		return produceM_Weight;
	}
	public void setProduceM_Weight(Double produceM_Weight) {
		this.produceM_Weight = produceM_Weight;
	}
	public Integer getP_Type() {
		return P_Type;
	}
	public void setP_Type(Integer p_Type) {
		P_Type = p_Type;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getProduceM_StartTime() {
		return produceM_StartTime;
	}
	public void setProduceM_StartTime(String produceM_StartTime) {
		this.produceM_StartTime = produceM_StartTime;
	}
	public String getProduceM_EndTime() {
		return produceM_EndTime;
	}
	public void setProduceM_EndTime(String produceM_EndTime) {
		this.produceM_EndTime = produceM_EndTime;
	}
	public Integer getProduceM_Warehouse() {
		return produceM_Warehouse;
	}
	public void setProduceM_Warehouse(Integer produceM_Warehouse) {
		this.produceM_Warehouse = produceM_Warehouse;
	}
	public String getProduceM_WarehouseName() {
		return produceM_WarehouseName;
	}
	public void setProduceM_WarehouseName(String produceM_WarehouseName) {
		this.produceM_WarehouseName = produceM_WarehouseName;
	}
	public Long getRealTime() {
		return realTime;
	}
	public void setRealTime(Long realTime) {
		this.realTime = realTime;
	}
	public Long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
	}
	public String getContractNo() {
		return ContractNo;
	}
	public void setContractNo(String contractNo) {
		ContractNo = contractNo;
	}
}