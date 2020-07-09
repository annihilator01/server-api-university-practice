package com.universitypractice.springapplication.services.logservices;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.logentities.UserStatusesRequestRecord;
import com.universitypractice.springapplication.exceptions.ElementNotFoundException;
import com.universitypractice.springapplication.models.logmodels.UserStatusesRequestModel;
import com.universitypractice.springapplication.repositories.logrepositories.UserStatusesRequestLogRepository;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetStatusEntityService;
import com.universitypractice.springapplication.services.interfaces.logoperations.UserStatusesRequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class DefaultUserStatusesRequestLogService implements UserStatusesRequestLogService {

    private final UserStatusesRequestLogRepository userStatusesRequestLogRepository;

    private final GetStatusEntityService statusService;

    @Autowired
    public DefaultUserStatusesRequestLogService(UserStatusesRequestLogRepository userStatusesRequestLogRepository,
                                                GetStatusEntityService statusService) {
        this.userStatusesRequestLogRepository = userStatusesRequestLogRepository;
        this.statusService = statusService;
    }

    @Override
    public UserStatusesRequestModel add(UserStatusesRequestModel userStatusesRequestModel) {
        UserStatusesRequestRecord userStatusesRequestRecord = new UserStatusesRequestRecord(
                statusService.getByString(userStatusesRequestModel.getStatusFilter()),
                userStatusesRequestModel.getChangedAfterTimestamp()
        );

        userStatusesRequestRecord = userStatusesRequestLogRepository.save(userStatusesRequestRecord);

        userStatusesRequestModel.setResponseParams(
                userStatusesRequestRecord.getId(),
                userStatusesRequestRecord.getRequestTimestamp()
        );

        return userStatusesRequestModel;
    }

    @Override
    public UserStatusesRequestModel get(Long id) {
        UserStatusesRequestRecord userStatusesRequestRecord = userStatusesRequestLogRepository.findById(id).orElseThrow(
                () -> new ElementNotFoundException("user statuses request record with id '" + id + "' was not found")
        );

        StatusEntity statusEntity = userStatusesRequestRecord.getStatusEntityFilter();

        UserStatusesRequestModel userStatusesRequestModel = new UserStatusesRequestModel(
                userStatusesRequestRecord.getId(),
                userStatusesRequestRecord.getRequestTimestamp(),

                (statusEntity != null)
                        ? statusEntity.getStatus().toString()
                        : null,

                userStatusesRequestRecord.getChangedAfterTimestamp()
        );

        return userStatusesRequestModel;
    }
}
