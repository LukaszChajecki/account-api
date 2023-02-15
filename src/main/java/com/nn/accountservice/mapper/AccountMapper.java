package com.nn.accountservice.mapper;

import com.nn.accountservice.controller.request.CreateAccountRequest;
import com.nn.accountservice.entity.Account;

import java.util.UUID;

public class AccountMapper {

	public Account toEntity(final CreateAccountRequest request) {
		final var id = UUID.randomUUID().toString();
		return new Account(id, request.getName(), request.getSurname(), request.getBalance());
	}

}
