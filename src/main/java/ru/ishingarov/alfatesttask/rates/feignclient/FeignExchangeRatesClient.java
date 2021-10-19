package ru.ishingarov.alfatesttask.rates.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ishingarov.alfatesttask.rates.model.ExchangeRatesEntity;

/**
 * Client for OpenExchangeRates API
 */
@FeignClient(name = "OpenExchangeRatesClient", url = "${api.openexchangerates.url}")
public interface FeignExchangeRatesClient {
    /**
     * Gets the latest rates USD
     * @param appId app_id for authorisation
     * @return ExchangeRateEntity which holds provided response
     */
    @GetMapping("/latest.json")
    ExchangeRatesEntity getLatestUSDRates(@RequestParam("app_id") String appId);

    /**
     * Gets rates by provided date for USD
     * @param date historical date
     * @param appId app_id for authorisation
     * @return ExchangeRateEntity which holds provided response
     */
    @GetMapping("/historical/{date}.json")
    ExchangeRatesEntity getUSDRatesByDate(@PathVariable String date, @RequestParam("app_id") String appId);

    /**
     * Gets the latest rates for base currency
     * @param appId app_id for authorisation
     * @param base base currency
     * @return ExchangeRateEntity which holds provided response
     */
    @GetMapping("/latest.json")
    ExchangeRatesEntity getLatestCurrencyRates(
            @RequestParam("app_id") String appId,
            @RequestParam("base") String base
    );

    /**
     * Gets rates by provided date for base currency
     * @param date historical date
     * @param appId app_id for authorisation
     * @param base base currency
     * @return ExchangeRateEntity which holds provided response
     */
    @GetMapping("/historical/{date}.json")
    ExchangeRatesEntity getCurrencyRatesByDate(
            @PathVariable String date,
            @RequestParam("app_id") String appId,
            @RequestParam("base") String base
    );
}
