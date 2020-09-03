package com.applycreditcard.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetailModel {
	private String citizenShip;
	private Date dateOfBirth;
	private String fatherName;
	private String maritalStatus;
	private String pancard;
	private String residentalStatus;
}
