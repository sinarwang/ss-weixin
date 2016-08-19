package com.ss.entity;

/**
* 实体类DataDir（数据字典）
* @author Administrator
*
*/
public class DataDir {
	private int dataId;
	private String dataName;
	/**
	 * 数据类别。产品类别（productCategory）为1；用户级别（userLevel）为2。
	 */
	private int dataCategory;
	
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public int getDataCategory() {
		return dataCategory;
	}
	public void setDataCategory(int dataCategory) {
		this.dataCategory = dataCategory;
	}
	
	public String getNameInDB() {
		return "weixin_dataDir";
	}
}
