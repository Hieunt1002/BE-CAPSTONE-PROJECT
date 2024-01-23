package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DiscountDTO {

	@JsonProperty("discountcourseid")
	private int discountcourseid;

	@JsonProperty("discountid")
	private int discountid;

	@JsonProperty("course")
	private int course;

	@JsonProperty("discount")
	private float discount;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("img")
	private String img;

	@JsonProperty("title")
	private String title;

	@JsonProperty("staffid")
	private int staffid;

	@JsonProperty("desciption")
	private String desciption;


}
