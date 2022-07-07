package com.edu.HotelReservationApp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_details",
uniqueConstraints = {@UniqueConstraint(columnNames= {"username"}),
		             @UniqueConstraint(columnNames= {"emailId"}),
		             @UniqueConstraint(columnNames= {"aadharNumber"}),
                     @UniqueConstraint(columnNames= {"contactNo"})})
public class User {
	@Id
	@GeneratedValue(generator="seq1", strategy= GenerationType.AUTO)
	@SequenceGenerator(name="seq1", initialValue=1)
    private long userId;
	@Column(nullable=false)
	@NotNull
	@NotBlank(message = "First name is mandatory ")
	private String firstName;
	@Column(nullable = false)
	@NotBlank(message= "Last name is mandatory")
	private String lastName;
	@Column(nullable=false)
	@Size(min=10,max=10,message="Contact number should be between 10 and 17")
	private String contactNo;
	@Column(nullable= false)
	@NotBlank(message="Username is mandatory")
	private String username;
	@NotEmpty
	@Size(min = 8, message = "password should have atleast 8 characters")
	private String password;
	@Column(nullable = false)
	@NotBlank(message="Email is mandatory")
	@Email(message="Invalid email id")
	private String emailId;
	@Column(nullable = false)
	@NotBlank(message="Aadhar number is mandatory")
	private String aadharNumber;
	@Column(nullable=false)
	@NotBlank(message="Address is mandatory")
	private String fullAddress;
	@OneToMany(mappedBy="user" , cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("user")
	private List<Reservation> reservation;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public List<Reservation> getReservation() {
		return reservation;
	}
	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(long userId, @NotNull @NotBlank(message = "First name is mandatory ") String firstName,
			@NotBlank(message = "Last name is mandatory") String lastName,
			@Size(min = 10, max = 17, message = "Contact number should be between 10 and 17") String contactNo,
			@NotBlank(message = "Username is mandatory") String username,
			@NotEmpty @Size(min = 8, message = "password should have atleast 8 characters") String password,
			@NotBlank(message = "Email is mandatory") @Email(message = "Invalid email id") String emailId,
			@NotBlank(message = "Aadhar number is mandatory") String aadharNumber,
			@NotBlank(message = "Address is mandatory") String fullAddress, List<Reservation> reservation) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.aadharNumber = aadharNumber;
		this.fullAddress = fullAddress;
		this.reservation = reservation;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", contactNo="
				+ contactNo + ", username=" + username + ", password=" + password + ", emailId=" + emailId
				+ ", aadharNumber=" + aadharNumber + ", fullAddress=" + fullAddress + ", reservation=" + reservation
				+ "]";
	}
	public User(long userId, @NotNull @NotBlank(message = "First name is mandatory ") String firstName,
			@NotBlank(message = "Last name is mandatory") String lastName,
			@Size(min = 10, max = 17, message = "Contact number should be between 10 and 17") String contactNo,
			@NotBlank(message = "Username is mandatory") String username,
			@NotEmpty @Size(min = 8, message = "password should have atleast 8 characters") String password,
			@NotBlank(message = "Email is mandatory") @Email(message = "Invalid email id") String emailId,
			@NotBlank(message = "Aadhar number is mandatory") String aadharNumber,
			@NotBlank(message = "Address is mandatory") String fullAddress) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.aadharNumber = aadharNumber;
		this.fullAddress = fullAddress;
	}
	public User(long userId, @NotNull @NotBlank(message = "First name is mandatory ") String firstName,
			@NotBlank(message = "Last name is mandatory") String lastName,
			@Size(min = 10, max = 17, message = "Contact number should be between 10 and 17") String contactNo) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
	}
	
	
	}