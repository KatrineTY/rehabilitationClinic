package com.javaschool.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
//    @Pattern(regexp = "^[a-zA-Z]+(\\.[a-zA-Z]+)*$")
    @NotBlank
    private String login;
    @Column
    @NotBlank
    private String password;
    @Column
    private String phone;
    @Column
    private String email;
    @ManyToOne
    @JoinColumn(name = "working_time", referencedColumnName = "working_time_id")
    private WorkingTime workingTime;
    @Column
    private boolean enabled = true;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "role_id")
    private Role role;

}
