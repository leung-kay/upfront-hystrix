package com.ruifucredit.cloud.upfront;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import okhttp3.OkHttpClient;

@Configuration
public class RestTemplateConfig {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(OkHttpClient okHttpClient) {
		return new RestTemplateBuilder().detectRequestFactory(false)
				.requestFactory(new OkHttp3ClientHttpRequestFactory(okHttpClient)).build();
	}

}
