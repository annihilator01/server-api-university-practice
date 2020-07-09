package com.universitypractice.springapplication.components.initializers;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.enums.Gender;
import com.universitypractice.springapplication.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GenderDataInitializerComponent implements ApplicationRunner {

    private final GenderRepository genderRepository;

    @Autowired
    public GenderDataInitializerComponent(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (genderRepository.count() == 0) {
            for (Gender gender: Gender.values()) {
                genderRepository.save(new GenderEntity(gender));
            }
        }
    }
}
