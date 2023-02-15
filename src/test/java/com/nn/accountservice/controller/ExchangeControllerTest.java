package com.nn.accountservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTest {

	private final static String EXCHANGE_PATH = "/api/exchange";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testExchange_ok() throws Exception {
		this.mockMvc.perform(get(EXCHANGE_PATH).param("from", "PLN")
				.param("to", "USD")
				.param("amount", "100")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testExchange_shouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get(EXCHANGE_PATH).param("from", "PLN")
				.param("to", "USD")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

}
