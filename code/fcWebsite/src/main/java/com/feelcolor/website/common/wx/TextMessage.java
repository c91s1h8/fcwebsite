package com.feelcolor.website.common.wx;

import lombok.Data;

@Data
public class TextMessage extends BaseMessage {
    // 回复的消息内容
    private String Content;
}
