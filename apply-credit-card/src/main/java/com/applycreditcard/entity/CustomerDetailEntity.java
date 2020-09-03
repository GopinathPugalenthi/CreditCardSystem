package com.applycreditcard.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author javadevopsmc13
 * Entity class for contactdetail table
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contactdetail")
public class CustomerDetailEntity implements Serializable{
	
	private static final long serialVersionUID = -5364045999754226882L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="emailid")
	private String emailId;

	@Column(name="fullname")
	private String fullName;

	@Column(name="mobile", nullable = false,columnDefinition="varchar(10)")
	private String mobile;
	
	@Column(name = "approvedstatus")
	private String status;
	
	@Column(name="approvedBy")
	private String approvedBy;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PersonalDetail> personalDetails;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProfessionalDetail> professionalDetails;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AddressDetail> addressDetails;
}
