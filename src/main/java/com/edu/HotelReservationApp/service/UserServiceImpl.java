package com.edu.HotelReservationApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.Exception.GivenEmailIdNotFoundException;
import com.edu.HotelReservationApp.Exception.GivenFirstNameNotFoundException;
import com.edu.HotelReservationApp.Exception.GivenFullNameNotFoundException;
import com.edu.HotelReservationApp.Exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.Exception.GivenLastNameNotFoundException;
import com.edu.HotelReservationApp.Exception.GivenUsernameNotFoundException;
import com.edu.HotelReservationApp.Exception.NoRecordFoundException;
import com.edu.HotelReservationApp.Exception.RecordAlreadyExistException;
import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepos;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> use =userRepos.findById(user.getUserId());
		if(!use.isPresent())
		return userRepos.save(user);
		else
			throw new RecordAlreadyExistException();
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		List<User> use = userRepos.findAll();
		if(use.isEmpty())
			throw new NoRecordFoundException();
		else
			return use;
	}

	@Override
	public User getUserById(long userId) {
		// TODO Auto-generated method stub
		/*User user = new User();
		user = userRepos.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User","Id",userId));
		return user;
	}*/
		Optional<User> user=userRepos.findById(userId);
        if(user.isPresent()) {
        	return user.get();
        }
        else {
        	throw new GivenIdNotFoundException();
        }
        }
	@Override
	public User updateUser(long userId, User user) {
		// TODO Auto-generated method stub
	   User use = new User();
		use = userRepos.findById(userId).orElseThrow(
				()->new NoRecordFoundException());
		
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
					()->new NoRecordFoundException());
	     userRepos.deleteById(userId);
	     return "Record is deleted successfully";
		
	}

	@Override
	public List<User> getUserByFirstName(String firstName) {
		// TODO Auto-generated method stub
	//	return userRepos.getUserByFirstName(firstName);
		List<User> use=userRepos.getUserByFirstName(firstName);
		if(use.isEmpty())
			throw new GivenFirstNameNotFoundException();
		else
			return use;
			
	}

	@Override
	public List<User> getUserByLastName(String lastName) {
		// TODO Auto-generated method stub
		//return userRepos.getUserByLastName(lastName);
		List<User> use=userRepos.getUserByLastName(lastName);
		if(use.isEmpty())
			throw new GivenLastNameNotFoundException();
		else
			return use;
	}

	@Override
	public List<User> getUserByFullName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		//return userRepos.getUserByFullName(firstName, lastName);
	    List<User> use= userRepos.getUserByFullName(firstName,lastName);
	    if(use.isEmpty())
	    	throw new GivenFullNameNotFoundException();
	    else
	    	return use;
		
	}

	@Override
	public User getUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		//return userRepos.findByEmailId(emailId);
		Optional<User> user = userRepos.findByEmailId(emailId);
		if(user.isPresent()) {
			return user.get();
	}
	else {
			throw new GivenEmailIdNotFoundException();
	}
		
}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		//return userRepos.getUserByUsername(username);
		Optional<User> user=userRepos.findByUsername(username);
		if(user.isPresent()) {
			return user.get();
	}
		else {
			throw new GivenUsernameNotFoundException();
}
}
}