package com.nn.accountservice.repository;

import com.nn.accountservice.entity.Account;

public interface AccountRepository {

    Account save(final Account account);

    Account findById(final String id);
}
