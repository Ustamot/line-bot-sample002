package com.example.springboot.sample002.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Configuration
public class RestTemplateResolver {
	
	@Bean
	public RestTemplate trainInfomationSearchRestTemplate() {
		System.out.println("##### trainInfomationSearchRestTemplate");
//		RestTemplate restTemplate = new RestTemplate();
//		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//		List<MediaType> supportedMediaTypes = new ArrayList<>(messageConverter.getSupportedMediaTypes());
//		supportedMediaTypes.add(MediaType.TEXT_PLAIN); // text/plainのJacksonの処理対象にくわえる
//		messageConverter.setSupportedMediaTypes(supportedMediaTypes);
//		restTemplate.setMessageConverters(Collections.singletonList(messageConverter)); // カスタムしたHttpMessageConverterを適用
		RestTemplate restTemplate = new RestTemplate(); 
		return restTemplate;
	}

}
