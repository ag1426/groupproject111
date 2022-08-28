package com.example.groupproject.controller;

import com.example.groupproject.entity.User;
import com.example.groupproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }

    @PostMapping({"/register"})
    public User registerNewUser(@RequestBody User user){
       return userService.registerNewUser(user);

    }
    @GetMapping({"/admin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to Admin";
    }

    @GetMapping({"/user"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the User";
    }

    @GetMapping({"/merchant"})
    @PreAuthorize("hasRole('Merchant')")
    public String forMerchant(){
        return "This URL is only accessible to the Merchant";
    }
}
