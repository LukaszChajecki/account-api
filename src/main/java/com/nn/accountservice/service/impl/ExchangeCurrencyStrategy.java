package com.nn.accountservice.service.impl;

import com.nn.accountservice.entity.CurrencyEnum;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@RequiredArgsConstructor
enum ExchangeCurrencyStrategy {

	PLN_USD(ExchangeServiceImpl::fromPLNToUSD),
	USD_PLN(ExchangeServiceImpl::fromUSDToPLN);

	public final BiFunction<ExchangeServiceImpl, BigDecimal, BigDecimal> exchange;

	public static ExchangeCurrencyStrategy from(final CurrencyEnum from, final CurrencyEnum to) {
		try {
			return ExchangeCurrencyStrategy.valueOf(from.name() + "_" + to.name());
		} catch (final Exception e) {
			final String msg = "Exchange from: %s to: %s is not supported".formatted(from, to);
			throw new IllegalArgumentException(msg);
		}
	}

}
