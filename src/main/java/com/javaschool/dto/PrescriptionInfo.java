package com.javaschool.dto;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
@Getter
@Setter
public class PrescriptionInfo {
    @Valid
    Prescription prescription;
    @Valid
    List<PrescriptionTime> prescriptionTimes;

}
