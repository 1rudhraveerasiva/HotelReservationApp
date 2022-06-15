package com.edu.HotelReservationApp.Repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.RoomRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class RoomRepositoryTest {
	@Autowired
	private RoomRepository roomRepository;

	@Test
	public void saveRoomTest() {
		 
		Room room = roomRepository.save(new Room(4,104,"5",70.6,false));
		Assertions.assertThat(room.getRoomId()).isGreaterThan(0);
	}
	@Test
	public void getRoomTest() {
		Room room = roomRepository.findById(4L).get();
		Assertions.assertThat(room.getRoomId()).isEqualTo(4L);
	}
	@Test
	public void getRoomListTest() {
		List<Room> rooms = roomRepository.findAll();
		Assertions.assertThat(rooms.size()).isGreaterThan(0);
	}
	@Test
	public void updateRoomTest() {
		Room room = roomRepository.findById(2L).get();
		room.setRoomFare(80.8);
		Room updated = roomRepository.save(room);
		
		Assertions.assertThat(updated.getRoomFare()).isEqualTo(80.8);
		
	}
	@Test
	public void deleteRoomTest() {
	Room roo = roomRepository.findById(602L).get();
    roomRepository.delete(roo);

	Room room = null;
	Optional<Room> roo1 = roomRepository.findByRoomFare(80.6);
	
	
	if(roo1.isPresent()) {
		room = roo1.get();
	}
	Assertions.assertThat(room).isNull();

	}
	}
