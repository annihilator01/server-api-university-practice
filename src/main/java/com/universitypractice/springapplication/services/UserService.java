package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.UserEntity;
import com.universitypractice.springapplication.enums.Gender;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.models.UserModel;
import com.universitypractice.springapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final StatusService statusService;
    private final GenderService genderService;

    @Autowired
    public UserService(UserRepository userRepository, StatusService statusService, GenderService genderService) {
        this.userRepository = userRepository;
        this.statusService = statusService;
        this.genderService = genderService;
    }

    public UserModel addUser(UserModel userModel) {
        StatusEntity offlineStatusEntity = statusService.getStatusEntityByStatus(Status.OFFLINE);

        GenderEntity genderEntity = null;
        if (userModel.getGender() != null) {
            genderEntity = genderService.getGenderEntityByGender(Gender.valueOf(userModel.getGender().toUpperCase()));
        }

        UserEntity userEntity = new UserEntity(
                offlineStatusEntity,
                userModel.getUsername(),
                userModel.getFirstName(),
                userModel.getLastName(),
                genderEntity,
                userModel.getAge(),
                userModel.getEmail()
        );

        userEntity = userRepository.save(userEntity);
        userModel.setId(userEntity.getId());

        return userModel;
    }

    public UserModel getUser(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " was not found")
        );

        Optional<String> genderOptional = Optional.of(userEntity.getGenderEntity().getGender().name().toLowerCase());

        UserModel userModel = new UserModel(
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                genderOptional.get(),
                userEntity.getAge(),
                userEntity.getEmail()
        );

        return userModel;
    }
}
