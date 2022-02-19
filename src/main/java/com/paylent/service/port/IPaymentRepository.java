package com.paylent.service.port;

import com.paylent.service.domain.Payment;
import io.micronaut.core.annotation.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IPaymentRepository {
    @NonNull
    Flux<Payment> getPayments();

    Mono<Payment> createPayment(@NonNull @NotNull @Valid Payment payment);

}
