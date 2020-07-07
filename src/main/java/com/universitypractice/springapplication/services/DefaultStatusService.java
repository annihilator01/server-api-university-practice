package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.repositories.StatusRepository;
import com.universitypractice.springapplication.services.interfaces.operations.StatusEntityGettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultStatusService implements StatusEntityGettingService {

    private final StatusRepository statusRepository;

    @Autowired
    public DefaultStatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override

    public StatusEntity get(Status status) {
        return statusRepository.getByStatus(status);
    }

    @Override

    public StatusEntity getByString(String status) {
        return statusRepository.getByStatus(Status.valueOf(status.toUpperCase()));
    }
}
