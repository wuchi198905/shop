package com.example.shop.api.constant;


import com.example.shop.api.bean.WxConfig;
import com.example.shop.api.mapper.WxConfigMapper;
import com.example.shop.base.filter.JwtFilter;
import com.example.shop.pub.Utils.DateUtil;
import com.example.shop.pub.Utils.RedisUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.awt.*;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;

@Component//这个注入与否影响不大
public class CommonWechatUtil {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private WxConfigMapper wxConfigMDAO;
    private static CommonWechatUtil commonUtil;

    @PostConstruct
    public void init() {
        commonUtil = this;
        log.info("加载JwtFilter***********");
    }

    private static Logger log = LoggerFactory.getLogger(CommonWechatUtil.class);
    
    // 凭证获取（GET）
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
    @SuppressWarnings("deprecation")
	public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// 把返回的结果转换为JSON对象
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSON.parseObject(result);
		}
		return jsonObject;
	}
	
    /**
     * 发送https请求
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return jsonObject;
    }
    
    /**
     * 获取接口访问凭证
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return


     */
    public static synchronized String getAccessToken(String appid, String appsecret) {


    	WxConfig wxConfig = new WxConfig();
    	wxConfig.setConfigId(WxConfig.ACCESS_TOKEN_TIME);
    	wxConfig = commonUtil.wxConfigMDAO.selectById(wxConfig);

    	AccessToken token = null;
    	//如果之前获取accessToken时间 > 119分钟，重新获取accessToken
    	if (Long.parseLong(TimeUtils.nowTimeSdf(DateUtil.now())) - Long.parseLong(wxConfig.getConfigKey()) > 19900) {

    		 String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
          //  System.out.println(requestUrl);
            // 发起GET请求获取凭证
            JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
            if (null != jsonObject) {
                try {
                    token = new AccessToken();
                    token.setAccessToken(jsonObject.getString("access_token"));
                    token.setExpiresIn(jsonObject.getIntValue("expires_in"));

                    //更新accessToken
                    WxConfig config = new WxConfig();
                    config.setConfigId(WxConfig.ACCESS_TOKEN_TIME);
                    config.setConfigKey(TimeUtils.nowTimeSdf(DateUtil.now()));
                    config.setConfigValue(jsonObject.getString("access_token"));
                    config.setUpdateTime(new Date());
                    commonUtil.wxConfigMDAO.updateById(config);
                } catch (JSONException e) {
                    token = null;
                    // 获取token失败
                    log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
                }
            }
            return jsonObject.getString("access_token");
		}

    	return wxConfig.getConfigValue();
    	
    }
    /**
     * URL编码（utf-8）
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据内容类型判断文件扩展名
     *
     * @param contentType 内容类型
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }





    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    /**
     * 查询菜单
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static JSONObject getMenu(String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
//        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpsRequest(url, "POST", null);

        return jsonObject;
    }

    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    /**
     * 查询菜单
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int deleteMenu(String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
        // 调用接口创建菜单
        JSONObject jsonObject = httpsRequest(url, "POST", null);

        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}


