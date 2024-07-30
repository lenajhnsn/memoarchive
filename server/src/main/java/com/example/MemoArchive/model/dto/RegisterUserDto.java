package com.example.MemoArchive.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints .*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * RegisterUserDto is a class used to hold the registration information for a new user
 * that's sent from the client to the server for the register endpoint.
 *
 * The acronym DTO is being used for "data transfer object". It means that this type of
 * class is specifically created to transfer data between the client and the server.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

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


// Getters, setters, and constructors (no arg and all arg) are created with Lombok.

}
