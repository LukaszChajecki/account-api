package com.nn.accountservice.repository.impl.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NbpResponse {

	private String table;
	private String currency;
	private String code;
	private List<NbpResponseRate> rates;

}
