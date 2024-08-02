package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.UsersDao;
import com.example.MemoArchive.exception.DaoException;
import com.example.MemoArchive.model.Users;
import com.example.MemoArchive.model.dto.LoginDto;
import com.example.MemoArchive.model.dto.LoginResponseDto;
import com.example.MemoArchive.model.dto.RegisterUserDto;
import com.example.MemoArchive.security.jwt.TokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

/**
 * AuthenticationController is a class used for handling requests to authenticate Users.
 *
 * It depends on an instance of a UserDao for retrieving and storing user data. This is provided
 * through dependency injection.
 */

@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UsersDao usersDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UsersDao usersDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.usersDao = usersDao;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResponseDto login(@Valid @RequestBody LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);

            Users user = usersDao.getUserByUsername(loginDto.getUsername());
            System.out.println(user);
            return new LoginResponseDto(jwt, user);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Users register(@Valid @RequestBody RegisterUserDto newUser) {
        try {
            Users usersObject = new Users();
            usersObject.setUsername(newUser.getUsername());
            usersObject.setPassword(newUser.getPassword());
            usersObject.setAccountCreationDate(LocalDate.now());
            usersObject.setEmail(newUser.getEmail());
            usersObject.setFirstName(newUser.getFirstName());
            usersObject.setLastName(newUser.getLastName());


            Users user = usersDao.addUser(usersObject);
            return user;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

}
