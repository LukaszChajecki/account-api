package com.nn.accountservice.service;

import com.nn.accountservice.controller.request.CreateAccountRequest;
import com.nn.accountservice.controller.response.AccountResponse;

public interface AccountService {

	String create(final CreateAccountRequest request);

	AccountResponse findById(final String id);
}
