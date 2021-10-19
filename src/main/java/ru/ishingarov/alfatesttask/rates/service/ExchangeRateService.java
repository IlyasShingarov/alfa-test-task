package ru.ishingarov.alfatesttask.rates.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ishingarov.alfatesttask.rates.feignclient.FeignExchangeRatesClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService {
    private final FeignExchangeRatesClient exchangeRatesClient;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${api.openexchangerates.appid}")
    private String appId;

    @Value("${api.openexchangerates.basecurrency}")
    private String baseCurrency;

    /**
     * Compares current rates with previous RUB to USD
     * returns true if current rate is higher than previous
     * @return boolean value of rate comparison
     */
    public boolean compareRates() {
        String prevDay = formatter.format(LocalDate.now().minus(1, ChronoUnit.DAYS));
        Double currRates = exchangeRatesClient
                .getLatestUSDRates(this.appId)
                .getRates()
                .get(baseCurrency);

        Double prevRates = exchangeRatesClient
                .getUSDRatesByDate(prevDay, appId)
                .getRates()
                .get(baseCurrency);

        log.info("Got rates -- Previous: {} Current: {}", prevRates, currRates);
        return Double.compare(currRates, prevRates) == 1;
    }

    /**
     * Compares current rates with previous CurrencyCode to RUB
     * returns true if current rate is higher than previous
     * @param currencyCode Currency which is used as base currency
     * @return boolean value of rate comparison
     */
    public boolean compareRatesByCode(String currencyCode) {
        String prevDay = formatter.format(LocalDate.now().minus(1, ChronoUnit.DAYS));
        Double currRates = exchangeRatesClient
                .getLatestCurrencyRates(this.appId, currencyCode)
                .getRates()
                .get(baseCurrency);

        Double prevRates = exchangeRatesClient
                .getCurrencyRatesByDate(prevDay, appId, currencyCode)
                .getRates()
                .get(baseCurrency);

        return Double.compare(currRates, prevRates) == 1;
    }
}
