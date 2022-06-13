package com.edu.HotelReservationApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.RoomRepository;

import exception.ResourceNotFoundException;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired 
	RoomRepository roomRepos;

	@Override
	public Room saveRoom(Room room) {
		// TODO Auto-generated method stub
		return roomRepos.save(room);
	}

	@Override
	public List<Room> getRoomList() {
		// TODO Auto-generated method stub
		return roomRepos.findAll();
	}

	@Override
	public Room getRoomById(long roomId) {
		// TODO Auto-generated method stub
		Room room = new Room();
		room = roomRepos.findById(roomId).orElseThrow(
				()->new ResourceNotFoundException("Room","Id",roomId));
		return room;
	}

	@Override
	public Room updateRoom(long roomId, Room room) {
		Room roo= new Room();
		roo = roomRepos.findById(roomId).orElseThrow(
				()-> new ResourceNotFoundException("Room","Id",roomId));
		roo.setRoomNo(room.getRoomNo());
		roo.setNoOfBed(room.getNoOfBed());
		roo.setRoomFare(room.getRoomFare());
		roo.setStatus(room.isStatus());
		roomRepos.save(roo);
		return roo;
	}

	@Override
	public String deleteRoom(long roomId) {
		// TODO Auto-generated method stub
		Room room = new Room();
		room = roomRepos.findById(roomId).orElseThrow(
				()->new ResourceNotFoundException("Room","Id",roomId));
		roomRepos.deleteById(roomId);
		return "Record is deleted successfully";
	}

	@Override
	public Room getRoomByRoomNo(long roomNo) {
		// TODO Auto-generated method stub
		return roomRepos.findByRoomNo(roomNo);
	}

	@Override
	public List<Room> getRoomByNoOfBed(String noOfBed) {
		// TODO Auto-generated method stub
		return roomRepos.findByNoOfBed(noOfBed);
	}

	@Override
	public List<Room> getRoomByStatus(boolean status) {
		// TODO Auto-generated method stub
		return roomRepos.findByStatus(status);
		
	}

	
}
