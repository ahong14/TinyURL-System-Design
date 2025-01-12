package com.tinyurl_system_design.tinyurl.services;

import com.tinyurl_system_design.tinyurl.models.TinyURLUser;
import com.tinyurl_system_design.tinyurl.repositories.TinyURLUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final TinyURLUserRepository userRepository;

    @Autowired
    UserServiceImpl(TinyURLUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * @param name - name of user
     * @param email - email of user
     * @return - created user
     * @throws IllegalArgumentException
     */
    @Override
    public TinyURLUser createUser(String name, String email) throws IllegalArgumentException {
        if (this.userRepository.findByNameAndEmail(name, email) != null) {
            throw new IllegalArgumentException("User with name and email already exists");
        }
        LocalDateTime userCreatedAt = LocalDateTime.now();

        return this.userRepository.save(new TinyURLUser(name, email, userCreatedAt));
    }

    /**
     * @param id - id of user
     * @return - found user
     * @throws NoSuchElementException
     */
    @Override
    public TinyURLUser getUser(String id) throws NoSuchElementException {
        Optional<TinyURLUser> foundUser = this.userRepository.findById(UUID.fromString(id));
        if (foundUser.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        return foundUser.get();
    }
}
