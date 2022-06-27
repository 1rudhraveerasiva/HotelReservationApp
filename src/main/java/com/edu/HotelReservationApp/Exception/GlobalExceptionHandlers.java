package com.edu.HotelReservationApp.Exception;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NameNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlers {
	
	@ExceptionHandler(GivenIdNotFoundException.class)
	public ResponseEntity<Object> handleGivenIdNotFoundException() {
		return new ResponseEntity<Object>("Given Id is Not Available", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<Object> handleNoRecordFoundException() {
		return new ResponseEntity<Object>("Given Record Is Not Available", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(GivenFullNameNotFoundException.class)
	public ResponseEntity<Object> handleFullNameNotFoundException() {
		return new ResponseEntity<Object>("Full Name Not Available", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(GivenFirstNameNotFoundException.class)
	public ResponseEntity<Object> handleGivenFirstNameNotFoundException() {
		return new ResponseEntity<Object>("Given FirstName Is Not Available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(GivenLastNameNotFoundException.class)
	public ResponseEntity<Object>handleGivenLastNameNotFoundException() {
		return new ResponseEntity<Object>("Given LastName is Not Available",HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(GivenUsernameNotFoundException.class)
	public ResponseEntity<Object>handleGivenUsernameNotFoundException() {
		return new ResponseEntity<Object>("Given Username is Not Availabe",HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(GivenEmailIdNotFoundException.class)
	public ResponseEntity<Object>handleGivenEmailIdNotFoundException() {
		return new ResponseEntity<Object>("Given EmailId is Not Available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(GivenRecordNotFoundException.class)
	public ResponseEntity<Object>handleGivenRecordNotFoundException() {
       return new ResponseEntity<Object>("Given Record is Not Availabe",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(GivenStatusNotFoundException.class)
	public ResponseEntity<Object>handleGivenStatusNotFoundException() {
		return new ResponseEntity<Object>("Given Status is Not Available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=(MethodArgumentNotValidException.class))
	public ResponseEntity<Map<String,String> > handleValidationExceptions (
			MethodArgumentNotValidException ex) {
		
		Map<String, String>errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			
			String fieldName= ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName,errorMessage);
			
			});
			
			return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
		}
}
		

