package com.edu.HotelReservationApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.HotelReservationApp.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Optional<Reservation> findByNoOfGuest(int i);
	

}
