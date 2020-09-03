package com.approval.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author javadevopsmc13
 * This is the test class for Credit Card Approval Service
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class CreditCardApprovalControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	private static String apiUrl = "http://localhost:8082";
	
	@Test
	public void testApproveCustomer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put(apiUrl + "/approveCreditCard/10/Approved/SLF4")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testApproveCustomerWithWrongId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put(apiUrl + "/approveCreditCard/SLS/Approved/SLF4")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400));
	}

	public static String asJsonString (final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
}
}
