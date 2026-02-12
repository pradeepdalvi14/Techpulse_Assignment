package com.example.Techpulse_Booking_System.service;


import com.example.Techpulse_Booking_System.entity.Slot;
import com.example.Techpulse_Booking_System.entity.SlotStatus;
import com.example.Techpulse_Booking_System.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {
    @Autowired
    private SlotRepository slotRepository;

    public List<Slot> getAllAvailableSlots(){

        return slotRepository.findBySlotStatus(SlotStatus.AVAILABLE);
    }

    public List<Slot> getAllBookedSlots(){

        return slotRepository.findBySlotStatus(SlotStatus.BOOKED);
    }
}
