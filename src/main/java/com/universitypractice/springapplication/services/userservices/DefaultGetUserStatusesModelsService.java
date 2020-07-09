package com.universitypractice.springapplication.services.userservices;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.UserEntity;
import com.universitypractice.springapplication.models.UserStatusesModel;
import com.universitypractice.springapplication.models.logmodels.UserStatusesRequestModel;
import com.universitypractice.springapplication.repositories.UserRepository;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetStatusEntityService;
import com.universitypractice.springapplication.services.interfaces.useroperations.GetUserStatusesModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class DefaultGetUserStatusesModelsService implements GetUserStatusesModelsService {

    private final UserRepository userRepository;

    private final GetStatusEntityService statusService;

    @Autowired
    public DefaultGetUserStatusesModelsService(UserRepository userRepository, GetStatusEntityService statusService) {
        this.userRepository = userRepository;
        this.statusService = statusService;
    }


    @Override
    public List<UserStatusesModel> get(UserStatusesRequestModel userStatusesRequestModel) {
        String statusFilter = userStatusesRequestModel.getStatusFilter();
        Long changedAfterTimestamp = userStatusesRequestModel.getChangedAfterTimestamp();

        StatusEntity statusEntity = statusService.getByString(statusFilter);

        Optional<List<UserEntity>> userEntitiesOptional = Optional.empty();

        if (statusEntity == null && changedAfterTimestamp == null) {
            userEntitiesOptional = Optional.of(userRepository.findAll());
        }

        if (statusEntity == null && changedAfterTimestamp != null) {
            userEntitiesOptional = userRepository.findByLastStatusChangedTimeGreaterThanEqual(changedAfterTimestamp);
        }

        if (statusEntity != null && changedAfterTimestamp == null) {
            userEntitiesOptional = userRepository.findByStatusEntity(statusEntity);
        }

        if (statusEntity != null && changedAfterTimestamp != null) {
            userEntitiesOptional = userRepository.findByStatusEntityAndLastStatusChangedTimeGreaterThanEqual(
                    statusEntity,
                    changedAfterTimestamp
            );
        }

        List<UserEntity> userEntities = userEntitiesOptional.orElse(new ArrayList<>());
        List<UserStatusesModel> resultUserStatusesModels = new ArrayList<>();

        for (UserEntity userEntity: userEntities) {
            UserStatusesModel userStatusesModel = new UserStatusesModel(
                    userEntity.getId(),
                    userEntity.getUsername(),
                    userEntity.getFirstName(),
                    userEntity.getStatusEntity().getStatus().toString(),
                    userEntity.getLastStatusChangedTime()
            );

            resultUserStatusesModels.add(userStatusesModel);
        }

        return resultUserStatusesModels;
    }
}
