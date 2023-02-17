package com.nn.accountservice.controller;

import com.nn.accountservice.entity.CurrencyEnum;
import com.nn.accountservice.service.AccountService;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
@Validated
public class ExchangeController {

    private final AccountService accountService;

    @GetMapping
    public void exchange(
            @RequestParam @NotNull final CurrencyEnum from, @NotNull @RequestParam final CurrencyEnum to,
            @RequestParam @NotBlank final String accountId,
            @RequestParam @NotNull @DecimalMin(value = "0.0", inclusive = false) final BigDecimal amount) {
        log.info("Processing exchange {} to {}, accountId: {}", from, to, accountId);
        this.accountService.exchange(accountId, from, to, amount);
    }

}
