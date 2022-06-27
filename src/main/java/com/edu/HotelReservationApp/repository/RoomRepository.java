package com.edu.HotelReservationApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.entity.User;

public interface RoomRepository extends JpaRepository<Room, Long> {

	Optional<Room> findByRoomNo(long roomNo);

	List<Room> findByNoOfBed(String noOfBed);

	List<Room> findByStatus(boolean status);

	Optional<Room> findByRoomFare(double roomFare);





}
