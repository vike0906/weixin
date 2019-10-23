package com.vike.weixin.common;

/**
 * @author: lsl
 * @createDate: 2019/10/22
 */
public class GlobalConstant {

    /**微信APi*/
    public static final String WEIXIN_API = "https://open.weixin.qq.com";

    /**获取AccessToken链接*/
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /**创建菜单链接*/
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    /**查看菜单链接*/
    public static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=%s";

    /**删除菜单链接*/
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    /**获取网页AccessToken链接*/
    public static final String WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**通过AccessToken拉取用户信息链接*/
    public static final String COLLECT_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
}
