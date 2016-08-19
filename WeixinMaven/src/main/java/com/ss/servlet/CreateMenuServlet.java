package com.ss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import com.ss.entity.pojo.AccessToken;
import com.ss.entity.pojo.Button;
import com.ss.entity.pojo.CommonButton;
import com.ss.entity.pojo.ComplexButton;
import com.ss.entity.pojo.Menu;
import com.ss.util.LoggerUtil;
import com.ss.util.accessToken.AccessTokenThread;
import com.ss.util.accessToken.WXUtil;
/**
 * 创建菜单的Servlet服务。通过请求到该Servlet。（同时通过传递openThread和flag参数，可以对获取AccessToken的线程进行设定）
 * @author Administrator
 *
 */
public class CreateMenuServlet extends HttpServlet {
	private static Logger logger = LoggerUtil.getInstance();
	private static Thread t = null;
	/**
	 * 线程数，用于控制线程只能有一个
	 */
	private static int threadNum = 0;
	/**
	 * 用于开启线程的密匙
	 */
	private static String openThread = "ss123456";
	/**
	 * 用于控制线程开关的标记，true为开，false为关
	 */
	private static String flag = null;
	
	public static int getThreadNum() {
		return threadNum;
	}
	public static void setThreadNum(int threadNum) {
		CreateMenuServlet.threadNum = threadNum;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String str = req.getParameter("openThread");
		flag = req.getParameter("flag");
		if(str != null && str.equals(openThread) && threadNum == 0) {
			//开启线程
			t = new Thread(new AccessTokenThread());
			t.start();
			threadNum ++;
    		logger.info("---------------AccessTokenThread线程开启成功-----------------");
		}
		if(flag != null && flag.equals("false")) {
			AccessTokenThread.setFlag(false);
			logger.info("---------------flag=" + AccessTokenThread.isFlag() + "-----------------threadNum=" + threadNum);
		} else {
			AccessTokenThread.setFlag(true);
			logger.info("---------------flag=" + AccessTokenThread.isFlag() + "-----------------threadNum=" + threadNum);
		}
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String createMenu = req.getParameter("createMenu");
		//获取全局的AccessToken的pojo对象
		AccessToken at = AccessTokenThread.getAccessToken();
		PrintWriter writer = resp.getWriter();
		logger.info(at.getToken());
		if (null != at) {
            // 调用接口创建菜单
			if(createMenu != null && createMenu.equals("true")) {
				int result = WXUtil.createMenu(getMenu(), at.getToken());
				// 判断菜单创建结果
	            if (0 == result) {
		    		writer.append("Menu create succeed!");
		    		logger.info("菜单创建成功！");
	            } else {
	            	writer.append("Menu create failed! And the ErrorCode：" + result);
	            	logger.info("菜单创建失败，错误码：" + result);
	            }
			} else {
				writer.append("Don't need to create Menu!");
			}
        } else {
        	writer.append("AccessToken is null!");
    		logger.info("AccessToken 为 null!");
        }
		writer.flush();
 		writer.close();
	}
	
	private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("单件流");
        btn11.setType("click");
        btn11.setKey("11");
        
        CommonButton btn12 = new CommonButton();
        btn12.setName("生产台账");
        btn12.setType("click");
        btn12.setKey("12");
        
        CommonButton btn13 = new CommonButton();
        btn13.setName("生产工单");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn21 = new CommonButton();
        btn21.setName("超排程");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("生产报废");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("生产异常");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("荒管投料");
        btn24.setType("click");
        btn24.setKey("24");
        
        CommonButton btn25 = new CommonButton();
        btn25.setName("成品查询");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("打包查询");
        btn31.setType("click");
        btn31.setKey("31");
        
        CommonButton btn32 = new CommonButton();
        btn32.setName("库存查询");
        btn32.setType("click");
        btn32.setKey("32");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("员工查询");
        mainBtn1.setSub_button(new CommonButton[] { btn11,btn12,btn13});
        
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("管理查询");
        mainBtn2.setSub_button(new CommonButton[] { btn21,btn22,btn23,btn24,btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("了解更多");
        mainBtn3.setSub_button(new CommonButton[] { btn31,btn32});
        
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }
	
	public static void main(String[] args) {
    	JSONObject jsonMenu = JSONObject.fromObject(getMenu());
    	System.out.println(jsonMenu);
	}
}
