package com.edu.HotelReservationApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.entity.User;

public interface RoomRepository extends JpaRepository<Room, Long> {

	Room findByRoomNo(long roomNo);

	List<Room> findByNoOfBed(String noOfBed);

	List<Room> findByStatus(boolean status);


}
