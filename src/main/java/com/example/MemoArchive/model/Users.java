package com.example.MemoArchive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @JsonIgnore // Doesn't need to be passed in on a POST/CREATE
    @Min(value = 1, message = "User ID must be greater than 0.")
    private int userId;

    @NotBlank(message = "First name is mandatory.")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is mandatory.")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
    private String lastName;

    @NotBlank(message = "Email address is mandatory.")
    @Size(min = 5, max = 50, message = "Email address must be between 5 and 50 characters.")
    private String email;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 2, max = 50, message = "Password must be between 2 and 50 characters.")
    private String password;

    @NotBlank(message = "Username is mandatory.")
    @Size(min = 2, max = 25, message = "Username must be between 2 and 25 characters.")
    private String username;

 //TODO: Make sure the not null is removed from SQL database code and other parts of classes
    @JsonIgnore // Not mandatory for post/put
    @PastOrPresent(message = "Account creation date must be in the past or today.")
    private LocalDate accountCreationDate;

    @JsonIgnore
    private boolean activated = true;

    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>(Set.of(new Authority("ROLE_USER")));

// Getters, setters, and constructors (no arg and all arg) are created with Lombok.
}
