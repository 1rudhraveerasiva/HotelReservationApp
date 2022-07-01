package com.edu.HotelReservationApp.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reservation_details")
public class Reservation {
       
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "reservation_details_seq")
	private long resId;
	@Column(nullable=false)
	@NotNull
	//@Size(max=3)
	private int noOfGuest;
	@Column(nullable=false)
	@NotNull
	private int stayDays;
	private LocalDateTime checkInDateTime;
	private LocalDateTime checkOutDateTime;
	
	@PrePersist
	public void addDateTime() {
		this.checkInDateTime= LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnoreProperties("reservation")
	private User user;
	
	@OneToOne
	@JoinColumn(name="roomId")
	@JsonIgnoreProperties("reservation")
	private Room room;

	public long getResId() {
		return resId;
	}

	public void setResId(long resId) {
		this.resId = resId;
	}

	public int getNoOfGuest() {
		return noOfGuest;
	}

	public void setNoOfGuest(int noOfGuest) {
		this.noOfGuest = noOfGuest;
	}

	public int getStayDays() {
		return stayDays;
	}

	public void setStayDays(int stayDays) {
		this.stayDays = stayDays;
	}

	public LocalDateTime getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(LocalDateTime checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public LocalDateTime getCheckOutDateTime() {
		return checkOutDateTime;
	}

	public void setCheckOutDateTime(LocalDateTime checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(long resId, @NotNull @Size(max = 3) int noOfGuest, @NotNull int stayDays,
			LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime, User user, Room room) {
		super();
		this.resId = resId;
		this.noOfGuest = noOfGuest;
		this.stayDays = stayDays;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.user = user;
		this.room = room;
	}
	

	public Reservation(long resId, @NotNull @Size(max = 3) int noOfGuest, @NotNull int stayDays,
			LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime) {
		super();
		this.resId = resId;
		this.noOfGuest = noOfGuest;
		this.stayDays = stayDays;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
	}

	public Reservation(long resId, @NotNull @Size(max = 3) int noOfGuest, @NotNull int stayDays,
			LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime, User user) {
		super();
		this.resId = resId;
		this.noOfGuest = noOfGuest;
		this.stayDays = stayDays;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Reservation [resId=" + resId + ", noOfGuest=" + noOfGuest + ", stayDays=" + stayDays
				+ ", checkInDateTime=" + checkInDateTime + ", checkOutDateTime=" + checkOutDateTime + ", user=" + user
				+ ", room=" + room + "]";
	} 
}
	
	
	