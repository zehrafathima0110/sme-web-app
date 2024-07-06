package com.credmarg.smewebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vendor {

	@Id
	private String email;
	private String name;
	private String upi;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpi() {
		return upi;
	}

	public void setUpi(String upi) {
		this.upi = upi;
	}

}
