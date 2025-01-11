//package com.tinyurl_system_design.tinyurl.services;
//
//import com.tinyurl_system_design.tinyurl.models.User;
//import com.tinyurl_system_design.tinyurl.repositories.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.util.Assert;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//public class UserServiceImplTests {
//    @Autowired
//    private UserServiceImpl userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    public void whenUserId_thenReturnExistingUser() {
//        User existingUser = new User("test-123", "Test Name", "test@mail.com", LocalDateTime.now());
//        Mockito.when(userRepository.findById("test-123")).thenReturn(Optional.of(existingUser));
//        User foundExistingUser = this.userService.getUser("test-123");
//        Assert.notNull(foundExistingUser, "found user not null");
//        Assert.isTrue(existingUser.getId() == foundExistingUser.getId(), "assert ids are equal");
//        Assert.isTrue(existingUser.getName() == foundExistingUser.getName(), "assert names are equal");
//        Assert.isTrue(existingUser.getEmail() == foundExistingUser.getEmail(), "assert emails are equal");
//    }
//
//    @Test
//    public void whenUserIdNotFound_thenReturnNull() {
//        Mockito.when(userRepository.findById("test-123")).thenReturn(Optional.ofNullable(null));
//        User foundExistingUser = this.userService.getUser("test-123");
//        Assert.isNull(foundExistingUser, "user does not exist, return null");
//    }
//
//    @Test
//    public void whenCreateUserExists_thenThrowIllegalArgumentException() {
//        User existingUser = new User("test-123", "Test Name", "test@mail.com", LocalDateTime.now());
//        Mockito.when(userRepository.findUserByNameMatchesAndEmailMatches(existingUser.getName(), existingUser.getEmail())).thenReturn(existingUser);
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser("Test Name", "test@mail.com"));
//        Assert.isTrue(exception.getMessage() == "User with name and email already exists", "assert exception message when name/email for user exists");
//    }
//
//    @Test
//    public void whenCreateUser_thenReturnCreatedUser() {
//        User testNewUser = new User("test-123", "Test Name", "test@mail.com", LocalDateTime.now());
//        Mockito.when(userRepository.findUserByNameMatchesAndEmailMatches(testNewUser.getName(), testNewUser.getEmail())).thenReturn(null);
//        Mockito.when(userRepository.save(testNewUser)).thenReturn(testNewUser);
//    }
//}
