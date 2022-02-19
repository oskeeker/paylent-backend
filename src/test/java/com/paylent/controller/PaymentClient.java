package com.paylent.controller;

import com.paylent.service.domain.Payment;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Client("/payments")
public interface PaymentClient {

    @Post
    void createPayment(@Body @NonNull @NotNull @Valid Payment payment);

    @Get
    List<Payment> getPayments();

}
