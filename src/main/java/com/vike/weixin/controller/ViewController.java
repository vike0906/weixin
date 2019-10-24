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
        long fansId = code2FansId(code);
        modelMap.addAttribute("fansId",fansId);
        return "index";
    }

    @GetMapping("history")
    public String history(ModelMap modelMap, @RequestParam(required = false) String code, @RequestParam(required = false) Integer state){
        long fansId = code2FansId(code);
        modelMap.addAttribute("fansId",fansId);
        return "history";
    }

    @GetMapping("invite")
    public String invite(ModelMap modelMap, @RequestParam(required = false) String code, @RequestParam(required = false) Integer state){
        long fansId = code2FansId(code);
        modelMap.addAttribute("fansId",fansId);
        return "invite";
    }

    @GetMapping("query")
    public String query(ModelMap modelMap, @RequestParam(required = false) Long fansId){
        if(fansId==null){
            fansId = -1L;
        }
        modelMap.addAttribute("fansId",fansId);
        return "query";
    }

    @GetMapping("agreement")
    public String agreement(){
        return "agreement";
    }

    @PostMapping("query-summit")
    public String query(ModelMap modelMap,
                        @RequestParam(required = false) Long fansId,
                        @RequestParam String userName,
                        @RequestParam String idNo,
                        @RequestParam String creditCardNo,
                        @RequestParam String phone,
                        @RequestParam String authCode){
        log.info("传递值：{} {} {} {} {}", userName, idNo, creditCardNo, phone, authCode);
        if(fansId==null){
            fansId = -1L;
        }
        modelMap.addAttribute("fansId",fansId);
        return "result";
    }

    private long code2FansId(String code){
        long fansId = -1L;
        if(code!=null&&!"".equals(code)){
            fansId = wxApiInfoComment.getFansIdByCode(code);
        }
        return fansId;
    }
}
