package ru.ishingarov.alfatesttask.rates.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ishingarov.alfatesttask.rates.feignclient.FeignExchangeRatesClient;
import ru.ishingarov.alfatesttask.rates.model.ExchangeRatesEntity;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeRateServiceTest {
    @Autowired
    private ExchangeRateService exchangeRateService;

    @MockBean
    private FeignExchangeRatesClient feignExchangeRatesClient;

    @Test
    public void USDExchangeRates() {
        //prepare
        ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity();
        Map<String, Double> setRates = new HashMap<>();
        setRates.put("RUB", 1d);
        exchangeRatesEntity.setRates(setRates);

        Mockito.when(feignExchangeRatesClient.getUSDRatesByDate(anyString(), anyString()))
                .thenReturn(exchangeRatesEntity);

        Mockito.when(feignExchangeRatesClient.getLatestUSDRates(anyString()))
                .thenReturn(exchangeRatesEntity);
        //when
        boolean result = exchangeRateService.compareRates();
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void CurrencyExchangeRates() {
        //prepare
        ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity();
        Map<String, Double> setRates = new HashMap<>();
        setRates.put("RUB", 1d);
        exchangeRatesEntity.setRates(setRates);

        Mockito.when(feignExchangeRatesClient.getCurrencyRatesByDate(anyString(), anyString(), anyString()))
                .thenReturn(exchangeRatesEntity);

        Mockito.when(feignExchangeRatesClient.getLatestCurrencyRates(anyString(), anyString()))
                .thenReturn(exchangeRatesEntity);
        //when
        boolean result = exchangeRateService.compareRatesByCode("EUR");
        //then
        Assertions.assertFalse(result);
    }
}
