package com.nn.accountservice.service.impl;

import com.nn.accountservice.controller.request.CreateAccountRequest;
import com.nn.accountservice.controller.response.AccountResponse;
import com.nn.accountservice.entity.CurrencyEnum;
import com.nn.accountservice.exception.ExchangeException;
import com.nn.accountservice.mapper.AccountMapper;
import com.nn.accountservice.repository.AccountRepository;
import com.nn.accountservice.service.AccountService;
import com.nn.accountservice.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ExchangeService exchangeService;

    @Override
    public String create(final CreateAccountRequest request) {
        request.validate();
        final var account = new AccountMapper().toEntity(request);
        final var savedAccount = this.accountRepository.save(account);
        log.info("Account saved with id: {}", account.getId());
        return savedAccount.getId();
    }


    @Override
    public AccountResponse findById(final String id) {
        final var account = this.accountRepository.findById(id);
        return AccountResponse.builder()
                .name(account.getName())
                .surname(account.getSurname())
                .amountPLN(account.getBalancePLN())
                .amountUSD(account.getBalanceUSD())
                .build();
    }

    @Override
    public void exchange(final String accountId, final CurrencyEnum from, final CurrencyEnum to, final BigDecimal amount) {
        var account = this.accountRepository.findById(accountId);
        boolean canBeExchanged = account.canBeExchanged(amount, from);
        if (!canBeExchanged) {
            final String msg = "Cannot exchange from: %s to %s".formatted(from, to);
            throw new ExchangeException(msg);
        }
        BigDecimal exchangedAmount = exchangeService.exchange(from, to, amount);
        account.exchange(from, to, exchangedAmount, amount);
    }

}
