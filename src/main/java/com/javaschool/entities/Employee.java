package com.javaschool.entities;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "[a-zA-Z]+([ ][a-zA-Z]+)+")
    private String name;
    @Column
    @Pattern(regexp = "[a-zA-Z ]+")
    private String position;
    @Column
    @ToString.Exclude
    private String login;
    @Column
    @ToString.Exclude
    private String password;
    @Column
    @Pattern(regexp = "[(0-9+\\- )]+")
    private String phone;
    @Column
    @Email(message = "Wrong email format")
    @NotBlank
    private String email;
    @ManyToOne
    @JoinColumn(name = "working_time", referencedColumnName = "working_time_id")
    @ToString.Exclude
    @NotNull
    private WorkingTime workingTime;
    @Column
    private boolean enabled = true;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "role_id")
    @NotNull
    private Role role;

}
