package com.universitypractice.springapplication.controllers;

import com.universitypractice.springapplication.dtos.UserDTO;
import com.universitypractice.springapplication.dtos.logdtos.ChangeStatusDTO;
import com.universitypractice.springapplication.models.UserModel;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.services.interfaces.logging.ChangeStatusLogService;
import com.universitypractice.springapplication.services.interfaces.operations.AGUUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final AGUUserService userService;
    private final ChangeStatusLogService changeStatusLogService;

    @Autowired
    public UserController(AGUUserService userService, ChangeStatusLogService changeStatusLogService) {
        this.userService = userService;
        this.changeStatusLogService = changeStatusLogService;
    }

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        UserModel userModel = new UserModel(
                userDTO.getUsername(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getGender(),
                userDTO.getAge(),
                userDTO.getEmail()
        );

        userModel = userService.add(userModel);
        userDTO.setId(userModel.getId());

        return userDTO;
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Long userId) {
        UserModel userModel = userService.get(userId);

        UserDTO userDTO = new UserDTO(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getGender(),
                userModel.getAge(),
                userModel.getEmail()
        );

        return userDTO;
    }

    @PatchMapping("/{userId}")
    public ChangeStatusDTO changeStatus(@PathVariable Long userId, @RequestBody ChangeStatusDTO changeStatusDTO) {
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(
                userId,
                changeStatusDTO.getNewStatus()
        );

        changeStatusModel = userService.update(userId, changeStatusModel);
        changeStatusModel = changeStatusLogService.add(changeStatusModel);

        changeStatusDTO.setResponseParams(
                changeStatusModel.getId(),
                changeStatusModel.getUserId(),
                changeStatusModel.getOldStatus(),
                changeStatusModel.getStatusChangedTime()
        );

        return changeStatusDTO;
    }
}
