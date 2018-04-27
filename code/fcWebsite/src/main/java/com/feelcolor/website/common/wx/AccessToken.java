package com.feelcolor.website.common.wx;

import lombok.Data;

@Data
public class AccessToken {
    // 获取到的凭证
    private String token;
    // 凭证有效时间，单位：秒
    private int expiresIn;
}
