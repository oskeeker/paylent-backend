package com.paylent.service.port;

import com.paylent.service.domain.User;
import io.micronaut.core.annotation.NonNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface IUserRepository {
    @NonNull
    Publisher<User> getUsers();

    Mono<Boolean> createUser(@NonNull @NotNull @Valid User user);

    void updateUser(@NonNull @NotNull @Valid User user, BigDecimal balance);

}
