package com.edu.HotelReservationApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="room_details")
public class Room {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator ="room_details_seq")
	private long roomId;
	private long roomNo;
	private String noOfBed;
	private double roomFare;
	private boolean status;
	
	@OneToOne(mappedBy="room")
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
	
}