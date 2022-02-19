package com.paylent.controller;

import com.paylent.service.domain.User;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Client("/users")
public interface UserClient {

    @Post
    void createUser(@Body @NonNull @NotNull @Valid User user);

    @Get
    List<User> getUsers();

}
