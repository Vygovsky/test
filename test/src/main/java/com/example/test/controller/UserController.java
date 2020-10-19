package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.services.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.sql.SQLException;
import java.util.Collection;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping(path = "/users", produces = "application/json")
    public Collection<User> getAllUsers() throws SQLException {
        return userService.findAll();
    }

    @PostMapping(path = "/user/add")
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping(path = "/user/update")
     public void updateUser(@RequestBody User user) {
          userService.update(user);
     }

    @GetMapping(path = "/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
