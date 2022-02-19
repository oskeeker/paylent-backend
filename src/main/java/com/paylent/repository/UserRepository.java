package com.paylent.repository;

import com.mongodb.client.model.Sorts;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.paylent.UserDbConfiguration;
import com.paylent.service.domain.User;
import com.paylent.service.port.IUserRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.bson.conversions.Bson;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

@Singleton
public class UserRepository implements IUserRepository {

    private static final String FIRSTNAME = "firstName";
    private static final String BALANCE = "balance";

    private final UserDbConfiguration mongoConf;
    private final MongoClient mongoClient;

    public UserRepository(UserDbConfiguration mongoConf,
                          MongoClient mongoClient) {
        this.mongoConf = mongoConf;
        this.mongoClient = mongoClient;
    }

    @NonNull
    @Override
    public Flux<User> getUsers() {
        return Flux.from(getCollection().find().sort(Sorts.descending(BALANCE)));
    }

    @Override
    public Mono<Boolean> createUser(@NonNull final User user) {
        return Mono.from(getCollection().insertOne(user))
                .map(insertOneResult -> Boolean.TRUE)
                .onErrorReturn(Boolean.FALSE);
    }

    @Override
    public void updateUser(@NonNull User user, BigDecimal balance) {
        Bson filter = eq(FIRSTNAME, user.getFirstName());
        Bson updateOperation = inc(BALANCE, balance);
        Mono.from(getCollection().updateOne(filter, updateOperation)).subscribe();
    }

    @NonNull
    private MongoCollection<User> getCollection() {
        return mongoClient.getDatabase(mongoConf.getName())
                .getCollection(mongoConf.getCollection(), User.class);
    }
}
