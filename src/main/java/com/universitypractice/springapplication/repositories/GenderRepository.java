package com.universitypractice.springapplication.repositories;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Integer> {
    GenderEntity getByGender(Gender gender);
}
