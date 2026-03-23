package com.cfs.BMS.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter  @Setter
@NoArgsConstructor   @AllArgsConstructor
@Builder
@Entity
@Table(name = "theaters")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    private String address;


    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    private City city;

}
