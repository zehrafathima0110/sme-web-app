package com.credmarg.smewebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credmarg.smewebapp.entity.Employee;
import com.credmarg.smewebapp.entity.Vendor;
import com.credmarg.smewebapp.service.EmployeeServiceImpl;
import com.credmarg.smewebapp.service.VendorServiceImpl;

@RestController
@RequestMapping("/sme/vendor")
public class VendorController {

	@Autowired
	private VendorServiceImpl vendorServiceImpl;

	@PostMapping("/createVendor")
	public Vendor createVendor(@RequestBody Vendor vendor) {
		return vendorServiceImpl.saveVendor(vendor);
	}

	@GetMapping("/allVendors")
	public List<Vendor> getAllvendors() {
		return vendorServiceImpl.getAllVendors();
	}

	@PutMapping("/updateVendor/{mailId}")
	public Vendor updateEmployee(@PathVariable String mailId, @RequestBody Vendor updatedVendor) {
		Vendor vendor = vendorServiceImpl.getVendorByMailId(mailId);
		if (vendor != null) {
			vendor.setName(updatedVendor.getName());
			vendor.setEmail(updatedVendor.getEmail());
			vendor.setUpi(updatedVendor.getUpi());
			return vendorServiceImpl.saveVendor(vendor);
		}
		return null;
	}

	@DeleteMapping("/deleteVendor/{mailId}")
	public void deleteEmployee(@PathVariable String mailId) {
		vendorServiceImpl.deleteVendor(mailId);
	}
}
