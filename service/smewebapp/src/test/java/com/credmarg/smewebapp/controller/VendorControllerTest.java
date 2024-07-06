package com.credmarg.smewebapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import com.credmarg.smewebapp.entity.Vendor;
import com.credmarg.smewebapp.service.VendorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VendorController.class)
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorServiceImpl vendorService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreateVendor() throws Exception {
    	
        Vendor vendor = new Vendor();
        vendor.setName("Vendor 1");
        vendor.setEmail("vendor1@example.com");
        vendor.setUpi("upi1@upi");

        when(vendorService.saveVendor(vendor)).thenReturn(vendor);

        mockMvc.perform(post("/sme/vendor/createVendor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendor)))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.name").value("Vendor 1"))
                //.andExpect(jsonPath("$.email").value("vendor1@example.com"));
    }

    @Test
    public void testGetAllVendors() throws Exception {
    	 Vendor vendor = new Vendor();
         vendor.setName("Vendor 1");
         vendor.setEmail("vendor1@example.com");
         vendor.setUpi("upi1@upi");
        List<Vendor> vendors = Arrays.asList(vendor);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get("/sme/vendor/allVendors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Vendor 1"))
                .andExpect(jsonPath("$[0].email").value("vendor1@example.com"));
    }

    @Test
    public void testUpdateVendor() throws Exception {
    	 Vendor existingVendor = new Vendor();
    	 existingVendor.setName("Vendor 1");
    	 existingVendor.setEmail("vendor1@example.com");
    	 existingVendor.setUpi("upi1@upi");
         Vendor updatedVendor = new Vendor();
         updatedVendor.setName("Vendor 1");
         updatedVendor.setEmail("vendor1@example.com");
         updatedVendor.setUpi("upi1_updated@upi");

        when(vendorService.getVendorByMailId("vendor1@example.com")).thenReturn(existingVendor);
        when(vendorService.saveVendor(existingVendor)).thenReturn(updatedVendor);

        mockMvc.perform(put("/sme/vendor/updateVendor/vendor1@example.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedVendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.upi").value("upi1_updated@upi"));
    }

    @Test
    public void testDeleteVendor() throws Exception {
        mockMvc.perform(delete("/sme/vendor/deleteVendor/vendor1@example.com"))
                .andExpect(status().isOk());
    }
}
