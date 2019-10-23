package com.vike.weixin.common;

/**
 * @author: lsl
 * @createDate: 2019/10/22
 */
public class GlobalConstant {

    /**获取AccessToken链接*/
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /**创建菜单链接*/
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
}
