package com.universitypractice.springapplication.controllers;

import com.universitypractice.springapplication.dtos.request.ChangeStatusRequestDTO;
import com.universitypractice.springapplication.dtos.request.UserRequestDTO;
import com.universitypractice.springapplication.dtos.response.ChangeStatusResponseDTO;
import com.universitypractice.springapplication.dtos.response.UserResponseDTO;
import com.universitypractice.springapplication.dtos.response.UserStatusesResponseDTO;
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
    @ApiOperation(value = "Add user", response = UserResponseDTO.class)
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserModel userModel = new UserModel(
                userRequestDTO.getUsername(),
                userRequestDTO.getFirstName(),
                userRequestDTO.getLastName(),
                userRequestDTO.getGender(),
                userRequestDTO.getAge(),
                userRequestDTO.getEmail()
        );

        userModel = userService.add(userModel);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getGender(),
                userModel.getAge(),
                userModel.getEmail()
        );

        return userResponseDTO;
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get user by id", response = UserResponseDTO.class)
    public UserResponseDTO getUser(@PathVariable Long userId) {
        UserModel userModel = userService.get(userId);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getGender(),
                userModel.getAge(),
                userModel.getEmail()
        );

        return userResponseDTO;
    }

    @PatchMapping("/{userId}")
    @ApiOperation(value = "Update user status", response = ChangeStatusResponseDTO.class)
    public ChangeStatusResponseDTO updateStatus(
            @PathVariable Long userId,
            @RequestBody ChangeStatusRequestDTO changeStatusRequestDTO
    ) {
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(
                userId,
                changeStatusRequestDTO.getNewStatus()
        );

        changeStatusModel = userService.update(userId, changeStatusModel);
        changeStatusModel = changeStatusLogService.add(changeStatusModel);

        ChangeStatusResponseDTO changeStatusResponseDTO = new ChangeStatusResponseDTO(
                changeStatusModel.getId(),
                changeStatusModel.getUserId(),
                changeStatusModel.getOldStatus(),
                changeStatusModel.getNewStatus(),
                changeStatusModel.getStatusChangedTime()
        );

        return changeStatusResponseDTO;
    }

    @GetMapping
    @ApiOperation(value = "Get list of users filtered by status and timestamp of status change", response = UserStatusesResponseDTO.class)
    public UserStatusesResponseDTO getUsersWithFilter(
            @RequestParam(required = false) String status,
            @RequestParam(name = "changed_after_timestamp", required = false) Long changedAfterTimestamp
    ) {
        UserStatusesRequestModel userStatusesRequestModel = new UserStatusesRequestModel(status, changedAfterTimestamp);

        List<UserStatusesModel> userStatusesModels = userStatusesModelsService.get(userStatusesRequestModel);

        userStatusesRequestModel = userStatusesRequestLogService.add(userStatusesRequestModel);

        UserStatusesResponseDTO userStatusesResponseDTO = new UserStatusesResponseDTO(
                userStatusesRequestModel.getId(),
                userStatusesRequestModel.getRequestTimestamp(),
                userStatusesRequestModel.getStatusFilter(),
                userStatusesRequestModel.getChangedAfterTimestamp(),
                userStatusesModels
        );

        return userStatusesResponseDTO;
    }
}
