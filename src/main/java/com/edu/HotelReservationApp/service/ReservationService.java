package com.edu.HotelReservationApp.service;

import java.util.List;

import com.edu.HotelReservationApp.entity.Reservation;

public interface ReservationService {

	Reservation saveReservation(Reservation reservation);

	List<Reservation> getReservationList();

	Reservation getReservationById(long resId);

	Reservation updateReservation(long resId, Reservation reservation);

	String deleteReservation(long resId);

}
