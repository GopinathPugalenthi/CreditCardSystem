package com.applycreditcard.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.applycreditcard.model.AddressModel;
import com.applycreditcard.model.CustomerModel;
import com.applycreditcard.model.PersonalDetailModel;
import com.applycreditcard.model.ProfessionalDetailModel;
import com.applycreditcard.service.IApplyCreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author javadevopsmc13
 * This is the test class for apply credit card service
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class ApplyCreditCardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	CustomerModel customerModelMock;
	
	@Mock
	private IApplyCreditCardService applyCreditCardService;
	
	private static String apiUrl = "http://localhost:8081";
	
	@Test
	public void testSaveCustomerDetail() throws Exception {
		CustomerModel customerModel = new CustomerModel();
		AddressModel address = new AddressModel();
		List<AddressModel> addressLst = new ArrayList<AddressModel>();
		PersonalDetailModel personal = new PersonalDetailModel();
		List<PersonalDetailModel> personalLst = new ArrayList<PersonalDetailModel>();
		ProfessionalDetailModel professional = new ProfessionalDetailModel();
		List<ProfessionalDetailModel> professionalLst = new ArrayList<ProfessionalDetailModel>();
		address.setPerAddress1("Housing board");
		address.setPerAddress2("Thillainagar");
		address.setPerCity("Chennai");
		address.setPerCountry("India");
		address.setPerState("TamilNadu");
		address.setProAddress1("Rosenagar");
		address.setProAddress2("Kovilam");
		address.setProCity("Chennai");
		address.setProCountry("India");
		address.setProState("TamilNadu");
		addressLst.add(address);
		
		personal.setCitizenShip("Indian");
		personal.setFatherName("Pugalenthi");
		personal.setMaritalStatus("Married");
		personal.setPancard("1234567867");
		personal.setResidentalStatus("Yes");
		personal.setDateOfBirth(new Date());
		personalLst.add(personal);
		
		professional.setCompanyName("IT Company");
		professional.setDesignation("Associate");
		professional.setGrossAnnualIncome(100000);
		professional.setProfession("IT");
		professionalLst.add(professional);
		
		customerModel.setEmailId("gopinath.pugalenthi@gmail.com");
		customerModel.setFullName("Gopikrishna");
		customerModel.setMobile("9994877572");
		customerModel.setAddressDetails(addressLst);
		customerModel.setPersonalDetails(personalLst);
		customerModel.setProfessionalDetails(professionalLst);
		
		mockMvc.perform(MockMvcRequestBuilders.post(apiUrl + "/applyCreditCard/create")					
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerModel)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testSaveCustomerDetailWithWrongValue() throws Exception {
		CustomerModel customerModel = new CustomerModel();
		AddressModel address = new AddressModel();
		List<AddressModel> addressLst = new ArrayList<AddressModel>();
		PersonalDetailModel personal = new PersonalDetailModel();
		List<PersonalDetailModel> personalLst = new ArrayList<PersonalDetailModel>();
		ProfessionalDetailModel professional = new ProfessionalDetailModel();
		List<ProfessionalDetailModel> professionalLst = new ArrayList<ProfessionalDetailModel>();
		address.setPerAddress1("Housing board");
		address.setPerAddress2("Thillainagar");
		address.setPerCity("Chennai");
		address.setPerCountry("India");
		address.setPerState("TamilNadu");
		address.setProAddress1("Rosenagar");
		address.setProAddress2("Kovilam");
		address.setProCity("Chennai");
		address.setProCountry("India");
		address.setProState("TamilNadu");
		addressLst.add(address);
		
		personal.setCitizenShip("Indian");
		personal.setFatherName("Pugalenthi");
		personal.setMaritalStatus("Married");
		personal.setPancard("1234567867");
		personal.setResidentalStatus("Yes");
		personal.setDateOfBirth(new Date());
		personalLst.add(personal);
		
		professional.setCompanyName("IT Company");
		professional.setDesignation("Associate");
		professional.setGrossAnnualIncome(100000);
		professional.setProfession("IT");
		professionalLst.add(professional);
		
		customerModel.setEmailId("gopinath.pugalenthi@gmail.com");
		customerModel.setFullName("Gopikrishna");
		customerModel.setMobile("9994877572123");
		customerModel.setAddressDetails(addressLst);
		customerModel.setPersonalDetails(personalLst);
		customerModel.setProfessionalDetails(professionalLst);
		
		mockMvc.perform(MockMvcRequestBuilders.post(apiUrl + "/applyCreditCard/create")					
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerModel)))
				.andExpect(status().is(500));
	}


	@Test
	public void testSaveCustomerDetailMockito() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(apiUrl + "/applyCreditCard/create")					
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerModelMock)))
				.andExpect(status().is(500));
		
	}
	
	@Test
	public void testfetchCustomerDetail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(apiUrl + "/applyCreditCard/customer/7")					
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
		
	}
	public static String asJsonString (final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
}
}
