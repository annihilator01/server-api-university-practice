package com.universitypractice.springapplication.repositories.logrepositories;

import com.universitypractice.springapplication.entities.logentities.ChangeStatusRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChangeStatusLogRepository extends JpaRepository<ChangeStatusRecord, Long> {
    Optional<ChangeStatusRecord> findById(Long id);
}
