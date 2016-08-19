package com.ss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.ss.entity.User;
import com.ss.service.UserService;
import com.ss.service.factory.ServiceFactory;
import com.ss.util.LoggerUtil;
/**
 * 用于后台登录的Servlet
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {
	private static Logger logger = LoggerUtil.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		String result = "";
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		//登录
		if(action != null && action.equals("enter")) {
			if(userId != null && password != null && userId.equals("1001") && password.equals("admin")) {
				User u = ServiceFactory.getUserService().getEntityByUiAndPw(userId, password);
				if(u != null) {
					session.setAttribute("user", u);
					resp.sendRedirect("boot.jsp");
				} else {
					resp.sendRedirect("login-failed.jsp");
				}
			} else {
				resp.sendRedirect("login-failed.jsp");
			}
		//退出
		} else if(action != null && action.equals("quit")) {
			session.removeAttribute("user");
			resp.sendRedirect("login.html");
		}
		
	}
	
}
