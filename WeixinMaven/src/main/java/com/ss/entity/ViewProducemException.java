package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * ViewProducemExceptionId entity. @author MyEclipse Persistence Tools
 */

public class ViewProducemException implements java.io.Serializable {

	// Fields

	private Integer D_Id;
	private Integer D_ParentId;
	private String D_Date;
	private String D_Type;
	private Integer N_Quantity;
	private String N_Unit;
	private Double N_Weight;
	private Double N_Numbers;
	private String N_Remark;
	private Integer N_Step;
	private Integer D_State;
	private Integer isPrint;
	private Integer isNumber;
	private String produceM_Name;
	private Integer produceM_RouteNo;
	private String produceM_Spec;
	private String produceM_UserNames;
	private String contractNo;
	private String cardNo;
	private String D_Steel;
	private String D_Standards;
	private String D_FurnaceNo;
	private String D_Spec1;
	private String D_Spec2;
	private Integer P_Type;
	
	// Constructors

	/** default constructor */
	public ViewProducemException() {
	}

	public String getNameInDB() {
		return "view_producem_exception";
	}
	
	// Property accessors
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = ViewProducemException.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = ViewProducemException.class.getDeclaredMethod(methodName, fieldType);
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
	public Integer getN_Step() {
		return N_Step;
	}
	public void setN_Step(Integer n_Step) {
		N_Step = n_Step;
	}
	public Integer getD_State() {
		return D_State;
	}
	public void setD_State(Integer d_State) {
		D_State = d_State;
	}
	public Integer getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(Integer isPrint) {
		this.isPrint = isPrint;
	}
	public Integer getIsNumber() {
		return isNumber;
	}
	public void setIsNumber(Integer isNumber) {
		this.isNumber = isNumber;
	}
	public String getProduceM_Name() {
		return produceM_Name;
	}
	public void setProduceM_Name(String produceM_Name) {
		this.produceM_Name = produceM_Name;
	}
	public Integer getProduceM_RouteNo() {
		return produceM_RouteNo;
	}
	public void setProduceM_RouteNo(Integer produceM_RouteNo) {
		this.produceM_RouteNo = produceM_RouteNo;
	}
	public String getProduceM_Spec() {
		return produceM_Spec;
	}
	public void setProduceM_Spec(String produceM_Spec) {
		this.produceM_Spec = produceM_Spec;
	}
	public String getProduceM_UserNames() {
		return produceM_UserNames;
	}
	public void setProduceM_UserNames(String produceM_UserNames) {
		this.produceM_UserNames = produceM_UserNames;
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
	public Integer getP_Type() {
		return P_Type;
	}
	public void setP_Type(Integer p_Type) {
		P_Type = p_Type;
	}
}