package com.universitypractice.springapplication.repositories;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<List<UserEntity>> findByStatusEntity(StatusEntity statusEntity);

    Optional<List<UserEntity>> findByLastStatusChangedTimeGreaterThanEqual(Long timeStampFilter);

    Optional<List<UserEntity>> findByStatusEntityAndLastStatusChangedTimeGreaterThanEqual(
            StatusEntity statusEntity,
            Long timestampFilter
    );

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
