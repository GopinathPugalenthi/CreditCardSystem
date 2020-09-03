package com.approval.service.impl;

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

import com.approval.entity.CustomerDetailEntity;
import com.approval.repository.ApprovalCreditCardRepository;
import com.approval.service.ICreditCardApprovalService;
import com.approval.util.CreditCardApprovalConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author javadevopsmc13 Service Implementation class for Credit Card Approval
 *         Service
 *
 */
@Service
@Transactional
@Slf4j
public class CreditCardApprovalService implements ICreditCardApprovalService {

	@Autowired
	private ApprovalCreditCardRepository approvalCreditCardRepository;

	/**
	 * This updateCustomerDetails method is used update customer details
	 * 
	 * @throws NotFoundException
	 */
	@Override
	public void updateCustomerDetails(Integer id, String status, String approvedBy,
			CustomerDetailEntity customerDetailEntity) throws NotFoundException {
		log.info("Credit card Approval Service updateCustomerDetails method");
		customerDetailEntity.setStatus(status);
		customerDetailEntity.setApprovedBy(approvedBy);
		approvalCreditCardRepository.save(customerDetailEntity);
		sendMail(customerDetailEntity.getEmailId());
		log.info("Credit card Application has been Approved Successfuly");
	}

	/**
	 * This method is used to send mail
	 * 
	 * @param emailTo
	 */
	public void sendMail(String emailTo) {
		log.info("Apply Credit card Service sentMail method");
		final String username = CreditCardApprovalConstants.EMAIL_ID;
		final String password = CreditCardApprovalConstants.PASSWORD;

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
			message.setFrom(new InternetAddress(CreditCardApprovalConstants.EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(CreditCardApprovalConstants.SUBJECT);
			message.setText(CreditCardApprovalConstants.MESSAGE);
			Transport.send(message);
			log.info("Mail Sent successfully");

		} catch (MessagingException e) {
			log.error("Unable to sent Mail to mentioned email address: {}, {}", emailTo, e.getMessage());
		}
	}

}
