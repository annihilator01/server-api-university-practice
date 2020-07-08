package com.universitypractice.springapplication.controllers;

import com.universitypractice.springapplication.dtos.UserDTO;
import com.universitypractice.springapplication.dtos.logdtos.ChangeStatusDTO;
import com.universitypractice.springapplication.dtos.logdtos.UserStatusesRequestDTO;
import com.universitypractice.springapplication.models.UserModel;
import com.universitypractice.springapplication.models.UserStatusesModel;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.models.logmodels.UserStatusesRequestModel;
import com.universitypractice.springapplication.services.interfaces.logoperations.ChangeStatusLogService;
import com.universitypractice.springapplication.services.interfaces.logoperations.UserStatusesRequestLogService;
import com.universitypractice.springapplication.services.interfaces.useroperations.AGUUserService;
import com.universitypractice.springapplication.services.interfaces.useroperations.GetUserStatusesModelsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final AGUUserService userService;
    private final ChangeStatusLogService changeStatusLogService;
    private final GetUserStatusesModelsService userStatusesModelsService;
    private final UserStatusesRequestLogService userStatusesRequestLogService;

    @Autowired
    public UserController(AGUUserService userService, ChangeStatusLogService changeStatusLogService,
                          GetUserStatusesModelsService userStatusesModelsService,
                          UserStatusesRequestLogService userStatusesRequestLogService) {
        this.userService = userService;
        this.changeStatusLogService = changeStatusLogService;
        this.userStatusesModelsService = userStatusesModelsService;
        this.userStatusesRequestLogService = userStatusesRequestLogService;
    }

    @PostMapping
    @ApiOperation(value = "Add user", response = UserDTO.class)
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
    @ApiOperation(value = "Get user by id", response = UserDTO.class)
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
    @ApiOperation(value = "Update user status", response = ChangeStatusDTO.class)
    public ChangeStatusDTO updateStatus(@PathVariable Long userId, @RequestBody ChangeStatusDTO changeStatusDTO) {
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

    @GetMapping
    @ApiOperation(value = "Get list of users filtered by status and timestamp of status change", response = UserStatusesRequestDTO.class)
    public UserStatusesRequestDTO getUsersWithFilter(
            @RequestParam(required = false) String status,
            @RequestParam(name = "changed_after_timestamp", required = false) Long changedAfterTimestamp
    ) {
        UserStatusesRequestModel userStatusesRequestModel = new UserStatusesRequestModel(status, changedAfterTimestamp);

        List<UserStatusesModel> userStatusesModels = userStatusesModelsService.get(userStatusesRequestModel);

        userStatusesRequestModel = userStatusesRequestLogService.add(userStatusesRequestModel);

        UserStatusesRequestDTO userStatusesRequestDTO = new UserStatusesRequestDTO(
                userStatusesRequestModel.getId(),
                userStatusesRequestModel.getRequestTimestamp(),
                userStatusesRequestModel.getStatusFilter(),
                userStatusesRequestModel.getChangedAfterTimestamp(),
                userStatusesModels
        );

        return userStatusesRequestDTO;
    }
}
