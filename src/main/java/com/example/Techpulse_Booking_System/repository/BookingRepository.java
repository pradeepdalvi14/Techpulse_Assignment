package com.example.Techpulse_Booking_System.repository;

import com.example.Techpulse_Booking_System.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

