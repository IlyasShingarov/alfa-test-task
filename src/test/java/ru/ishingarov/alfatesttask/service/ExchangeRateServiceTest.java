package ru.ishingarov.alfatesttask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ishingarov.alfatesttask.feignclient.FeignExchangeRatesClient;
import ru.ishingarov.alfatesttask.model.ExchangeRatesEntity;

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
    public void ExchangeRates() {
        //prepare
        ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity();
        Map<String, Double> setRates = new HashMap<>();
        setRates.put("RUB", 1d);
        exchangeRatesEntity.setRates(setRates);
        Mockito.when(feignExchangeRatesClient.getRatesByDate(anyString(), anyString()))
                .thenReturn(exchangeRatesEntity);

        Mockito.when(feignExchangeRatesClient.getLatestRates(anyString()))
                .thenReturn(exchangeRatesEntity);
        //when
        boolean result = exchangeRateService.getAndCompareRates();
        //then
        Assertions.assertFalse(result);
    }
}
