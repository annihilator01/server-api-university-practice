package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.repositories.StatusRepository;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetStatusEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class DefaultStatusService implements GetStatusEntityService {

    private final StatusRepository statusRepository;

    @Autowired
    public DefaultStatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override

    public StatusEntity get(Status status) {
        return (status != null)
                ? statusRepository.getByStatus(status)
                : null;
    }

    @Override
    public StatusEntity getByString(String status) {
        return (status != null)
                ? statusRepository.getByStatus(Status.valueOf(status.toUpperCase()))
                : null;
    }
}
