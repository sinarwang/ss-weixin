package com.ss.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ss.dao.UserDAO;
import com.ss.entity.User;
import com.ss.util.LoggerUtil;

public class UserService {
	private static UserService userService = null;
	private static Logger logger = LoggerUtil.getInstance();
	private static UserDAO userDAO = UserDAO.getInstance();
	
	public static UserService getInstance() {
		if(userService == null) {
			userService = new UserService();
		}
		return userService;
	}
	/**
	 * 添加用户
	 * @param u 用户对象
	 * @return 若为true，则添加成功；若为false，则添加失败。
	 */
	public boolean addUser(User u) {
		logger.error("-------------User Service begin add User!-----------------");
		User user = new User();
		//通过OpenId查有没有该用户。
		user = userDAO.getEntityByOpenId(u.getOpenId());
		if(user == null) {
			userDAO.add(u);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 通过openId判断用户是否存在。
	 * @param 
	 * @return 若存在，返回true；若不存在，返回false。
	 */
	public boolean isExist(String openId) {
		if(userDAO.getEntityByOpenId(openId) != null) {
			return true;
		}
		return false;
	}
	/**
	 * 根据工号查找用户。
	 * @param userId 工号
	 * @return 查找到的用户类的对象
	 */
	public User getUserByUserId(String userId) {
		User u = userDAO.getEntityByUserId(userId);
		return u;
	}
	/**
	 * 根据工号和密码取得用户类。
	 * @param userId 工号
	 * @param password 密码
	 * @return 用户类的对象，若没有查找到用户，返回null。
	 */
	public User getEntityByUiAndPw(String userId, String password) {
		User u = userDAO.getEntityByUiAndPw(userId,password);
		return u;
	}
	
	/**
	 * 获得所有的User对象。
	 * @return 装有User对象的List容器。
	 */
	public List<User> getUsers() {
		List<User> users = userDAO.getEntitys();
		return users;
	}
	/**
	 * 更新User对象
	 * @param u User对象
	 * @return 若成功，返回true；若失败，返回false；
	 */
	public boolean update(User u) {
		if(userDAO.update(u)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 删除User对象
	 * @param u User对象
	 * @return 若成功，返回true；若失败，返回false；
	 */
	public boolean deleteUser(String openId) {
		User u = new User();
		u.setOpenId(openId);
		if(userDAO.delete(u)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 根据id查找用户。
	 * @param id 用户id
	 * @return 查找到的用户类的对象
	 */
	public User getUserById(int id) {
		User u = userDAO.getEntityById(id);
		return u;
	}
}
