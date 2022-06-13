package com.edu.HotelReservationApp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.UserRepository;

import exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepos;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepos.save(user);
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userRepos.findAll();
	}

	@Override
	public User getUserById(long userId) {
		// TODO Auto-generated method stub
		User user = new User();
		user = userRepos.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User","Id",userId));
		return user;
	}

	@Override
	public User updateUser(long userId, User user) {
		// TODO Auto-generated method stub
	   User use = new User();
		use = userRepos.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User","Id",userId));
		
		use.setFirstName(user.getFirstName());
		use.setLastName(user.getLastName());
		use.setContactNo(user.getContactNo());
		use.setUsername(user.getUsername());
		use.setPassword(user.getPassword());
		use.setEmailId(user.getEmailId());
		use.setAadharNumber(user.getAadharNumber());
        use.setFullAddress(user.getFullAddress());		
		userRepos.save(use);
		return use;
	}

	@Override
	public String deleteUser(long userId) {
		// TODO Auto-generated method stub
		 User user = new User();
			user = userRepos.findById(userId).orElseThrow(
					()->new ResourceNotFoundException("User","Id",userId));
	     userRepos.deleteById(userId);
	     return "Record is deleted successfully";
		
	}

	@Override
	public List<User> getUserByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return userRepos.getUserByFirstName(firstName);
	}

	@Override
	public List<User> getUserByLastName(String lastName) {
		// TODO Auto-generated method stub
		return userRepos.getUserByLastName(lastName);
	}

	@Override
	public List<User> getUserByFullName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return userRepos.getUserByFullName(firstName, lastName);
	}

	@Override
	public User getUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return userRepos.findByEmailId(emailId);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepos.getUserByUsername(username);
	}
}