package com.vike.weixin.controller;

import com.vike.weixin.service.QueryService;
import com.vike.weixin.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lsl
 * @createDate: 2019/10/24
 */
@RestController
@Slf4j
@RequestMapping("query")
public class QueryController {

    @Autowired
    QueryService queryService;

    @PostMapping("gain")
    public Response gain(@RequestParam Long fansId,
                       @RequestParam String userName,
                       @RequestParam String idNo,
                       @RequestParam String creditCardNo,
                       @RequestParam String phone){
        String orderNo = queryService.gainVerificationCode(fansId, userName, idNo, creditCardNo, phone);
        if(orderNo==null){
            return new Response(Response.ERROR, Response.NULL);
        }
        return new Response(Response.SUCCESS, orderNo);
    }

    /**创建查询订单*/
    @PostMapping("check")
    public Response check(@RequestParam String orderNo, @RequestParam String code){
        int status = queryService.checkVerificationCode(orderNo, code);
        String msg;
        switch (status){
            case 1:
                msg = "校验通过";
                break;
            case 2:
                msg = "验证码错误";
                break;
            case 3:
                msg = "验证码已过期";
                break;
            default:
                msg = "系统维护中，请稍后再试";
        }
        return new Response(status, msg);
    }

    @PostMapping("summit")
    public Response summit(@RequestParam String userName,
                         @RequestParam String idNo,
                         @RequestParam String creditCardNo,
                         @RequestParam String phone,
                         @RequestParam String code,
                         @RequestParam String orderNo){
        String url = queryService.queryCardData(userName, idNo, creditCardNo, phone, code, orderNo);
        if(url==null){
            return new Response(Response.ERROR, Response.NULL);
        }
        return new Response(Response.SUCCESS, url);
    }

    /**全部查询结果*/
}
