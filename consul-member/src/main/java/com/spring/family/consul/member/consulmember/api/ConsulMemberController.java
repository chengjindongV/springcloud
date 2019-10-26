/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.spring.family.consul.member.consulmember.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月12日 下午8:52:10<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulMemberController {
	@Value("${server.port}")
	private String serverPort;

	@RequestMapping("/getMember")
	public String getMember() {
		return "会员服务,订单服务调用会员服务接口,端口号为:" + serverPort;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsulMemberController.class, args);
	}
	// @EnableDiscoveryClient 作用是 如果服务使用connsul、zookeeper 使用
	// @EnableDiscoveryClient 向注册中心上注册服务


}
