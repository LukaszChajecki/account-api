package com.nn.accountservice.repository;

import java.math.BigDecimal;

public interface NbpRateRepository {

	BigDecimal findRateByCurrencyCode(final String code);

}
