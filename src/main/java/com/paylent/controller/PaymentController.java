package com.paylent.controller;

import com.paylent.service.PaymentService;
import com.paylent.service.domain.Payment;
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
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller("/payments")
@Tag(name = "Payments")
public class PaymentController {


    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Post
    @Operation(summary = "Creates a new payment.",
            description = "Creates a new payment for different friends."
    )
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = "HttpStatus"))
    )
    @ApiResponse(responseCode = "201", description = "Payment created.")
    @ApiResponse(responseCode = "409", description = "Conflict due to payment creation.")
    public Mono<HttpStatus> createPayment(@Parameter(required = true, description = "Payment parameter.")
                                          @Body @NonNull @NotNull @Valid Payment payment) {
        return paymentService.createPayment(payment)
                .map(added -> added != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @Get
    @Operation(summary = "Get Payments.",
            description = "Retrieves the payments done in the app."
    )
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = "Payment"))
    )
    @ApiResponse(responseCode = "200", description = "System payments")
    public Publisher<Payment> getPayments() {
        return paymentService.getPayments();
    }

}
