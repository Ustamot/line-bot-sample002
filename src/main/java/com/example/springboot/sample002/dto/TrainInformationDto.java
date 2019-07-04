package com.example.springboot.sample002.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class TrainInformationDto {
	
	@JsonProperty("@context")
	private String context;
	
	@JsonProperty("@id")
	private String id;
	
	@JsonProperty("@type")
	private String type;
	
	@JsonProperty("dc:date")
	private String date;

	@JsonProperty("dct:valid")
	private String valid;

	@JsonProperty("odpt:operator")
	private String operator;

	@JsonProperty("odpt:timeOfOrigin")
	private String timeOfOrigin;

	@JsonProperty("odpt:railway")
	private String railway;

	@JsonProperty("odpt:trainInformationStatus")
	private String trainInformationStatus;

	@JsonProperty("odpt:trainInformationText")
	private String trainInformationText;

}
