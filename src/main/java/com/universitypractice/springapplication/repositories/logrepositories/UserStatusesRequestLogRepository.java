package com.universitypractice.springapplication.repositories.logrepositories;

import com.universitypractice.springapplication.entities.logentities.UserStatusesRequestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusesRequestLogRepository extends JpaRepository<UserStatusesRequestRecord, Long> {
    Optional<UserStatusesRequestRecord> findById(Long id);
}
