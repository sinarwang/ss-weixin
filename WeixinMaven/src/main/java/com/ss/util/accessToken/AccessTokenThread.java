package com.ss.util.accessToken;

import java.util.Date;

import org.apache.log4j.Logger;
import com.ss.entity.pojo.AccessToken;
import com.ss.servlet.CreateMenuServlet;
import com.ss.util.LoggerUtil;
import com.ss.util.Sign;
/**
 * 用于获取accessToken的线程，同时是获取accessToken的地方。
 * @author Administrator
 *
 */
public class AccessTokenThread implements Runnable {
	private static Logger logger = LoggerUtil.getInstance();
	// 保存AccessToken的pojo类的地方
	private static AccessToken accessToken = null;
	// 第三方用户唯一凭证
	private static final String APP_ID = "wx3bf9b6bb8a8fa231";
	// 第三方用户唯一凭证密钥
    private static final String SECRET = "d3a6e0a7c3628706df0d85ef4713e936";
    private static boolean flag = true;
	
	public static String getAppId() {
		return APP_ID;
	}
	public static String getSecret() {
		return SECRET;
	}

	/**
     * 获取全局的AccessToken的pojo类的对象。
     * @return AccessToken类的对象
     */
	public static AccessToken getAccessToken() {
		return accessToken;
	}
	public static void setAccessToken(AccessToken accessToken) {
		AccessTokenThread.accessToken = accessToken;
	}
	public static boolean isFlag() {
		return flag;
	}
	/**
	 * 设置线程的开关。
	 * @param flag 若为true则线程开，若为false，则线程关。
	 */
	public static void setFlag(boolean flag) {
		AccessTokenThread.flag = flag;
	}
	
	@Override
	public void run() {
		while(flag) {
			// 调用接口获取access_token
			accessToken = WXUtil.getAccessToken(APP_ID, SECRET);
			Sign.setJsapi_ticket(WXUtil.getJsApiTicket(accessToken.getToken()));
			//System.out.println("create time:" + new Date() + "initServlet Succeed! access_token=" + accessToken.getToken() + ";" + "     ExpiresIn:" + accessToken.getExpiresIn());
			logger.error("create time:" + new Date() + "initServlet Succeed! access_token=" + accessToken.getToken() + ";" + "     ExpiresIn:" + accessToken.getExpiresIn());
			try {
				Thread.sleep(7000000);
			} catch (InterruptedException e) {
				logger.error("-----------------获取access_token的线程出错！-------------------");
				e.printStackTrace();
			}
		}
		CreateMenuServlet.setThreadNum(0);
		logger.info("------------AccessTokenThread线程关闭成功！--------------threadNum=" + CreateMenuServlet.getThreadNum());
	}
}
