package com.universitypractice.springapplication.services.logservices;

import com.universitypractice.springapplication.entities.logentities.ChangeStatusRecord;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.repositories.logrepositories.ChangeStatusLogRepository;
import com.universitypractice.springapplication.services.interfaces.logging.ChangeStatusLogService;
import com.universitypractice.springapplication.services.interfaces.operations.StatusEntityGettingService;
import com.universitypractice.springapplication.services.interfaces.operations.UserEntityGettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DefaultChangeStatusLogService implements ChangeStatusLogService {

    private final ChangeStatusLogRepository changeStatusLogRepository;

    private final StatusEntityGettingService statusService;
    private final UserEntityGettingService userEntityGettingService;

    @Autowired
    public DefaultChangeStatusLogService(ChangeStatusLogRepository changeStatusLogRepository,
                                         StatusEntityGettingService statusService, UserEntityGettingService userEntityGettingService) {
        this.changeStatusLogRepository = changeStatusLogRepository;
        this.statusService = statusService;
        this.userEntityGettingService = userEntityGettingService;
    }


    @Override
    public ChangeStatusModel add(ChangeStatusModel changeStatusModel) {
        String oldStatus = changeStatusModel.getOldStatus();
        String newStatus = changeStatusModel.getNewStatus();

        if (!newStatus.equals(oldStatus)) {
            ChangeStatusRecord changeStatusRecord = new ChangeStatusRecord(
                    userEntityGettingService.get(changeStatusModel.getUserId()),
                    statusService.getByString(oldStatus),
                    statusService.getByString(newStatus),
                    changeStatusModel.getStatusChangedTime()
            );

            changeStatusRecord = changeStatusLogRepository.save(changeStatusRecord);

            changeStatusModel.setId(changeStatusRecord.getId());
        }

        return changeStatusModel;
    }

    @Override
    public ChangeStatusModel get(Long id) {
        ChangeStatusRecord changeStatusRecord = changeStatusLogRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Change status record with id \"" + id + "\" was not found")
        );

        ChangeStatusModel changeStatusModel = new ChangeStatusModel(
                changeStatusRecord.getId(),
                changeStatusRecord.getUserEntity().getId(),
                changeStatusRecord.getOldStatusEntity().getStatus().name().toLowerCase(),
                changeStatusRecord.getNewStatusEntity().getStatus().name().toLowerCase(),
                changeStatusRecord.getStatusChangedTime()
        );

        return changeStatusModel;
    }
}
