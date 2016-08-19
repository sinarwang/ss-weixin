package com.ss.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ss.entity.User;
import com.ss.service.UserService;
import com.ss.service.factory.ServiceFactory;
/**
 * 用于用户管理的相关操作的Servlet。
 * @author Administrator
 *
 */
public class UserServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		UserService userService = ServiceFactory.getUserService();
		if(u == null) {
			return;
		}
		String action = req.getParameter("action");
		int id = 0;
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		if(action != null && action.equals("audit")) {
			String audit = req.getParameter("audit");
			if(audit != null && audit.equals("audit")) {
				User user = userService.getUserById(id);
				user.setAudit(1);
				userService.update(user);
			} else if(audit != null && audit.equals("unaudit")) {
				User user = userService.getUserById(id);
				user.setAudit(0);
				userService.update(user);
			}
		} else if(action != null && action.equals("disable")) {
			String disable = req.getParameter("disable");
			if(disable != null && disable.equals("disable")) {
				User user = userService.getUserById(id);
				user.setDisable(1);
				userService.update(user);
			} else if(disable != null && disable.equals("able")) {
				User user = userService.getUserById(id);
				user.setDisable(0);
				userService.update(user);
			}
		}
	}

}
