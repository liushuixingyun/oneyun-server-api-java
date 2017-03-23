package com.oneyun.sapi;

import com.oneyun.sapi.config.SapiSdkConfig;
import com.oneyun.sapi.utils.HttpClientUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息API
 * Created by zhangxb on 2017/3/20.
 */
public class MessageSapiSdk extends SapiSdkConfig {
    public MessageSapiSdk(String url, String certId, String secretKey) {
        super(url, certId, secretKey);
    }

    //模板操作
    public static final String msg_template =  "msg/template";

    //发送USSD模板短信
    public static final String msg_ussd_send =  "msg/ussd/send";
    //群发模板闪印任务接口
    public static final String msg_ussd_mass_task =  "msg/ussd/mass/task";
    //发送结果查询
    public static final String msg_ussd =  "msg/ussd";

    //模板短信单发
    public static final String msg_sms_send =  "msg/sms/send";
    //模板短信群发
    public static final String msg_sms_mass_task =  "msg/sms/mass/task";
    //单发结果查询
    public static final String msg_sms=  "msg/sms";
    /**
     * 发送USSD模板短信
     */
    /**
     * 群发模板闪印任务接口
     */
    /**
     * 添加模板
     * name 名称
     * type 类型（目前有两个有效值：msg_sms、msg_ussd）不填默认为 msg_sms
     * content 模板内容
     * remark 备注
     */
    public String  addTemplate(String appId, String name, String type, String content, String remark) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        params.put("type",type);
        params.put("content",content);
        params.put("remark",remark);
        return HttpClientUtils.doPost(getUrl(msg_template),getSecretKey(),getCertId(),appId,params);
    }
    /**
     * 修改模板
     * tempId 模板ID
     * name 名称
     * type 类型（目前有两个有效值：msg_sms、msg_ussd）不填默认为 msg_sms
     * content 模板内容
     * remark 备注
     */
    public String  editTemplate(String appId,String tempId, String name, String type, String content, String remark) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        params.put("type",type);
        params.put("content",content);
        params.put("remark",remark);
        return HttpClientUtils.doPost(getUrl(msg_template+"/"+tempId),getSecretKey(),getCertId(),appId,params);
    }
    /**
     *  删除模板
     * tempId 模板ID
     */
    public String  deleteTemplate(String appId, String tempId) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        return HttpClientUtils.doDelete(getUrl(msg_template+"/"+tempId),getSecretKey(),getCertId(),appId,params);
    }
    /**
     *  查询模板
     * tempId 模板ID
     */
    public String  selectTemplate(String appId, String tempId,Integer pageNo,Integer pageSize) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        if(org.apache.commons.lang.StringUtils.isNotEmpty(tempId)){
            return HttpClientUtils.doGet(getUrl(msg_template+"/"+tempId),getSecretKey(),getCertId(),appId,params);
        }else{
            return HttpClientUtils.doGet(getUrl(msg_template),getSecretKey(),getCertId(),appId,params);
        }
    }
    /**
     * 闪印单发
     * tempId 模板ID
     * mobile 手机号码
     * tempArgs 模板内容
     */
    public String  sendUssd(String appId,String mobile, String tempId, String tempArgs) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("mobile",mobile);
        params.put("tempId",tempId);
        params.put("tempArgs",tempArgs);
        return HttpClientUtils.doPost(getUrl(msg_ussd_send),getSecretKey(),getCertId(),appId,params);
    }
    /**
     * 闪印群发
     * taskName 群发任务名称
     * tempId 模板ID
     * mobiles 手机号码
     * tempArgs 模板内容
     * sendTime 手机号码集合
     */
    public String  addUssdMassTask(String appId,String taskName, String tempId, String mobiles, String tempArgs, String sendTime) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("taskName",taskName);
        params.put("tempId",tempId);
        params.put("tempArgs",tempArgs);
        params.put("mobiles",mobiles);
        params.put("sendTime",sendTime);
        return HttpClientUtils.doPost(getUrl(msg_ussd_mass_task),getSecretKey(),getCertId(),appId,params);
    }
    /**
     * 闪印发送结果查询
     * tempId 模板ID
     * mobile 手机号码
     * tempArgs 模板内容
     */
    public String  getUssdSendResult(String appId,String msgKey, Integer pageNo, Integer pageSize) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("pageNo",pageNo);
        params.put("pageSize",pageSize);
        if(org.apache.commons.lang.StringUtils.isNotEmpty(msgKey)) {
            return HttpClientUtils.doGet(getUrl(msg_ussd+"/"+msgKey), getSecretKey(), getCertId(), appId, params);
        }else{
            return HttpClientUtils.doGet(getUrl(msg_ussd), getSecretKey(), getCertId(), appId, params);
        }
    }
    /**
     * 短信单发
     * tempId 模板ID
     * mobile 手机号码
     * tempArgs 模板内容
     */
    public String sendSms(String appId,String mobile, String tempId, String tempArgs) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("mobile",mobile);
        params.put("tempId",tempId);
        params.put("tempArgs",tempArgs);
        return HttpClientUtils.doPost(getUrl(msg_sms_send),getSecretKey(),getCertId(),appId,params);
    }
    /**
     * 短信群发
     * taskName 群发任务名称
     * tempId 模板ID
     * mobiles 手机号码
     * tempArgs 模板内容
     * sendTime 手机号码集合
     */
    public String  addSmsMassTask(String appId,String taskName, String tempId, String mobiles, String tempArgs, String sendTime) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("taskName",taskName);
        params.put("tempId",tempId);
        params.put("tempArgs",tempArgs);
        params.put("mobiles",mobiles);
        params.put("sendTime",sendTime);
        return HttpClientUtils.doPost(getUrl(msg_sms_mass_task),getSecretKey(),getCertId(),appId,params);
    }
    /**
     * 短信发送结果查询
     * tempId 模板ID
     * mobile 手机号码
     * tempArgs 模板内容
     */
    public String  getSmsSendResult(String appId,String msgKey, Integer pageNo, Integer pageSize) throws IOException, NoSuchAlgorithmException, InvalidKeyException, KeyManagementException {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("pageNo",pageNo);
        params.put("pageSize",pageSize);
        if(org.apache.commons.lang.StringUtils.isNotEmpty(msgKey)) {
            return HttpClientUtils.doGet(getUrl(msg_sms+"/"+msgKey), getSecretKey(), getCertId(), appId, params);
        }else{
            return HttpClientUtils.doGet(getUrl(msg_sms), getSecretKey(), getCertId(), appId, params);
        }
    }
}
