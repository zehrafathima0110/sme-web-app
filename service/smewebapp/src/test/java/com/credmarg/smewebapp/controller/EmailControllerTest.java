package com.credmarg.smewebapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.credmarg.smewebapp.entity.Email;
import com.credmarg.smewebapp.entity.Vendor;
import com.credmarg.smewebapp.service.EmailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmailController.class)
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailServiceImpl emailService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSendMails() throws Exception {
    	Vendor vendor = new Vendor();
    	vendor.setEmail("vendor1@example.kom");
    	vendor.setName("Vendor One");
    	vendor.setUpi("upi@upi");
        List<Vendor> vendors = Arrays.asList(vendor);

        when(emailService.sendEmails(vendors)).thenReturn("Success");

        mockMvc.perform(post("/sme/sendMail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendors)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Success"));
    }

    @Test
    public void testGetAllEmails() throws Exception {
    	Email email = new Email();
    	email.setEmail("vendor1@example.kom");
    	email.setMailBody("Test email body");
    	List<Email> emails = Arrays.asList(email);

        when(emailService.getAllEmails()).thenReturn(emails);

        mockMvc.perform(get("/sme/allEmails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("vendor1@example.kom"))
                .andExpect(jsonPath("$[0].mailBody").value("Test email body"));
    }
}
