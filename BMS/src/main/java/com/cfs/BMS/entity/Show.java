package com.cfs.BMS.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter  @Setter
@NoArgsConstructor   @AllArgsConstructor
@Builder
@Entity
@Table(name = "shows")

public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id",nullable = false)
    private Screen screen;

    @Column(nullable = false)
    private LocalDate showDate;

    @Column(nullable = false )
    private LocalTime startTime;
    private LocalTime endTime;

    private Double ticketPrice;

}
