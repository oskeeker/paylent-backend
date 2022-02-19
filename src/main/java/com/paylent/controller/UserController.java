package com.paylent.controller;

import com.paylent.service.UserService;
import com.paylent.service.domain.User;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Post
    public Mono<HttpStatus> createUser(@Body @NonNull @NotNull @Valid User user) {
        return userService.createUser(user).map(added -> Boolean.TRUE.equals((added)) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @Get
    public Flux<User> getUsers() {
        return userService.getUsers();
    }

}
