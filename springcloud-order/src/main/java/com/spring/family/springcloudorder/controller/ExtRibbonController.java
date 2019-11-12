package com.spring.family.springcloudorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author ：chengjindong
 * @description：TODO
 * @date ：Created in 2019/11/12 0012 19:27
 * 手动实现赋在均衡算法 注意:去掉@LoadBalanced注解，因为他实现类赋在均衡的功能
 */
@RestController
public class ExtRibbonController {

    // 可以获取注册中心上的服务列表
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    // 接口的请求总数
    private int reqCount = 1;


    @RequestMapping("/ribbonMember")
    public String ribbonMember() {

        String instancesUrl = getInstances() + "/getMember";

        System.out.println("instancesUrl： "+ instancesUrl);
        String result = restTemplate.getForObject(instancesUrl, String.class);

        return  result;
    }


    private String getInstances() {
        List<ServiceInstance> instances = discoveryClient.getInstances("app-member");

        if (instances == null || instances.size()<=0){
            return null;
        }

        int serviceCount = instances.size();

        int index =  reqCount%serviceCount;

        String serviceNameb = instances.get(index).getUri().toString();

        reqCount++;
        return serviceNameb;
    }
}
