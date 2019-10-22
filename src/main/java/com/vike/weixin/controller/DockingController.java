package com.vike.weixin.controller;

import com.vike.weixin.utils.EncryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author: lsl
 * @createDate: 2019/10/22
 */
@RestController
@Slf4j
public class DockingController {

    @GetMapping("wx")
    public String wx(@RequestParam(required = false)String signature,
                     @RequestParam(required = false)String timestamp,
                     @RequestParam(required = false)String nonce,
                     @RequestParam(required = false)String echostr){

        log.info("接收信息：signature:{}, timestamp:{}, nonce:{}, echostr:{},",signature, timestamp, nonce, echostr);

        if(StringUtils.isEmpty(signature)||StringUtils.isEmpty(timestamp)||StringUtils.isEmpty(nonce)||StringUtils.isEmpty(echostr)){
            return "Hello World";
        }

        String[] arr = new String[]{"wx123456",timestamp,nonce};

        Arrays.sort(arr);

        StringBuffer content = new StringBuffer();

        for(int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }

        String temp = EncryUtil.Sha1(content.toString());

        if(temp.equals(signature)){
            return echostr;
        }

        return "Hello World";
    }

    @PostMapping("wx")
    public String wx(){
        return "";
    }


}
