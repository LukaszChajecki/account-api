package com.nn.accountservice.controller;

import com.nn.accountservice.entity.CurrencyEnum;
import com.nn.accountservice.service.ExchangeService;
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

	private final ExchangeService exchangeService;

	@GetMapping
	public BigDecimal exchange(
			@RequestParam @NotNull final CurrencyEnum from, @NotNull @RequestParam final CurrencyEnum to,
			@RequestParam @NotNull final BigDecimal amount) {
		log.info("Processing exchange {} to {}", from, to);
		return this.exchangeService.exchange(from, to, amount);
	}

}
