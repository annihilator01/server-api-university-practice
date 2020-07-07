package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.enums.Gender;
import com.universitypractice.springapplication.repositories.GenderRepository;
import com.universitypractice.springapplication.services.interfaces.operations.GenderEntityGettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGenderService implements GenderEntityGettingService {

    private final GenderRepository genderRepository;

    @Autowired
    public DefaultGenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public GenderEntity get(Gender gender) {
        return genderRepository.getByGender(gender);
    }

    @Override
    public GenderEntity getByString(String gender) {
        return genderRepository.getByGender(Gender.valueOf(gender.toUpperCase()));
    }
}
