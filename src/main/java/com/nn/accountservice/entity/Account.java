package com.nn.accountservice.entity;

import java.math.BigDecimal;

public record Account(String id, String name, String surname, BigDecimal balance) {

}
