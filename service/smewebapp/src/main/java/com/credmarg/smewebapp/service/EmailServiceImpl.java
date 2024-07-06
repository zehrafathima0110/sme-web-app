package com.credmarg.smewebapp.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credmarg.smewebapp.entity.Email;
import com.credmarg.smewebapp.entity.Vendor;
import com.credmarg.smewebapp.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailRepository emailRepository;

	@Override
	public Email saveEmail(Email email) {
		return emailRepository.save(email);
	}

	@Override
	public List<Email> getAllEmails() {
		return emailRepository.findAll();
	}

	public String sendEmail(Vendor vendor) {
		// Mock email sending
		Email email = new Email();
		email.setEmail(vendor.getEmail());
		String content = "Sending payments to vendor " + vendor.getName() + " at upi " + vendor.getUpi();
		email.setMailBody(content);
		email.setMailSubject("Payment Details");
		Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		email.setSentDateTime(date);
		emailRepository.save(email);
		return "Email sent to " + vendor.getName() + " with content: " + content;
	}

	public String sendEmails(List<Vendor> vendors) {
		for (Vendor vendor : vendors) {
			String str = sendEmail(vendor);
		}
		return "Success";
	}

}
