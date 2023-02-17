package com.nn.accountservice.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal balance;

    public void validate() {
        boolean result = Stream.of(this.name, this.surname, this.balance)
                .anyMatch(Objects::isNull);

        if (result) {
            throw new RuntimeException("Required field for create account not present");
        }
    }

}
