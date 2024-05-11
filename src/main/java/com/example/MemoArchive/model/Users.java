package com.example.MemoArchive.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

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

    @NotNull(message = "Account creation date is mandatory.")
    @PastOrPresent(message = "Account creation date must be in the past or today.")
    private LocalDate accountCreationDate;

// Getters, setters, and constructors (no arg and all arg) are created with Lombok.
}
