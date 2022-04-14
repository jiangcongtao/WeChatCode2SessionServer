package com.nickjiang.WeChatCode2SessionServer.controllers;

import com.nickjiang.WeChatCode2SessionServer.converters.WxSessionInfoHttpMessageConverter;
import com.nickjiang.WeChatCode2SessionServer.models.JscodeInfo;
import com.nickjiang.WeChatCode2SessionServer.models.WxCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/v1")
public class Jscode2SessionController {
    Logger logger = LoggerFactory.getLogger(Jscode2SessionController.class);

    @Value("${wx.api.jscode2session}")
    private String wxJscode2SessionApi;

    @Autowired
    private WxCredential wxCredential;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/wxlink", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> linkWxAccount(@RequestBody JscodeInfo jscodeInfo) throws Exception {
        logger.info("Get js_code info: " + jscodeInfo.toString());

        restTemplate.getMessageConverters().add(new WxSessionInfoHttpMessageConverter());

        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(wxJscode2SessionApi)
                .queryParam("appid", wxCredential.WxAppId)
                .queryParam("secret", wxCredential.WxAppSecret)
                .queryParam("js_code", jscodeInfo.wxcode);
        logger.info(builder.build().toUri().toString());

        // Http headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        requestHeaders.add("Accept", "application/json");

        var result = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, new HttpEntity<String>(requestHeaders), String.class);

        if (result.getBody().contains("errcode")) {
            // handle error
            logger.error(result.getBody());
            throw new Exception(result.getBody());
        }

        logger.info(result.getBody());
        return result;
    }

}
