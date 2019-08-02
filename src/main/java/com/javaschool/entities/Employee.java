package com.javaschool.entities;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Audited
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
    private String login;
    @Column
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
