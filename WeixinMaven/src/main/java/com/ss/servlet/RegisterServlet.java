package com.ss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ss.entity.User;
import com.ss.entity.dto.UserDTO;
import com.ss.service.UserService;
import com.ss.service.factory.ServiceFactory;
import com.ss.util.LoggerUtil;

public class RegisterServlet extends HttpServlet {
	private static Logger logger = LoggerUtil.getInstance();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//先对action进行验证，看是已经过验证没有该用户的还是未经验证的，未经验证的action=unCheck，验证过是action=check
		if(req.getParameter("action") != null && req.getParameter("action").equals("unCheck")) {
			if(ServiceFactory.getUserService().isExist(req.getParameter("openId"))) {
				resp.sendRedirect("register-finish.jsp");
			} else {
				resp.sendRedirect("register.jsp?openId=" + req.getParameter("openId"));
			}
		} else if(req.getParameter("action") != null && req.getParameter("action").equals("check")) {
			PrintWriter writer = resp.getWriter();
			User u = new User();
			UserDTO userDTO = new UserDTO();
			logger.error("--------------RegisterServlet中获得openId：" + req.getParameter("openId") + "--------------------");
			userDTO.setOpenId(req.getParameter("openId"));
			userDTO.setUserId(req.getParameter("userId"));
			userDTO.setUserName(req.getParameter("userName"));
			userDTO.setPassword(req.getParameter("password"));
			userDTO.setConfirm_password(req.getParameter("confirm_password"));
			if(req.getParameter("openId") == null || req.getParameter("openId").equals("")) {
				writer.println("failed5");
			}
			if(!req.getParameter("userId").equals("") && !req.getParameter("userName").equals("") && !req.getParameter("password").equals("") && !req.getParameter("confirm_password").equals("")) {
				if(u.initFromDTO(userDTO)) {
					if(ServiceFactory.getUserService().addUser(u)) {
						writer.println("succeed");
					} else {
						writer.println("failed1");
					}
				} else {
					writer.println("failed2");
				}
			} else {
				writer.println("failed3");
			}
			
			writer.flush();
			writer.close();
		}
	}
}
