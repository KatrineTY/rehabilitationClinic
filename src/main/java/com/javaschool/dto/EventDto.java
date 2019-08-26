package com.javaschool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String date;
    private String patientName;
    private String promedName;
    private String promedKind;
    private String nurseName;
    private String comment;
    private String dose;
    private String building;
    private int ward;
    private String status;

}
