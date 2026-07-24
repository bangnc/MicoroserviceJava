package com.bang.authservice.dto.request;

import com.bang.authservice.validator.DobConstraint;

import java.time.LocalDate;

public class RegisterRequest {

    private String userName;

    private String email;

    private String password;

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    @DobConstraint(min = 2, message = "INVALID_DOB")
    private LocalDate dob;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}