package com.paylent.controller;

import com.paylent.service.domain.Payment;
import com.paylent.service.domain.User;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@MicronautTest
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BusinessCasesTest implements TestPropertyProvider {

    @Test
    void createUser(UserClient userClient, PaymentClient paymentClient) {

        // GIVEN
        User francisco = new User("Francisco", "Buyo", new BigDecimal(0));
        User alfonso = new User("Alfonso", "Pérez", new BigDecimal(0));
        User raul = new User("Raúl", "González", new BigDecimal(0));
        User joseMaria = new User("José María", "Gutiérrez", new BigDecimal(0));
        List<User> users = Arrays.asList(francisco, alfonso, raul, joseMaria);

        Payment firstPayment = new Payment(BigDecimal.valueOf(100.0),
                "Cena",
                new Date(), francisco,
                Arrays.asList(raul, alfonso, joseMaria));
        Payment secondPayment = new Payment(BigDecimal.valueOf(10.0),
                "Taxi",
                new Date(), alfonso,
                Arrays.asList(francisco, raul, joseMaria));
        Payment thirdPayment = new Payment(BigDecimal.valueOf(53.40),
                "Compra",
                new Date(), alfonso,
                Arrays.asList(francisco, raul, joseMaria));
        List<Payment> payments = Arrays.asList(firstPayment, secondPayment, thirdPayment);


        // WHEN
        users.forEach(userClient::createUser);
        payments.forEach(paymentClient::createPayment);

        // THEN
        List<Payment> paymentsResponse = paymentClient.getPayments();
        assertFalse(paymentsResponse.isEmpty());
        assertEquals(3, paymentsResponse.size());
        assertEquals(paymentsResponse.get(0).getDescription(), thirdPayment.getDescription());
        assertEquals(paymentsResponse.get(0).getValue(), thirdPayment.getValue());
        assertEquals(paymentsResponse.get(0).getUser().getFirstName(), alfonso.getFirstName());
        assertEquals(paymentsResponse.get(1).getDescription(), secondPayment.getDescription());
        assertEquals(paymentsResponse.get(1).getValue(), secondPayment.getValue());
        assertEquals(paymentsResponse.get(1).getUser().getFirstName(), alfonso.getFirstName());
        assertEquals(paymentsResponse.get(2).getDescription(), firstPayment.getDescription());
        assertEquals(paymentsResponse.get(2).getValue(), firstPayment.getValue());
        assertEquals(paymentsResponse.get(2).getUser().getFirstName(), francisco.getFirstName());

        List<User> usersResponse = userClient.getUsers();
        assertFalse(usersResponse.isEmpty());
        assertEquals(4, usersResponse.size());
        assertEquals(usersResponse.get(0).getFirstName(), francisco.getFirstName());
        assertEquals(BigDecimal.valueOf(59.15).setScale(2, RoundingMode.HALF_UP), usersResponse.get(0).getBalance());
        assertEquals(usersResponse.get(1).getFirstName(), alfonso.getFirstName());
        assertEquals(BigDecimal.valueOf(22.55).setScale(2, RoundingMode.HALF_UP), usersResponse.get(1).getBalance());
        assertEquals(usersResponse.get(2).getFirstName(), raul.getFirstName());
        assertEquals(BigDecimal.valueOf(-40.85).setScale(2, RoundingMode.HALF_UP), usersResponse.get(2).getBalance());
        assertEquals(usersResponse.get(3).getFirstName(), joseMaria.getFirstName());
        assertEquals(BigDecimal.valueOf(-40.85).setScale(2, RoundingMode.HALF_UP), usersResponse.get(3).getBalance());

    }

    @Override
    public Map<String, String> get() {
        return TestPropertyProvider.super.get();
    }

    @NonNull
    @Override
    public Map<String, String> getProperties() {
        MongoTestsUtils.startMongoDb();
        return Collections.singletonMap("mongodb.uri", MongoTestsUtils.getMongoDbUri());
    }

    @AfterAll
    static void cleanup() {
        MongoTestsUtils.closeMongoDb();
    }
}