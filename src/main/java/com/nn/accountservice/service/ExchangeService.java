package com.nn.accountservice.service;

import com.nn.accountservice.entity.CurrencyEnum;

import java.math.BigDecimal;

public interface ExchangeService {

	BigDecimal exchange(CurrencyEnum from, CurrencyEnum to, BigDecimal amount);
}
