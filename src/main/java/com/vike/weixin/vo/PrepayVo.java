package com.vike.weixin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lsl
 * @createDate: 2019/10/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrepayVo {
    private String appId;
    private String timeStamp;
    private String nonceStr;
    private String packageStr;
    private String signType;
    private String paySign;
}
