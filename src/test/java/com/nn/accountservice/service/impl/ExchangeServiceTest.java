package com.nn.accountservice.service.impl;

import com.nn.accountservice.entity.CurrencyEnum;
import com.nn.accountservice.repository.NbpRateRepository;
import com.nn.accountservice.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ExchangeServiceTest {

	private final NbpRateRepository nbpRateRepository = Mockito.mock(NbpRateRepository.class);
	private final ExchangeService exchangeService = new ExchangeServiceImpl(this.nbpRateRepository);

	@Test
	public void testExchangeFromPLNtoUSD() {
		//		given
		when(this.nbpRateRepository.findRateByCurrencyCode(CurrencyEnum.USD.name())).thenReturn(
				new BigDecimal("4.00"));

		//		when
		final var exchange = this.exchangeService.exchange(CurrencyEnum.PLN, CurrencyEnum.USD,
				new BigDecimal("100.0"));

		//		then
		assertEquals(exchange, new BigDecimal("25.00"));
	}

	@Test
	public void testExchangeFromUSDToPLN() {
		//		given
		when(this.nbpRateRepository.findRateByCurrencyCode(CurrencyEnum.USD.name())).thenReturn(
				new BigDecimal("4.00"));

		//		when
		final var exchange = this.exchangeService.exchange(CurrencyEnum.USD, CurrencyEnum.PLN,
				new BigDecimal("100.0"));

		//		then
		assertEquals(exchange, new BigDecimal("400.00"));
	}

}
