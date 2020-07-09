package com.universitypractice.springapplication.services.logservices;

import com.universitypractice.springapplication.entities.logentities.ChangeStatusRecord;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.repositories.logrepositories.ChangeStatusLogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DefaultChangeStatusLogServiceTest {

    @Autowired
    private DefaultChangeStatusLogService changeStatusLogService;

    @MockBean
    private ChangeStatusLogRepository changeStatusLogRepository;

    @MockBean
    private ChangeStatusRecord changeStatusRecord;

    @BeforeEach
    void mockChangeStatusLogRepositoryMethods() {
        Mockito.when(changeStatusLogRepository.save(ArgumentMatchers.any(ChangeStatusRecord.class)))
                .thenReturn(changeStatusRecord);
    }

    @BeforeEach
    void mockChangeStatusRecordMethods() {
        Mockito.when(changeStatusRecord.getId()).thenReturn(1L);
    }

    @Test
    void whenAddingChaStatusRecord_PreviousUserStatus_ReturnChangeStatusModelWithoutRecordId() {
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(
                null,
                1L,
                "online",
                "online",
                null);

        Assertions.assertNull(changeStatusLogService.add(changeStatusModel).getId());
    }

    @Test
    void whenAddingChaStatusRecord_NewUserStatus_ReturnChangeStatusModelWithRecordId() {
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(
                null,
                1L,
                "offline",
                "online",
                1L);

        Assertions.assertNotNull(changeStatusLogService.add(changeStatusModel).getId());
    }
}