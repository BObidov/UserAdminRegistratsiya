package com.company.userandadminauthorities.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    private String surname;
    private String name;
    private String middleName;
    private String email;
    @Column(name = "email_password")
    private String emailPassword;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Integer birthday;
    @Column(name = "birth_month")
    private Integer birthMonth;
    @Column(name = "birth_year")
    private Integer birthYear;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String username;
    private String password;
    private Boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private List<Authorities> authorities;

}
