package com.universitypractice.springapplication.services.userservices;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.UserEntity;
import com.universitypractice.springapplication.enums.Gender;
import com.universitypractice.springapplication.enums.Status;
import com.universitypractice.springapplication.exceptions.ElementAlreadyExistsException;
import com.universitypractice.springapplication.exceptions.ElementNotFoundException;
import com.universitypractice.springapplication.exceptions.InvalidDataException;
import com.universitypractice.springapplication.exceptions.NoDataForRequiredParameterException;
import com.universitypractice.springapplication.models.UserModel;
import com.universitypractice.springapplication.models.logmodels.ChangeStatusModel;
import com.universitypractice.springapplication.repositories.GenderRepository;
import com.universitypractice.springapplication.repositories.StatusRepository;
import com.universitypractice.springapplication.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class DefaultAGUUserServiceTest {

    @Autowired
    private DefaultAGUUserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private StatusRepository statusRepository;

    @MockBean
    private GenderRepository genderRepository;

    @MockBean
    private UserEntity userEntity;

    @MockBean
    private GenderEntity genderEntity;

    @MockBean
    private StatusEntity statusEntity;

    @BeforeEach
    void mockUserRepositoryMethods() {
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(ArgumentMatchers.any(UserEntity.class))).thenReturn(userEntity);
    }

    @BeforeEach
    void mockGenderRepositoryMethods() {
        Mockito.when(genderRepository.getByGender(ArgumentMatchers.any(Gender.class))).thenReturn(genderEntity);
    }

    @BeforeEach
    void mockStatusRepositoryMethods() {
        Mockito.when(statusRepository.getByStatus(ArgumentMatchers.any(Status.class))).thenReturn(statusEntity);
    }

    @BeforeEach
    void mockUserEntityMethods() {
        Mockito.when(userEntity.getId()).thenReturn(1L);
    }

    @BeforeEach
    void mockStatusEntityMethods() {
        Mockito.when(statusEntity.getStatus()).thenReturn(Status.OFFLINE);
    }

    @Test
    void whenAddingUser_UserWithoutUsername_ThrowNoDataForRequiredParameterException() {
        UserModel userModel = new UserModel(null, "John", null, null, null, null);

        Assertions.assertThrows(NoDataForRequiredParameterException.class, () -> userService.add(userModel));
    }

    @Test
    void whenAddingUser_UserWithoutFirstName_ThrowNoDataForRequiredParameterException() {
        UserModel userModel = new UserModel(
                "john_username",
                null,
                null,
                null,
                null,
                null
        );

        Assertions.assertThrows(NoDataForRequiredParameterException.class, () -> userService.add(userModel));
    }

    @Test
    void whenAddingUser_NonExistentGender_ThrowInvalidDataException() {
        UserModel userModel = new UserModel(
                "john_username",
                "John",
                null,
                "fake",
                null,
                null
        );

        Assertions.assertThrows(InvalidDataException.class, () -> userService.add(userModel));
    }

    @Test
    void whenAddingUser_UserWithExistingUsername_ThrowElementAlreadyExistsException() {
        UserModel userModel = new UserModel(
                "john_username",
                "John",
                "Bond",
                "male",
                26,
                "john_bond@gmail.com"
        );

        Mockito.when(userRepository.existsByUsername(userModel.getUsername())).thenReturn(true);

        Assertions.assertThrows(ElementAlreadyExistsException.class, () -> userService.add(userModel));
    }

    @Test
    void whenAddingUser_UserWithExistingEmail_ThrowElementAlreadyExistsException() {
        UserModel userModel = new UserModel(
                "john_username",
                "John",
                "Bond",
                "male",
                26,
                "john_bond@gmail.com"
        );

        Mockito.when(userRepository.existsByEmail(userModel.getEmail())).thenReturn(true);

        Assertions.assertThrows(ElementAlreadyExistsException.class, () -> userService.add(userModel));
    }

    @Test
    void whenGettingUser_NonExistentId_ThrowElementNotFoundException() {
        Mockito.when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Long id = 1L;

        Assertions.assertThrows(ElementNotFoundException.class, () -> userService.get(id));
    }

    @Test
    void whenUpdatingUser_NoStatusProvided_ThrowNoDataForRequiredParameterException() {
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(1L, null);

        Mockito.when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(userEntity));
        Mockito.when(userEntity.getStatusEntity()).thenReturn(statusEntity);

        Assertions.assertThrows(NoDataForRequiredParameterException.class, () -> userService.update(1L, changeStatusModel));
    }

    @Test
    void whenUpdatingUser_NonExistentStatus_ThrowInvalidDataException() {
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(1L, "fake");

        Mockito.when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(userEntity));
        Mockito.when(userEntity.getStatusEntity()).thenReturn(statusEntity);

        Assertions.assertThrows(InvalidDataException.class, () -> userService.update(1L, changeStatusModel));
    }

    @Test
    void whenUpdatingUser_PreviousStatus_ReturnChangeStatusModelWithoutStatusChangedTime() {
        ChangeStatusModel changeStatusModel = new ChangeStatusModel(1L, "offline");

        Mockito.when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(userEntity));
        Mockito.when(userEntity.getStatusEntity()).thenReturn(statusEntity);

        Assertions.assertNull(userService.update(1L, changeStatusModel).getStatusChangedTime());
    }
}