package com.nn.accountservice.repository.impl;

import com.nn.accountservice.entity.Account;
import com.nn.accountservice.exception.AccountNotFoundException;
import com.nn.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class AccountInMemoryRepository implements AccountRepository {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public Account save(final Account account) {
        this.accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account findById(final String id) {
        return Optional.ofNullable(this.accounts.get(id))
                .orElseThrow(() -> AccountNotFoundException.forNotFoundById(id));
    }

}
