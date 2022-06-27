package com.edu.HotelReservationApp.service;

import java.util.List;

import com.edu.HotelReservationApp.entity.User;

public interface UserService {

	User saveUser(User user);

	List<User> getUserList();

	User getUserById(long userId);

	User updateUser(long userId, User user);

	String deleteUser(long userId);

	List<User> getUserByFirstName(String firstName);

	List<User> getUserByLastName(String lastName);
	List<User> getUserByFullName(String firstName, String lastName);

	User getUserByEmailId(String emailId);

	User getUserByUsername(String username);

	
}