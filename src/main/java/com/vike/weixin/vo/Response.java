package com.vike.weixin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lsl
 * @createDate: 2019/10/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    public static final int SUCCESS = 0;

    public static final int ERROR = 2;

    public static final String NULL = "";

    private Integer code;

    private String data;
}
