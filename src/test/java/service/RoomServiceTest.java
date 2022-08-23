package service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.HotelReservationApp.Exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.Exception.NoRecordFoundException;
import com.edu.HotelReservationApp.Exception.RecordAlreadyExistException;
import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.repository.RoomRepository;
import com.edu.HotelReservationApp.service.RoomServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
	
	@Mock
	private RoomRepository roomRepos;
	@Autowired
	@InjectMocks
	private RoomServiceImpl roomService;
	
	//
	private Room room1;
	private Room room2;
	List<Room> roomList;
	
	@BeforeEach
	public void setup() {
		roomList = new ArrayList<>();
		room1 = new Room(210,3,"110");
		
		room2 = new Room(211,2,"111");
		
		roomList.add(room1);
		roomList.add(room2);
	}
	//
	@AfterEach
	public void afterTest() {
		room1 = room2=null;
		roomList = null;
	}
	//
	@DisplayName("Test for saveUser() method")
	@Test
	public void givenUserToAddShouldReturnAddUser() {
		when(roomRepos.save(room1)).thenReturn(room1);
		//
		Room savedRoom = roomService.saveRoom(room1);
		
		
		assertThat(savedRoom).isNotNull();
	}
	
	//
	@Test
	public void givenExistingIdWhenSaveUserThenThrowsException() {
		Room room = new Room(210,3,"110");
		when(roomRepos.findById(room.getRoomId()))//159 
		      .thenReturn(Optional.of(room));//159
	

	org.junit.jupiter.api.Assertions.assertThrows(RecordAlreadyExistException.class, () -> {
		roomService.saveRoom(room);
	});
}

	//
	@Test
	public void givenGetAllRoomShouldReturnListOfAllUsers()throws NoRecordFoundException {
		roomRepos.saveAll(roomList);
		when(roomRepos.findAll()).thenReturn(roomList);
        List<Room> actualRoomList = roomService.getRoomList();
        assertThat(actualRoomList).isEqualTo(roomList);
	}
	@Test
	public void givenIdThenShouldReturnRoomOfThatId() throws GivenIdNotFoundException{
		when(roomRepos.findById(210L)).thenReturn(Optional.ofNullable(room1));
		assertThat(roomService.getRoomById(room1.getRoomId())).isEqualTo(room1);
	}
	@Test
	public void givenIdToDeleteThenShouldDeleteRoomOfThatId() {
		when(roomRepos.findById(room1.getRoomId())).thenReturn (Optional.ofNullable(room1));
		assertThat(roomService.deleteRoom(room1.getRoomId())).isEqualTo("Record is deleted successfully");
		
	}
	@Test
	public void givenIdToDeleteThenThrowsException() {
		long roomId = 212L;
		org.junit.jupiter.api.Assertions.assertThrows(NoRecordFoundException.class, () ->  {
		    roomService.deleteRoom(roomId);
		});
	}
	
	@DisplayName("JUnit test for updateUser method")
    @Test
    public void givenRoomObject_whenUpdateRoom_thenReturnUpdatedRoom(){
    	long roomId = 210L;
        when(roomRepos.save(room1)).thenReturn(room1);
        when(roomRepos.findById(roomId)).thenReturn(Optional.of(room1));
        room1.setRoomNo(2);
        room1.setNoOfBed("1");
        Room updatedRoom = roomService.updateRoom(roomId, room1);
        System.out.println(updatedRoom);
        assertThat(updatedRoom.getRoomNo()).isEqualTo(2);
        assertThat(updatedRoom.getNoOfBed()).isEqualTo("1");
    }
    
    @Test
	public void givenIdToUpdateNotExistThenThrowsException()  {
		long roomId = 210L;
		Room room = new Room();
		room1.setRoomNo(2);
        room1.setNoOfBed("1");
        
	    org.junit.jupiter.api.Assertions.assertThrows(NoRecordFoundException.class, () -> {
        roomService.updateRoom(roomId, room);
});

	} 

}


