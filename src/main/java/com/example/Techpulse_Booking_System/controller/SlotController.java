package com.example.Techpulse_Booking_System.controller;

import com.example.Techpulse_Booking_System.entity.Slot;
import com.example.Techpulse_Booking_System.entity.SlotStatus;
import com.example.Techpulse_Booking_System.repository.SlotRepository;
import com.example.Techpulse_Booking_System.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private SlotService slotService;

    @PostMapping
    public Slot createSlot(@RequestBody Slot slot) {
        slot.setStatus(SlotStatus.AVAILABLE);
        return slotRepository.save(slot);
    }

    @GetMapping
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    @GetMapping("availableSlots")
    public ResponseEntity<?> getAllAvailableSlots(){
        try{
            List<Slot> response = slotService.getAllAvailableSlots();
            return ResponseEntity.ok(response);

        }catch(Exception e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }
    }
    @GetMapping("bookedSlots")
    public ResponseEntity<?> getAllBookedSlots(){
        try{
            List<Slot> response = slotService.getAllBookedSlots();
            return ResponseEntity.ok(response);

        }catch(Exception e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }
    }
}

