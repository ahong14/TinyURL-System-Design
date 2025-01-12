package com.tinyurl_system_design.tinyurl.services;

import com.tinyurl_system_design.tinyurl.models.TinyURLUser;
import com.tinyurl_system_design.tinyurl.repositories.TinyURLUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TinyURLUserServiceImplTests {
    @Autowired
    private UserServiceImpl TinyURLUserService;

    @MockBean
    private TinyURLUserRepository TinyURLUserRepository;

   private static UUID testUserId;

    @BeforeAll
    public static void setup() {
        testUserId = UUID.randomUUID();
    }

    @Test
    public void whenTinyURLUserId_thenReturnExistingTinyURLUser() {
        TinyURLUser existingTinyURLUser = new TinyURLUser(testUserId, "Test Name", "test@mail.com", LocalDateTime.now());
        Mockito.when(TinyURLUserRepository.findById(testUserId)).thenReturn(Optional.of(existingTinyURLUser));
        TinyURLUser foundExistingTinyURLUser = this.TinyURLUserService.getUser(testUserId.toString());
        Assert.notNull(foundExistingTinyURLUser, "found TinyURLUser not null");
        Assert.isTrue(existingTinyURLUser.getId() == foundExistingTinyURLUser.getId(), "assert ids are equal");
        Assert.isTrue(existingTinyURLUser.getName().equals(foundExistingTinyURLUser.getName()), "assert names are equal");
        Assert.isTrue(existingTinyURLUser.getEmail().equals(foundExistingTinyURLUser.getEmail()), "assert emails are equal");
    }

    @Test
    public void whenTinyURLUserIdNotFound_thenReturnNull() {
        Mockito.when(TinyURLUserRepository.findById(testUserId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> this.TinyURLUserService.getUser(testUserId.toString()));
        Assert.isTrue(exception.getMessage().equals("User not found"), "assert exception message when user not found");
    }

    @Test
    public void whenCreateTinyURLUserExists_thenThrowIllegalArgumentException() {
        TinyURLUser existingTinyURLUser = new TinyURLUser(testUserId, "Test Name", "test@mail.com", LocalDateTime.now());
        Mockito.when(TinyURLUserRepository.findByNameAndEmail(existingTinyURLUser.getName(), existingTinyURLUser.getEmail())).thenReturn(existingTinyURLUser);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TinyURLUserService.createUser("Test Name", "test@mail.com"));
        Assert.isTrue(exception.getMessage().equals("User with name and email already exists"), "assert exception message when name/email for TinyURLUser exists");
    }

    @Test
    public void whenCreateTinyURLUser_thenReturnCreatedTinyURLUser() {
        TinyURLUser testNewTinyURLUser = new TinyURLUser(testUserId, "Test Name", "test@mail.com", LocalDateTime.now());
        Mockito.when(TinyURLUserRepository.findByNameAndEmail(testNewTinyURLUser.getName(), testNewTinyURLUser.getEmail())).thenReturn(null);
        Mockito.when(TinyURLUserRepository.save(testNewTinyURLUser)).thenReturn(testNewTinyURLUser);
    }
}
