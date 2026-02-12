package com.example.Techpulse_Booking_System.controller;

import com.example.Techpulse_Booking_System.entity.Booking;
import com.example.Techpulse_Booking_System.exception.SlotNotAvailableException;
import com.example.Techpulse_Booking_System.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> book(@RequestParam Long slotId,
                               Authentication auth) {
        try {
            Booking b = bookingService.bookSlot(slotId, auth.getName());
            return ResponseEntity.ok(b);
        }catch(SlotNotAvailableException e){
                return ResponseEntity.badRequest()
                        .body(e.getMessage());
        }
    }

    @PostMapping("/{id}/cancel")
    public String cancel(@PathVariable Long id,
                         Authentication auth) {

        bookingService.cancelBooking(id, auth.getName(), false);
        return "Booking cancelled";
    }
}

