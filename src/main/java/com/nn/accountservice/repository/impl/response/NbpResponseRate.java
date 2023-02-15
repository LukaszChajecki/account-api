package com.nn.accountservice.repository.impl.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NbpResponseRate {
	private String no;
	private String effectiveDate;
	private BigDecimal mid;
}
