package service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
import com.edu.HotelReservationApp.entity.Reservation;
import com.edu.HotelReservationApp.repository.ReservationRepository;
import com.edu.HotelReservationApp.service.ReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
	@Mock
	private ReservationRepository reservationRepos;
	@Autowired
	@InjectMocks
	private ReservationServiceImpl reservationService;
	
	//
	private Reservation reservation1;
	private Reservation reservation2;
	List<Reservation> reservationList;
	LocalDateTime d=LocalDateTime.of(2022,07,10,14,56);
	LocalDateTime d1=LocalDateTime.of(2022,07,13,14,56);
	
	//Method to execute before each testcase execution
	//before each testcase
	@BeforeEach
	public void setup() {
		reservationList = new ArrayList<>();
		reservation1 = new Reservation(11,2,3,d,d1);
		
		reservation2 = new Reservation(12,3,2,d,d1);
		
		reservationList.add(reservation1);
		reservationList.add(reservation2);
	}
	//  Method to execute after each testcase execution
	@AfterEach
	public void afterTest() {
		reservation1 = reservation2=null;
		reservationList = null;
	}
	//
	@DisplayName("Test for saveReservation() method")
	@Test
	public void givenReservationToAddShouldReturnAddReservation() {
		when(reservationRepos.save(reservation1)).thenReturn(reservation1);
		//
		Reservation savedReservation = reservationService.saveReservation(reservation1);
		System.out.println(savedReservation);
		assertThat(savedReservation).isNotNull();
	}
	
	//
	@Test
	public void givenExistingIdWhenSaveReservationThenThrowsException() {
		Reservation reservation = new Reservation(11,2,3,d,d1);
		when(reservationRepos.findById(reservation.getResId()))// 11 
		      .thenReturn(Optional.of(reservation));// 11
	

	org.junit.jupiter.api.Assertions.assertThrows(RecordAlreadyExistException.class, () -> {
		reservationService.saveReservation(reservation);
	});
}

	//
	@Test
	public void givenGetAllReservationShouldReturnListOfAllReservations()throws NoRecordFoundException {
		reservationRepos.saveAll(reservationList);
		when(reservationRepos.findAll()).thenReturn(reservationList);
        List<Reservation> actualReservationList = reservationService.getReservationList();
        assertThat(actualReservationList).isEqualTo(reservationList);
	}
	@Test
	public void givenIdThenShouldReturnReservationOfThatId() throws GivenIdNotFoundException{
		when(reservationRepos.findById(11L)).thenReturn(Optional.ofNullable(reservation1));
		assertThat(reservationService.getReservationById(reservation1.getResId())).isEqualTo(reservation1);
	}
	@Test
	public void givenIdToDeleteThenShouldDeleteReservationOfThatId() {
		when(reservationRepos.findById(reservation1.getResId())).thenReturn (Optional.ofNullable(reservation1));
		assertThat(reservationService.deleteReservation(reservation1.getResId())).isEqualTo("Record is deleted successfully");
		
	}
	@Test
	public void givenIdToDeleteNotExistThenThrowsException() {
		long resId = 11L;
		org.junit.jupiter.api.Assertions.assertThrows(NoRecordFoundException.class, () ->  {
			reservationService.deleteReservation(resId);
		});
	}
	
	@DisplayName("JUnit test for updateReservation method")
    @Test
    public void givenReservationObject_whenUpdateReservation_thenReturnUpdatedReservation(){
    	long resId = 11L;
        when(reservationRepos.save(reservation1)).thenReturn(reservation1);
        when(reservationRepos.findById(resId)).thenReturn(Optional.of(reservation1));
        reservation1.setNoOfGuest(4);
        reservation1.setStayDays(3);
        reservation1.setCheckInDateTime(d);
        reservation1.setCheckOutDateTime(d1);
        Reservation updatedReservation = reservationService.updateReservation(resId, reservation1);
		System.out.println(updatedReservation);

        assertThat(updatedReservation.getNoOfGuest()).isEqualTo(4);
        assertThat(updatedReservation.getStayDays()).isEqualTo(3);
        assertThat(updatedReservation.getCheckInDateTime()).isEqualTo(d);
        assertThat(updatedReservation.getCheckOutDateTime()).isEqualTo(d1);
    }
    
    @Test
	public void givenIdToUpdateNotExistThenThrowsException()  {
		long resId = 11L;
		Reservation reservation = new Reservation();
		reservation1.setNoOfGuest(2);
		reservation1.setStayDays(3);
		reservation1.setCheckInDateTime(d);
        reservation1.setCheckOutDateTime(d1);   
        
	    org.junit.jupiter.api.Assertions.assertThrows(NoRecordFoundException.class, () -> {
	    reservationService.updateReservation(resId, reservation);
});

	} 
}