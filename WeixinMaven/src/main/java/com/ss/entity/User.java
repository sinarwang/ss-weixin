package com.ss.entity;

import java.util.Date;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ss.entity.dto.UserDTO;

public class User implements java.io.Serializable {
	private Integer id;
	private String openId;
	private String userId;
	private String userName;
	private String password;
	private Date createDate;
	private Integer audit;
	private Integer disable;
	
	public String getNameInDB() {
		return "weixin_user1";
	}
	/**
	 * 通过传输层UserDTO将初始化User。
	 * @param userDTO UserDTO类的对象
	 * @return 若初始化成功，返回true；否则返回false。
	 */
	public boolean initFromDTO(UserDTO userDTO) {
		if(userDTO.getPassword().equals(userDTO.getConfirm_password()) && userDTO.getOpenId() != null && !userDTO.getOpenId().equals("")) {
			openId = userDTO.getOpenId();
			userName = userDTO.getUserName();
			password = userDTO.getPassword();
			userId = userDTO.getUserId();
			createDate = new Date();
			return true;
		}
		return false;
	}
	/**
	 * 根据ResultSet对用户（User）进行初始化
	 * @param rs 结果集ResultSet
	 */
	public void initFromRs(ResultSet rs) {
		Field[] fields = User.class.getDeclaredFields();
		try {
			for(Field f : fields) {
				String fieldName = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "set" + fieldName;
				Class fieldType = f.getType();
				Method m = User.class.getDeclaredMethod(methodName, fieldType);
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
				} else if(fieldType.equals(Date.class)) {
					try {
						Date date = new Date(rs.getTimestamp(fieldName).getTime());
						m.invoke(this, date);
					} catch (Exception e) {
						m.invoke(this, null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public Integer getDisable() {
		return disable;
	}
	public void setDisable(Integer disable) {
		this.disable = disable;
	}
}
