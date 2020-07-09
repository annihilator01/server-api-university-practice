package com.universitypractice.springapplication.services.userservices;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.exceptions.InvalidDataException;
import com.universitypractice.springapplication.models.logmodels.UserStatusesRequestModel;
import com.universitypractice.springapplication.repositories.StatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DefaultGetUserStatusesModelsServiceTest {

    @Autowired
    private DefaultGetUserStatusesModelsService userStatusesModelsService;

    @MockBean
    private StatusRepository statusRepository;

    @MockBean
    private StatusEntity statusEntity;

    @BeforeEach
    void mockStatusRepositoryMethods() {
        Mockito.when(statusRepository.getByStatus(ArgumentMatchers.any(Status.class))).thenReturn(statusEntity);
    }

    @BeforeEach
    void mockStatusEntityMethods() {
        Mockito.when(statusEntity.getStatus()).thenReturn(Status.OFFLINE);
    }


    @Test
    void whenGettingListOfUserStatusesModels_NonExistentStatus_ThrowInvalidDataException() {
        UserStatusesRequestModel userStatusesRequestModel = new UserStatusesRequestModel(
                "fake",
                null
        );

        Assertions.assertThrows(InvalidDataException.class, () -> userStatusesModelsService.get(userStatusesRequestModel));
    }

}