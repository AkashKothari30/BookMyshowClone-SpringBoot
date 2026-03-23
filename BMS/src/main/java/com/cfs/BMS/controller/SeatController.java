package com.cfs.BMS.controller;

import com.cfs.BMS.entity.Seat;
import com.cfs.BMS.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @GetMapping("screen/{screenId}")
   public ResponseEntity<List<Seat>>  getSeatByScreen(@PathVariable Long screenId){
       return ResponseEntity.ok(seatService.getSeatByScreen(screenId));
   }

   @GetMapping("/id")
   public ResponseEntity<Seat> getSeatById(@PathVariable Long id){
        return ResponseEntity.ok(seatService.getSeatById(id));
   }

}
