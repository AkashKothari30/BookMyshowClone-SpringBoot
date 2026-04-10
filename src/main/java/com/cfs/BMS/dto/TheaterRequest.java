package com.cfs.BMS.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TheaterRequest
{
    private String name;
    private String address;

    private Long cityId;

}
