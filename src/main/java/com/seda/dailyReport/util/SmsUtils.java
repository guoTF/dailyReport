package com.seda.dailyReport.util;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsUtils {

    private static final Logger logger = LoggerFactory.getLogger("sms");

    private static String url;

    @Value("${msg.url}")
    private String _url;

    private static String account;
    @Value("${msg.LoginName}")
    private String _account;

    private static String password;

    @Value("${msg.Password}")
    private String _password;

    /*private static String strCode =
        "0:提交失败;2:提交成功;400:非法ip访问;401:帐号不能为空;402:密码不能为空;403:手机号码不能为空;4030:手机号码已被列入黑名单;" +
        "404:短信内容不能为空;405:用户名或密码不正确;4050:账号被冻结;4051:剩余条数不足;4052:访问ip与备案ip不符;406:手机格式不正确;" +
        "407:短信内容含有敏感字符;4070:签名格式不正确;4071:没有提交备案模板;4072:短信内容与模板不匹配;4073:短信内容超出长度限制;" +
        "408:您的帐户疑被恶意利用,已被自动冻结,如有疑问请与客服联系;";*/

    @PostConstruct
    public void init() {
        SmsUtils.url = _url;
        SmsUtils.account = _account;
        SmsUtils.password = _password;
    }
/*    public static String getCode(String parse){
        String[] array=strCode.split(";");
        String ret="";
        for (int i = 0; i <array.length ; i++) {
            String str=array[i];
            if (str.contains(parse)){  //contains代码有bug，不能使用
                ret= str.substring(str.indexOf(":")+1,str.length());
            }
        }
        return ret;
    }*/

    public static String sendSms(String mobile, String content,String page) {
        logger.info("进入互亿短信发送类");
        boolean isNotReSend=true;
        if(mobile.contains("_")){
            isNotReSend=false;
            mobile=mobile.substring(0, mobile.indexOf("_"));
        }
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
        NameValuePair[] data = {//提交短信
            new NameValuePair("account", account), //查看用户名请登录用户中心->验证码、通知短信->帐户及签名设置->APIID
            new NameValuePair("password", password),  //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
            //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
            new NameValuePair("mobile", mobile), new NameValuePair("content", content),};
        method.setRequestBody(data);
        logger.info("获取到互亿短信发送的账号");
        String msg = ":";
        try {
            logger.info("开始互亿短信发送");
            client.executeMethod(method);
            String SubmitResult = method.getResponseBodyAsString();
            logger.info("互亿短信发送返回结果：\n" + SubmitResult);
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            msg = code + ":" + root.elementText("msg");
            String codeMsg = root.elementText("msg");
            //String code=(msg.substring(0,msg.indexOf(":")));
            //msg=msg.substring(msg.indexOf(":")+1,msg.length());
            logger.info("互亿短信发送返回：" + msg);
            if ("2".equals(code)) {
                logger.info("互亿短信发送成功,手机号:" + mobile + ",短信发送内容:" + content);
//                String smsCodeMsg = "成功编号："+SmsCodeMsg.SMSTRUE.getCode()+",原因:"+SmsCodeMsg.SMSTRUE.getValue();
            } else {
                logger.info("互亿短信发送失败,失败编号:" + code + ",手机号:" + mobile + ",短信发送内容:" + content);
//                String smsCodeMsg = "失败编号:" + SmsCodeMsg.smsCodeMsg(Integer.parseInt(code)).getCode()
//                        + ",原因:" + SmsCodeMsg.smsCodeMsg(Integer.parseInt(code)).getValue();
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return msg;
    }

}
