package com.tryout.backend.ristoranteEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="telnum")
	private int telnum;
	
	@Column(name="email")
	private String email;
	
	@Column(name="agree")
	private boolean agree;
	
	@Column(name="contactType")
	private String contactType;
	
	@Column(name="message")
	private String message;
	
	@Column(name="date")
	private String date;

	
	//constructor
	public Feedback() {
		
	}


	public Feedback(String firstname, String lastname, int telnum, String email, String contactType, String message,
			String date, boolean agree) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.telnum = telnum;
		this.email = email;
		this.contactType = contactType;
		this.message = message;
		this.date = date;
		this.agree = agree;
	}

	
	//getters and setters

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public int getTelnum() {
		return telnum;
	}


	public void setTelnum(int telnum) {
		this.telnum = telnum;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContactType() {
		return contactType;
	}


	public void setContactType(String contactType) {
		this.contactType = contactType;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	
	public boolean getAgree() {
		return agree;
	}
	
	public void setAgree(boolean agree) {
		this.agree = agree;
	}


	//toString
	
	@Override
	public String toString() {
		return "Feedback [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", telnum=" + telnum
				+ ", email=" + email + ", contactType=" + contactType + ", message=" + message + ", date=" + date + "]";
	}
	
	
	
}
