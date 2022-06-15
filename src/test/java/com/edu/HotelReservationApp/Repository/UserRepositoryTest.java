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
	
    User user =userRepository.save(new User(352,"shree","devi","9465776267","shreepriya","shree123","sri@gmail.com",264234234,"pondy"));
	
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
@Test
public void deleteUserTest() {
User use = userRepository.findById(302L).get();
userRepository.delete(use);

User user = null;
Optional<User> use1 = userRepository.findByContactNo("9465784267");
if(use1.isPresent()) {
	user = use1.get();
}
Assertions.assertThat(user).isNull();

}
}

