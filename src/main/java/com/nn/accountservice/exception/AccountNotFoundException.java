package com.nn.accountservice.exception;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(final String message) {
		super(message);
	}

	public static AccountNotFoundException forNotFoundById(final String id) {
		final var msg = "Account not found by id: %s".formatted(id);
		return new AccountNotFoundException(msg);
	}

}
