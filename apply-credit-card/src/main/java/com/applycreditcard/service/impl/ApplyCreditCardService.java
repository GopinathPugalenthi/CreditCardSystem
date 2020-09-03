package com.applycreditcard.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applycreditcard.entity.AddressDetail;
import com.applycreditcard.entity.CustomerDetailEntity;
import com.applycreditcard.entity.PersonalDetail;
import com.applycreditcard.entity.ProfessionalDetail;
import com.applycreditcard.model.CustomerModel;
import com.applycreditcard.repository.CreditCardRepository;
import com.applycreditcard.service.IApplyCreditCardService;
import com.applycreditcard.util.ApplyCreditCardConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author javadevopsmc13 ApplyCreditCardService is the service class
 *         implementation for apply credit card service controller
 *
 */
@Service
@Transactional
@Slf4j
public class ApplyCreditCardService implements IApplyCreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
	 * saveCustomerDetails method is used to save customer details.
	 */
	@Override
	public void saveCustomerDetails(CustomerModel customerDetail) {
		log.info("Apply credit card service saveCustomerDetails method");
		AddressDetail addressDetail = new AddressDetail();
		CustomerDetailEntity customerDetailEntity = new CustomerDetailEntity();
		PersonalDetail personalDetail = new PersonalDetail();
		ProfessionalDetail professionalDetail = new ProfessionalDetail();
		List<AddressDetail> addressList = new ArrayList<>();
		List<ProfessionalDetail> professionList = new ArrayList<>();
		List<PersonalDetail> personalList = new ArrayList<>();
		if (customerDetail != null) {
			customerDetailEntity.setEmailId(customerDetail.getEmailId());
			customerDetailEntity.setFullName(customerDetail.getFullName());
			customerDetailEntity.setMobile(customerDetail.getMobile());
			if (customerDetail.getPersonalDetails() != null && !customerDetail.getPersonalDetails().isEmpty()) {
				customerDetail.getPersonalDetails().forEach(perdetail -> {
					personalDetail.setFatherName(perdetail.getFatherName());
					personalDetail.setMaritalStatus(perdetail.getMaritalStatus());
					byte[] encodedBytes = null;
					encodedBytes = Base64.getEncoder().encode(perdetail.getPancard().getBytes(StandardCharsets.UTF_8));
					personalDetail.setPancard(new String(encodedBytes));
					personalDetail.setResidentalStatus(perdetail.getResidentalStatus());
					personalDetail.setDateOfBirth(perdetail.getDateOfBirth());
					personalDetail.setCitizenShip(perdetail.getCitizenShip());
					personalList.add(personalDetail);
				});

				if (customerDetail.getProfessionalDetails() != null
						&& !customerDetail.getProfessionalDetails().isEmpty()) {
					customerDetail.getProfessionalDetails().forEach(prodetail -> {
						professionalDetail.setCompanyName(prodetail.getCompanyName());
						professionalDetail.setDesignation(prodetail.getDesignation());
						professionalDetail.setGrossAnnualIncome(prodetail.getGrossAnnualIncome());
						professionalDetail.setProfession(prodetail.getProfession());
						professionList.add(professionalDetail);
					});
				}

				if (customerDetail.getAddressDetails() != null && !customerDetail.getAddressDetails().isEmpty()) {
					customerDetail.getAddressDetails().forEach(address -> {
						addressDetail.setPerAddress1(address.getPerAddress1());
						addressDetail.setPerAddress2(address.getPerAddress2());
						addressDetail.setPerCity(address.getPerCity());
						addressDetail.setPerState(address.getPerState());
						addressDetail.setPerCountry(address.getPerCountry());
						addressDetail.setProCountry(address.getPerCountry());
						addressDetail.setProAddress1(address.getProAddress1());
						addressDetail.setProAddress2(address.getProAddress2());
						addressDetail.setProCity(address.getProCity());
						addressDetail.setProState(address.getProState());
						addressDetail.setProCountry(address.getProCountry());
						addressList.add(addressDetail);
					});
				}

			}

			customerDetailEntity.setPersonalDetails(personalList);
			customerDetailEntity.setProfessionalDetails(professionList);
			customerDetailEntity.setAddressDetails(addressList);
			customerDetailEntity.setApprovedBy("System");
			customerDetailEntity.setStatus("Pending");

			creditCardRepository.save(customerDetailEntity);
			log.info("Credit card Application has been Saved Successfuly");

			this.sendMail(customerDetail.getEmailId());
		}

	}

	/**
	 * This method is used to send mail
	 * 
	 * @param emailTo
	 */
	public void sendMail(String emailTo) {
		log.info("Apply Credit card Service sentMail method");
		final String username = ApplyCreditCardConstants.EMAIL_ID;
		final String password = ApplyCreditCardConstants.PASSWORD;

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ApplyCreditCardConstants.EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(ApplyCreditCardConstants.SUBJECT);
			message.setText(ApplyCreditCardConstants.MESSAGE);
			Transport.send(message);
			log.info("Mail Sent successfully");

		} catch (MessagingException e) {
			log.error("Unable to sent Mail to mentioned email address: {}, {}", emailTo, e.getMessage());
		}
	}

	/**
	 * This method to find a customer detail by id
	 */
	@Override
	public CustomerDetailEntity findCustomer(int id) throws NotFoundException {
		return creditCardRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}

}
