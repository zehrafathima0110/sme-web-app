package com.credmarg.smewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credmarg.smewebapp.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,String> {

}
