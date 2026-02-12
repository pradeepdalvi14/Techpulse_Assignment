package com.example.Techpulse_Booking_System.service;

import com.example.Techpulse_Booking_System.entity.Booking;
import com.example.Techpulse_Booking_System.entity.BookingStatus;
import com.example.Techpulse_Booking_System.entity.Slot;
import com.example.Techpulse_Booking_System.entity.SlotStatus;
import com.example.Techpulse_Booking_System.exception.SlotNotAvailableException;
import com.example.Techpulse_Booking_System.repository.BookingRepository;
import com.example.Techpulse_Booking_System.repository.SlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public Booking bookSlot(Long slotId, String userId) throws SlotNotAvailableException {

        Slot slot = slotRepository.findSlotForUpdate(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.getStatus() == SlotStatus.BOOKED) {
            throw new SlotNotAvailableException("Slot already booked");
        }

        slot.setStatus(SlotStatus.BOOKED);

        Booking booking = new Booking();
        booking.setSlot(slot);
        booking.setUserId(userId);
        booking.setStatus(BookingStatus.ACTIVE);
        booking.setCreatedAt(LocalDateTime.now());

        bookingRepository.save(booking);
        slotRepository.save(slot);

        return booking;
    }

    @Transactional
    public void cancelBooking(Long bookingId, String userId, boolean isAdmin) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!isAdmin && !booking.getUserId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Slot slot = booking.getSlot();
        slot.setStatus(SlotStatus.AVAILABLE);

        bookingRepository.save(booking);
        slotRepository.save(slot);
    }
}

