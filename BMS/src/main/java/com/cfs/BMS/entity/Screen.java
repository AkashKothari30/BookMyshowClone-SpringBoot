package com.cfs.BMS.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter   @Setter
@AllArgsConstructor     @NoArgsConstructor
@Builder
@Entity
@Table(name="screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "total_seats")
    private Integer totalSeat;

    @ManyToOne
    @JoinColumn(name= "theater_id",nullable = false)
    private Theater theater;

}
