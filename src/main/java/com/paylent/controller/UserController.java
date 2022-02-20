package com.paylent.controller;

import com.paylent.service.UserService;
import com.paylent.service.domain.User;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller("/users")
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Post
    @Operation(summary = "Creates a user.",
            description = "Creates a new user in the app."
    )
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = "HttpStatus"))
    )
    @ApiResponse(responseCode = "201", description = "User created.")
    @ApiResponse(responseCode = "409", description = "Conflict due to user creation.")
    public Mono<HttpStatus> createUser(@Parameter(required = true, description = "User parameter.")
                                       @Body @NonNull @NotNull @Valid User user) {
        return userService.createUser(user).map(added -> Boolean.TRUE.equals((added)) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @Get
    @Operation(summary = "Get Users.",
            description = "Retrieves system Users."
    )
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = "User"))
    )
    @ApiResponse(responseCode = "201", description = "User created.")
    @ApiResponse(responseCode = "409", description = "Conflict due to user creation.")
    public Flux<User> getUsers() {
        return userService.getUsers();
    }

}
