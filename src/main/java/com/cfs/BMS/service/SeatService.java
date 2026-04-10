package com.cfs.BMS.service;

import com.cfs.BMS.entity.Seat;
import com.cfs.BMS.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final  SeatRepository seatRepository;
    private final  ScreenService screenService;

    private Seat addSeat(Seat seat){
        return seatRepository.save(seat);
    }

    public  List<Seat> getSeatByScreen(Long screenId){
        return seatRepository.findByScreen_Id(screenId);
    }

    public Seat getSeatById(Long id){
        return seatRepository.findById(id).orElseThrow(()-> new RuntimeException("Seat not found by id" + id));
    }


}
