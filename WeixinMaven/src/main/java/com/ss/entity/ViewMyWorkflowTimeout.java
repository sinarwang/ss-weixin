package com.ss.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * ViewMyWorkflowTimeoutId entity. @author MyEclipse Persistence Tools
 */

public class ViewMyWorkflowTimeout implements java.io.Serializable {

	// Fields

	private Integer cardId;
	private String cardNo;
	private Long timeOut;
	private String produceM_Plan;
	private Integer workCardType;
	private Integer P_Type;
	private String contractNo;
	private String D_Standards;
	private String pro_Name;
	private String cardD1;
	private String cardD2;
	private String cardS1;
	private String cardS2;
	private String D_Steel;
	private String D_FurnaceNo;
	private String D_HeatNo;
	private String D_Spec1;
	private Integer D_Quantity;
	private String D_Weight;
	private Double D_Weights;
	private Double D_TotalWeight;
	private String D_Cs;
	private String D_Spec2;
	private String D_Number;
	private String D_Length;
	private String D_SurfaceState;
	private String D_EndfaceState;
	private String D_SolutionTemp;
	private String special_Requirements;
	private String sprayWord_Requirements;
	private String package_Requirements;
	private String D_Tolerances;
	private String S_Tolerances;
	private String feedingDate;
	private Double F_Weight;
	private String F_Date;
	private Double W_Weight;
	private String W_Date;
	private Double R_Weight;
	private String R_Date;
	private String finishRate;
	private String passRate;
	private String cardBeginDate;
	private String deliveryDate;
	private String batchNo;
	private String cardmake;
	private String cardMakeDate;
	private String cardAudting;
	private String cardAudtingDate;
	private Integer cardState;
	private String remark;
	private Integer R_Type;
	private Integer deptId;
	private Integer produceM_Id;
	private String produceM_Name;
	private Integer produceM_Number;
	private Double produceM_Weight;
	private Double produceM_Weights;
	private String produceM_Spec;
	private String produceM_EndTime;
	private Long produceM_State;
	private Integer produceM_RouteNo;
	private String D_Situation;

	// Constructors

	/** default constructor */
	public ViewMyWorkflowTimeout() {
	}

	/** minimal constructor */
	public ViewMyWorkflowTimeout(Integer cardId, String cardNo,
			Long produceM_State) {
		this.cardId = cardId;
		this.cardNo = cardNo;
		this.produceM_State = produceM_State;
	}
	
	public String getNameInDB() {
		return "view_my_workflow_timeout";
	}
	
	// Property accessors
	
	public void initFromRs(ResultSet rs) {
		Field[] fields = ViewMyWorkflowTimeout.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = ViewMyWorkflowTimeout.class.getDeclaredMethod(methodName, fieldType);
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
	
	/*public static void main(String[] args) {
		Field[] fields = ViewMyWorkflowTimeout.class.getDeclaredFields();
		for(Field f : fields) {
			String methodName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
			System.out.println(f.getType() + "\n");
			System.out.println(methodName + "\n");
		}
	}*/

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
	}

	public String getProduceM_Plan() {
		return produceM_Plan;
	}

	public void setProduceM_Plan(String produceM_Plan) {
		this.produceM_Plan = produceM_Plan;
	}

	public Integer getWorkCardType() {
		return workCardType;
	}

	public void setWorkCardType(Integer workCardType) {
		this.workCardType = workCardType;
	}

	public Integer getP_Type() {
		return P_Type;
	}

