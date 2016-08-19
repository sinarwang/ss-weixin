package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * WmsProduct entity. @author MyEclipse Persistence Tools
 */

public class WmsProduct implements java.io.Serializable {

	// Fields

	private Integer D_Id;
	private String warehouseCode;
	private String bin;
	private String pro_No;
	private String pro_Name;
	private String D_Type;
	private String D_Steel;
	private String D_Spec;
	private String D_FurnaceNo;
	private String D_Unit;
	private Integer D_Quantity;
	private Double D_Weight;
	private Double D_Numbers;
	private Integer R_Quantity;
	private Double R_Weight;
	private Double R_Numbers;
	private Integer M_Quantity;
	private Double M_Weight;
	private Double M_Numbers;
	private Integer S_Quantity;
	private Double S_Weight;
	private Double S_Numbers;
	private String D_Create;
	private String D_CreateDate;
	private String D_Update;
	private String D_UpdateDate;
	private Integer I_Quantity;
	private Double I_Weight;
	private Double I_Numbers;
	private Integer F_Quantity;
	private Double F_Weight;
	private Double F_Numbers;

	// Constructors

	/** default constructor */
	public WmsProduct() {
	}

	public String getNameInDB() {
		return "wms_product";
	}
	
	// Property accessors
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = WmsProduct.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = WmsProduct.class.getDeclaredMethod(methodName, fieldType);
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

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
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

	public Integer getR_Quantity() {
		return R_Quantity;
	}

	public void setR_Quantity(Integer r_Quantity) {
		R_Quantity = r_Quantity;
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

	public Integer getM_Quantity() {
		return M_Quantity;
	}

	public void setM_Quantity(Integer m_Quantity) {
		M_Quantity = m_Quantity;
	}

	public Double getM_Weight() {
		return M_Weight;
	}

	public void setM_Weight(Double m_Weight) {
		M_Weight = m_Weight;
	}

	public Double getM_Numbers() {
		return M_Numbers;
	}

	public void setM_Numbers(Double m_Numbers) {
		M_Numbers = m_Numbers;
	}

	public Integer getS_Quantity() {
		return S_Quantity;
	}

	public void setS_Quantity(Integer s_Quantity) {
		S_Quantity = s_Quantity;
	}

	public Double getS_Weight() {
		return S_Weight;
	}

	public void setS_Weight(Double s_Weight) {
		S_Weight = s_Weight;
	}

	public Double getS_Numbers() {
		return S_Numbers;
	}

	public void setS_Numbers(Double s_Numbers) {
		S_Numbers = s_Numbers;
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

	public Integer getI_Quantity() {
		return I_Quantity;
	}

	public void setI_Quantity(Integer i_Quantity) {
		I_Quantity = i_Quantity;
	}

	public Double getI_Weight() {
		return I_Weight;
	}

	public void setI_Weight(Double i_Weight) {
		I_Weight = i_Weight;
	}

	public Double getI_Numbers() {
		return I_Numbers;
	}

	public void setI_Numbers(Double i_Numbers) {
		I_Numbers = i_Numbers;
	}

	public Integer getF_Quantity() {
		return F_Quantity;
	}

	public void setF_Quantity(Integer f_Quantity) {
		F_Quantity = f_Quantity;
	}

	public Double getF_Weight() {
		return F_Weight;
	}

	public void setF_Weight(Double f_Weight) {
		F_Weight = f_Weight;
	}

	public Double getF_Numbers() {
		return F_Numbers;
	}

	public void setF_Numbers(Double f_Numbers) {
		F_Numbers = f_Numbers;
	}
}