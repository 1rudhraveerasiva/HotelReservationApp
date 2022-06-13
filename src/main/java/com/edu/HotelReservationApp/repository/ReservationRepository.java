package com.edu.HotelReservationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.HotelReservationApp.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	

}
