package com.credmarg.smewebapp.service;

import java.util.List;

import com.credmarg.smewebapp.entity.Email;

public interface EmailService {
	
	Email saveEmail(Email email);
	List<Email> getAllEmails();
	

}
