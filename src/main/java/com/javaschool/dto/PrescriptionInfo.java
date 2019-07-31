package com.javaschool.dto;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class PrescriptionInfo {
    Prescription prescription;
    List<PrescriptionTime> prescriptionTimes;

}
