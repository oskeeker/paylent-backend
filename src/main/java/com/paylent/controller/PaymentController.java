package com.paylent.controller;

import com.paylent.service.PaymentService;
import com.paylent.service.domain.Payment;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller("/payments")
public class PaymentController {


    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Post
    public Mono<HttpStatus> createPayment(@Body @NonNull @NotNull @Valid Payment payment) {
        return paymentService.createPayment(payment)
                .map(added -> added != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @Get
    public Publisher<Payment> getPayments() {
        return paymentService.getPayments();
    }

}
