package com.edu.HotelReservationApp.service;

import java.util.List;

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.entity.User;

public interface RoomService {

	Room saveRoom(Room room);

	List<Room> getRoomList();

	Room getRoomById(long roomId);

	Room updateRoom(long roomId, Room room);

	String deleteRoom(long roomId);
	Room getRoomByRoomNo(long roomNo);

	List<Room> getRoomByStatus(boolean status);

	List<Room> getRoomByNoOfBed(String noOfBed);



}
