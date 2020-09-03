package com.applycreditcard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDetailModel {
	private String companyName;
	private String designation;
	private int grossAnnualIncome;
	private String profession;
}
