package com.vike.weixin.pojo;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author: lsl
 * @createDate: 2019/10/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VerificationCodeRequest {

    private long fansId;

    private String orderNo;

    private String serialNumber;

    private String realName;

    private String idNO;

    private String creditCardNo;

    private String phone;
}
