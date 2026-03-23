package com.cfs.BMS.service;

import com.cfs.BMS.dto.BookingRequest;
import com.cfs.BMS.entity.Booking;
import com.cfs.BMS.entity.Seat;
import com.cfs.BMS.entity.Show;
import com.cfs.BMS.entity.User;
import com.cfs.BMS.enums.BookingStatus;
import com.cfs.BMS.repository.BookingRepository;
import com.cfs.BMS.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final UserService userService;
    private final ShowService showService;


@Transactional
    public Booking createBooking(BookingRequest request){
        User user = userService.getUserdById(request.getUserId());
        Show show = showService.getShowById(request.getUserId());

        //check if seat are already book
        List<Long> alreadyBookedSeats = bookingRepository.findBookedSeatBYShowId(show.getId());

        for(Long seatId : request.getSeatIds()){
            if(alreadyBookedSeats.contains(seatId)){
                throw new RuntimeException("Seat with id" + seatId + "already Booked");
            }
        }
        List<Seat> seats = seatRepository.findAllById(request.getSeatIds());
        if(seats.size() != request.getSeatIds().size()){
            throw new RuntimeException("Something seats are Invalid");
        }

        Double totalPrice =seats.size() * show.getTicketPrice();

        Booking booking = Booking.builder()
                .user(user)
                .show(show)
                .seats(seats)
                .totalPrice(totalPrice)
                .status(BookingStatus.CONFIRMED)
                .build();
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id){
        return bookingRepository.findById(id).orElseThrow(()->new RuntimeException("Booking seat not found with id" + id));
    }
    public List<Booking> getBookingByUserId(Long userId){
        return bookingRepository.findByUserId(userId);

    }

    @Transactional
    public Booking cancelBooking(Long bookingId){
        Booking booking = getBookingById(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);

        return bookingRepository.save(booking);

    }

    public List<Seat> getAvailableSeat(Long showId){
        Show show = showService.getShowById(showId);
        List<Seat> allSeats = seatRepository.findByScreen_Id(show.getScreen().getId());
        List<Long> bookingSeatsIds = bookingRepository.findBookedSeatBYShowId(showId);

        return allSeats.stream()
                .filter(seat -> !bookingSeatsIds.contains(seat.getId()))
                        .toList();
    }

}
