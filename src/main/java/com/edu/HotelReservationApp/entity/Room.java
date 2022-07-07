package com.edu.HotelReservationApp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="room_details",
uniqueConstraints = {@UniqueConstraint(columnNames= {"roomNo"})})
public class Room {

	@Id
	@GeneratedValue(generator ="seq2",strategy = GenerationType.AUTO)
	@SequenceGenerator(name="seq2",initialValue=101)
	private long roomId;
	private long roomNo;
	@Column(nullable=false)
	@Range(min=1,max=3,message="No of bed allows only between 1 to 3")
	private String noOfBed;
	private double roomFare;
	private boolean status;
	
	@OneToOne(mappedBy="room", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("room")
	private Reservation reservation;
    
	
	public Room(long roomId, long roomNo, String noOfBed, double roomFare, boolean status, Reservation reservation) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.noOfBed = noOfBed;
		this.roomFare = roomFare;
		this.status = status;
		this.reservation = reservation;
	}
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public long getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(long roomNo) {
		this.roomNo = roomNo;
	}
	public String getNoOfBed() {
		return noOfBed;
	}
	public void setNoOfBed(String noOfBed) {
		this.noOfBed = noOfBed;
	}
	public double getRoomFare() {
		return roomFare;
	}
	public void setRoomFare(double roomFare) {
		this.roomFare = roomFare;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Room(long roomId, long roomNo, String noOfBed, double roomFare, boolean status) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.noOfBed = noOfBed;
		this.roomFare = roomFare;
		this.status = status;
	}
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNo=" + roomNo + ", noOfBed=" + noOfBed + ", roomFare=" + roomFare
				+ ", status=" + status + ", reservation=" + reservation + "]";
	}

	public Room(long roomId, long roomNo,
			@Range(min = 1, max = 3, message = "No of bed allows only between 1 to 3") String noOfBed) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.noOfBed = noOfBed;
	}
	
}