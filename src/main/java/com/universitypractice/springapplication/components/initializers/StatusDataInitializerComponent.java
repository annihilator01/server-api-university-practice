package com.universitypractice.springapplication.components.initializers;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StatusDataInitializerComponent implements ApplicationRunner {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusDataInitializerComponent(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (statusRepository.count() == 0) {
            for (Status status: Status.values()) {
                statusRepository.save(new StatusEntity(status));
            }
        }
    }
}
