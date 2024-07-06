package com.credmarg.smewebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credmarg.smewebapp.entity.Email;
import com.credmarg.smewebapp.entity.Vendor;
import com.credmarg.smewebapp.service.EmailServiceImpl;

@RestController
@RequestMapping("/sme")
public class EmailController {

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@PostMapping("/sendMail")
	public String sendMails(@RequestBody List<Vendor> emails) {
		String msg = emailServiceImpl.sendEmails(emails);
		return "Success";
	}

	@GetMapping("/allEmails")
	public List<Email> getAllEmails() {
		return emailServiceImpl.getAllEmails();
	}

}
