package com.nn.accountservice.repository.impl;

import com.nn.accountservice.exception.NbpApiException;
import com.nn.accountservice.repository.NbpRateRepository;
import com.nn.accountservice.repository.impl.response.NbpResponse;
import com.nn.accountservice.repository.impl.response.NbpResponseRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
class NbpRateRepositoryImpl implements NbpRateRepository {

	private final static String NBP_API_URL = "http://api.nbp.pl";
	private final RestTemplate restTemplate;

	@Override
	public BigDecimal findRateByCurrencyCode(final String code) {
		final var url = NBP_API_URL + "/api/exchangerates/rates/A/" + code;
		final var nbpResponse = this.restTemplate.getForObject(url, NbpResponse.class);
		return Optional.ofNullable(nbpResponse)
				.map(NbpResponse::getRates)
				.map(nbpResponseRates -> nbpResponseRates.get(0))
				.map(NbpResponseRate::getMid)
				.orElseThrow(() -> new NbpApiException("Error while searching rate for currency: " + code));
	}

}
