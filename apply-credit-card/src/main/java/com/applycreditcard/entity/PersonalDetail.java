package com.applycreditcard.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author javadevopsmc13
 * Entity class for personaldetail table
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personaldetail")
public class PersonalDetail implements Serializable{

	private static final long serialVersionUID = 3386766744963730275L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="citizenship")
	private String citizenShip;

	@Temporal(TemporalType.DATE)
	@Column(name="dateofbirth")
	private Date dateOfBirth;

	@Column(name="fathername")
	private String fatherName;

	@Column(name="maritalstatus")
	private String maritalStatus;

	@Column(name="pancard")
	private String pancard;

	@Column(name="residentalstatus")
	private String residentalStatus;
}
