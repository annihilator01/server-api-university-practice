package com.universitypractice.springapplication.services.userservices;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.UserEntity;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.exceptions.ElementAlreadyExistsException;
import com.universitypractice.springapplication.exceptions.ElementNotFoundException;
import com.universitypractice.springapplication.exceptions.NoDataForRequiredParameterException;
import com.universitypractice.springapplication.models.UserModel;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.repositories.UserRepository;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetGenderEntityService;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetStatusEntityService;
import com.universitypractice.springapplication.services.interfaces.useroperations.AGUUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
        if (userModel.getUsername() == null) {
            throw new NoDataForRequiredParameterException("username is required parameter");
        }

        if (userModel.getFirstName() == null) {
            throw new NoDataForRequiredParameterException("first name is required parameter");
        }

        if (userRepository.existsByUsername(userModel.getUsername())) {
            throw new ElementAlreadyExistsException("user with username '" + userModel.getUsername() + "' already exists");
        } else if (userModel.getEmail() != null && userRepository.existsByEmail(userModel.getEmail())) {
            throw new ElementAlreadyExistsException("user with email '" + userModel.getEmail() + "' already exists");
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

        if (!oldStatusFromDB.equals(newStatus)) {
            StatusEntity newStatusEntity = statusService.getByString(newStatus);
            if (newStatusEntity == null) {
                throw new NoDataForRequiredParameterException("new status is required parameter");
            }

            userEntity.setStatusEntity(newStatusEntity);
            changeStatusModel.setStatusChangedTime(userEntity.getLastStatusChangedTime());

            userRepository.save(userEntity);
        }

        return changeStatusModel;
    }

    private UserEntity getUserFromDB(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new ElementNotFoundException("user with id '" + id + "' was not found")
        );

        return userEntity;
    }
}
