package com.javaschool.dao.objects;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
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
    @Column(name = "working_time")
    private String workingTime;

}
