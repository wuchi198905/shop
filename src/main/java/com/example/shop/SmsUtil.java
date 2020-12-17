package com.example.shop;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsUtil {
    private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    private static final String SIGN_NAME = "沧港物联";
    private static final String TEMPLATE_CODE = "SMS_123674347";
    //发货方支付运费，通知车辆
    private static final String TELL_VEHICLE = "SMS_138078164";
    //经理人接单后邀请车辆
    private static final String INVITE_VEHICLE = "SMS_138063292";
    private static final String ONE_NAME = "发货方发布订单通知经理人";
    //订单失效通知货主
    private static final String OrdersPastDue   = "SMS_141595059";
    //发货方发布订单通知经理人
    private static final String TELL_MANAGER = "SMS_138073160";
    //身份验证验证码
    private static final String AUTHENTICATION = "SMS_186596969";
//	//登录确认验证码
//	private static final String LOGIN_INTO = "SMS_138550143";
//	//登录异常验证码
//	private static final String LOGIN_ABNORMITY = "SMS_138550142";
//	//用户注册验证码
//	private static final String REGISTER = "SMS_186596969";
//	//修改密码验证码
//	private static final String UPDATE_PWD = "SMS_186596969";
//	//信息变更验证码
//	private static final String INFORMATION_CHANGE = "SMS_186596969";
    /**
     * 港务局审核结果通知
     */
    private static final String INFORMATION_CHANGE = "SMS_195226899";
    /**
     * 港务局审核结果通知
     */
    private static final String INFORMATION_black = "SMS_195261872";

    //private static final String appkey = "LTAIJ78v5bACCJQ3";
    //private static final String appsecret = "nEhdTNG5qucduajWzPqro1pZLywi3T";
    private static final String appkey = "LTAIlRgwx2Oj8ecM";
    private static final String appsecret = "iEIl2sU1WYVPcm8pyd3Sbh7KPJC4bU";
    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static IAcsClient acsClient = null;

    static {
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", appkey, appsecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            acsClient = new DefaultAcsClient(profile);
        } catch (ClientException e) {
            logger.error("发送短信初始化出错", e);
        }
    }

    public static SendSmsResponse sendCheckcode(String mobile, String checkcode)  {
        logger.debug("发送短信," + mobile + "--" + checkcode);
        return sendSms(mobile, AUTHENTICATION, "{\"code\":\""+checkcode+"\"}", SIGN_NAME);
    }

    private static SendSmsResponse sendSms(String mobile, String template, String param, String sign) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(AUTHENTICATION);//TEMPLATE_CODE
        request.setTemplateParam(param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
           // throw new SysException("发送短信出错", e);
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
           // throw new SysException("发送短信出错", e);
        }
        return sendSmsResponse;
    }


    //货主发布订单给经理人发送消息
    public static SendSmsResponse tellCar(String mobile, String content){
        logger.debug("发送短信," + mobile + "--" + content);
        /*
         * 您有新待接受订单，发货人：${contactname}，于${starttime}至${endtime}，从${origin_city}到${cust_city}运输${goods_name}共${weight}吨，如有意向，请尽快打开大宗集运经理人版进行接单操作。
         */

        return tellCar(mobile, TELL_MANAGER, content, SIGN_NAME);

    }

    private static SendSmsResponse tellCar(String mobile, String template, String param, String sign) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(TELL_MANAGER);
        request.setTemplateParam(param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
            //throw new SysException("发送短信出错", e);
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
           // throw new SysException("发送短信出错", e);
        }
        return sendSmsResponse;
    }

    //通知车辆
    public static SendSmsResponse sendCar(String mobile, String content) {
        logger.debug("发送短信," + mobile + "--" + content);
        return sendCar(mobile, TELL_VEHICLE, content, SIGN_NAME);

    }

    private static SendSmsResponse sendCar(String mobile, String template, String param, String sign)  {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(TELL_VEHICLE);
        request.setTemplateParam(param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
           // throw new SysException("发送短信出错", e);
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
            //throw new SysException("发送短信出错", e);
        }
        return sendSmsResponse;
    }

    //邀请车辆通知
    public static SendSmsResponse sendVehicle(String mobile, String content) {
        logger.debug("发送短信," + mobile + "--" + content);
        return sendVehicle(mobile, INVITE_VEHICLE, content, SIGN_NAME);

    }

    private static SendSmsResponse sendVehicle(String mobile, String template, String param, String sign)  {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(INVITE_VEHICLE);
        request.setTemplateParam(param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
            //throw new SysException("发送短信出错", e);
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
            //throw new SysException("发送短信出错", e);
        }
        return sendSmsResponse;
    }
    //过期订单通知货主
    public static SendSmsResponse sendShipper(String mobile, String content) {
        logger.debug("发送短信," + mobile + "--" + content);
        return sendShipper(mobile, OrdersPastDue, content, SIGN_NAME);

    }

    private static SendSmsResponse sendShipper(String mobile, String template, String param, String sign) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(OrdersPastDue);
        request.setTemplateParam(param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
            //throw new SysException("发送短信出错", e);
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
           // throw new SysException("发送短信出错", e);
        }
        return sendSmsResponse;
    }
    public static SendSmsResponse gangwu(String mobile, String template) {
        logger.debug("发送短信," + mobile + "--" + template);
        JSONObject map = new JSONObject();

        map.put("template", template);

        return gangwusendSms(mobile, INFORMATION_CHANGE, map.toJSONString(), SIGN_NAME);
    }
    private static SendSmsResponse gangwusendSms(String mobile, String template, String param, String sign) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(INFORMATION_CHANGE);//TEMPLATE_CODE
        request.setTemplateParam(param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
           // throw new SysException("发送短信出错", e);
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
            //throw new SysException("发送短信出错", e);
        }
        return sendSmsResponse;
    }
    public static SendSmsResponse black(String mobile, String template)  {
        logger.debug("发送短信," + mobile + "--" + template);
        JSONObject map = new JSONObject();
        map.put("template", template);

        return blacklist(mobile, INFORMATION_black, map.toJSONString(), SIGN_NAME);
    }
    private static SendSmsResponse blacklist(String mobile, String template, String param, String sign) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(INFORMATION_black);//TEMPLATE_CODE
        request.setTemplateParam(param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
            //throw new SysException("发送短信出错", e);
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("发送短信出错", e);
           // throw new SysException("发送短信出错", e);
        }
        return sendSmsResponse;
    }
//	//public static void main(String[] args) {
//		SmsUtil.gangwu("15232123027","陈志浩","审核通过");
//	}

}
