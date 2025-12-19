package com.Tarea.demo.repo;

import com.Tarea.demo.entity.Reservation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    @Query("""
        SELECT COUNT(r) FROM Reservation r
        WHERE r.roomId = :roomId
          AND r.checkInDate < :checkOut
          AND r.checkOutDate > :checkIn
    """)
    long countConflictingReservations(
            @Param("roomId") Integer roomId,
            @Param("checkIn") LocalDateTime checkIn,
            @Param("checkOut") LocalDateTime checkOut
    );
}
