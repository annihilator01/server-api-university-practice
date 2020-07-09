package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.enums.Gender;
import com.universitypractice.springapplication.exceptions.InvalidDataException;
import com.universitypractice.springapplication.repositories.GenderRepository;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetGenderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class DefaultGenderService implements GetGenderEntityService {

    private final GenderRepository genderRepository;

    @Autowired
    public DefaultGenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public GenderEntity get(Gender gender) {
        return (gender != null)
                ? genderRepository.getByGender(gender)
                : null;
    }

    @Override
    public GenderEntity getByString(String gender) {
        if (gender == null) {
            return null;
        }

        GenderEntity genderEntity;
        try {
            genderEntity = genderRepository.getByGender(Gender.valueOf(gender.toUpperCase()));
        } catch (IllegalArgumentException ignored) {
            throw new InvalidDataException("gender '" + gender + "' doesn't exist");
        }

        return genderEntity;
    }
}
