package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.enums.Gender;
import com.universitypractice.springapplication.exceptions.DBDataLossException;
import com.universitypractice.springapplication.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenderService {

    private final GenderRepository genderRepository;

    @Autowired
    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public GenderEntity getGenderEntityByGender(Gender gender) {
        Optional<GenderEntity> genderEntityOptional = genderRepository.findByGender(gender);

        if (genderEntityOptional.isPresent()) {
            return genderEntityOptional.get();
        } else {
            throw new DBDataLossException("Existing gender " + gender.name() + " is not stored in database");
        }
    }
}
