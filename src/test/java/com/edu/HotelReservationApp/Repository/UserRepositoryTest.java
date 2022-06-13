package com.edu.HotelReservationApp.Repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    
@Test
public void saveUserTest() {///testcase
	
    User user =userRepository.save(new User(4,"sri","devi","9438784267","sripriya","shree123","sri@gmail.com",264234234,"pondy"));
	
	Assertions.assertThat(user.getUserId()).isGreaterThan(0);

}
@Test
public void getUserTest() {
	User user = userRepository.findById(3L).get();
	Assertions.assertThat(user.getUserId()).isEqualTo(3L);
}
@Test
public void getUserListTest() {
	List<User> users = userRepository.findAll();
	Assertions.assertThat(users.size()).isGreaterThan(0);
}
@Test
public void updateUserTest() {
	User user = userRepository.findById(2L).get();
	user.setEmailId("rudh@gmail.com");
	User updated = userRepository.save(user);
	
	Assertions.assertThat(updated.getEmailId()).isEqualTo("rudh@gmail.com");
	
}
}
