package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public StatusEntity getStatusEntityByStatus(Status status) {
        return statusRepository.getByStatus(status);
    }
}
