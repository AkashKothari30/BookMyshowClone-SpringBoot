package com.cfs.BMS.controller;

import com.cfs.BMS.dto.BookingRequest;
import com.cfs.BMS.entity.Booking;
import com.cfs.BMS.enums.BookingStatus;
import com.cfs.BMS.service.BookingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request){
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>>  getBookingByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(bookingService.getBookingByUserId(userId));
    }


    @GetMapping("/show/{showId}/available-seats")
    public ResponseEntity<List<Long>> getAvailableSeats(@PathVariable Long showId) {
        return ResponseEntity.ok(bookingService.getAvailableSeatIds(showId));
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

}
