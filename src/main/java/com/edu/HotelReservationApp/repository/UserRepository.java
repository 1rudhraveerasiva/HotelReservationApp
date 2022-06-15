package com.edu.HotelReservationApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.HotelReservationApp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    @Query("select u from User u where u.firstName = :firstName")
	List<User> getUserByFirstName(@Param("firstName")String firstName);
    
    @Query("select u from User u where u.lastName = :lastName")
	List<User> getUserByLastName(@Param("lastName")String lastName);
    
    @Query("select u from User u where u.firstName = :firstName and u.lastName = :lastName")
	List<User> getUserByFullName(@Param("firstName")String firstName,@Param("lastName")String lastName);

	User findByEmailId(String emailId);

    @Query("select u from User u where u.username = :username")

	User getUserByUsername(@Param("username")String username);

	Optional<User> findByContactNo(String string);

    }
