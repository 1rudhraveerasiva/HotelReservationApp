package com.edu.HotelReservationApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.Exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.Exception.GivenRecordNotFoundException;
import com.edu.HotelReservationApp.Exception.GivenStatusNotFoundException;
import com.edu.HotelReservationApp.Exception.NoRecordFoundException;
import com.edu.HotelReservationApp.Exception.ResourceNotFoundException;
import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.RoomRepository;

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
		List<Room> roo = roomRepos.findAll();
		if(roo.isEmpty())
			throw new NoRecordFoundException();
		else
			return roo;
	}

	@Override
	public Room getRoomById(long roomId) {
		// TODO Auto-generated method stub
		/*Room room = new Room();
		room = roomRepos.findById(roomId).orElseThrow(
				()->new ResourceNotFoundException("Room","Id",roomId));
		return room;
	}*/
		Optional<Room> room=roomRepos.findById(roomId);
		if(room.isPresent()) {
			return room.get();
		}
		else {
			throw new GivenIdNotFoundException();
		}
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
		//return roomRepos.findByRoomNo(roomNo);
		Optional<Room> room=roomRepos.findByRoomNo(roomNo);
		if(room.isPresent()) {
			return room.get();
		}
		else
			throw new GivenRecordNotFoundException();
	}

	@Override
	public List<Room> getRoomByNoOfBed(String noOfBed) {
		// TODO Auto-generated method stub
		//return roomRepos.findByNoOfBed(noOfBed);
		List<Room> roo=roomRepos.findByNoOfBed(noOfBed);
		if(roo.isEmpty())
			throw new NoRecordFoundException();
		else
			return roo;
	}

	@Override
	public List<Room> getRoomByStatus(boolean status) {
		// TODO Auto-generated method stub
	//	return roomRepos.findByStatus(status);
		List<Room> roo=roomRepos.findByStatus(status);
		if(roo.isEmpty())
			throw new GivenStatusNotFoundException();
		else
			return roo;
	}

}

