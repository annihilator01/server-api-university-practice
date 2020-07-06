package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.exceptions.DBDataLossException;
import com.universitypractice.springapplication.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public StatusEntity getStatusEntityByStatus(Status status) {
        Optional<StatusEntity> statusEntityOptional = statusRepository.findByStatus(status);

        if (statusEntityOptional.isPresent()) {
            return statusEntityOptional.get();
        } else {
            throw new DBDataLossException("Existing status " + status.name() + " is not stored in database");
        }
    }
}
