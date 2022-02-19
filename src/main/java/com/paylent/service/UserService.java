package com.paylent.service;

import com.paylent.repository.UserRepository;
import com.paylent.service.domain.User;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Singleton
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<Boolean> createUser(final User user) {
        return userRepository.createUser(user);
    }

    public Flux<User> getUsers() {
        return userRepository.getUsers();
    }

    public void updateUser(@NonNull User user, BigDecimal balance) {
        userRepository.updateUser(user, balance);
    }

}
