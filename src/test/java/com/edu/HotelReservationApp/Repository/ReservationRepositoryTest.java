package com.edu.HotelReservationApp.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.edu.HotelReservationApp.entity.Reservation;
import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.repository.ReservationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;
    
@Test
public void saveReservationTest() {///testcase
	
	LocalDateTime d= LocalDateTime.of(2022,06,18,14,50);
	LocalDateTime d1= LocalDateTime.of(2022,06,20,14,50);
	
    Reservation reservation =reservationRepository.save(new Reservation(902,2,2,d,d1, d1));
	
	Assertions.assertThat(reservation.getResId()).isGreaterThan(0);
}

@Test
public void getReservationTest() {
	Reservation reservation = reservationRepository.findById(252L).get();
	Assertions.assertThat(reservation.getResId()).isEqualTo(252L);
}
@Test
public void getReservationListTest() {
	List<Reservation> reservations = reservationRepository.findAll();
	Assertions.assertThat(reservations.size()).isGreaterThan(0);
}

@Test
public void updateReservationTest() {
	Reservation reservation = reservationRepository.findById(902L).get();
	reservation.setStayDays(2);
	Reservation updated = reservationRepository.save(reservation);
	
	Assertions.assertThat(updated.getStayDays()).isEqualTo(2);
	
}
@Test
public void deleteReservationTest() {
Reservation res= reservationRepository.findById(952L).get();
reservationRepository.delete(res);

Reservation reservation = null;
Optional<Reservation> reservation1 = reservationRepository.findByNoOfGuest(1);


if(reservation1.isPresent()) {
	reservation  = reservation1.get();
}
Assertions.assertThat(reservation).isNull();

}
}


