package com.bang.authservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AspNetUsers", schema = "dbo")
public class User {

    @Id
    @Column(name = "Id")
    private String id;


    @Column(name = "UserName")
    private String userName;


    @Column(name = "NormalizedUserName")
    private String normalizedUserName;


    @Column(name = "Email")
    private String email;


    @Column(name = "NormalizedEmail")
    private String normalizedEmail;


    @Column(name = "EmailConfirmed")
    private boolean emailConfirmed;


    @Column(name = "PasswordHash")
    private String passwordHash;


    @Column(name = "SecurityStamp")
    private String securityStamp;


    @Column(name = "ConcurrencyStamp")
    private String concurrencyStamp;


    @Column(name = "PhoneNumber")
    private String phoneNumber;


    @Column(name = "PhoneNumberConfirmed")
    private boolean phoneNumberConfirmed;


    @Column(name = "TwoFactorEnabled")
    private boolean twoFactorEnabled;


    @Column(name = "LockoutEnd")
    private OffsetDateTime lockoutEnd;


    @Column(name = "LockoutEnabled")
    private boolean lockoutEnabled;


    @Column(name = "AccessFailedCount")
    private int accessFailedCount;


    @Column(name = "FullName")
    private String fullName;


    @Column(name = "IsActive")
    private boolean isActive;


    @Column(name = "UnitId")
    private UUID unitId;

    @ManyToMany
    Set<Role> roles;

    @PostLoad
    public void postLoad() {
        System.out.println("=== USER ENTITY LOADED ===");
    }

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }

        if (securityStamp == null) {
            securityStamp = UUID.randomUUID().toString();
        }

        if (concurrencyStamp == null) {
            concurrencyStamp = UUID.randomUUID().toString();
        }
    }
}
