package com.nickjiang.WeChatCode2SessionServer.models;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class WxCredential {
    @Value("${wx.mp.app.id}")
    public String WxAppId;

    @Value("${wx.mp.app.secret}")
    public String WxAppSecret;
}
