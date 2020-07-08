package com.universitypractice.springapplication.services.userservices;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.UserEntity;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.models.UserModel;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.repositories.UserRepository;
import com.universitypractice.springapplication.services.interfaces.useroperations.AGUUserService;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetGenderEntityService;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetStatusEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Primary
@Service
public class DefaultAGUUserService implements AGUUserService {

    private final UserRepository userRepository;

    private final GetStatusEntityService statusService;
    private final GetGenderEntityService genderService;

    @Autowired
    public DefaultAGUUserService(UserRepository userRepository,
                                 GetStatusEntityService statusService, GetGenderEntityService genderService) {
        this.userRepository = userRepository;
        this.statusService = statusService;
        this.genderService = genderService;
    }

    @Override
    public UserModel add(UserModel userModel) {
        if (userRepository.existsByUsername(userModel.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with username \"" + userModel.getUsername() + "\" already exists");
        } else if (userModel.getEmail() != null && userRepository.existsByEmail(userModel.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email \"" + userModel.getEmail() + "\" already exists");
        }

        StatusEntity offlineStatusEntity = statusService.get(Status.OFFLINE);
        GenderEntity genderEntity = genderService.getByString(userModel.getGender());

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

    @Override
    public UserModel get(Long id) {
        UserEntity userEntity = getUserFromDB(id);

        Optional<String> genderOptional = Optional.empty();
        if (userEntity.getGenderEntity() != null) {
            genderOptional = Optional.of(userEntity.getGenderEntity().getGender().toString());
        }

        UserModel userModel = new UserModel(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                genderOptional.orElse(null),
                userEntity.getAge(),
                userEntity.getEmail()
        );

        return userModel;
    }

    @Override
    public ChangeStatusModel update(Long id, ChangeStatusModel changeStatusModel) {
        UserEntity userEntity = getUserFromDB(id);

        String newStatus = changeStatusModel.getNewStatus();
        String oldStatusFromDB = userEntity.getStatusEntity().getStatus().toString();

        changeStatusModel.setOldStatus(oldStatusFromDB);

        if (!newStatus.equals(oldStatusFromDB)) {
            StatusEntity newStatusEntity = statusService.getByString(newStatus);
            userEntity.setStatusEntity(newStatusEntity);
            changeStatusModel.setStatusChangedTime(userEntity.getLastStatusChangedTime());

            userRepository.save(userEntity);
        }

        return changeStatusModel;
    }

    private UserEntity getUserFromDB(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id \"" + id + "\" was not found")
        );

        return userEntity;
    }
}
