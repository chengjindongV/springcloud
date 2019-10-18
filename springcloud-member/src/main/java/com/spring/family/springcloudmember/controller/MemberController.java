package com.spring.family.springcloudmember.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：chengjindong
 * @description：TODO
 * @date ：Created in 2019/10/18 0018 17:05
 */
@RestController
public class MemberController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getMember")
    public String getMember() {
        return "this is getMember,服务端口："+ serverPort;
    }
}
