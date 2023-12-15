package com.ctrlaltelite.mycashrevamp.serivce;

import com.ctrlaltelite.mycashrevamp.bean.GenericResponse;
import com.ctrlaltelite.mycashrevamp.entity.User;
import com.ctrlaltelite.mycashrevamp.exceptions.GenericException;
import com.ctrlaltelite.mycashrevamp.exceptions.UserNotFoundException;
import com.ctrlaltelite.mycashrevamp.repository.UserRepository;
import com.ctrlaltelite.mycashrevamp.service.WalletService;
import com.ctrlaltelite.mycashrevamp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private WalletService walletService;
    @Test
    public void should_save_one_user() throws GenericException {

        final User userToSave = User.builder().username("Mary Jane").email("mary@jane.com").password("somepass").build();
        when(repository.save(any(User.class))).thenReturn(userToSave);
        when(walletService.createWallet(any(User.class))).thenReturn(null);


        final GenericResponse<User> actualUser = service.createUser("", "", "", false);



        assertThat(actualUser.getData()).usingRecursiveComparison().isEqualTo(userToSave);
        verify(repository, times(1)).save(any(User.class));
        verify(repository, times(1)).saveAndFlush(any(User.class));

    }

    @Test
    void should_find_and_return_one_user() throws UserNotFoundException {

        final User expectedUser = User.builder().username("Mary Jane").email("mary@jane.com").password("somepass").build();
        when(repository.findById(anyInt())).thenReturn(Optional.of(expectedUser));


        final User actual = service.getUser(getRandomInt());


        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedUser);
        verify(repository, times(2)).findById(anyInt());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_not_found_a_student_that_doesnt_exists() {

        when(repository.findById(anyInt())).thenReturn(Optional.empty());


        Assertions.assertThrows(UserNotFoundException.class, () -> service.getUser(getRandomInt()));
        verify(repository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(repository);
    }
    private String getRandomInt() {
        return String.valueOf(new Random().ints(1, 10).findFirst().getAsInt());
    }
}
