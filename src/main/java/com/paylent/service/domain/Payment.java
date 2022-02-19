package com.paylent.service.domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Introspected
public class Payment {

    @NonNull
    @NotBlank
    private BigDecimal value;

    @NonNull
    @NotBlank
    private String description;

    private Date date;

    private User user;

    private List<User> usersToPay;

    /**
     * Empty constructor.
     */
    public Payment() {
        value = new BigDecimal(0);
        description = "";
    }

    /**
     * Default constructor.
     *
     * @param value       amount value.
     * @param description payment description.
     * @param date        payment date.
     * @param user        user who paid.
     * @param usersToPay  users who should pay.
     */
    public Payment(@NonNull BigDecimal value, @NonNull String description, Date date, User user, List<User> usersToPay) {
        this.value = value;
        this.description = description;
        this.date = date;
        this.user = user;
        this.usersToPay = usersToPay;
    }

    @NonNull
    public BigDecimal getValue() {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public void setValue(@NonNull BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsersToPay() {
        return usersToPay;
    }

    public void setUsersToPay(List<User> usersToPay) {
        this.usersToPay = usersToPay;
    }
}
