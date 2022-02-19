package com.paylent;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.naming.Named;

@ConfigurationProperties("paymentdb")
public interface PaymentDbConfiguration extends Named {
    @NonNull
    String getCollection();
}
