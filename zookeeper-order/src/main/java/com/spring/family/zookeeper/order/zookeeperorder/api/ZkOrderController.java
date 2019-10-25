package com.spring.family.zookeeper.order.zookeeperorder.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ZkOrderController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;

	// springcloud 中使用那些技术实现调用服务接口 feign 或者rest
	@RequestMapping("/orderToMember")
	public String orderToMember() {
		String memberUrl = "http://zk-member/getMember";
		return restTemplate.getForObject(memberUrl, String.class);
	}

	@RequestMapping("/discoveryClientMember")
	public List<ServiceInstance> discoveryClientMember() {
		List<ServiceInstance> instances = discoveryClient.getInstances("zk-member");
		for (ServiceInstance serviceInstance : instances) {
			System.out.println("url:" + serviceInstance.getUri());
		}
		return instances;

	}

	public static void main(String[] args) {
		SpringApplication.run(ZkOrderController.class, args);
	}

	// 默认rest方式开启 负载均衡功能 如果以app-itmayiedu-member名称进行调用服务接口的时候 必须
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// 如何获取到注册中心上服务列表信息
}
