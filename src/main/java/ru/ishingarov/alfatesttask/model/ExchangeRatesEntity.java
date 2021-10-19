package ru.ishingarov.alfatesttask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Model for working with exchange rate response
 * JSON example from API OpenExchangeRates
 * {
 *   "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
 *   "license": "https://openexchangerates.org/license",
 *   "timestamp": 1608595199,
 *   "base": "USD",
 *   "rates": {
 *     "AED": 3.6731,
 *     "AFN": 77.149997,
 *     "ALL": 101.187085,
 *     ...
 *   }
 * }
 * */

@Data
@NoArgsConstructor
public class ExchangeRatesEntity {
    @JsonProperty(value = "timestamp", required = true)
    private Long timestamp;

    @JsonProperty(value = "base", required = true)
    private String baseCurrency;

    @JsonProperty(value = "rates", required = true)
    private Map<String, Double> rates;
}
