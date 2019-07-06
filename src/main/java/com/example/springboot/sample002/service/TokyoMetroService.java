package com.example.springboot.sample002.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.sample002.dto.TrainInformationDto;

@ComponentScan("com.example.springboot.sample002")
@Component
public class TokyoMetroService {
	
	static private final String API_ENDPOINT = "https://api.tokyometroapp.jp/api/v2/datapoints?rdf:type=odpt:TrainInformation&acl:consumerKey=b26ce611b4195e1ca43197c4ca9bc19082afe5b93a61bf4cb60036346f2cd797&odpt:railway={railway}";
	
	@Autowired
	@Qualifier("trainInfomationSearchRestTemplate")
	private RestTemplate restTemplate;
	
	public TokyoMetroService () {
		
	}
	
	public TrainInformationDto[] getTrainInfomation(String railway) {
		
		restTemplate = new RestTemplate();
//		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//		List<MediaType> supportedMediaTypes = new ArrayList<>(messageConverter.getSupportedMediaTypes());
//		supportedMediaTypes.add(MediaType.TEXT_PLAIN); // text/plainのJacksonの処理対象にくわえる
//		messageConverter.setSupportedMediaTypes(supportedMediaTypes);
//		restTemplate.setMessageConverters(Collections.singletonList(messageConverter)); // カスタムしたHttpMessageConverterを適用

		return restTemplate.getForObject(API_ENDPOINT, TrainInformationDto[].class, railway);
	}

}
