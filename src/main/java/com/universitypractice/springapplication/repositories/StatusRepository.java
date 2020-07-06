package com.universitypractice.springapplication.repositories;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
    StatusEntity getByStatus(Status status);
}
