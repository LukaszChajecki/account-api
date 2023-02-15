package com.nn.accountservice.controller;

import com.nn.accountservice.controller.request.CreateAccountRequest;
import com.nn.accountservice.controller.response.AccountResponse;
import com.nn.accountservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@PostMapping
	public String create(@Valid @RequestBody final CreateAccountRequest request) {
		log.info("Processing creating account: {}", request);
		return this.accountService.create(request);
	}

	@GetMapping("{id}")
	public AccountResponse findById(@PathVariable final String id) {
		log.info("Processing searching account by id: {}", id);
		return this.accountService.findById(id);
	}

}
