package com.ss.util.accessToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import com.ss.entity.pojo.AccessToken;
import com.ss.entity.pojo.Menu;
import com.ss.util.LoggerUtil;

public class WXUtil {
    private static Logger logger = LoggerUtil.getInstance();
    
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
    public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    
    public static AccessToken getAccessToken(String appId,String appSecret) {
        String reqUrl = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject jsonObject = httpRequest(reqUrl, "GET", null);
        AccessToken access_Token = (AccessToken)JSONObject.toBean(jsonObject, AccessToken.class);
        if (null != jsonObject) {
            try {
                access_Token = new AccessToken();
                access_Token.setToken(jsonObject.get("access_token").toString());
                access_Token.setExpiresIn((Integer)jsonObject.get("expires_in"));
                AccessTokenThread.setAccessToken(access_Token);
            } catch (Exception e) {
                access_Token = null;
                // 获取token失败
                logger.error("获取token失败 errcode:" + (Integer)jsonObject.get("errcode") + " errmsg:" + jsonObject.get("errmsg").toString());
            }
        }
        
        return access_Token;
    }
    
    /**
     * 调用微信JS接口的临时票据
     * 
     * @param access_token 接口访问凭证
     * @return
     */
    public static String getJsApiTicket(String access_token) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        String requestUrl = url.replace("ACCESS_TOKEN", access_token);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        String ticket = null;
        if (null != jsonObject) {
            try {
                ticket = jsonObject.getString("ticket");
            } catch (JSONException e) {
                // 获取token失败
                logger.error("获取token失败 errcode:{} errmsg:{}" + jsonObject.getInt("errcode") + jsonObject.getString("errmsg"));
            }
        }
        return ticket;
    }
    
    public static JSONObject httpRequest(String reqUrl, String reqMethod, String outStr) {
        StringBuffer buffer = new StringBuffer();
        JSONObject jsonObject = null;
        try {
            TrustManager[] tm = { new MyX509TrustManager()};
            SSLContext  sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            
            URL url = new URL(reqUrl);
            HttpsURLConnection httpUrlCon = (HttpsURLConnection)url.openConnection();
            httpUrlCon.setSSLSocketFactory(ssf);
            
            httpUrlCon.setDoInput(true);
            httpUrlCon.setDoOutput(true);
            httpUrlCon.setUseCaches(false);
            
            httpUrlCon.setRequestMethod(reqMethod);
            
            if ("GET".equalsIgnoreCase(reqMethod)) {
                httpUrlCon.connect();
            }
            
            if (null != outStr) {
                OutputStream outputStream = httpUrlCon.getOutputStream();
                outputStream.write(outStr.getBytes("utf-8"));
                outputStream.close();
            }
            
            InputStream inputStream = httpUrlCon.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            
            bufferedReader.close();
            reader.close();
            inputStream.close();
            httpUrlCon.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
           
        } catch (ConnectException ex) {
            logger.error("Weixin server connection timed out.");
        } catch (Exception e) {
            logger.error("https request error:{}", e);
        }
        
        return jsonObject;
    }
    
    public static int createMenu(Menu menu,String accessToken) {
        int result = 0;
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonMenu = JSONObject.fromObject(menu);
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu.toString());
        
        if (jsonObject != null) {
            if (0 != (Integer)jsonObject.get("errcode")) {
                result = (Integer)jsonObject.get("errcode");
                logger.error("创建菜单失败errcode:" + result + "errmsg:" + jsonObject.get("errmsg").toString());
            }
        }
        
        return result;
    }
    
}