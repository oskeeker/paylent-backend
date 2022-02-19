package com.paylent.service;

import com.paylent.repository.PaymentRepository;
import com.paylent.service.domain.Payment;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Service that handles Payment business cases.
 */
@Singleton
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;

    public PaymentService(PaymentRepository paymentRepository, UserService userService) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
    }

    /**
     * Creates a new payment.
     *
     * @param payment payment info.
     * @return {@link Mono<HttpStatus>} returns HttpStatus code.
     */
    public Mono<HttpStatus> createPayment(Payment payment) {

        payment.setDate(new Date());


        // Amount that should pay every user
        BigDecimal totalToPay = BigDecimal.valueOf(payment.getValue().doubleValue() / (payment.getUsersToPay().size() + 1));

        // we should adjust the balance of the user that paid. For that we should subtract the amount of money that
        // every user should pay to the total amount of money that the user paid.
        userService.updateUser(payment.getUser(), payment.getValue().subtract(totalToPay));

        // Now the balance for other users should be updated.
        payment.getUsersToPay().forEach(user ->
                userService.updateUser(user, totalToPay.multiply(BigDecimal.valueOf(-1)) )
        );

        return paymentRepository.createPayment(payment)
                .map(added -> added != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    /**
     * Returns all the payments stored in the system.
     *
     * @return {@link Publisher<Payment>} payment list.
     */
    public Publisher<Payment> getPayments() {
        return paymentRepository.getPayments();
    }


}
