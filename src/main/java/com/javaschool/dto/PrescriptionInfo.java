package com.javaschool.dto;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionInfo {
    @Valid
    Prescription prescription;
    @Valid
    List<PrescriptionTime> prescriptionTimes;

}
