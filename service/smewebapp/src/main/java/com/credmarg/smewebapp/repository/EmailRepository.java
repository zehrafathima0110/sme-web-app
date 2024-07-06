package com.credmarg.smewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credmarg.smewebapp.entity.Email;

public interface EmailRepository extends JpaRepository<Email,Long> {

}
