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
 * Entity class for addressdetail table
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addressdetail")
public class AddressDetail implements Serializable{
	
	private static final long serialVersionUID = 4981284483332140518L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="per_address1")
	private String perAddress1;

	@Column(name="per_address2")
	private String perAddress2;

	@Column(name="per_city")
	private String perCity;
	
	@Column(name="per_state")
	private String perState;
	
	@Column(name="per_country")
	private String perCountry;

	@Column(name="pro_address1")
	private String proAddress1;

	@Column(name="pro_address2")
	private String proAddress2;

	@Column(name="pro_city")
	private String proCity;
	
	@Column(name="pro_state")
	private String proState;
	
	@Column(name="pro_country")
	private String proCountry;

}
