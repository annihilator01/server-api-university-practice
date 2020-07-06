package com.universitypractice.springapplication.controllers;

import com.universitypractice.springapplication.dtos.UserDTO;
import com.universitypractice.springapplication.models.UserModel;
import com.universitypractice.springapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public Map<String, Long> addUser(@RequestBody UserDTO userDTO) {
        UserModel userModel = new UserModel(userDTO.getUsername(), userDTO.getFirstName());
        userModel.setLastName(userDTO.getLastName());
        userModel.setGender(userDTO.getGender());
        userModel.setAge(userDTO.getAge());
        userModel.setEmail(userDTO.getEmail());

        userModel = userService.addUser(userModel);
        userDTO.setId(userModel.getId());

        return Collections.singletonMap("id", userDTO.getId());
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        UserModel userModel = userService.getUser(id);

        UserDTO userDTO = new UserDTO(userModel.getUserName(), userModel.getFirstName());
        userDTO.setId(userModel.getId());
        userDTO.setLastName(userModel.getLastName());
        userDTO.setGender(userModel.getGender());
        userDTO.setAge(userModel.getAge());
        userDTO.setEmail(userModel.getEmail());

        return userDTO;
    }
}
