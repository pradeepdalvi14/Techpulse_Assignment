package com.example.Techpulse_Booking_System.controller;

import com.example.Techpulse_Booking_System.entity.Slot;
import com.example.Techpulse_Booking_System.entity.SlotStatus;
import com.example.Techpulse_Booking_System.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private SlotRepository slotRepository;

    @PostMapping
    public Slot createSlot(@RequestBody Slot slot) {
        slot.setStatus(SlotStatus.AVAILABLE);
        return slotRepository.save(slot);
    }

    @GetMapping
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }
}

