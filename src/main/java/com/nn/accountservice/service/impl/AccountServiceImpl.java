package com.nn.accountservice.service.impl;

import com.nn.accountservice.controller.request.CreateAccountRequest;
import com.nn.accountservice.controller.response.AccountResponse;
import com.nn.accountservice.entity.CurrencyEnum;
import com.nn.accountservice.mapper.AccountMapper;
import com.nn.accountservice.repository.AccountRepository;
import com.nn.accountservice.service.AccountService;
import com.nn.accountservice.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final ExchangeService exchangeService;

	@Override
	public String create(final CreateAccountRequest request) {
		final var account = new AccountMapper().toEntity(request);
		final var savedAccount = this.accountRepository.save(account);
		log.info("Account saved with id: {}", account.id());
		return savedAccount.id();
	}

	@Override
	public AccountResponse findById(final String id) {
		final var account = this.accountRepository.findById(id);
		final var usdAmount = this.exchangeService.exchange(CurrencyEnum.PLN, CurrencyEnum.USD,
				account.balance());
		return AccountResponse.builder()
				.name(account.name())
				.surname(account.surname())
				.amountPLN(account.balance())
				.amountUSD(usdAmount)
				.build();
	}

}
