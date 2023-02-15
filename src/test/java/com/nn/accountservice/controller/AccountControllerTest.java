package com.nn.accountservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	private final static String ACCOUNT_PATH = "/api/accounts";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateAccount_ok() throws Exception {
		final String requestBody = """
					{
						"name": "Jan",
						"surname": "Kowalski",
						"balance": 100.0
					}
				""";
		this.mockMvc.perform(post(ACCOUNT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testCreateAccount_shouldReturnBadRequest() throws Exception {
		final String requestBody = """
					{
						"surname": "Kowalski",
						"balance": 100.0
					}
				""";
		this.mockMvc.perform(post(ACCOUNT_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

}
