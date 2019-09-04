package com.javaschool.entities;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Audited
@Table(name = "incompatible_promeds")
public class IncompatibleProcedureAndMedicament {
    @Id
    @Column(name = "incompatible_promed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "promed", referencedColumnName = "type_id")
    private ProcedureAndMedicament promed;
    @ManyToOne
    @JoinColumn(name = "incomp_promed", referencedColumnName = "type_id")
    private ProcedureAndMedicament incompatiblePromed;

}
