package com.edu.HotelReservationApp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.HotelReservationApp.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Optional<Reservation> findByNoOfGuest(int i);

	
	@Query("select r from Reservation r where r.checkInDateTime = :checkInDateTime")

	List<Reservation> getReservationByCheckInDateTime(@Param("checkInDateTime")LocalDateTime checkInDateTime);

    @Query("select r from Reservation r where r.reserveDate= :reserveDate")
     List<Reservation> getReservationByReserveDate(@Param("reserveDate")LocalDateTime reserveDate);

    @Query("select r from Reservation r where user.userId= :userId")
     List<Reservation> getReservationByUserId(@Param("userId")long userId);


}
