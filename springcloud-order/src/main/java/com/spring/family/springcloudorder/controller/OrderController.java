package com.spring.family.springcloudorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：chengjindong
 * @description：TODO
 * @date ：Created in 2019/10/18 0018 17:25
 */
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * restTemplate是由springboot web组件提供的，默认整合ribbon负载均衡器
     * restTemplate底层是httpclient
     * @return
     */
    @RequestMapping("/getorder")
    public String getOrder() {
        // order 使用rpc 远程调用技术 调用 会员服务
        String memberUrl = "http://app-member/getMember";
        String result = restTemplate.getForObject(memberUrl, String.class);
        System.out.println("会员服务调用订单服务,result:" + result);
        return result;
    }
}
