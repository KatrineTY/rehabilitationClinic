package com.javaschool.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Data
@Component
public class TimePeriodInfo {
    private LocalTime startTime = LocalTime.of(0, 0);
    private LocalTime endTime = LocalTime.of(23, 59);

}

