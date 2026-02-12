package com.example.Techpulse_Booking_System.controller;

import com.example.Techpulse_Booking_System.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings/{id}/cancel")
    public String adminCancel(@PathVariable Long id) {

        bookingService.cancelBooking(id, null, true);
        return "Admin cancelled booking";
    }
}

