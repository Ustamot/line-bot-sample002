package com.example.springboot.sample002.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
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

//	@JsonProperty("odpt:trainInformationStatus")
//	private String trainInformationStatus;

	@JsonProperty("odpt:trainInformationText")
	private String trainInformationText;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTimeOfOrigin() {
		return timeOfOrigin;
	}

	public void setTimeOfOrigin(String timeOfOrigin) {
		this.timeOfOrigin = timeOfOrigin;
	}

	public String getRailway() {
		return railway;
	}

	public void setRailway(String railway) {
		this.railway = railway;
	}

	public String getTrainInformationText() {
		return trainInformationText;
	}

	public void setTrainInformationText(String trainInformationText) {
		this.trainInformationText = trainInformationText;
	}

}
