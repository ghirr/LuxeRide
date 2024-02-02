package com.ghirr.LuxeRide.entity;

import com.ghirr.LuxeRide.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
          ,generator = "user_seq"
    )
    @SequenceGenerator(
            name ="user_seq" ,
        //    sequenceName = "",
            allocationSize = 1
    )
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private UserRole userRole;
}
