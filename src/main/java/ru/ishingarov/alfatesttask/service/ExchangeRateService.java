package ru.ishingarov.alfatesttask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ishingarov.alfatesttask.feignclient.FeignExchangeRatesClient;
import ru.ishingarov.alfatesttask.model.ExchangeRatesEntity;

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
     * Compares current rates with previous
     * returns true if current rate is higher than previous
     * @return boolean value of rate comparison
     */
    public boolean getAndCompareRates() {
        String prevDay = formatter.format(LocalDate.now().minus(1, ChronoUnit.DAYS));
        Double currRates = exchangeRatesClient.getLatestRates(this.appId).getRates().get(baseCurrency);
        ExchangeRatesEntity exchangeRatesEntity = exchangeRatesClient.getRatesByDate(prevDay, appId);
        Double prevRates = exchangeRatesEntity.getRates().get(baseCurrency);
        log.info("Got rates -- Previous: {} Current: {}", prevRates, currRates);
        return Double.compare(currRates, prevRates) == 1;
    }
}
