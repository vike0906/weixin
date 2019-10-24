package com.vike.weixin.service.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vike.weixin.common.GlobalConstant;
import com.vike.weixin.common.HihippoSign;
import com.vike.weixin.service.QueryService;
import com.vike.weixin.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lsl
 * @createDate: 2019/10/24
 */
@Slf4j
@Service
public class QueryServiceImpl implements QueryService {

    @Value("${system.hp.app_id:app_id}")
    private String APP_ID;
    @Value("${system.hp.app_secret:app_secret}")
    private String APP_SECRET;

    @Override
    public Integer gainVerificationCode(String name, String idCard, String bankCard, String mobile) {

        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("account", APP_ID);
        paramsMap.put("bankCard", bankCard);
        paramsMap.put("idCard", idCard);
        paramsMap.put("mobile", mobile);
        paramsMap.put("name", name);
        paramsMap.put("orderNo", "0000");
        String sign = HihippoSign.value(paramsMap,APP_SECRET);
        log.info("构造sign:{}",sign);
        paramsMap.put("sign", sign);

        Gson gson = new Gson();
        String params = gson.toJson(paramsMap);
        String response = HttpUtil.doPost(GlobalConstant.HP_AUTH_SMS, params);
        log.info("请求发送验证码接口返回结果：{}",response);

        JsonObject data = parseResponse(response);
        if(data!=null){
            String serialNumber = data.getAsJsonPrimitive("serialNumber").getAsString();
            log.info("解析返回结果获得serialNumber：{}",serialNumber);
            return 1;
        }
        log.info("返回结果解析失败");
        return -1;
    }

    /**
     *  1:校验成功
     * -1:未通过校验
     * -2:返回status异常
     * -3:返回结果解析失败
     */
    @Override
    public Integer checkVerificationCode(String code) {
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("account", APP_ID);
        paramsMap.put("identifyingCode", code);
        paramsMap.put("orderNo", "0000");
        paramsMap.put("serialNumber", "1017248584260968449");
        String sign = HihippoSign.value(paramsMap,APP_SECRET);
        log.info("构造sign:{}",sign);
        paramsMap.put("sign", sign);

        Gson gson = new Gson();
        String params = gson.toJson(paramsMap);
        String response = HttpUtil.doPost(GlobalConstant.HP_AUTH_SMS_CHECK, params);
        log.info("请求校验验证码接口返回结果：{}",response);

        JsonObject data = parseResponse(response);
        if(data!=null){
            int status = data.getAsJsonPrimitive("status").getAsInt();
            if(status==0){
                log.info("验证码{}过期",code);
            }else if(status==1){
                boolean isConsistent = data.getAsJsonPrimitive("isConsistent").getAsBoolean();
                return isConsistent ? 1 : -1;
            }
            log.info("返回status异常");
            return -2;
        }
        log.info("返回结果解析失败");
        return -3;
    }

    @Override
    public String queryCardData(String name, String idCard, String bankCard, String mobile, String code) {
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("account", APP_ID);
        paramsMap.put("bankCard", bankCard);
        paramsMap.put("idCard", idCard);
        paramsMap.put("identifyingCode", code);
        paramsMap.put("mobile", mobile);
        paramsMap.put("name", name);
        paramsMap.put("orderNo", "0000");
        paramsMap.put("serialNumber", "1017248584260968449");
        String sign = HihippoSign.value(paramsMap,APP_SECRET);
        log.info("构造sign:{}",sign);
        paramsMap.put("sign", sign);

        Gson gson = new Gson();
        String params = gson.toJson(paramsMap);
        String response = HttpUtil.doPost(GlobalConstant.HP_AUTH_SMS_CHECK, params);
        log.info("请求卡测评接口返回结果：{}",response);

        JsonObject data = parseResponse(response);
        if(data!=null){
            return data.getAsJsonPrimitive("url").getAsString();
        }
        log.info("返回结果解析失败");
        return null;
    }


    private JsonObject parseResponse(String response){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
        String code = jsonObject.getAsJsonPrimitive("code").getAsString();
        String result = jsonObject.getAsJsonPrimitive("result").getAsString();
        JsonObject data = jsonObject.getAsJsonObject("data");
        if("0".equals(code)&&"0000".equals(result)){
            return data;
        }
        return null;
    }
}
