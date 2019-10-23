package com.vike.weixin.controller;

import com.vike.weixin.comments.WXApiInfoComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: lsl
 * @createDate: 2019/10/22
 */
@Slf4j
@Controller
@RequestMapping("view")
public class ViewController {

    @Autowired
    WXApiInfoComment wxApiInfoComment;

    @GetMapping("index")
    public String index(ModelMap modelMap, @RequestParam(required = false) String code, @RequestParam(required = false) Integer state){
        /**获取OpenId，即用户身份信息*/
        long fansId = wxApiInfoComment.getFansIdByCode(code);
        modelMap.addAttribute("fansId",fansId);
        return "index";
    }

    @GetMapping("history")
    public String history(){
        return "history";
    }

    @GetMapping("invite")
    public String invite(){
        return "invite";
    }

    @GetMapping("query")
    public String query(){
        return "query";
    }

    @PostMapping("query-summit")
    public String query(@RequestParam String userName,
                        @RequestParam String idNo,
                        @RequestParam String creditCardNo,
                        @RequestParam String phone,
                        @RequestParam String authCode){
        log.info("传递值：{} {} {} {} {}", userName, idNo, creditCardNo, phone, authCode);
        return "result";
    }
}
