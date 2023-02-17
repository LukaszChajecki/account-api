package com.nn.accountservice.service;

import com.nn.accountservice.controller.request.CreateAccountRequest;
import com.nn.accountservice.controller.response.AccountResponse;
import com.nn.accountservice.entity.CurrencyEnum;

import java.math.BigDecimal;

public interface AccountService {

    String create(final CreateAccountRequest request);

    AccountResponse findById(final String id);

    void exchange(String accountId, CurrencyEnum from, CurrencyEnum to, final BigDecimal amount);
}
