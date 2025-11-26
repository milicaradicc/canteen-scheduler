package com.example.demo.repository;

import com.example.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;


public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query(
            value = "SELECT COUNT(*) FROM reservation r " +
                    "WHERE r.canteen_id = :canteenId " +
                    "AND r.status = 'ACTIVE' " +
                    "AND r.start_time < :slotEnd " +
                    "AND DATEADD('MINUTE', r.duration_minutes, r.start_time) > :slotStart",
            nativeQuery = true
    )
    int countReservationsInInterval(@Param("canteenId") Long canteenId,
                                    @Param("slotStart") LocalDateTime slotStart,
                                    @Param("slotEnd") LocalDateTime slotEnd);

}
