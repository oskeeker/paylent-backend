package com.paylent;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Paylent",
                version = "1",
                description = "Payment app",
                license = @License(name = "Apache 2.0", url = "https://foo.bar")
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
