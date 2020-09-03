package com.approval.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author javadevopsmc13
 * Entity class for professionaldetail table
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professionaldetail")
public class ProfessionalDetail implements Serializable{

	private static final long serialVersionUID = 415154706722046251L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="companyname")
	private String companyName;

	@Column(name="designation")
	private String designation;

	@Column(name="grossannualincome")
	private int grossAnnualIncome;

	@Column(name="profession")
	private String profession;
}
