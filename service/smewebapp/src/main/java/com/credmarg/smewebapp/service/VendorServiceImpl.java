package com.credmarg.smewebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credmarg.smewebapp.entity.Vendor;
import com.credmarg.smewebapp.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepository;

	@Override
	public Vendor saveVendor(Vendor vendor) {
		vendor.setEmail(vendor.getEmail().toLowerCase());
		return vendorRepository.save(vendor);
	}

	@Override
	public List<Vendor> getAllVendors() {
		return (List<Vendor>) vendorRepository.findAll();
	}

	@Override
	public Vendor getVendorByMailId(String mailId) {
		return vendorRepository.findById(mailId).orElse(null);
	}

	@Override
	public void deleteVendor(String mailId) {
		vendorRepository.deleteById(mailId);
	}
}
