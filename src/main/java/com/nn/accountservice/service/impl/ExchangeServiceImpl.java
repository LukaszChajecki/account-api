package com.nn.accountservice.service.impl;

import com.nn.accountservice.entity.CurrencyEnum;
import com.nn.accountservice.repository.NbpRateRepository;
import com.nn.accountservice.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
class ExchangeServiceImpl implements ExchangeService {

    private final NbpRateRepository nbpRateRepository;

    @Override
    public BigDecimal exchange(final CurrencyEnum from, final CurrencyEnum to, BigDecimal amount) {
        return ExchangeCurrencyStrategy.from(from, to).exchange.apply(this, amount);
    }

    BigDecimal fromPLNToUSD(final BigDecimal amount) {
        return amount.multiply(this.getPlnToUsdRate()).setScale(2, RoundingMode.DOWN);
    }

    BigDecimal fromUSDToPLN(final BigDecimal amount) {
        return amount.multiply(this.getUsdToPlnRate()).setScale(2, RoundingMode.DOWN);
    }

    private BigDecimal getPlnToUsdRate() {
        final var usdRate = this.nbpRateRepository.findRateByCurrencyCode(CurrencyEnum.USD.name());
        return BigDecimal.ONE.divide(usdRate, 5, RoundingMode.DOWN);
    }

    private BigDecimal getUsdToPlnRate() {
        final var usdRate = this.nbpRateRepository.findRateByCurrencyCode(CurrencyEnum.USD.name());
        return usdRate.divide(BigDecimal.ONE, 5, RoundingMode.DOWN);
    }

}
