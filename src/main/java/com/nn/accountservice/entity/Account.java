package com.nn.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Account {

    private String id;
    private String name;
    private String surname;
    private BigDecimal balancePLN;
    private BigDecimal balanceUSD;

    public boolean canBeExchanged(BigDecimal amountToExchange, CurrencyEnum currency) {
        BigDecimal balanceByCurrency = this.getBalanceByCurrency(currency);
        return amountToExchange.compareTo(BigDecimal.ZERO) > 0 && amountToExchange.compareTo(balanceByCurrency) <= 0;
    }

    private BigDecimal getBalanceByCurrency(CurrencyEnum currency) {
        switch (currency) {
            case PLN -> {
                return this.balancePLN;
            }
            case USD -> {
                return this.balanceUSD;
            }
            default ->
                    throw new IllegalArgumentException("Account balance for currency: " + currency + " not supported");
        }
    }

    public void exchange(CurrencyEnum from, CurrencyEnum to, BigDecimal exchangedAmount, BigDecimal amount) {
        if (CurrencyEnum.PLN.equals(from) && CurrencyEnum.USD.equals(to)) {
            this.balancePLN = this.balancePLN.subtract(amount);
            this.balanceUSD = this.balanceUSD.add(exchangedAmount);
        } else if (CurrencyEnum.USD.equals(from) && CurrencyEnum.PLN.equals(to)) {
            this.balancePLN = this.balancePLN.add(exchangedAmount);
            this.balanceUSD = this.balanceUSD.subtract(amount);
        }
    }


}
