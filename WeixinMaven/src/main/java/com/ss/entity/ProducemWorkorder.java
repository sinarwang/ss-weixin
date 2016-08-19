package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * ProducemWorkorder entity. @author MyEclipse Persistence Tools
 */

public class ProducemWorkorder implements java.io.Serializable {

	// Fields

	private Integer D_Id;
	private String D_No;
	private Integer P_Type;
	private String produceM_No;
	private String produceM_Name;
	private String produceM_Spec;
	private String cardNo;
	private Integer produceM_Number;
	private Double produceM_Weight;
	private Double produceM_Weights;
	private String contractNo;
	private String deliveryDate;
	private String D_Steel;
	private String D_Spec2;
	private String D_Create;
	private String D_CreateDate;
	private String produceM_BeginDate;
	
	private Integer state;

	// Constructors

	/** default constructor */
	public ProducemWorkorder() {
	}
	
	public String getNameInDB() {
		return "ProduceM_WorkOrder";
	}
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = ProducemWorkorder.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = ProducemWorkorder.class.getDeclaredMethod(methodName, fieldType);
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

	public String getD_No() {
		return D_No;
	}

	public void setD_No(String d_No) {
		D_No = d_No;
	}

	public Integer getP_Type() {
		return P_Type;
	}

	public void setP_Type(Integer p_Type) {
		P_Type = p_Type;
	}

	public String getProduceM_No() {
		return produceM_No;
	}

	public void setProduceM_No(String produceM_No) {
		this.produceM_No = produceM_No;
	}

	public String getProduceM_Name() {
		return produceM_Name;
	}

	public void setProduceM_Name(String produceM_Name) {
		this.produceM_Name = produceM_Name;
	}

	public String getProduceM_Spec() {
		return produceM_Spec;
	}

	public void setProduceM_Spec(String produceM_Spec) {
		this.produceM_Spec = produceM_Spec;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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

	public Double getProduceM_Weights() {
		return produceM_Weights;
	}

	public void setProduceM_Weights(Double produceM_Weights) {
		this.produceM_Weights = produceM_Weights;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getD_Steel() {
		return D_Steel;
	}

	public void setD_Steel(String d_Steel) {
		D_Steel = d_Steel;
	}

	public String getD_Spec2() {
		return D_Spec2;
	}

	public void setD_Spec2(String d_Spec2) {
		D_Spec2 = d_Spec2;
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

	public String getProduceM_BeginDate() {
		return produceM_BeginDate;
	}

	public void setProduceM_BeginDate(String produceM_BeginDate) {
		this.produceM_BeginDate = produceM_BeginDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}