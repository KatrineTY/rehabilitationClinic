package com.javaschool.entities;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String position;
    @Column
    @NotBlank
    @ToString.Exclude
    private String login;
    @Column
    @NotBlank
    @ToString.Exclude
    private String password;
    @Column
    private String phone;
    @Column
    private String email;
    @ManyToOne
    @JoinColumn(name = "working_time", referencedColumnName = "working_time_id")
    @ToString.Exclude
    private WorkingTime workingTime;
    @Column
    private boolean enabled = true;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "role_id")
    private Role role;

}
