package com.paylent.service.domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Introspected
public class User {

    @NonNull
    @NotBlank
    private String firstName;
    @NonNull
    @NotBlank
    private String lastName;
    @NotBlank
    private BigDecimal balance;


    /**
     * Empty constructor
     */
    public User() {
        firstName = "";
        lastName = "";
        balance = new BigDecimal("0");
    }

    /**
     * Default Constructor
     *
     * @param firstName User firstname.
     * @param lastName  User lastname.
     * @param balance   User monetary balance.
     */
    public User(@NonNull String firstName, @NonNull String lastName, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.HALF_UP);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }
}
