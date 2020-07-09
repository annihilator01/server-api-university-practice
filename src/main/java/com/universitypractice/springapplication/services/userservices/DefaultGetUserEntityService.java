package com.universitypractice.springapplication.services.userservices;

import com.universitypractice.springapplication.entities.UserEntity;
import com.universitypractice.springapplication.exceptions.ElementNotFoundException;
import com.universitypractice.springapplication.repositories.UserRepository;
import com.universitypractice.springapplication.services.interfaces.entityoperations.GetUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
public class DefaultGetUserEntityService implements GetUserEntityService {

    private final UserRepository userRepository;

    @Autowired
    public DefaultGetUserEntityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity get(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new ElementNotFoundException("user with id '" + id + "' was not found")
        );

        return userEntity;
    }
}
