package com.universitypractice.springapplication.services;

import com.universitypractice.springapplication.entities.UserEntity;
import com.universitypractice.springapplication.repositories.UserRepository;
import com.universitypractice.springapplication.services.interfaces.operations.UserEntityGettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Primary
@Service
public class DefaultUserEntityGettingService implements UserEntityGettingService {

    private final UserRepository userRepository;

    @Autowired
    public DefaultUserEntityGettingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity get(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id \"" + id + "\" was not found")
        );

        return userEntity;
    }
}
