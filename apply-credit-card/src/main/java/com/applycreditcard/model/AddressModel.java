package com.applycreditcard.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel implements Serializable{
	private static final long serialVersionUID = -9043689418434544817L;
	private String perAddress1;
	private String perAddress2;
	private String perCity;
	private String perState;
	private String perCountry;
	private String proAddress1;
	private String proAddress2;
	private String proCity;
	private String proState;
	private String proCountry;
}
