package com.edu.HotelReservationApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.Exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.Exception.GivenRecordNotFoundException;
import com.edu.HotelReservationApp.Exception.NoRecordFoundException;
import com.edu.HotelReservationApp.Exception.RecordAlreadyExistException;
import com.edu.HotelReservationApp.entity.Reservation;
import com.edu.HotelReservationApp.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepos;

	@Override
	public Reservation saveReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		Optional<Reservation> reserve=reservationRepos.findById(reservation.getResId());
		if(! reserve.isPresent())
		return reservationRepos.save(reservation);
		else
			throw new RecordAlreadyExistException();
		
	}

	@Override
	public List<Reservation> getReservationList() {
		// TODO Auto-generated method stub
	    List<Reservation> reserve = reservationRepos.findAll();
	    if(reserve.isEmpty())
	    	throw new NoRecordFoundException();
	    else
	    	return reserve;
	}

	@Override
	public Reservation getReservationById(long resId) {
		// TODO Auto-generated method stub
	/*	Reservation reservation = new Reservation();
		reservation = reservationRepos.findById(resId).orElseThrow(
				()->new ResourceNotFoundException("Reservation" , "Id",resId));
		return reservation;
	}*/
		Optional<Reservation> reservation =reservationRepos.findById(resId);
		if(reservation.isPresent()) {
			return reservation.get();
		}
		else {
			throw new GivenIdNotFoundException();	
		}
		}
	
	@Override
	public Reservation updateReservation(long resId, Reservation reservation) {
		// TODO Auto-generated method stub
		Reservation reserv = new Reservation();
		reserv = reservationRepos.findById(resId).orElseThrow(
				()->new NoRecordFoundException());
	    reserv.setNoOfGuest(reservation.getNoOfGuest());
	    reserv.setStayDays(reservation.getStayDays());
	    reserv.setReserveDate(reservation.getReserveDate().now());
	    reserv.setCheckInDateTime(reservation.getCheckInDateTime());
	    reserv.setCheckOutDateTime(reservation.getCheckInDateTime().plusDays(reservation.getStayDays()));
	    reservationRepos.save(reserv);
		return reserv;
	}

	@Override
	public String deleteReservation(long resId) {
		// TODO Auto-generated method stub
		Reservation reservation = new Reservation();
		reservation = reservationRepos.findById(resId).orElseThrow(
				()->new NoRecordFoundException());
		reservationRepos.deleteById(resId);
		return "Record is deleted successfully";
	}
    @Override
    public List<Reservation> getReservationByCheckInDateTime(LocalDateTime checkInDateTime ) {
    	
    	System.out.println(checkInDateTime);
    	List<Reservation> reservation = reservationRepos.getReservationByCheckInDateTime(checkInDateTime);
    	if(reservation.isEmpty())
    		throw new NoRecordFoundException();
    	else
    		return reservation;
    }
    @Override
    public List<Reservation> getReservationDateByReserveDate(LocalDateTime reserveDate) {
    	System.out.println(reserveDate);
    	List<Reservation> reservation = reservationRepos.getReservationByReserveDate(reserveDate);
        if(reservation.isEmpty())
        	throw new NoRecordFoundException();
        else
        	return reservation;
    }
    @Override
    public List<Reservation> getReservationByUserId(long userId) {
    	List<Reservation> reservation = reservationRepos.getReservationByUserId(userId);
    	if(reservation.isEmpty())
    		throw new GivenIdNotFoundException();
    	else
    		return reservation;
    }
   
    }
