package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.google.common.collect.ImmutableMap;

@SpringBootApplication
public class OlianApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlianApplication.class, args);
	}

	@Bean
	public Servlet baiduProxyServlet(){
		return new ProxyServlet();
	}

	@Bean
	public ServletRegistrationBean proxyServletRegistration(HttpServletRequest request){
		Servlet servlet=baiduProxyServlet();
		System.out.println(servlet);
		servlet.getServletConfig();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(baiduProxyServlet(), "/*");
		Map<String, String> params = ImmutableMap.of(
				"targetUri", "https://cn.bing.com/",
				"log", "true");
		registrationBean.setInitParameters(params);
		return registrationBean;
	}

}

