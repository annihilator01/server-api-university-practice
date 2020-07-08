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
import com.universitypractice.springapplication.services.interfaces.baseoperations.AGUUserService;
import com.universitypractice.springapplication.services.interfaces.GetUserStatusesModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final AGUUserService userService;
    private final ChangeStatusLogService changeStatusLogService;
    private final GetUserStatusesModelsService getUserStatusesModelsService;
    private final UserStatusesRequestLogService userStatusesRequestLogService;

    @Autowired
    public UserController(AGUUserService userService, ChangeStatusLogService changeStatusLogService,
                          GetUserStatusesModelsService getUserStatusesModelsService,
                          UserStatusesRequestLogService userStatusesRequestLogService) {
        this.userService = userService;
        this.changeStatusLogService = changeStatusLogService;
        this.getUserStatusesModelsService = getUserStatusesModelsService;
        this.userStatusesRequestLogService = userStatusesRequestLogService;
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
    public UserStatusesRequestDTO getUsersWithFilter(
            @RequestParam(required = false) String status,
            @RequestParam(name = "changed_after_timestamp", required = false) Long changedAfterTimestamp
    ) {
        UserStatusesRequestModel userStatusesRequestModel = new UserStatusesRequestModel(status, changedAfterTimestamp);

        List<UserStatusesModel> userStatusesModels = getUserStatusesModelsService.get(userStatusesRequestModel);

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
