package com.applycreditcard.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel{
	private String emailId;
	private String fullName;
	private String mobile;
	private List<AddressModel> addressDetails;
	private List<PersonalDetailModel> personalDetails;
	private List<ProfessionalDetailModel> professionalDetails;
}
