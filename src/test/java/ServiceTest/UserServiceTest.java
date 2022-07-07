package ServiceTest;

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
import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.UserRepository;
import com.edu.HotelReservationApp.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepos;
	@Autowired
	@InjectMocks
	private UserServiceImpl userService;
	
	//
	private User user1;
	private User user2;
	List<User> userList;
	
	@BeforeEach
	public void setup() {
		userList = new ArrayList<>();
		user1 = new User(159,"ram","rudh","7871182304");
		
		user2 = new User(160,"ramy","rudhy","7654635121");
		
		userList.add(user1);
		userList.add(user2);
	}
	//
	@AfterEach
	public void afterTest() {
		user1 = user2=null;
		userList = null;
	}
	//
	@DisplayName("Test for saveUser() method")
	@Test
	public void givenUserToAddShouldReturnAddUser() {
		when(userRepos.save(user1)).thenReturn(user1);
		//
		User savedUser = userService.saveUser(user1);
		
		System.out.println(savedUser);
		assertThat(savedUser).isNotNull();
	}
	
	//
	@Test
	public void givenExistingIdWhenSaveUserThenThrowsException() {
		User user = new User(159,"ram","rudh","7871182304");
		when(userRepos.findById(user.getUserId()))//159 
		      .thenReturn(Optional.of(user));//159
	

	org.junit.jupiter.api.Assertions.assertThrows(RecordAlreadyExistException.class, () -> {
		userService.saveUser(user);
	});
}

	//
	@Test
	public void givenGetAllUserShouldReturnListOfAllUsers()throws NoRecordFoundException {
		userRepos.saveAll(userList);
		when(userRepos.findAll()).thenReturn(userList);
        List<User> actualUserList = userService.getUserList();
        assertThat(actualUserList).isEqualTo(userList);
	}
	@Test
	public void givenIdThenShouldReturnUserOfThatId() throws GivenIdNotFoundException{
		when(userRepos.findById(159L)).thenReturn(Optional.ofNullable(user1));
		assertThat(userService.getUserById(user1.getUserId())).isEqualTo(user1);
	}
	@Test
	public void givenIdToDeleteThenShouldDeleteUserOfThatId() {
		when(userRepos.findById(user1.getUserId())).thenReturn (Optional.ofNullable(user1));
		assertThat(userService.deleteUser(user1.getUserId())).isEqualTo("Record is deleted successfully");
		
	}
	@Test
	public void givenIdToDeleteThenThrowsException() {
		long userId = 159L;
		org.junit.jupiter.api.Assertions.assertThrows(NoRecordFoundException.class, () ->  {
			userService.deleteUser(userId);
		});
	}
	
	@DisplayName("JUnit test for updateUser method")
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser(){
    	long userId = 2L;
        when(userRepos.save(user1)).thenReturn(user1);
        when(userRepos.findById(userId)).thenReturn(Optional.of(user1));
        user1.setContactNo("7871182304");
        user1.setFirstName("priya");
        User updatedUser = userService.updateUser(userId, user1);

        assertThat(updatedUser.getContactNo()).isEqualTo("7871182304");
        assertThat(updatedUser.getFirstName()).isEqualTo("priya");
    }
    
    @Test
	public void givenIdToUpdateNotExistThenThrowsException()  {
		long userId = 2L;
		User user = new User();
		user1.setContactNo("7871182304");
        user1.setFirstName("priya");
        
	    org.junit.jupiter.api.Assertions.assertThrows(NoRecordFoundException.class, () -> {
        userService.updateUser(userId, user);
});

	} 

}