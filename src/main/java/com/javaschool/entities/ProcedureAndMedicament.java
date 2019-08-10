package com.javaschool.entities;

import com.javaschool.validators.ProcAndMedConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Audited
@Table(name = "proceds_and_medics")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ProcAndMedConstraint
public class ProcedureAndMedicament {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String kind;

}
