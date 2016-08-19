package com.ss.util;

import java.util.Date;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;  

import org.apache.log4j.Logger;

import com.ss.entity.pojo.AccessToken;
import com.ss.util.accessToken.AccessTokenThread;
import com.ss.util.accessToken.WXUtil;

public class Sign {
	private static String jsapi_ticket = "";
	private static Date last_js_time = new Date();
	private static Logger logger = LoggerUtil.getInstance();
	
    public static String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public static void setJsapi_ticket(String jsapi_ticket) {
		Sign.jsapi_ticket = jsapi_ticket;
	}
	public static Date getLast_js_time() {
		return last_js_time;
	}
	public static void setLast_js_time(Date last_js_time) {
		Sign.last_js_time = last_js_time;
	}

	/*public static void main(String[] args) {
		String t = WXUtil.getAccessToken("wxa7f327e357e90111", "e5f948380be99c5a79971d253eded737").getToken();
		
		String jsapi_ticket = WXUtil.getJsApiTicket(t);

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };*/
	
    
    public static Map<String, String> getAuthorityCheck(String url) {
    	AccessToken accessToken = AccessTokenThread.getAccessToken();
    	logger.error("accessToken=" + accessToken);
    	if((System.currentTimeMillis() - last_js_time.getTime()) > 3600000) {
    		String token = null;
    		try {
				token = accessToken.getToken();
			} catch (NullPointerException e) {
				accessToken = WXUtil.getAccessToken(AccessTokenThread.getAppId(), AccessTokenThread.getSecret());
				token = accessToken.getToken();
			}
    		jsapi_ticket = WXUtil.getJsApiTicket(token);
    		last_js_time = new Date();
    	}
    	logger.error("jsapi_ticket=" + jsapi_ticket);
    	Map<String, String> ret = sign(jsapi_ticket, url);
    	return ret;
    }

    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);
        logger.error("string1=" + string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            logger.error("signature=" + signature);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    
}
