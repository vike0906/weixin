package com.vike.weixin.comments;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vike.weixin.common.GlobalConstant;
import com.vike.weixin.entity.Fans;
import com.vike.weixin.entity.FansInfo;
import com.vike.weixin.service.FansService;
import com.vike.weixin.utils.HttpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Optional;

/**
 * @author: lsl
 * @createDate: 2019/10/24
 */
@Slf4j
@Component
public class WXApiInfoComment {

    @Value("${system.wx.app_id:app_id}")
    private String APP_ID;
    @Value("${system.wx.app_secret:app_secret}")
    private String APP_SECRET;
    @Autowired
    FansService fansService;

    /**通过网页授权code获取用户id*/
    public long getFansIdByCode(String code){

        return getOauth2AccessToken(code);

    }

    /**
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60&redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
     */
    public String getUrlWithAuthorize(String url, String state){
        try {
            url = URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String menuUrl = String.format(GlobalConstant.CODE_MENU_URL, APP_ID, url, state);
        log.info("组装链接为：{}", menuUrl);
        return menuUrl;
    }



    /**
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     * 通过code换取网页授权access_token
     */
    private long getOauth2AccessToken(String code){

        String url = String.format(GlobalConstant.WEB_ACCESS_TOKEN_URL, APP_ID, APP_SECRET, code);

        String result = HttpsUtil.httpsRequest(url, HttpsUtil.METHOD_GET);

        log.info("获取网页AccessToken结果：{}",result);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(result).getAsJsonObject();

        String accessToken = jsonObject.getAsJsonPrimitive("access_token").getAsString();
        String openid = jsonObject.getAsJsonPrimitive("openid").getAsString();

        Optional<Fans> optional = fansService.findByOpenId(openid);

        if(optional.isPresent()){
            Fans fans = optional.get();
            if(fans.getIsCollectInfo()==1){
                /**采集基础信息*/
                collectInfoByAccessToken(accessToken, openid, fans.getId());
                fans.setIsCollectInfo(2).setUpdateTime(new Date(System.currentTimeMillis()));
                fansService.saveFans(fans);
            }
            return fans.getId();
        }else{
            return -1L;
        }
    }

    /**
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     * 拉取用户信息
     */
    private void collectInfoByAccessToken(String accessToken, String openId, Long fansId){

        String url = String.format(GlobalConstant.COLLECT_INFO_URL, accessToken, openId);

        String result = HttpsUtil.httpsRequest(url, HttpsUtil.METHOD_GET);

        log.info("拉取用户信息结果：{}",result);

        Gson gson = new Gson();
        FansInfo fansInfo = gson.fromJson(result, FansInfo.class);
        String privilegeStr = gson.toJson(fansInfo.getPrivilege());
        fansInfo.setFansId(fansId).setPrivilegeStr(privilegeStr);
        fansInfo.setCreateTime(new Date(System.currentTimeMillis()));

        fansService.saveFansInfo(fansInfo);
    }

}
