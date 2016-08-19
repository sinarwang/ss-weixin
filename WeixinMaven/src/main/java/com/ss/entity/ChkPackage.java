package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * ChkPackage entity. @author MyEclipse Persistence Tools
 */

public class ChkPackage implements java.io.Serializable {

	// Fields

	private Integer D_Id;
	private Integer D_ParentId;
	private String D_Type;
	private String D_BarCode;
	private String contractNo;
	private String D_Name;
	private String D_Steel;
	private String D_Spec;
	private Integer D_Quantity;
	private String D_Unit;
	private Double D_Weight;
	private Double D_Numbers;
	private Double D_Weights;
	private Integer D_Percent;
	private String D_Verify;
	private String D_CheckType;
	private String D_Standards;
	private String D_FurnaceNo;
	private String D_HeatNo;
	private String D_BoxNo;
	private Integer R_Quantity;
	private String R_Unit;
	private Double R_Weight;
	private Double R_Numbers;
	private Double R_Weights;
	private Integer N_Quantity;
	private String N_Unit;
	private Double N_Weight;
	private Double N_Numbers;
	private Double N_Weights;
	private String D_StartTime;
	private String D_EndTime;
	private Integer D_WaitTime;
	private String D_CreateTime;
	private String D_Area;
	private String D_Rang;
	private Integer deptId;
	private String D_Supply;
	private String D_HoldNo;
	private Integer D_State;

	/** default constructor */
	public ChkPackage() {
	}

	public String getNameInDB() {
		return "chk_package";
	}
	
	// Property accessors
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = ChkPackage.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = ChkPackage.class.getDeclaredMethod(methodName, fieldType);
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
	public Integer getD_ParentId() {
		return D_ParentId;
	}
	public void setD_ParentId(Integer d_ParentId) {
		D_ParentId = d_ParentId;
	}
	public String getD_Type() {
		return D_Type;
	}
	public void setD_Type(String d_Type) {
		D_Type = d_Type;
	}
	public String getD_BarCode() {
		return D_BarCode;
	}
	public void setD_BarCode(String d_BarCode) {
		D_BarCode = d_BarCode;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getD_Name() {
		return D_Name;
	}
	public void setD_Name(String d_Name) {
		D_Name = d_Name;
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
	public Integer getD_Quantity() {
		return D_Quantity;
	}
	public void setD_Quantity(Integer d_Quantity) {
		D_Quantity = d_Quantity;
	}
	public String getD_Unit() {
		return D_Unit;
	}
	public void setD_Unit(String d_Unit) {
		D_Unit = d_Unit;
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
	public Double getD_Weights() {
		return D_Weights;
	}
	public void setD_Weights(Double d_Weights) {
		D_Weights = d_Weights;
	}
	public Integer getD_Percent() {
		return D_Percent;
	}
	public void setD_Percent(Integer d_Percent) {
		D_Percent = d_Percent;
	}
	public String getD_Verify() {
		return D_Verify;
	}
	public void setD_Verify(String d_Verify) {
		D_Verify = d_Verify;
	}
	public String getD_CheckType() {
		return D_CheckType;
	}
	public void setD_CheckType(String d_CheckType) {
		D_CheckType = d_CheckType;
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
	public String getD_HeatNo() {
		return D_HeatNo;
	}
	public void setD_HeatNo(String d_HeatNo) {
		D_HeatNo = d_HeatNo;
	}
	public String getD_BoxNo() {
		return D_BoxNo;
	}
	public void setD_BoxNo(String d_BoxNo) {
		D_BoxNo = d_BoxNo;
	}
	public Integer getR_Quantity() {
		return R_Quantity;
	}
	public void setR_Quantity(Integer r_Quantity) {
		R_Quantity = r_Quantity;
	}
	public String getR_Unit() {
		return R_Unit;
	}
	public void setR_Unit(String r_Unit) {
		R_Unit = r_Unit;
	}
	public Double getR_Weight() {
		return R_Weight;
	}
	public void setR_Weight(Double r_Weight) {
		R_Weight = r_Weight;
	}
	public Double getR_Numbers() {
		return R_Numbers;
	}
	public void setR_Numbers(Double r_Numbers) {
		R_Numbers = r_Numbers;
	}
	public Double getR_Weights() {
		return R_Weights;
	}
	public void setR_Weights(Double r_Weights) {
		R_Weights = r_Weights;
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
	public Double getN_Weights() {
		return N_Weights;
	}
	public void setN_Weights(Double n_Weights) {
		N_Weights = n_Weights;
	}
	public String getD_StartTime() {
		return D_StartTime;
	}
	public void setD_StartTime(String d_StartTime) {
		D_StartTime = d_StartTime;
	}
	public String getD_EndTime() {
		return D_EndTime;
	}
	public void setD_EndTime(String d_EndTime) {
		D_EndTime = d_EndTime;
	}
	public Integer getD_WaitTime() {
		return D_WaitTime;
	}
	public void setD_WaitTime(Integer d_WaitTime) {
		D_WaitTime = d_WaitTime;
	}
	public String getD_CreateTime() {
		return D_CreateTime;
	}
	public void setD_CreateTime(String d_CreateTime) {
		D_CreateTime = d_CreateTime;
	}
	public String getD_Area() {
		return D_Area;
	}
	public void setD_Area(String d_Area) {
		D_Area = d_Area;
	}
	public String getD_Rang() {
		return D_Rang;
	}
	public void setD_Rang(String d_Rang) {
		D_Rang = d_Rang;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getD_Supply() {
		return D_Supply;
	}
	public void setD_Supply(String d_Supply) {
		D_Supply = d_Supply;
	}
	public String getD_HoldNo() {
		return D_HoldNo;
	}
	public void setD_HoldNo(String d_HoldNo) {
		D_HoldNo = d_HoldNo;
	}
	public Integer getD_State() {
		return D_State;
	}
	public void setD_State(Integer d_State) {
		D_State = d_State;
	}
}