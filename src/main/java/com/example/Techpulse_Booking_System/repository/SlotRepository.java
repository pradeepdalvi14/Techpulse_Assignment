package com.example.Techpulse_Booking_System.repository;

import com.example.Techpulse_Booking_System.entity.Slot;
import com.example.Techpulse_Booking_System.entity.SlotStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Slot s WHERE s.id = :id")
    Optional<Slot> findSlotForUpdate(@Param("id") Long id);

    @Query("SELECT s FROM Slot s where s.status = :status")
    List<Slot> findBySlotStatus(SlotStatus status);

}

