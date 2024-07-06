package com.credmarg.smewebapp.service;

import java.util.List;

import com.credmarg.smewebapp.entity.Employee;
import com.credmarg.smewebapp.entity.Vendor;

public interface VendorService {

	Vendor saveVendor(Vendor Vendor);

	List<Vendor> getAllVendors();

	Vendor getVendorByMailId(String mailId);

	void deleteVendor(String mailId);
}
