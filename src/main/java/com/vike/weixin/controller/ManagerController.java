package com.vike.weixin.controller;

import com.google.gson.Gson;
import com.vike.weixin.comments.AccessTokenSync;
import com.vike.weixin.common.GlobalConstant;
import com.vike.weixin.pojo.Button;
import com.vike.weixin.pojo.Menu;
import com.vike.weixin.utils.HttpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lsl
 * @createDate: 2019/10/22
 */
@RestController
@Slf4j
@RequestMapping("manager")
public class ManagerController {

    @GetMapping("create-menu")
    public String createMenu(){

        Menu menu1 = new Menu("view","报告查询","http://106.13.222.152/view/index");
        Menu menu2 = new Menu("view","查询记录","http://106.13.222.152/view/history");
        Menu menu3 = new Menu("view","我的邀请","http://106.13.222.152/view/invite");

        Menu [] menus = {menu1,menu2,menu3};
        Button button = new Button(menus);

        Gson gson = new Gson();
        String s = gson.toJson(button);
        log.info(s);

        String url = String.format(GlobalConstant.CREATE_MENU_URL, AccessTokenSync.getAccessToken());
        String result = HttpsUtil.httpsRequest(url, HttpsUtil.METHOD_POST, s);

        return result;
    }
}