	public void setP_Type(Integer p_Type) {
		P_Type = p_Type;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getD_Standards() {
		return D_Standards;
	}

	public void setD_Standards(String d_Standards) {
		D_Standards = d_Standards;
	}

	public String getPro_Name() {
		return pro_Name;
	}

	public void setPro_Name(String pro_Name) {
		this.pro_Name = pro_Name;
	}

	public String getCardD1() {
		return cardD1;
	}

	public void setCardD1(String cardD1) {
		this.cardD1 = cardD1;
	}

	public String getCardD2() {
		return cardD2;
	}

	public void setCardD2(String cardD2) {
		this.cardD2 = cardD2;
	}

	public String getCardS1() {
		return cardS1;
	}

	public void setCardS1(String cardS1) {
		this.cardS1 = cardS1;
	}

	public String getCardS2() {
		return cardS2;
	}

	public void setCardS2(String cardS2) {
		this.cardS2 = cardS2;
	}

	public String getD_Steel() {
		return D_Steel;
	}

	public void setD_Steel(String d_Steel) {
		D_Steel = d_Steel;
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

	public String getD_Spec1() {
		return D_Spec1;
	}

	public void setD_Spec1(String d_Spec1) {
		D_Spec1 = d_Spec1;
	}

	public Integer getD_Quantity() {
		return D_Quantity;
	}

	public void setD_Quantity(Integer d_Quantity) {
		D_Quantity = d_Quantity;
	}

	public String getD_Weight() {
		return D_Weight;
	}

	public void setD_Weight(String d_Weight) {
		D_Weight = d_Weight;
	}

	public Double getD_Weights() {
		return D_Weights;
	}

	public void setD_Weights(Double d_Weights) {
		D_Weights = d_Weights;
	}

	public Double getD_TotalWeight() {
		return D_TotalWeight;
	}

	public void setD_TotalWeight(Double d_TotalWeight) {
		D_TotalWeight = d_TotalWeight;
	}

	public String getD_Cs() {
		return D_Cs;
	}

	public void setD_Cs(String d_Cs) {
		D_Cs = d_Cs;
	}

	public String getD_Spec2() {
		return D_Spec2;
	}

	public void setD_Spec2(String d_Spec2) {
		D_Spec2 = d_Spec2;
	}

	public String getD_Number() {
		return D_Number;
	}

	public void setD_Number(String d_Number) {
		D_Number = d_Number;
	}

	public String getD_Length() {
		return D_Length;
	}

	public void setD_Length(String d_Length) {
		D_Length = d_Length;
	}

	public String getD_SurfaceState() {
		return D_SurfaceState;
	}

	public void setD_SurfaceState(String d_SurfaceState) {
		D_SurfaceState = d_SurfaceState;
	}

	public String getD_EndfaceState() {
		return D_EndfaceState;
	}

	public void setD_EndfaceState(String d_EndfaceState) {
		D_EndfaceState = d_EndfaceState;
	}

	public String getD_SolutionTemp() {
		return D_SolutionTemp;
	}

	public void setD_SolutionTemp(String d_SolutionTemp) {
		D_SolutionTemp = d_SolutionTemp;
	}

	public String getSpecial_Requirements() {
		return special_Requirements;
	}

	public void setSpecial_Requirements(String special_Requirements) {
		this.special_Requirements = special_Requirements;
	}

	public String getSprayWord_Requirements() {
		return sprayWord_Requirements;
	}

	public void setSprayWord_Requirements(String sprayWord_Requirements) {
		this.sprayWord_Requirements = sprayWord_Requirements;
	}

	public String getPackage_Requirements() {
		return package_Requirements;
	}

	public void setPackage_Requirements(String package_Requirements) {
		this.package_Requirements = package_Requirements;
	}

	public String getD_Tolerances() {
		return D_Tolerances;
	}

	public void setD_Tolerances(String d_Tolerances) {
		D_Tolerances = d_Tolerances;
	}

	public String getS_Tolerances() {
		return S_Tolerances;
	}

	public void setS_Tolerances(String s_Tolerances) {
		S_Tolerances = s_Tolerances;
	}

	public String getFeedingDate() {
		return feedingDate;
	}

	public void setFeedingDate(String feedingDate) {
		this.feedingDate = feedingDate;
	}

	public Double getF_Weight() {
		return F_Weight;
	}

	public void setF_Weight(Double f_Weight) {
		F_Weight = f_Weight;
	}

	public String getF_Date() {
		return F_Date;
	}

	public void setF_Date(String f_Date) {
		F_Date = f_Date;
	}

	public Double getW_Weight() {
		return W_Weight;
	}

	public void setW_Weight(Double w_Weight) {
		W_Weight = w_Weight;
	}

	public String getW_Date() {
		return W_Date;
	}

	public void setW_Date(String w_Date) {
		W_Date = w_Date;
	}

	public Double getR_Weight() {
		return R_Weight;
	}

	public void setR_Weight(Double r_Weight) {
		R_Weight = r_Weight;
	}

	public String getR_Date() {
		return R_Date;
	}

	public void setR_Date(String r_Date) {
		R_Date = r_Date;
	}

	public String getFinishRate() {
		return finishRate;
	}

	public void setFinishRate(String finishRate) {
		this.finishRate = finishRate;
	}

	public String getPassRate() {
		return passRate;
	}

	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	public String getCardBeginDate() {
		return cardBeginDate;
	}

	public void setCardBeginDate(String cardBeginDate) {
		this.cardBeginDate = cardBeginDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCardmake() {
		return cardmake;
	}

	public void setCardmake(String cardmake) {
		this.cardmake = cardmake;
	}

	public String getCardMakeDate() {
		return cardMakeDate;
	}

	public void setCardMakeDate(String cardMakeDate) {
		this.cardMakeDate = cardMakeDate;
	}

	public String getCardAudting() {
		return cardAudting;
	}

	public void setCardAudting(String cardAudting) {
		this.cardAudting = cardAudting;
	}

	public String getCardAudtingDate() {
		return cardAudtingDate;
	}

	public void setCardAudtingDate(String cardAudtingDate) {
		this.cardAudtingDate = cardAudtingDate;
	}

	public Integer getCardState() {
		return cardState;
	}

	public void setCardState(Integer cardState) {
		this.cardState = cardState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getR_Type() {
		return R_Type;
	}

	public void setR_Type(Integer r_Type) {
		R_Type = r_Type;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getProduceM_Id() {
		return produceM_Id;
	}

	public void setProduceM_Id(Integer produceM_Id) {
		this.produceM_Id = produceM_Id;
	}

	public String getProduceM_Name() {
		return produceM_Name;
	}

	public void setProduceM_Name(String produceM_Name) {
		this.produceM_Name = produceM_Name;
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

	public String getProduceM_Spec() {
		return produceM_Spec;
	}

	public void setProduceM_Spec(String produceM_Spec) {
		this.produceM_Spec = produceM_Spec;
	}

	public String getProduceM_EndTime() {
		return produceM_EndTime;
	}

	public void setProduceM_EndTime(String produceM_EndTime) {
		this.produceM_EndTime = produceM_EndTime;
	}

	public Long getProduceM_State() {
		return produceM_State;
	}

	public void setProduceM_State(Long produceM_State) {
		this.produceM_State = produceM_State;
	}

	public Integer getProduceM_RouteNo() {
		return produceM_RouteNo;
	}

	public void setProduceM_RouteNo(Integer produceM_RouteNo) {
		this.produceM_RouteNo = produceM_RouteNo;
	}

	public String getD_Situation() {
		return D_Situation;
	}

	public void setD_Situation(String d_Situation) {
		D_Situation = d_Situation;
	}

}