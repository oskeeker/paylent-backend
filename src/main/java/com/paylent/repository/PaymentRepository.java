package com.paylent.repository;

import com.mongodb.client.model.Sorts;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.paylent.PaymentDbConfiguration;
import com.paylent.service.domain.Payment;
import com.paylent.service.port.IPaymentRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
public class PaymentRepository implements IPaymentRepository {

    private static final String DATE = "date";

    private final PaymentDbConfiguration mongoConf;
    private final MongoClient mongoClient;

    public PaymentRepository(PaymentDbConfiguration mongoConf,
                             MongoClient mongoClient) {
        this.mongoConf = mongoConf;
        this.mongoClient = mongoClient;
    }

    @NonNull
    @Override
    public Flux<Payment> getPayments() {
        return Flux.from(getCollection().find().sort(Sorts.descending(DATE)));
    }

    @Override
    public Mono<Payment> createPayment(@NonNull final Payment payment) {
        return Mono.from(getCollection().insertOne(payment))
                .map(insertOneResult -> payment)
                .onErrorReturn(payment);
    }

    @NonNull
    private MongoCollection<Payment> getCollection() {
        return mongoClient.getDatabase(mongoConf.getName())
                .getCollection(mongoConf.getCollection(), Payment.class);
    }
}
