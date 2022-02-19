package com.paylent;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.naming.Named;

@ConfigurationProperties("userdb")
public interface UserDbConfiguration extends Named {
    @NonNull
    String getCollection();
}
