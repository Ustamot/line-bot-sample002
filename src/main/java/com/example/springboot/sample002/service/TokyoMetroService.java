package com.example.springboot.sample002.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.sample002.dto.TrainInformationDto;

public class TokyoMetroService {
	
	static private final String API_ENDPOINT = "https://api.tokyometroapp.jp/api/v2/datapoints?rdf:type=odpt:TrainInformation&acl:consumerKey=b26ce611b4195e1ca43197c4ca9bc19082afe5b93a61bf4cb60036346f2cd797&odpt:railway={railway}";
	
	@Autowired
//	@Qualifier("trainInfomationSearchRestTemplate")
	private RestTemplate restTemplate;
	
	public TokyoMetroService () {
		
	}
	
	public TrainInformationDto getTrainInfomation(String railway) {
		if (restTemplate != null) {
			System.out.println("restTemplate is not null");
		} else {
			System.out.println("restTemplate is null");
		}
		System.out.println("size: " + restTemplate.getMessageConverters().size());
		return restTemplate.getForObject(API_ENDPOINT, TrainInformationDto.class, railway);
	}

}
