package com.universitypractice.springapplication.services.logservices;

import com.universitypractice.springapplication.entities.logentities.ChangeStatusRecord;
import com.universitypractice.springapplication.exceptions.ElementNotFoundException;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.repositories.logrepositories.ChangeStatusLogRepository;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetStatusEntityService;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetUserEntityService;
import com.universitypractice.springapplication.services.interfaces.logoperations.ChangeStatusLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DefaultChangeStatusLogService implements ChangeStatusLogService {

    private final ChangeStatusLogRepository changeStatusLogRepository;

    private final GetStatusEntityService statusService;
    private final GetUserEntityService getUserEntityService;

    @Autowired
    public DefaultChangeStatusLogService(ChangeStatusLogRepository changeStatusLogRepository,
                                         GetStatusEntityService statusService, GetUserEntityService getUserEntityService) {
        this.changeStatusLogRepository = changeStatusLogRepository;
        this.statusService = statusService;
        this.getUserEntityService = getUserEntityService;
    }


    @Override
    public ChangeStatusModel add(ChangeStatusModel changeStatusModel) {
        String oldStatus = changeStatusModel.getOldStatus();
        String newStatus = changeStatusModel.getNewStatus();

        if (!Objects.equals(newStatus, oldStatus)) {
            ChangeStatusRecord changeStatusRecord = new ChangeStatusRecord(
                    getUserEntityService.get(changeStatusModel.getUserId()),
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
                () -> new ElementNotFoundException("change status record with id '" + id + "' was not found")
        );

        ChangeStatusModel changeStatusModel = new ChangeStatusModel(
                changeStatusRecord.getId(),
                changeStatusRecord.getUserEntity().getId(),
                changeStatusRecord.getOldStatusEntity().getStatus().toString(),
                changeStatusRecord.getNewStatusEntity().getStatus().toString(),
                changeStatusRecord.getStatusChangedTime()
        );

        return changeStatusModel;
    }
}
